package io.mitter.models.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.DecimalNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import io.mitter.data.domain.entity.*
import java.math.BigDecimal

fun JsonNode.toMetadata(): List<Metadata> {
    val metadata = mutableListOf<Metadata>()
    this.fields().forEach {
        metadata.add(Metadata(it.key, it.value.toMetadataValue()))
    }
    return metadata
}

fun JsonNode.toMetadataValue(): MetadataValue {
    return when {
        isNumber -> NumericMetadataValue(number = this.numberValue())
        isTextual -> StringMetadataValue(text = textValue())
        else -> JsonMetadataValue(json = this)
    }
}

fun MetadataValue.toJsonNode(): JsonNode {
    return when (this) {
        is NumericMetadataValue ->
            DecimalNode(BigDecimal(this.number.toString()))

        is StringMetadataValue -> TextNode(this.text)
        is JsonMetadataValue -> this.json
    }
}

fun Metadata.intoJson(json: ObjectNode) {
    when (value) {
        is NumericMetadataValue ->
            json.set(key, DecimalNode(BigDecimal((value as NumericMetadataValue).number.toString())))

        is StringMetadataValue ->
            json.set(key, TextNode((value as StringMetadataValue).text))

        is JsonMetadataValue ->
            json.set(key, (value as JsonMetadataValue).json)
    }
}

fun List<Metadata>.toJson(): JsonNode {
    return ObjectMapper().createObjectNode().apply {
        this@toJson.forEach {
            it.intoJson(this)
        }
    }
}

fun Metadata.toQuery(): MetadataQuery = MetadataQuery(key, value)