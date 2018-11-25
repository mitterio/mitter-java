package io.mitter.models.mardle.messaging

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import io.mitter.data.domain.IdGenerator
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.annotations.MitterDomainEntity
import io.mitter.data.domain.entity.EntityMetadata
import io.mitter.data.domain.entity.emptyEntityMetadata
import io.mitter.data.domain.user.User
import io.mitter.models.acolyte.AclEntity
import io.mitter.models.acolyte.AppliedAclList
import io.mitter.models.acolyte.emptyAclList
import io.mitter.models.commons.AuditInfo
import io.mitter.models.commons.Auditable
import io.mitter.models.mardle.wire.ChannelMessage

/**
 * Created by rohan on 5/31/17.
 */
object StandardPayloadTypeNames {
    const val TextMessage = "mitter.mt.Text"
    const val FormattedTextMessage = "mitter.mt.FormattedText"
    const val LinkInsetTextMessage = "mitter.mt.LinkInsetText"
    const val ImageMessage = "mitter.mt.Image"
    const val EmptyMessage = "mitter.mt.Empty"
    const val FileMessage = "mitter.mt.File"
}

enum class StandardPayloadTypes(
    val qualifiedPayloadType: String,
    val __restrictedPrefixes: List<String> = listOf("io.mitter.", "mitter.")
) {
    TextMessage(qualifiedPayloadType = StandardPayloadTypeNames.TextMessage),
    FormattedTextMessage(qualifiedPayloadType = StandardPayloadTypeNames.FormattedTextMessage),
    LinkInsetTextMessage(qualifiedPayloadType = StandardPayloadTypeNames.LinkInsetTextMessage),
    ImageMessage(qualifiedPayloadType = StandardPayloadTypeNames.ImageMessage),
    FileMessage(qualifiedPayloadType = StandardPayloadTypeNames.FileMessage),
    EmptyMessage(qualifiedPayloadType = StandardPayloadTypeNames.EmptyMessage)
}

enum class StandardMessageType {
    Standard,
    Notification,
    OutOfBand
}

data class MessageDatum(
    @JsonProperty("dataType")
    val dataType: String,

    @JsonProperty("data")
    val data: JsonNode
)

abstract class MessageBase(
    /* A globally unique domainId for this message */
    open val messageId: String,

    /* Internal domain id */
    open var internalId: String? = null,

    open val messageType: StandardMessageType = StandardMessageType.Standard,

    /* The type of the payload that this message contains */
    open val payloadType: String = StandardPayloadTypeNames.TextMessage,

    /* The time of sending the message */
    open val senderId: Identifiable<User>,

    /* The minimum field that must be set in the list of message data that is provided.
       For non-text messages, this must contain the text-representation of the contained data.
       This field is presented because a text-message is the minimum and the only required
       implementation for any mitter client.
     */
    open val textPayload: String,

    /* A list of different types of representations for this message */
    open val messageData: List<MessageDatum> = emptyList(),

    /* A list of associated receipts */
    open val timelineEvents: List<TimelineEvent>,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    override val appliedAcls: AppliedAclList = emptyAclList(),

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    override var entityMetadata: EntityMetadata = emptyEntityMetadata(),
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    override var auditInfo: AuditInfo? = null
) : MitterDomainEntity<Message>, AclEntity<Message>, Auditable {
    override fun domainId() = messageId
    override fun internalId(): String? = internalId
    override fun type() = Message::class.java


    override fun toString(): String {
        return "MessageBase(messageId='$messageId', messageType=$messageType, payloadType='$payloadType'," +
            " senderId=$senderId, textPayload='$textPayload', messageData=$messageData," +
            " timelineEvents=$timelineEvents, appliedAcls=$appliedAcls, entityMetadata=$entityMetadata)"
    }
}

