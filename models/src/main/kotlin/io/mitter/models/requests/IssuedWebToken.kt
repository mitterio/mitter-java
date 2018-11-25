package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A token that is issued by the system (signed and verifiable) for use in subsequent HTTP requets.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class IssuedWebToken(
    // TODO. Move Jackson annotations to a separate mix-in
    @JsonProperty("signedToken") val signedToken: String,
    @JsonProperty("supportedHeaders") val supportedHeaders: List<String>,
    @JsonProperty("supportedCookies") val supportedCookies: List<String>,
    @JsonProperty("tokenId") val tokenId: String
)
