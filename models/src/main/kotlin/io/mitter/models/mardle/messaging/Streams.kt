package io.mitter.models.mardle.messaging

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.annotations.IdentifiableEntity
import io.mitter.models.commons.AuditInfo
import io.mitter.models.commons.Auditable

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardStreamTypes {
    const val BroadcastObjectStream = "mitter.streams.BroadcastObjectStream"
}

object ReservedStreamIds {
    const val TypingIndicatorStream = ".typing-indicator-stream"

    val ReservedStreamIds = listOf(TypingIndicatorStream)
}

data class Stream(
    val streamId: String,
    var internalId: String? = null,
    val type: String,
    val supportedContentTypes: List<String> = emptyList(),

    override var auditInfo: AuditInfo? = null
) : IdentifiableEntity<Stream>, Auditable {
    override fun domainId() = streamId
    override fun internalId(): String? = internalId
    override fun type() = Stream::class.java

    companion object {
        @JsonCreator
        @JvmStatic
        fun constructionSupport(
            @JsonProperty("streamId") streamId: String,
            @JsonProperty("type") type: String,
            @JsonProperty("supportedContentTypes") supportedContentTypes: List<String>?,
            @JsonProperty("auditInfo") auditInfo: AuditInfo?
        ) = Stream(
            streamId = streamId,
            type = type,
            supportedContentTypes = supportedContentTypes ?: emptyList(),
            auditInfo = auditInfo
        )
    }
}

data class ChannelStream(
    val channelId: Identifiable<Channel>,
    val stream: ChannelStream
)
