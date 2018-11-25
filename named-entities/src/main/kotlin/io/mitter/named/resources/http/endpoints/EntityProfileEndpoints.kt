package io.mitter.named.resources.http.endpoints

/**
 * @author Ankush Kumar (ankush@mitter.io)
 */
object EntityProfileEndpointsV1 {
    ///v1/applications/{applicationId}

    const val AttributeKeyParam: String = "key"
    const val AttributeKeysParam: String = "keys"
    const val EntityTypeParam = "entityType"
    const val EntityIdParam = "entityId"
    const val DefaultQueryParam = "*"

    const val Base = EndpointsV1.VersionPrefix + "/{entityType}"

    object AttributeDef {
        const val Base = EndpointsV1.VersionPrefix + "/attribute-def" + "/{entityType}"

        object Specified {
            const val ByAttributeKey = Base + "/{key}"
            const val ByAttributeKeys = Base + "/{keys}"
        }
    }

    object Profile {
        const val Base = EntityProfileEndpointsV1.Base + "/{entityId}" + "/profile"

        object Specified {
            const val ByAttributeKey = Base + "/{key}"
            const val ByAttributeKeys = Base + "/{keys}"
        }
    }
}