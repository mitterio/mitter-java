package io.mitter.models.commons

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */

interface Auditable {
    var auditInfo: AuditInfo?
}

data class AuditInfo(
    @JsonProperty("createdAt")
    val createdAt: Long? = null,
    @JsonProperty("updatedAt")
    val updatedAt: Long? = null
)