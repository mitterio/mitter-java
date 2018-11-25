package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
data class PatchEventbusPropertyRequest(
    @JsonProperty("systemName")
    val systemName: String,
    @JsonProperty("instanceName")
    val instanceName: String,
    @JsonProperty("active")
    val active: Boolean
)