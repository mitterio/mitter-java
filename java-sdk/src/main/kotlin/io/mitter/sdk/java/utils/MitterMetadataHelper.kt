package io.mitter.sdk.java.utils

import io.mitter.data.domain.entity.Metadata
import io.mitter.models.utils.toJson

class MitterMetadataHelper {
    companion object {
        @JvmStatic
        fun metadataQuery(
                metadata: List<Metadata>
        ) = mutableMapOf<String, Any>().apply {
            put("metadata", metadata.toJson().toString())
        }

        @JvmStatic
        fun shouldFetchMetadataFlag() = mutableMapOf<String, Any>().apply {
            put("shouldFetchMetadata", true)
        }
    }
}