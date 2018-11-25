package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class PatchUserMetadataRequest(
    @JsonProperty("mergeMetadata") val mergeMetadata: JsonNode?,
    @JsonProperty("deleteKeys") val deleteKeys: List<String>?
)
