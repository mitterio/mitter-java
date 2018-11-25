package io.mitter.named.resources.http.endpoints

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object DomainEntityEndpoints {
    const val Base = EndpointsV1.VersionPrefix + "/{entityType}"
    const val EntityTypeParam = "entityType"
    const val EntityIdParam   = "entityId"

    object Specified {
        const val Base = DomainEntityEndpoints.Base + "/{entityId}"
    }

    object Acolyte {
        object AclOperations {
            const val ForEntityId = DomainEntityEndpoints.Specified.Base + "/acls"
        }
    }
}
