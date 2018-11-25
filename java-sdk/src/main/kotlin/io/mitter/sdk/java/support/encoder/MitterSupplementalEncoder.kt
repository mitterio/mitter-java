package io.mitter.sdk.java.support.encoder

import feign.*
import feign.codec.Encoder
import io.mitter.sdk.java.clients.BinaryPayload
import mu.KLoggable
import okhttp3.MediaType
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterSupplementalEncoder(
    private val internalEncoder: Encoder
) : Encoder {
    companion object : Any(), KLoggable {
        object Multipart {
            const val Type = "multipart"
            const val SubType = "form-data"
            const val Value = "$Type/$SubType"
        }

        object Headers {
            const val ContentType = "Content-Type"
            const val ContentDisposition = "Content-Disposition"
            const val ContentTransferEncoding = "Content-Transfer-Encoding"
        }

        object HeaderValues {
            const val TextPlain = "text/plain"
        }

        object TextStreams {
            const val Crlf = "\r\n"
        }

        override val logger = logger()
    }

    constructor() : this(Encoder.Default())

    override fun encode(obj: Any, bodyType: Type, requestTemplate: RequestTemplate) {
        if (Util.MAP_STRING_WILDCARD == bodyType &&
            (requestTemplate.headers()[Headers.ContentType]?.first()
                ?.let { MediaType.parse(it) }
                ?.let { it.type() == Multipart.Type && it.subtype() == Multipart.SubType } == true)) {

            val output = ByteArrayOutputStream()
            val boundary = System.currentTimeMillis().toString(16)
            val binaryPayloads : MutableList<BinaryPayload> = mutableListOf()
            val contentTypeHeaderValue = "${Multipart.Value}; charset=utf-8; boundary=$boundary"

            requestTemplate.header(Headers.ContentType, contentTypeHeaderValue)

            @Suppress("UNCHECKED_CAST")
            (obj as Map<String, Any>).forEach { t, u ->
                val binaryPayloadList = if (u is List<*> && u.filter { it is BinaryPayload }.size == u.size) {
                    u as List<BinaryPayload>
                } else if (u is BinaryPayload) {
                    listOf(u)
                } else {
                    val contentType = t.split(";", limit = 2).getOrNull(1)?.trim() ?: HeaderValues.TextPlain
                    val fake = RequestTemplate()
                    internalEncoder.encode(u, u::class.java, fake)
                    val internalEncodedBody = String(fake.body(), Charsets.UTF_8).replace("\n", "")

                    listOf(BinaryPayload(
                        name = t.split(";").first(),
                        mimeType = contentType,
                        data = ByteArrayInputStream(internalEncodedBody.toByteArray(Charsets.UTF_8))
                    ))
                }

                binaryPayloads.addAll(binaryPayloadList)
            }

            binaryPayloads.forEach {
                output.apply {
                    writeBoundary(boundary)
                    writeHeaderValue(Headers.ContentDisposition, "form-data; name=\"${it.name}\"; filename=\"${it.name}\"")
                    writeHeaderValue(Headers.ContentType, it.mimeType)
                    writeHeaderValue(Headers.ContentTransferEncoding, "binary")

                    crlf()

                    writeHeaderValue(it.data.use {
                        it.readBytes()
                    })
                }
            }

            output.apply {
                writeBoundary("$boundary--")
            }

            requestTemplate.body(output.toByteArray(), Charsets.UTF_8)
        } else {
            internalEncoder.encode(obj, bodyType, requestTemplate)
        }
    }

    private fun ByteArrayOutputStream.writeHeaderValue(key: String, value: String) {
        writeHeaderValue("$key: $value")
    }

    private fun ByteArrayOutputStream.write(string: String) {
        this.write(string.toByteArray())
    }

    private fun ByteArrayOutputStream.writeHeaderValue(string: String) {
        writeHeaderValue(string.toByteArray())
    }

    private fun ByteArrayOutputStream.writeHeaderValue(byteArray: ByteArray) {
        write(byteArray)
        crlf()
    }

    private fun ByteArrayOutputStream.writeBoundary(boundary: String) {
        writeHeaderValue("--$boundary")
    }

    private fun ByteArrayOutputStream.crlf() {
        this.write(TextStreams.Crlf)
    }
}
