package io.mitter.models.auth.issuance

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.mitter.models.auth.issuance.entities.SubscriberTokenIssuable
import io.mitter.models.auth.issuance.entities.UserTokenIssuable

object StandardTokenIssuables {
    const val User = "tokenIssuable.user"
    const val Subscriber = "tokenIssuable.subscriber"
}

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "entityTypeName"
)
@JsonSubTypes(*arrayOf(
    JsonSubTypes.Type(UserTokenIssuable::class),
    JsonSubTypes.Type(SubscriberTokenIssuable::class)
))
abstract class TokenIssuable<T>(
    @JsonProperty("entityId")
    val entityId: String,

    @JsonProperty("entityType")
    val entityType: Class<T>,

    @JsonProperty("entityTypeName")
    val entityTypeName: String
)

data class Token(
    @JsonProperty("token")
    val token: String
)

data class IssuedToken<T>(
    @JsonProperty("token")
    val token: Token,

    @JsonProperty("ttl")
    val ttl: Int,

    @JsonProperty("tokenIssuable")
    val tokenIssuable: TokenIssuable<T>,

    @JsonProperty("tokenId")
    val tokenId: String
)

data class AttachedIssuedToken<T>(
    @JsonProperty("token")
    val token: Token,

    @JsonProperty("ttl")
    val ttl: Int,

    @JsonProperty("tokenId")
    val tokenId: String
)