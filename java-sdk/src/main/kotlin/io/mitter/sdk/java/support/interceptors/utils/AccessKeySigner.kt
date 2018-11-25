package io.mitter.sdk.java.support.interceptors.utils

import feign.RequestInterceptor
import feign.RequestTemplate
import io.mitter.sdk.java.objects.credentials.AccessKeyCredential
import io.mitter.sdk.java.support.auth.MitterCentralDigestGenerator
import io.mitter.sdk.java.support.auth.MitterCentralDigestParts
import io.mitter.sdk.java.support.interceptors.SigningInterceptor
import mu.KLoggable
import org.apache.commons.codec.binary.Base64
import java.net.URI

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
open class AccessKeySigner(
    private val keyPairCredentials: AccessKeyCredential,
    private val accessKeyHeader: String
) : RequestInterceptor, SigningInterceptor {
    private val mitterCentralDigestGenerator = MitterCentralDigestGenerator()

    companion object : Any(), KLoggable {
        override val logger = logger()

        private object ExpectedHeaders {
            val ContentType = "Content-Type"
            val Date = "Date"
        }

        private object SettableHeader {
            val Date = "Date"
            val Nonce = "Nonce"
            val ContentMd5 = "Content-Md5"
            val Authorization = "Authorization"
        }
    }

    override fun apply(template: RequestTemplate) {
        val mitterCentralDigestParts = MitterCentralDigestParts(
            method = template.method(),
            date = template.headers()[ExpectedHeaders.Date]?.first(),
            path = URI(template.url()).path,
            payload = String(template.body() ?: "".toByteArray()),
            contentType = template.headers()[ExpectedHeaders.ContentType]?.first()
        )

        val generatedDigest = mitterCentralDigestGenerator.generateCentralDigest(
            mitterCentralDigestParts = mitterCentralDigestParts,
            signingUser = keyPairCredentials.accessKeyPair.accessKey,
            signingKey = Base64.decodeBase64(keyPairCredentials.accessKeyPair.accessSecretBase64)
        )

        template.header(SettableHeader.Date, generatedDigest.date)
        template.header(SettableHeader.ContentMd5, generatedDigest.contentMd5)
        template.header(SettableHeader.Nonce, generatedDigest.nonce)
        template.header(SettableHeader.Authorization, generatedDigest.authorizationHeader)
        template.header(accessKeyHeader, keyPairCredentials.accessKeyPair.accessKey)
    }
}