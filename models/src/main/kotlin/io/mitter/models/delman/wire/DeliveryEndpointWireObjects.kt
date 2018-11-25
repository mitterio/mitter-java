package io.mitter.models.delman.wire

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @author shouvik
 */

data class PatchDeliveryEndpoint(
    @JsonProperty("disable")
    val disable: Boolean
)
