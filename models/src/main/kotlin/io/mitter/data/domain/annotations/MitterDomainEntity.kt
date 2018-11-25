package io.mitter.data.domain.annotations

import io.mitter.data.domain.entity.MetadataAttachable
import io.mitter.models.central.entityprofile.ProfileAttachable
import kotlin.reflect.KClass

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
interface MitterDomainEntity<out T: MitterDomainEntity<T>> : IdentifiableEntity<T>, MetadataAttachable

fun KClass<out MitterDomainEntity<*>>.getEntityName() =
    this.java.simpleName!!.toLowerCase() + "s" // Looks like a bit of a stupid hack here...

enum class EntityName {
    User,
    Channel,
    Message;

    companion object {
        fun entityNameFrom(string: String): EntityName {
            return when (string) {
                "users" -> User
                "channels" -> Channel
                "messages" -> Message
                else -> throw IllegalArgumentException("Unknown entity: $string")
            }
        }
    }
}