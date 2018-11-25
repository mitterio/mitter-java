package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class PatchApplicationPropertyRequest(
    @JsonProperty("makeDefault")
    val makeDefault: Boolean
)
