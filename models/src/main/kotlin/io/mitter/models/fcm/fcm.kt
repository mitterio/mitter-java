package io.mitter.models.fcm

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.models.delman.chat.MessagingPipelinePayload

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
data class FcmPacket(
    val payload: MessagingPipelinePayload,
    val type: String
) {
    val fcmDiscriminator: String
        @JsonProperty("__mitter_cloud_message") get() = "__mitter_cloud_message"
}

object PacketTypes {
    @JvmField
    val NEW_MESSAGE = "new-message"
    @JvmField
    val NEW_CHAT = "new-chat"
    @JvmField
    val NEW_RECEIPT = "new-receipt"
}
