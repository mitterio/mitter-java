package io.mitter.models.delman.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import io.mitter.data.domain.annotations.IdUtils
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.User
import io.mitter.models.mardle.messaging.*

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */

object StandardPipelinePayloadNames {
    const val NewChannelPayload : String = "new-channel-payload"
    const val NewMessagePayload : String = "new-message-payload"

    const val NewMessageTimelineEventPayload : String = "new-message-timeline-event-payload"
    const val NewChannelTimelineEventPayload : String = "new-channel-timeline-event-payload"

    const val ParticipationChangedEventPayload : String = "participation-changed-event-payload"
    const val ChannelStreamData : String = "stream-data"

    const val PipelineControlPayload : String = "pipeline-control-payload"
}

@JsonTypeName(StandardPipelinePayloadNames.NewChannelPayload)
data class NewChannelPayload(
    @JsonProperty("channel")
    val channel: Channel
) : MessagingPipelinePayload()

@JsonTypeName(StandardPipelinePayloadNames.NewMessagePayload)
data class NewMessagePayload(
    @JsonProperty("message")
    val message : Message,
    @JsonProperty("channelId")
    val channelId : Identifiable<Channel>
) : MessagingPipelinePayload()

@JsonTypeName(StandardPipelinePayloadNames.NewMessageTimelineEventPayload)
data class NewMessageTimelineEventPayload(
    @JsonProperty("timelineEvent")
    val timelineEvent : TimelineEvent,
    @JsonProperty("messageId")
    val messageId : Identifiable<Message>
) : MessagingPipelinePayload()

@JsonTypeName(StandardPipelinePayloadNames.NewChannelTimelineEventPayload)
data class NewChannelTimelineEventPayload(
    @JsonProperty("timelineEvent")
    val timelineEvent : TimelineEvent,
    @JsonProperty("channelId")
    val channelId : Identifiable<Channel>
) : MessagingPipelinePayload()

@JsonTypeName(StandardPipelinePayloadNames.ParticipationChangedEventPayload)
data class ParticipationChangedEventPayload(
    @JsonProperty("participantId")
    val participantId: Identifiable<User>,

    @JsonProperty("channelId")
    val channelId : Identifiable<Channel>,

    @JsonProperty("newStatus")
    val newStatus: ParticipationStatus,

    @JsonProperty("oldStatus")
    val oldStatus : ParticipationStatus?
) : MessagingPipelinePayload()

@JsonTypeName(StandardPipelinePayloadNames.ChannelStreamData)
data class ChannelStreamData(
    @JsonProperty("channelId")
    val channelId: Identifiable<Channel>,

    @JsonProperty("streamId")
    val streamId: Identifiable<Stream>,

    @JsonProperty("streamData")
    val streamData: ContextFreeMessage
) : MessagingPipelinePayload()

@JsonTypeName(StandardPipelinePayloadNames.PipelineControlPayload)
data class PipelineControlPayload(
    @JsonProperty("errorBody")
    val errorBody: Any? = null,
    val allOk: Boolean  = false
) : MessagingPipelinePayload()
