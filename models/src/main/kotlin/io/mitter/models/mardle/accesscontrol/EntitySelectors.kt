package io.mitter.models.mardle.accesscontrol

import io.mitter.data.domain.annotations.IdUtils
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.User
import io.mitter.models.acolyte.AclAccessorSelector
import io.mitter.models.acolyte.SelectorName
import io.mitter.models.mardle.messaging.Channel
import io.mitter.models.mardle.messaging.ParticipationStatus

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardEntitySelectorNames {
    const val ParticipantEntitySelector = "participant"
    const val UserIdEntitySelector = "user"
    const val AnyUserEntitySelector = "any_user"
}

@SelectorName(StandardEntitySelectorNames.ParticipantEntitySelector)
class ChannelParticipantAccessorSelector(
    val channelId: Identifiable<Channel>,
    val participationStatus: ParticipationStatus
) : AclAccessorSelector() {
    companion object {
        fun factory(serialized: String) = serialized.split(":").let {
            ChannelParticipantAccessorSelector(
                channelId = IdUtils.of(it[0]),
                participationStatus = ParticipationStatus.valueOf(it[1])
            )
        }
    }

    override fun serialize() = "${channelId.domainId()}:${participationStatus.name}"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ChannelParticipantAccessorSelector) return false

        if (channelId.domainId() != other.channelId.domainId()) return false
        if (participationStatus != other.participationStatus) return false

        return true
    }

    override fun hashCode(): Int {
        var result = channelId.hashCode()
        result = 31 * result + participationStatus.hashCode()
        return result
    }
}

@SelectorName(StandardEntitySelectorNames.UserIdEntitySelector)
class UserIdAccessorSelector(
    val userId: Identifiable<User>
) : AclAccessorSelector() {
    companion object {
        fun factory(serialized: String) = UserIdAccessorSelector(
            userId = IdUtils.of(serialized)
        )
    }

    override fun serialize() = userId.domainId()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserIdAccessorSelector) return false

        if (userId.domainId() != other.userId.domainId()) return false

        return true
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }
}

@SelectorName(StandardEntitySelectorNames.AnyUserEntitySelector)
class AnyUserAccessorSelector : AclAccessorSelector() {
    companion object {
        fun factory() = AnyUserAccessorSelector()
    }

    override fun serialize() = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnyUserAccessorSelector) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

