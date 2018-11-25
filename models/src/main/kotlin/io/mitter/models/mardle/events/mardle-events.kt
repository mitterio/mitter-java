package io.mitter.models.mardle.events

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.models.central.events.ApplicationEvent
import io.mitter.models.mardle.messaging.*
import io.mitter.models.nerif.EventLevel
import io.mitter.models.nerif.NerifEvent

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object MardleEventNames {
    const val NewChannelMessageEvent            = "mitter.mpe.channels.NewChannelMessage"
    const val ChannelMessageReadyEvent          = "mitter.mpe.channels.ChannelMessageReady"

    const val ChannelMessageDeletedEvent        = "mitter.mpe.messages.ChannelMessageDeleted"
    const val ChannelMessagesTruncatedEvent     = "mitter.mpe.messages.ChannelMessagesTruncated"

    const val NewChannelEvent                   = "mitter.mpe.channels.NewChannel"
    const val ChannelDeletedEvent               = "mitter.mpe.channels.ChannelDeleted"

    const val ChannelParticipantAddedEvent      = "mitter.mpe.channels.ChannelParticipantAdded"
    const val ChannelParticipationModifiedEvent = "mitter.mpe.channels.ChannelParticipationModified"
    const val ChannelParticipationDeletedEvent  = "mitter.mpe.channels.ChannelParticipationDeleted"

    const val NewTimelineEventForMessageEvent   = "mitter.mpe.messages.NewTimelineEventForMessage"
    const val NewTimelineEventForChannelEvent   = "mitter.mpe.channels.NewTimelineEventForChannel"
    const val NewTimelineEvent                  = "mitter.mpe.tle.NewTimelineEvent"

    const val NewChannelStreamEvent             = "mitter.mpe.channels.NewChannelStream"
    const val ChannelStreamMessageReady         = "mitter.mpe.channels.ChannelStreamMessageReady"
}

abstract class ChannelEvent(
    @JsonProperty("channelId")
    open val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(MardleEventNames.NewChannelMessageEvent, eventLevel = EventLevel.Action)
data class NewChannelMessageEvent(
    @JsonProperty("message")
    val message: Message,

    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.ChannelMessageReadyEvent, eventLevel = EventLevel.Action)
data class ChannelMessageReadyEvent(
    @JsonProperty("message")
    val message: Message,

    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.NewChannelEvent, eventLevel = EventLevel.Action)
data class NewChannelEvent(
    @JsonProperty("channel")
    val channel: Channel,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.ChannelMessageDeletedEvent, eventLevel = EventLevel.Action)
data class ChannelMessageDeletedEvent(
    @JsonProperty("deletedMessage")
    val deletedMessage: Message,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.ChannelDeletedEvent, eventLevel = EventLevel.Action)
data class ChannelDeletedEvent(
    @JsonProperty("deletedChannel")
    val deletedChannel: Channel,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.ChannelParticipantAddedEvent, eventLevel = EventLevel.Action)
data class ChannelParticipantAddedEvent(
    @JsonProperty("channelParticipation")
    val channelParticipation: ChannelParticipation,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)


@NerifEvent(MardleEventNames.ChannelParticipationModifiedEvent, eventLevel = EventLevel.Action)
data class ChannelParticipationModifiedEvent(
    @JsonProperty("oldParticipation")
    val oldParticipation: ChannelParticipation,
    @JsonProperty("newParticipation")
    val newParticipation: ChannelParticipation,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.NewTimelineEventForMessageEvent, eventLevel = EventLevel.Action)
data class NewTimelineEventForMessageEvent(
    @JsonProperty("timelineEvent")
    val timelineEvent: TimelineEvent,
    @JsonProperty("messageId")
    val messageId: Identifiable<Message>,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.NewTimelineEventForChannelEvent, eventLevel = EventLevel.Action)
data class NewTimelineEventForChannelEvent(
    @JsonProperty("timelineEvent")
    val timelineEvent: TimelineEvent,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.NewTimelineEvent, eventLevel = EventLevel.Atomic)
data class NewTimelineEventEvent(
    @JsonProperty("timelineEvent")
    val timelineEvent: TimelineEvent,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(MardleEventNames.ChannelMessagesTruncatedEvent, eventLevel = EventLevel.Action)
data class ChannelMessagesTruncatedEvent(
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)


@NerifEvent(MardleEventNames.ChannelParticipationDeletedEvent, eventLevel = EventLevel.Action)
data class ChannelParticipantDeletedEvent(
    @JsonProperty("channelParticipation")
    val channelParticipation: ChannelParticipation,
    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.NewChannelStreamEvent, eventLevel = EventLevel.Action)
data class NewChannelStreamEvent(
    @JsonProperty("stream")
    val stream: Stream,

    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)

@NerifEvent(MardleEventNames.ChannelStreamMessageReady, eventLevel = EventLevel.Action)
data class ChannelStreamMessageReady(
    @JsonProperty("streamData")
    val streamData: ContextFreeMessage,
    @JsonProperty("streamId")
    val streamId: Identifiable<Stream>,

    @JsonProperty("channelId")
    override val channelId: Identifiable<Channel>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ChannelEvent(channelId, applicationId)
