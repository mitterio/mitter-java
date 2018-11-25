package io.mitter.models.mardle.accesscontrol

import io.mitter.data.domain.application.Application
import io.mitter.models.acolyte.AclPrivilege
import io.mitter.models.acolyte.PrivilegeName

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardApplicationPrivilegeNames {
    const val CreateChannel         = "create_channel"
    const val CreateMessage         = "create_message"
    const val CreateUser            = "create_user"
    const val ListChannels          = "list_channels"
    const val ListUserData          = "list_user_data"
    const val WriteUserCredentials = "write_user_credentials"
}

@PrivilegeName(StandardApplicationPrivilegeNames.CreateChannel)
class CreateChannelPrivilege : AclPrivilege<Application>(Application::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CreateChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardApplicationPrivilegeNames.CreateMessage)
class CreateMessagePrivilege : AclPrivilege<Application>(Application::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CreateMessagePrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardApplicationPrivilegeNames.CreateUser)
class CreateUserPrivilege    : AclPrivilege<Application>(Application::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CreateUserPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardApplicationPrivilegeNames.ListChannels)
class ListChannelPrivilege  : AclPrivilege<Application>(Application::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ListChannelPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardApplicationPrivilegeNames.ListUserData)
class ListUserDataPrivilege : AclPrivilege<Application>(Application::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ListUserDataPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardApplicationPrivilegeNames.WriteUserCredentials)
class WriteUserCredentials : AclPrivilege<Application>(Application::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WriteUserCredentials) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
