package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class PasswordResetRequest(
    @JsonProperty("resetUrl") val resetUrl: String
)

data class PasswordResetClientRequest(
        @JsonProperty("email") val email: String
)