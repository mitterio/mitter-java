package io.mitter.models.central.events

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.IdUtils
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.data.domain.application.properties.ApplicationProperty
import io.mitter.data.domain.entity.AttributeDef
import io.mitter.data.domain.entity.EntityProfile
import io.mitter.data.domain.federation.FederatedUser
import io.mitter.data.domain.subscriber.Subscriber
import io.mitter.data.domain.user.*
import io.mitter.data.domain.user.locators.UserLocator
import io.mitter.models.nerif.BaseEvent
import io.mitter.models.nerif.EventLevel
import io.mitter.models.nerif.NerifEvent

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object CentralEvents {
    const val NewUserEvent                           = "mitter.mpe.users.NewUser"
    const val ScreenNameChangedEvent                 = "mitter.mpe.users.ScreenNameChanged"
    const val UserDeletedEvent                       = "mitter.mpe.users.UserDeleted"

    const val UserLocatorAddedEvent                  = "mitter.mpe.users.UserLocatorAdded"
    const val UserLocatorRemovedEvent                = "mitter.mpe.users.UserLocatorRemoved"

    const val UserTokenIssuedEvent                   = "mitter.mpe.users.UserTokenIssued"
    const val UserTokenRevokedEvent                  = "mitter.mpe.users.UserTokenRevoked"

    const val EntityProfileAttributeDefCreatedEvent  = "mitter.mpe.entities.profiles.EntityProfileAttributeDefCreated"
    const val EntityProfileModifiedEvent             = "mitter.mpe.entities.profiles.EntityProfileModified"

    const val UserPresenceChangedEvent               = "mitter.mpe.users.UserPresenceChanged"

    const val NewFederatedUserEvent                  = "mitter.mpe.users.federation.NewFederatedUser"
    const val FederatedUserLinkStateChangedEvent     = "mitter.mpe.users.federation.FederatedUserLinkStateChanged"

    const val SubscriberTokenIssuedEvent             = "mitter.mpe.subscribers.SubscriberTokenIssued"
    const val SubscriberTokenRevokedEvent            = "mitter.mpe.subscribers.SubscriberTokenRevoked"

    const val NewSubscriberEvent                     = "mitter.mpe.subscribers.NewSubscriber"

    const val TokenIssuedEvent                       = "mitter.mpe.central.TokenIssuedEvent"
    const val TokenRevokedEvent                      = "mitter.mpe.central.TokenRevokedEvent"

    const val NewApplicationPropertyEvent            = "mitter.mpe.applications.NewApplicationProperty"
    const val ApplicationPropertyDeletedEvent        = "mitter.mpe.applications.ApplicationPropertyDeleted"
    const val ApplicationPropertyPatchedEvent        = "mitter.mpe.applications.ApplicationPropertyPatched"

    const val NewApplicationEvent                    = "mitter.mpe.applications.NewApplication"

    const val NewApplicationAccessKeyEvent           = "mitter.mpe.applications.NewApplicationAccessKey"
    const val ApplicationAccessKeyDeletedEvent       = "mitter.mpe.applications.ApplicationAccessKeyDeleted"
}

abstract class ApplicationEvent(
    open val applicationId: Identifiable<Application>
): BaseEvent

abstract class SubscriberEvent(
    subscriberId: Identifiable<Subscriber>
) : BaseEvent {
    open val subscriberId: Identifiable<Subscriber> = IdUtils.of(subscriberId.domainId(), Subscriber::class.java)
}

abstract class SystemEvent : BaseEvent

abstract class UserEvent(
    userId: Identifiable<User>,

    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId) {
    open val userId: Identifiable<User> = IdUtils.of(userId.domainId(), User::class.java)
}

