package io.mitter.named.resources.http.endpoints

object EntityMetadataEndpointsV1 {
    const val Base = EndpointsV1.VersionPrefix

    const val EntityTypeParam = "entityType"
    const val EntityIdParam = "entityId"

    const val MetadataKeyParam = "key"

    object Specified {
        const val ByEntityType = "$Base/{entityType}"
        const val ByEntityTypeAndId = "$ByEntityType/{entityId}/metadata"
        const val ByMetadataKey = "$ByEntityTypeAndId/{key}"
    }
}