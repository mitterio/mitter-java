package io.mitter.data.domain.user

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */

object StandardUserPresenceTypeNames {
    const val Online: String = "mitter.up.Online"
    const val Sleeping: String = "mitter.up.Sleeping"
    const val Away: String = "mitter.up.Away"
    const val Missing: String = "mitter.up.Missing"
    const val Offline: String = "mitter.up.Offline"
}

data class Presence(
    @JsonProperty("type")
    val type: String,
    @JsonProperty("timeToLive")
    val timeToLive: Int = 0,
    @JsonProperty("expiresTo")
    val expiresTo: Presence? = null
)