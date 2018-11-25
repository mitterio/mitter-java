package io.mitter.models.mardle.messaging

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.IdGenerator
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.annotations.IdentifiableEntity
import io.mitter.data.domain.user.User
import io.mitter.models.commons.AuditInfo
import io.mitter.models.commons.Auditable

/**
 * Created by rohan on 5/31/17.
 */
object StandardTimelineEventTypeNames {
    const val CreationTime: String = "io.mitter.types.timeline.CreationTime"

    object Messages {
        const val SentTime: String = "mitter.mtet.SentTime"
        const val ReceivedTime: String = "mitter.mtet.ReceivedTime"
        const val DeliveredTime: String = "mitter.mtet.DeliveredTime"
        const val ReadTime: String = "mitter.mtet.ReadTime"
    }
}

enum class StandardTimelineEventTypes(
    val qualifiedTimelineEventType: String,
    val __restricted_prefix: String = "io.mitter"
) {
    CreationTime(qualifiedTimelineEventType = StandardTimelineEventTypeNames.CreationTime),

    SentTime(qualifiedTimelineEventType = StandardTimelineEventTypeNames.Messages.SentTime),
    ReceivedTime(qualifiedTimelineEventType = StandardTimelineEventTypeNames.Messages.ReceivedTime),
    DeliveredTime(qualifiedTimelineEventType = StandardTimelineEventTypeNames.Messages.DeliveredTime),
    ReadTime(qualifiedTimelineEventType = StandardTimelineEventTypeNames.Messages.ReadTime)
}

data class TimelineEvent(
    val eventId: String,

    var internalId: String? = null,

    val type: String,

    val eventTimeMs: Long,

    val subject: Identifiable<User>,

    override var auditInfo: AuditInfo? = null
) : IdentifiableEntity<TimelineEvent>, Auditable {
    override fun domainId() = eventId
    override fun internalId(): String? = internalId
    override fun type() = TimelineEvent::class.java

    companion object {
        @JvmStatic
        @JsonCreator
        fun constructionSupport(
            @JsonProperty("eventId") eventId: String?,
            @JsonProperty("type") type: String,
            @JsonProperty("eventTimeMs") eventTimeMs: Long,
            @JsonProperty("subject") subject: Identifiable<User>,
            @JsonProperty("auditInfo") auditInfo: AuditInfo?
        ) = TimelineEvent(
            eventId = eventId ?: IdGenerator.generateDomainId(TimelineEvent::class.java),
            type = type,
            eventTimeMs = eventTimeMs,
            subject = subject,
            auditInfo = auditInfo
        )
    }
}
