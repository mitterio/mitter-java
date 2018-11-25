package io.mitter.models.nerif

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.mitter.models.deliverymanagement.events.PayloadDeliveryAttemptEvent
import io.mitter.models.mardle.events.*
import io.mitter.models.deliverymanagement.events.*
import io.mitter.models.mediamanagement.events.*
import io.mitter.models.central.events.*

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "__event_name"
)
@JsonSubTypes(
    JsonSubTypes.Type(PayloadDeliveryAttemptEvent::class, name = DeliveryManagementEventNames.PayloadDeliveryAttemptEvent),
    JsonSubTypes.Type(UserTargetDeliveryRequestedEvent::class, name = DeliveryManagementEventNames.UserTargetDeliveryRequestedEvent),
    JsonSubTypes.Type(DeliveryEndpointRegisteredEvent::class, name = DeliveryManagementEventNames.DeliveryEndpointRegisteredEvent),
    JsonSubTypes.Type(DeliveryEndpointUnregisteredEvent::class, name = DeliveryManagementEventNames.DeliveryEndpointUnregisteredEvent),
    JsonSubTypes.Type(DeliveryEndpointTransferredEvent::class, name = DeliveryManagementEventNames.DeliveryEndpointTransferredEvent),
    JsonSubTypes.Type(HostedBinaryAvailableEvent::class, name = MediaManagementEventNames.HostedBinaryAvailableEvent),
    JsonSubTypes.Type(NewChannelMessageEvent::class, name = MardleEventNames.NewChannelMessageEvent),
    JsonSubTypes.Type(ChannelMessageReadyEvent::class, name = MardleEventNames.ChannelMessageReadyEvent),
    JsonSubTypes.Type(NewChannelEvent::class, name = MardleEventNames.NewChannelEvent),
    JsonSubTypes.Type(ChannelMessageDeletedEvent::class, name = MardleEventNames.ChannelMessageDeletedEvent),
    JsonSubTypes.Type(ChannelDeletedEvent::class, name = MardleEventNames.ChannelDeletedEvent),
    JsonSubTypes.Type(ChannelParticipantAddedEvent::class, name = MardleEventNames.ChannelParticipantAddedEvent),
    JsonSubTypes.Type(ChannelParticipationModifiedEvent::class, name = MardleEventNames.ChannelParticipationModifiedEvent),
    JsonSubTypes.Type(NewTimelineEventForMessageEvent::class, name = MardleEventNames.NewTimelineEventForMessageEvent),
    JsonSubTypes.Type(NewTimelineEventForChannelEvent::class, name = MardleEventNames.NewTimelineEventForChannelEvent),
    JsonSubTypes.Type(NewTimelineEventEvent::class, name = MardleEventNames.NewTimelineEvent),
    JsonSubTypes.Type(ChannelMessagesTruncatedEvent::class, name = MardleEventNames.ChannelMessagesTruncatedEvent),
    JsonSubTypes.Type(ChannelParticipantDeletedEvent::class, name = MardleEventNames.ChannelParticipationDeletedEvent),
    JsonSubTypes.Type(NewChannelStreamEvent::class, name = MardleEventNames.NewChannelStreamEvent),
    JsonSubTypes.Type(ChannelStreamMessageReady::class, name = MardleEventNames.ChannelStreamMessageReady),
    JsonSubTypes.Type(NewUserEvent::class, name = CentralEvents.NewUserEvent),
    JsonSubTypes.Type(ScreenNameChangedEvent::class, name = CentralEvents.ScreenNameChangedEvent),
    JsonSubTypes.Type(UserDeletedEvent::class, name = CentralEvents.UserDeletedEvent),
    JsonSubTypes.Type(UserLocatorAddedEvent::class, name = CentralEvents.UserLocatorAddedEvent),
    JsonSubTypes.Type(UserLocatorRemovedEvent::class, name = CentralEvents.UserLocatorRemovedEvent),
    JsonSubTypes.Type(UserTokenIssuedEvent::class, name = CentralEvents.UserTokenIssuedEvent),
    JsonSubTypes.Type(UserTokenRevokedEvent::class, name = CentralEvents.UserTokenRevokedEvent),
    JsonSubTypes.Type(NewApplicationPropertyEvent::class, name = CentralEvents.NewApplicationPropertyEvent),
    JsonSubTypes.Type(ApplicationPropertyDeletedEvent::class, name = CentralEvents.ApplicationPropertyDeletedEvent),
    JsonSubTypes.Type(NewApplicationEvent::class, name = CentralEvents.NewApplicationEvent),
    JsonSubTypes.Type(NewApplicationAccessKeyEvent::class, name = CentralEvents.NewApplicationAccessKeyEvent),
    JsonSubTypes.Type(ApplicationAccessKeyDeletedEvent::class, name = CentralEvents.ApplicationAccessKeyDeletedEvent),
    JsonSubTypes.Type(SubscriberTokenIssuedEvent::class, name = CentralEvents.SubscriberTokenIssuedEvent),
    JsonSubTypes.Type(SubscriberTokenRevokedEvent::class, name = CentralEvents.SubscriberTokenRevokedEvent),
    JsonSubTypes.Type(NewSubscriberEvent::class, name = CentralEvents.NewSubscriberEvent),
    JsonSubTypes.Type(TokenIssuedEvent::class, name = CentralEvents.TokenIssuedEvent),
    JsonSubTypes.Type(TokenRevokedEvent::class, name = CentralEvents.TokenRevokedEvent),
    JsonSubTypes.Type(UserPresenceChangedEvent::class, name = CentralEvents.UserPresenceChangedEvent),
    JsonSubTypes.Type(FederatedUserLinkStateChangedEvent::class, name = CentralEvents.FederatedUserLinkStateChangedEvent),
    JsonSubTypes.Type(NewFederatedUserEvent::class, name = CentralEvents.NewFederatedUserEvent)
)
interface BaseEvent

enum class EventLevel {
    Action,
    Atomic
}

data class TransportableEvent<out E : BaseEvent>(
    @JsonProperty("globalEventId")
    val globalEventId: String,
    @JsonProperty("event")
    val event: E,
    @JsonProperty("eventName")
    val eventName: String,
    @JsonProperty("eventTimeMs")
    val eventTimeMs: Long = System.currentTimeMillis()
)

annotation class NerifEvent(
    val eventName: String,
    val eventLevel: EventLevel
)

