package io.mitter.sdk.java.clients.hlc

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.mardle.messaging.Channel
import io.mitter.models.mardle.messaging.Message
import io.mitter.sdk.java.clients.MitterMessagesClient
import io.mitter.sdk.java.clients.hlc.messages.MitterMessagesIterator
import io.mitter.sdk.java.support.MitterSdkSupport
import mu.KLoggable

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterMessagesHlcClient(
    private val mitterMessagesClient: MitterMessagesClient
) {
    companion object : Any(), KLoggable {
        override val logger = logger()
    }

    fun getAllMessagesBefore(channelId: Identifiable<Channel>, before: Identifiable<Message>) =
        MitterMessagesIterator(
            channelId,
            mitterMessagesClient,
            iterationMode = MitterMessagesIterator.IterationMode.FetchExisting,
            startingFromMessageId = before
        )

    fun getAllMessagesAfter(channelId: Identifiable<Channel>, after: Identifiable<Message>) =
        MitterMessagesIterator(
            channelId,
            mitterMessagesClient,
            iterationMode = MitterMessagesIterator.IterationMode.FetchNew,
            startingFromMessageId = after
        )

    fun getAllExistingMessages(channelId: Identifiable<Channel>) =
        MitterMessagesIterator(
            channelId,
            mitterMessagesClient,
            iterationMode = MitterMessagesIterator.IterationMode.FetchExisting
        )

    fun getNewMessages(channelId: Identifiable<Channel>, fetchLastExisting: Int = 10) =
        MitterMessagesIterator(
            channelId,
            mitterMessagesClient,
            fetchLastExisting,
            MitterMessagesIterator.IterationMode.FetchNew
        )
}

fun main(args: Array<String>) {
    val dt = "[{\"messageId\":\"4a1441a5-f900-4658-8f0c-c8fcc3686514\",\"messageType\":\"Standard\",\"payloadType\":\"mitter.mt.Text\",\"senderId\":{\"domainId\":\"a877bc4f-2792-4bcc-91f8-2c9bc7947dc1\"},\"textPayload\":\"Hello [20]\",\"messageData\":[],\"timelineEvents\":[{\"eventId\":\"74ac514c-f439-4089-be73-fddaa69a538f\",\"type\":\"mitter.mtet.SentTime\",\"eventTimeMs\":1514905380448,\"subject\":{\"domainId\":\"a877bc4f-2792-4bcc-91f8-2c9bc7947dc1\"}}],\"appliedAcls\":{\n" +
        "  \"plusAppliedAcls\": [\"+read:whatever\"]\n" +
        "},\"entityMetadata\":{\"metadata\":{}}}]"

    MitterSdkSupport.configureJackson(ObjectMapper()).apply {
        val x : List<Message> = this.readValue(dt, object : TypeReference<List<Message>>() {})
        println(">> $x")
    }
}
