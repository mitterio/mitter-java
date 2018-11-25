package io.mitter.models.central.entityprofile

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on May / 2018
 *
 */

enum class StandardEntityType(val value: String) {
    User("users"),
    Channel("channels"),
    Application("applications"),
    Message("messages");

    companion object {
        fun getEntityType(type: String) : StandardEntityType {
            StandardEntityType.values().filter {
                type.contains(it.toString().toLowerCase())
            }.let { return it[0] }
        }
    }
}
