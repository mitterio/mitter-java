package io.mitter.models.mardle.accesscontrol

import io.mitter.models.acolyte.AclPrivilege
import io.mitter.models.acolyte.PrivilegeName
import io.mitter.models.mardle.messaging.Channel

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardChannelPrivilegeNames {
    const val JoinChannel = "join_channel"
    const val AddParticipantToChannel = "add_participant_to_channel"
    const val ListParticipants = "list_participants"
    const val RemoveParticipant = "remove_participant"
    const val RemoveSelf = "remove_self"
    const val DeleteMessagesFromChannel = "delete_messages_from_channel"
    const val ReadFromChannel = "read_from_channel"
    const val SendToChannel = "send_to_channel"
    const val SendAsOtherToChannel = "send_as_other_to_channel"
    const val ModifyChannelParticipation = "modify_channel_participation"
    const val DeleteChannel = "delete_channel"
    const val CreateChannelStream = "create_channel_stream"
}

@PrivilegeName(StandardChannelPrivilegeNames.JoinChannel)
class JoinChannelPrivilege       : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JoinChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.ListParticipants)
class ListParticipantsPrivilege  : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ListParticipantsPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.RemoveParticipant)
class RemoveParticipantPrivilege : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RemoveParticipantPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.RemoveSelf)
class RemoveSelfPrivilege        : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RemoveSelfPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.ReadFromChannel)
class ReadFromChannelPrivilege   : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ReadFromChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.CreateChannelStream)
class CreateChannelStreamPrivilege : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CreateChannelStreamPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.DeleteMessagesFromChannel)
class DeleteMessagesFromChannelPrivilege  : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DeleteMessagesFromChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.SendToChannel)
class SendToChannelPrivilege     : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SendToChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.SendAsOtherToChannel)
class SendAsOtherToChannelPrivilege : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SendAsOtherToChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.AddParticipantToChannel)
class AddParticipantToChannelPrivilege : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AddParticipantToChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.ModifyChannelParticipation)
class ModifyParticipationStatus : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (other !is ModifyParticipationStatus) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardChannelPrivilegeNames.DeleteChannel)
class DeleteChannelPrivilege : AclPrivilege<Channel>(Channel::class) {
    override fun equals(other: Any?): Boolean {
        if (other !is DeleteChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

