package io.mitter.models.mardle.wire

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.mardle.messaging.*

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
data class MessageTimelineEvent(
    @JsonProperty("messageId")
    val messageId: Identifiable<Message>,
    @JsonProperty("timelineEvent")
    val timelineEvent: TimelineEvent,

    @JsonProperty("channelId")
    val channelId: Identifiable<Channel>? = null
)

data class MessageTimelineEventSet(
    val messageId: Identifiable<Message>,
    val timelineEvents: List<TimelineEvent>,

    val channelId: Identifiable<Channel>? = null
)

data class ChannelTimelineEvent(
    @JsonProperty("channelId")
    val channelId: Identifiable<Channel>,
    @JsonProperty("timelineEvent")
    val timelineEvent: TimelineEvent
)

data class ChannelTimelineEventSet(
    val channelId: Identifiable<Channel>,
    val timelineEvents: List<TimelineEvent>
)

/* PATCH type requests */
data class PatchChannelParticipation(
    @JsonProperty("newStatus")
    val newStatus: ParticipationStatus
)

data class PatchChannelParticipationResult(
    @JsonProperty("oldChannelParticipation")
    val oldChannelParticipation: ChannelParticipation,

    @JsonProperty("newChannelParticipation")
    val newChannelParticipation: ChannelParticipation
)