abstract class EntityProfileEvent(
    open val entityId: Identifiable<*>,
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(CentralEvents.NewUserEvent, eventLevel = EventLevel.Action)
data class NewUserEvent(
    @JsonProperty("user")
    val user: User,

    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(user, applicationId)

@NerifEvent(CentralEvents.ScreenNameChangedEvent, eventLevel = EventLevel.Action)
data class ScreenNameChangedEvent(
    @JsonProperty("oldScreenName")
    val oldScreenName: ScreenName? = null,
    @JsonProperty("newScreenName")
    val newScreenName: ScreenName,

    @JsonProperty("userId")
    override val userId: Identifiable<User>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(userId, applicationId)

@NerifEvent(CentralEvents.UserDeletedEvent, eventLevel = EventLevel.Action)
data class UserDeletedEvent(
    @JsonProperty("userId")
    override val userId: Identifiable<User>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(userId, applicationId)

@NerifEvent(CentralEvents.UserLocatorAddedEvent, eventLevel = EventLevel.Action)
data class UserLocatorAddedEvent(
    @JsonProperty("userId")
    override val userId: Identifiable<User>,
    @JsonProperty("userLocator")
    val userLocator: UserLocator,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(userId, applicationId)


@NerifEvent(CentralEvents.UserLocatorRemovedEvent, eventLevel = EventLevel.Action)
data class UserLocatorRemovedEvent(
    @JsonProperty("userId")
    override val userId: Identifiable<User>,
    @JsonProperty("userLocator")
    val userLocator: UserLocator,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(userId, applicationId)

@NerifEvent(CentralEvents.UserTokenIssuedEvent, eventLevel = EventLevel.Action)
data class UserTokenIssuedEvent(
    @JsonProperty("tokenId")
    val tokenId: String,
    @JsonProperty("userId")
    override val userId: Identifiable<User>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(userId, applicationId)

@NerifEvent(CentralEvents.UserTokenRevokedEvent, eventLevel = EventLevel.Action)
data class UserTokenRevokedEvent(
    @JsonProperty("tokenId")
    val tokenId: String,
    @JsonProperty("userId")
    override val userId: Identifiable<User>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(userId, applicationId)

@NerifEvent(CentralEvents.UserPresenceChangedEvent, eventLevel = EventLevel.Atomic)
data class UserPresenceChangedEvent(
    @JsonProperty("oldPresence")
    val oldPresence: Presence? = null,
    @JsonProperty("newPresence")
    val newPresence: Presence?,
    @JsonProperty("userId")
    override val userId: Identifiable<User>,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : UserEvent(userId, applicationId)

@NerifEvent(CentralEvents.EntityProfileAttributeDefCreatedEvent, eventLevel = EventLevel.Action)
data class EntityProfileAttributeDefCreatedEvent(
    @JsonProperty("attributeDef")
    val attributeDef: AttributeDef,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(CentralEvents.EntityProfileModifiedEvent, eventLevel = EventLevel.Action)
data class EntityProfileModifiedEvent(
    @JsonProperty("newEntityProfile")
    val newEntityProfile: EntityProfile,
    @JsonProperty("oldEntityProfile")
    val oldEntityProfile: EntityProfile? = null,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : EntityProfileEvent(newEntityProfile.entityId, applicationId)

@NerifEvent(CentralEvents.NewFederatedUserEvent, eventLevel = EventLevel.Atomic)
data class NewFederatedUserEvent(
    @JsonProperty("federatedUser")
    val federatedUser: FederatedUser
) : UserEvent(federatedUser.referencedUser, federatedUser.applicationId)

@NerifEvent(CentralEvents.FederatedUserLinkStateChangedEvent, eventLevel = EventLevel.Atomic)
data class FederatedUserLinkStateChangedEvent(
    @JsonProperty("federatedUser")
    val federatedUser: FederatedUser,
    @JsonProperty("previousLinkState")
    val previousLinkState: String?,
    @JsonProperty("newLinkState")
    val newLinkState: String
) : UserEvent(federatedUser.referencedUser, federatedUser.applicationId)

@NerifEvent(CentralEvents.NewApplicationPropertyEvent, eventLevel = EventLevel.Action)
data class NewApplicationPropertyEvent(
    @JsonProperty("applicationProperty")
    val applicationProperty: ApplicationProperty,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(CentralEvents.ApplicationPropertyDeletedEvent, eventLevel = EventLevel.Action)
data class ApplicationPropertyDeletedEvent(
    @JsonProperty("applicationProperty")
    val applicationProperty: ApplicationProperty,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(CentralEvents.ApplicationPropertyPatchedEvent, eventLevel = EventLevel.Action)
data class ApplicationPropertyPatchedEvent(
    @JsonProperty("applicationProperty")
    val applicationProperty: ApplicationProperty,
    @JsonProperty("default")
    val default: Boolean,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(CentralEvents.NewApplicationEvent, eventLevel = EventLevel.Action)
data class NewApplicationEvent(
    @JsonProperty("application")
    val application: Application,
    @JsonProperty("subscriberId")
    override val subscriberId: Identifiable<Subscriber>
) : SubscriberEvent(subscriberId)

@NerifEvent(CentralEvents.NewApplicationAccessKeyEvent, eventLevel = EventLevel.Action)
data class NewApplicationAccessKeyEvent(
    @JsonProperty("accessKey")
    val accessKey: String,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(CentralEvents.ApplicationAccessKeyDeletedEvent, eventLevel = EventLevel.Action)
data class ApplicationAccessKeyDeletedEvent(
    @JsonProperty("accessKey")
    val accessKey: String,
    @JsonProperty("applicationId")
    override val applicationId: Identifiable<Application>
) : ApplicationEvent(applicationId)

@NerifEvent(CentralEvents.SubscriberTokenIssuedEvent, eventLevel = EventLevel.Action)
data class SubscriberTokenIssuedEvent(
    @JsonProperty("tokenId")
    val tokenId: String,
    @JsonProperty("subscriberId")
    override val subscriberId: Identifiable<Subscriber>
) : SubscriberEvent(subscriberId)

//TODO There don't seem to be any calls on central for this event. SUI
@NerifEvent(CentralEvents.SubscriberTokenRevokedEvent, eventLevel = EventLevel.Action)
data class SubscriberTokenRevokedEvent(
    @JsonProperty("tokenId")
    val tokenId: String,
    @JsonProperty("subscriberId")
    override val subscriberId: Identifiable<Subscriber>
) : SubscriberEvent(subscriberId)

@NerifEvent(CentralEvents.NewSubscriberEvent, eventLevel = EventLevel.Action)
data class NewSubscriberEvent(
    @JsonProperty("subscriber")
    val subscriber: Subscriber
) : SystemEvent()

@NerifEvent(CentralEvents.TokenIssuedEvent, eventLevel = EventLevel.Atomic)
data class TokenIssuedEvent(
    @JsonProperty("tokenId")
    val tokenId: String,
    @JsonProperty("entityType")
    val entityType: String
) : SystemEvent()

@NerifEvent(CentralEvents.TokenRevokedEvent, eventLevel = EventLevel.Atomic)
data class TokenRevokedEvent(
    @JsonProperty("tokenId")
    val tokenId: String,
    @JsonProperty("entityType")
    val entityType: String
) : SystemEvent()
