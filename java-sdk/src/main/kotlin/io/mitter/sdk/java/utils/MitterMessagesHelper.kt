package io.mitter.sdk.java.utils

import io.mitter.data.domain.IdGenerator
import io.mitter.data.domain.annotations.EntityIdentifierHolder
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.User
import io.mitter.models.mardle.messaging.Message
import io.mitter.models.mardle.messaging.StandardPayloadTypeNames
import io.mitter.models.mardle.messaging.StandardTimelineEventTypeNames
import io.mitter.models.mardle.messaging.TimelineEvent

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterMessagesHelper {
    companion object {
        @JvmStatic
        fun emptyMessage() = Message(
            messageId = IdGenerator.generateDomainId(Message::class.java),
            payloadType = StandardPayloadTypeNames.EmptyMessage,
            textPayload = "",
            senderId = EntityIdentifierHolder("", null),
            timelineEvents = emptyList()
        )

        @JvmStatic
        fun textMessage(senderId: Identifiable<User>, content: String) = Message (
            messageId = IdGenerator.generateDomainId(Message::class.java),
            payloadType = StandardPayloadTypeNames.TextMessage,
            textPayload = content,
            senderId = senderId,
            timelineEvents = standardTimelineEvents(senderId)
        )

        @JvmStatic
        fun fileMessage(senderId: Identifiable<User>, content: String) = Message (
            messageId = IdGenerator.generateDomainId(Message::class.java),
            payloadType = StandardPayloadTypeNames.FileMessage,
            textPayload = content,
            senderId = senderId,
            timelineEvents = standardTimelineEvents(senderId)
        )

        @JvmStatic
        fun messageQuery(
            limit: Int? = null, before: Identifiable<Message>? = null, after: Identifiable<Message>? = null,
            payloadFilter: List<String>? = emptyList()
        ) =
            mutableMapOf<String, Any>().apply {
                limit ?.let { this.put("limit",  it) }
                before?.let { this.put("before", it.domainId()) }
                after ?.let { this.put("after",  it.domainId()) }
                payloadFilter?.let { this.put("payloadFilter", it)}
            }

        private fun standardTimelineEvents(subject: Identifiable<User>) = listOf(
            TimelineEvent(
                eventId = IdGenerator.generateDomainId(TimelineEvent::class.java),
                type = StandardTimelineEventTypeNames.Messages.SentTime,
                eventTimeMs = System.currentTimeMillis(),
                subject = subject
            )
        )
    }
}
