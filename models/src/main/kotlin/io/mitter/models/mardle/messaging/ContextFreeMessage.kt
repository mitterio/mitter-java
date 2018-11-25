package io.mitter.models.mardle.messaging

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.User

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class ContextFreeMessage(
    val contentType: String,
    val context: String,
    val senderId: Identifiable<User>?,
    val data: JsonNode = JsonNodeFactory.instance.objectNode()
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun constructionSupport(
            @JsonProperty("contentType") contentType: String,
            @JsonProperty("context")     context: String,
            @JsonProperty("senderId")    senderId: Identifiable<User>?,
            @JsonProperty("data")        data: JsonNode?
        ) = ContextFreeMessage(
            contentType = contentType,
            context = context,
            senderId = senderId,
            data = data ?: JsonNodeFactory.instance.objectNode()
        )
    }
}
