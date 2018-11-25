package io.mitter.models.delman

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.data.domain.user.User

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardEndpointTypes {
    const val FcmDeliveryEndpoint: String = "fcm"
    const val TransactionalWebHook: String = "twh"
    const val WebSocketEndpoint: String = "ws"

    object Peripheral {
        const val Delimiter = ":"
    }
}

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="endpointType")
@JsonSubTypes(
    JsonSubTypes.Type(FcmDeliveryEndpoint::class),
    JsonSubTypes.Type(TransactionalWebHookEndpoint::class),
    JsonSubTypes.Type(WebSocketDeliveryEndpoint::class)
)
abstract class DeliveryEndpoint(
    @JsonProperty("serializedEndpoints")
    var serializedEndpoint: String,
    @JsonProperty("endpointType")
    var endpointType: String,
    @JsonProperty("autoExpireAt")
    var autoExpireAt: Long = Long.MAX_VALUE
) {
    override fun toString(): String {
        return "DeliveryEndpoint(serializedEndpoint='$serializedEndpoint', endpointType='$endpointType', autoExpireAt='$autoExpireAt')"
    }
}

@JsonTypeName(StandardEndpointTypes.FcmDeliveryEndpoint)
data class FcmDeliveryEndpoint(
    @JsonProperty("registrationToken") val registrationToken: String
) : DeliveryEndpoint(
        serializedEndpoint = "${StandardEndpointTypes.FcmDeliveryEndpoint}:$registrationToken",
        endpointType = StandardEndpointTypes.FcmDeliveryEndpoint
) {
    override fun toString(): String {
        return "FcmDeliveryEndpoint(registrationToken='$registrationToken') <- ${super.toString()}"
    }
}

@JsonTypeName(StandardEndpointTypes.WebSocketEndpoint)
data class WebSocketDeliveryEndpoint(
    @JsonProperty("applicationId") val applicationId: Identifiable<Application>,
    @JsonProperty("mappedUserId") val userId: Identifiable<User>
) : DeliveryEndpoint(
    serializedEndpoint = "${StandardEndpointTypes.WebSocketEndpoint}:${applicationId.domainId()};${userId.domainId()}",
    endpointType = StandardEndpointTypes.WebSocketEndpoint
) {
    override fun toString(): String {
        return "WebSocketDeliveryEndpoint(applicationId=$applicationId, userId=$userId)"
    }
}

@JsonTypeName(StandardEndpointTypes.TransactionalWebHook)
data class TransactionalWebHookEndpoint(
    @JsonProperty("webHookUri") val webHookUri: String
) : DeliveryEndpoint(
    serializedEndpoint = "${StandardEndpointTypes.TransactionalWebHook}:$webHookUri",
    endpointType = StandardEndpointTypes.TransactionalWebHook
) {
    override fun toString(): String {
        return "TransactionalWebHookEndpoint(webHookUri='$webHookUri') <- ${super.toString()}"
    }
}

data class UserDeliveryEndpoint(
    val userId: Identifiable<User>,
    val deliveryEndpoint: DeliveryEndpoint,
    val disabled: Boolean = false
)
