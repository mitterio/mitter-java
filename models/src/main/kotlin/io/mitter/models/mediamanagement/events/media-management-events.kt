package io.mitter.models.mediamanagement.events

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.models.central.events.ApplicationEvent
import io.mitter.models.nerif.EventLevel
import io.mitter.models.nerif.NerifEvent

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object MediaManagementEventNames {
    const val HostedBinaryAvailableEvent = "mitter.mpe.media.HostedBinaryAvailable"
}

@NerifEvent(MediaManagementEventNames.HostedBinaryAvailableEvent, eventLevel = EventLevel.Atomic)
data class HostedBinaryAvailableEvent(
    @JsonProperty("resourceUri")
    val resourceUri: String,
    @JsonProperty("mediaKey")
    val mediaKey: String,
    @JsonProperty("representation")
    val representation: String,
    @JsonProperty("mimeType")
    val mimeType: String,
    @JsonProperty("contentLength")
    val contentLength: Long? = null,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)
