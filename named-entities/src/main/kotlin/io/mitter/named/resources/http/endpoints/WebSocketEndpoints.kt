package io.mitter.named.resources.http.endpoints

object WebSocketEndpoints {
    const val Base = EndpointsV1.VersionPrefix + "/socket"

    object SubscriptionPoints {
        const val EventStream = "/queue/event-stream"
    }

    object Support {
        const val SockJsControl = "$Base/control/sockjs"
    }
}
