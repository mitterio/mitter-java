package io.mitter.models.mardle.accesscontrol

import io.mitter.models.acolyte.AclPrivilege
import io.mitter.models.acolyte.PrivilegeName
import io.mitter.models.mardle.messaging.Message

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardMessagePrivilegeNames {
    const val ReadMessage = "read_message"
    const val DeleteMessage = "delete_message"
}

@PrivilegeName(StandardMessagePrivilegeNames.ReadMessage)
class ReadMessagePrivilege : AclPrivilege<Message>(Message::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ReadMessagePrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@PrivilegeName(StandardMessagePrivilegeNames.DeleteMessage)
class DeleteMessagePrivilege : AclPrivilege<Message>(Message::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DeleteMessagePrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