data class Message(
    override val messageId: String,
    override var internalId: String? = null,
    override val messageType: StandardMessageType = StandardMessageType.Standard,
    override val payloadType: String = StandardPayloadTypeNames.TextMessage,
    override val senderId: Identifiable<User>,
    override val textPayload: String,
    override val messageData: List<MessageDatum> = emptyList(),
    override val timelineEvents: List<TimelineEvent>,
    override val appliedAcls: AppliedAclList = emptyAclList(),
    override var entityMetadata: EntityMetadata = emptyEntityMetadata(),
    override var auditInfo: AuditInfo? = null
) : MessageBase(
    messageId, internalId, messageType, payloadType, senderId, textPayload,
    messageData, timelineEvents, appliedAcls, entityMetadata, auditInfo
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun constructionSupport(
            @JsonProperty("messageId") messageId: String?,
            @JsonProperty("messageType") messageType: StandardMessageType?,
            @JsonProperty("payloadType") payloadType: String?,
            @JsonProperty("senderId") senderId: Identifiable<User>,
            @JsonProperty("textPayload") textPayload: String,
            @JsonProperty("messageData") messageData: List<MessageDatum>?,
            @JsonProperty("timelineEvents") timelineEvents: List<TimelineEvent>?,
            @JsonProperty("appliedAcls") appliedAcls: AppliedAclList?,
            @JsonProperty("entityMetadata") entityMetadata: EntityMetadata?
        ) = Message(
            messageId = messageId ?: IdGenerator.generateDomainId(Message::class.java),
            messageType = messageType ?: StandardMessageType.Standard,
            payloadType = payloadType ?: StandardPayloadTypeNames.TextMessage,
            senderId = senderId,
            textPayload = textPayload,
            messageData = messageData ?: emptyList(),
            timelineEvents = timelineEvents ?: emptyList(),
            appliedAcls = appliedAcls ?: emptyAclList(),
            entityMetadata = entityMetadata ?: emptyEntityMetadata()
        )
    }
}

data class ChannelReferencingMessage(
    val channelId: Identifiable<Channel>,
    override val messageId: String,
    override var internalId: String? = null,
    override val messageType: StandardMessageType,
    override val payloadType: String,
    override val senderId: Identifiable<User>,
    override val textPayload: String,
    override val messageData: List<MessageDatum>,
    override val timelineEvents: List<TimelineEvent>,
    override val appliedAcls: AppliedAclList,
    override var entityMetadata: EntityMetadata,
    override var auditInfo: AuditInfo? = null
) : MessageBase(
    messageId, internalId, messageType, payloadType, senderId, textPayload,
    messageData, timelineEvents, appliedAcls, entityMetadata, auditInfo
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun constructionSupport(
            @JsonProperty("channelId")      channelId: Identifiable<Channel>,
            @JsonProperty("messageId")      messageId: String?,
            @JsonProperty("messageType")    messageType: StandardMessageType?,
            @JsonProperty("payloadType")    payloadType: String?,
            @JsonProperty("senderId")       senderId: Identifiable<User>,
            @JsonProperty("textPayload")    textPayload: String,
            @JsonProperty("messageData")    messageData: List<MessageDatum>?,
            @JsonProperty("timelineEvents") timelineEvents: List<TimelineEvent>?,
            @JsonProperty("appliedAcls")    appliedAcls: AppliedAclList?,
            @JsonProperty("entityMetadata") entityMetadata: EntityMetadata?
        ) = ChannelReferencingMessage(
            channelId = channelId,
            messageId = messageId ?: IdGenerator.generateDomainId(ChannelReferencingMessage::class.java),
            messageType = messageType ?: StandardMessageType.Standard,
            payloadType = payloadType ?: StandardPayloadTypeNames.TextMessage,
            senderId = senderId,
            textPayload = textPayload,
            messageData = messageData ?: emptyList(),
            timelineEvents = timelineEvents ?: emptyList(),
            appliedAcls = appliedAcls ?: emptyAclList(),
            entityMetadata = entityMetadata ?: emptyEntityMetadata()
        )
    }

    override fun toString(): String {
        return "ChannelReferencingMessage(channelId=$channelId) ${super.toString()}"
    }
}

fun Message.referencing(channelId: Identifiable<Channel>) = ChannelReferencingMessage(
    channelId = channelId,
    messageId = messageId,
    messageType = messageType,
    payloadType = payloadType,
    senderId = senderId,
    textPayload = textPayload,
    messageData = messageData,
    timelineEvents = timelineEvents,
    appliedAcls = appliedAcls,
    entityMetadata = entityMetadata,
    auditInfo = auditInfo
)

fun ChannelMessage.flatten() = this.message.referencing(this.channelId)
