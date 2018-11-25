package io.mitter.sdk.java.support.auth

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
data class MitterCentralDigestParts(
    val method: String,
    val date: String? = null,
    val contentType: String?,
    val nonce: String? = null,
    val path: String,
    val payload: String,
    val payloadMd5: String? = null
)

data class MitterCentralDigestGenerationArtifacts(
    val nonce: String,
    val contentMd5: String,
    val date: String,
    val authorizationHeader: String
)
