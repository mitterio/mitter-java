package io.mitter.models.deliverymanagement.events

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.data.domain.user.User
import io.mitter.models.central.events.ApplicationEvent
import io.mitter.models.delman.DeliveryResult
import io.mitter.models.delman.chat.MessagingPipelinePayload
import io.mitter.models.nerif.EventLevel
import io.mitter.models.nerif.NerifEvent

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object DeliveryManagementEventNames {
    const val PayloadDeliveryAttemptEvent = "mitter.mpe.delivery.PayloadDeliveryAttempt"
    const val UserTargetDeliveryRequestedEvent = "mitter.mpe.delivery.UserTargetDeliveryRequested"

    const val DeliveryEndpointRegisteredEvent = "mitter.mpe.delivery.DeliveryEndpointRegistered"
    const val DeliveryEndpointUnregisteredEvent = "mitter.mpe.delivery.DeliveryEndpointUnregistered"
    const val DeliveryEndpointTransferredEvent = "mitter.mpe.delivery.DeliveryEndpointTransferred"
    const val DeliveryEndpointEnabledEvent = "mitter.mpe.delivery.DeliveryEndpointEnabled"
    const val DeliveryEndpointDisabledEvent = "mitter.mpe.delivery.DeliveryEndpointDisabled"
}

@NerifEvent(DeliveryManagementEventNames.PayloadDeliveryAttemptEvent, eventLevel = EventLevel.Atomic)
data class PayloadDeliveryAttemptEvent(
    @JsonProperty("serializedDeliveryEndpoint")
    val serializedDeliveryEndpoint: String,
    @JsonProperty("deliveryResult")
    val deliveryResult: DeliveryResult,
    @JsonProperty("payload")
    val payload: Any,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(DeliveryManagementEventNames.UserTargetDeliveryRequestedEvent, eventLevel = EventLevel.Action)
data class UserTargetDeliveryRequestedEvent(
    @JsonProperty("userId")
    val userId: Identifiable<User>,
    @JsonProperty("messagingPipelinePayload")
    val messagingPipelinePayload: MessagingPipelinePayload,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(DeliveryManagementEventNames.DeliveryEndpointRegisteredEvent, eventLevel = EventLevel.Action)
data class DeliveryEndpointRegisteredEvent(
    @JsonProperty("userId")
    val userId: Identifiable<User>,
    @JsonProperty("deliveryEndpoint")
    val deliveryEndpoint: String,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(DeliveryManagementEventNames.DeliveryEndpointUnregisteredEvent, eventLevel = EventLevel.Action)
data class DeliveryEndpointUnregisteredEvent(
    @JsonProperty("userId")
    val userId: Identifiable<User>,
    @JsonProperty("deliveryEndpoint")
    val deliveryEndpoint: String,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

//TODO send this event
@NerifEvent(DeliveryManagementEventNames.DeliveryEndpointTransferredEvent, eventLevel = EventLevel.Atomic)
data class DeliveryEndpointTransferredEvent(
    @JsonProperty("deliveryEndpointId")
    val deliveryEndpointId: String,
    @JsonProperty("oldOwner")
    val oldOwner: Identifiable<User>,
    @JsonProperty("newOwner")
    val newOwner: Identifiable<User>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(DeliveryManagementEventNames.DeliveryEndpointEnabledEvent, eventLevel = EventLevel.Action)
data class DeliveryEndpointEnabledEvent(
    @JsonProperty("userId")
    val userId: Identifiable<User>,
    @JsonProperty("deliveryEndpoint")
    val deliveryEndpoint: String,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(DeliveryManagementEventNames.DeliveryEndpointDisabledEvent, eventLevel = EventLevel.Action)
data class DeliveryEndpointDisabledEvent(
    @JsonProperty("userId")
    val userId: Identifiable<User>,
    @JsonProperty("deliveryEndpoint")
    val deliveryEndpoint: String,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)
