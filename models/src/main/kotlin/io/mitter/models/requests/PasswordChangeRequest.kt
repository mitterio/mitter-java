package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class PasswordChangeRequest(
    @JsonProperty("newPasswordHash") val newPasswordHash: String,
    @JsonProperty("resetPasswordActionToken") val resetPasswordActionToken: String
)

data class PasswordChangeClientRequest(
    @JsonProperty("subscriberId") val subscriberId: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("resetPasswordActionToken") val resetPasswordActionToken: String
)