package io.mitter.sdk.java.clients.hlc.messages

import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.mardle.messaging.Channel
import io.mitter.models.mardle.messaging.Message
import io.mitter.sdk.java.clients.MitterMessagesClient
import io.mitter.sdk.java.utils.MitterMessagesHelper
import io.mitter.sdk.java.utils.identifiable
import mu.KLoggable

class MitterMessagesIterator internal constructor(
    private val channelId: Identifiable<Channel>,
    private val messagesClient: MitterMessagesClient,

    /*
    There is no guarantee that every batch will receive these many messages, use this only to pass on as
    a value while fetching messages from mitter. Other places should not rely on this value.
     */
    private val fetchCount: Int = 10,
    private val iterationMode: IterationMode = IterationMode.FetchExisting,
    startingFromMessageId: Identifiable<Message>? = null
) : Iterator<Message> {
    enum class IterationMode {
        FetchNew,
        FetchExisting
    }

    private var buffer: List<Message> = listOf()
    private var noMoreMessages: Boolean = false
    private var firstFetchCommitted: Boolean = false

    var lastMessageId: Identifiable<Message>? = startingFromMessageId
        get() = field?.domainId()?.identifiable()
        private set

    companion object : Any(), KLoggable {
        override val logger = logger()
    }

    override fun hasNext(): Boolean {
        if (!firstFetchCommitted || buffer.isEmpty()) {
            fillBuffer()
        }

        return !noMoreMessages
    }

    override fun next(): Message {
        if (!firstFetchCommitted || buffer.isEmpty()) {
            fillBuffer()
        }

        return buffer.firstOrNull()?.apply {
            buffer = buffer.drop(n = 1)
        } ?: throw IndexOutOfBoundsException()
    }

    private fun fillBuffer() {
        logger.debug { "Filling buffer" }

        buffer = messagesClient.getMessagesFromChannel(
            channelId,
            MitterMessagesHelper.messageQuery(
                limit = fetchCount,
                before = if (iterationMode == IterationMode.FetchExisting) lastMessageId else null,
                after = if (iterationMode == IterationMode.FetchNew) lastMessageId else null
            )
        )

        logger.debug { "Got [${buffer.size}] messages, ending at ${buffer.lastOrNull()?.messageId ?: "EMPTY-LIST"}" }

        if (buffer.isEmpty()) {
            noMoreMessages = true
        } else {
            noMoreMessages = false
            lastMessageId = when(iterationMode) {
                IterationMode.FetchExisting -> buffer.last()
                IterationMode.FetchNew -> buffer.first()
            }
        }

        firstFetchCommitted = true
    }
}
