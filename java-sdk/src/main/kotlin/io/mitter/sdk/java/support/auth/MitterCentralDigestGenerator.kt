package io.mitter.sdk.java.support.auth

import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import java.util.stream.Collectors
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
class MitterCentralDigestGenerator {
    companion object {
        private val HmacSha1AlgorithmKey = "HmacSHA1"
        private val DefaultNonceLength = 32
        private val HttpIsoDateFormatObject = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
        private val AuthorizationPrefix = "Auth"
    }

    fun generateCentralDigest(
        mitterCentralDigestParts: MitterCentralDigestParts,
        signingUser: String,
        signingKey: ByteArray)
        : MitterCentralDigestGenerationArtifacts {
        var finalizedDigestParts = mitterCentralDigestParts

        if (mitterCentralDigestParts.payloadMd5 == null) {
            finalizedDigestParts = finalizedDigestParts.copy(
                payloadMd5 = base64Md5(mitterCentralDigestParts.payload)
            )
        }

        if (mitterCentralDigestParts.nonce == null) {
            finalizedDigestParts = finalizedDigestParts.copy(
                nonce = randomHex(DefaultNonceLength)
            )
        }

        if (mitterCentralDigestParts.date == null) {
            finalizedDigestParts = finalizedDigestParts.copy(
                date = instantToIsoDate(Instant.now())
            )
        }

        val authorizationHeader = generateAuthorizationHeader(finalizedDigestParts, signingUser, signingKey)

        return MitterCentralDigestGenerationArtifacts(
            nonce = finalizedDigestParts.nonce!!,
            date = finalizedDigestParts.date!!,
            authorizationHeader = authorizationHeader,
            contentMd5 = finalizedDigestParts.payloadMd5!!
        )
    }

    private fun generateAuthorizationHeader(
        digestParts: MitterCentralDigestParts,
        signingUser: String,
        signingKey: ByteArray) : String {

        val digestPayloadParts = listOf(
            digestParts.method.toUpperCase(),
            digestParts.contentType,
            digestParts.payloadMd5,
            digestParts.date,
            digestParts.path,
            digestParts.nonce
        ).joinToString("\n")

        return "$AuthorizationPrefix $signingUser:${getHmacSha1(signingKey, digestPayloadParts)}"
    }

    private fun base64Md5(payload: String) =
        Base64.encodeBase64String(DigestUtils.md5(payload))

    private fun getHmacSha1(signingKey: ByteArray, digestPayload: String) : String {
        val mac = Mac.getInstance(HmacSha1AlgorithmKey)
        mac.init(SecretKeySpec(signingKey, HmacSha1AlgorithmKey))

        val rawHmac = mac.doFinal(digestPayload.toByteArray())

        return Base64.encodeBase64String(rawHmac)
    }

    private fun randomHex(length: Int) : String {
        val resBuf = ByteArray(length)
        Random().nextBytes(resBuf)

        return String(Hex.encodeHex(resBuf))
    }

    private fun instantToIsoDate(instant: Instant) = HttpIsoDateFormatObject.format(Date.from(instant))
}
