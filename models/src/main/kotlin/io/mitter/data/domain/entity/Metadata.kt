package io.mitter.data.domain.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.JsonNode

interface MetadataAttachable {
    var entityMetadata: EntityMetadata
}

fun emptyEntityMetadata() = EntityMetadata()

data class EntityMetadata(
    val metadata: List<Metadata> = emptyList()
)

data class Metadata(
    @JsonProperty("key")
    val key: String,
    @JsonProperty("value")
    val value: MetadataValue
)

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes(
        JsonSubTypes.Type(NumericMetadataValue::class, name = "numeric"),
        JsonSubTypes.Type(StringMetadataValue::class, name = "string"),
        JsonSubTypes.Type(JsonMetadataValue::class, name = "json")
)
sealed class MetadataValue

data class NumericMetadataValue(
    @JsonProperty("number")
    val number: Number
) : MetadataValue()

data class StringMetadataValue(
    @JsonProperty("text")
    val text: String
) : MetadataValue()

data class JsonMetadataValue(
    @JsonProperty("json")
    val json: JsonNode
) : MetadataValue()

data class MetadataQuery(
    var key: String,
    var value: MetadataValue
)
