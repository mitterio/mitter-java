package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class TokenRevocationRequest(
        @JsonProperty("tokenIds") val tokenIds: List<String>,
        @JsonProperty("entityType") val entityType: String,
        @JsonProperty("entityId") val entityId: String?
)

data class IssueUserTokenResponse(
    @JsonProperty("userToken")
    val userToken: IssuedWebToken
)
