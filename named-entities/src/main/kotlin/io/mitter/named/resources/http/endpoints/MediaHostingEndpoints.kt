package io.mitter.named.resources.http.endpoints

object MediaHostingEndpoints {
    const val ApplicationIdParam = "applicationId"

    const val Base = EndpointsV1.VersionPrefix + "/media"

    object Specified {
        const val ByMediaReference = "$Base/{applicationId}/{mediaStorageKey}"
    }
}
