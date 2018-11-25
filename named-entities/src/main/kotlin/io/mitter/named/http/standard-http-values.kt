package io.mitter.named.http

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardHttpHeaders {
    object Authorization {
        const val UserAuthorizationHeader       = "X-Issued-Mitter-User-Authorization"
        const val SubscriberAuthorizationHeader = "X-Issued-Mitter-Subscriber-Authorization"

        const val SudoApplicationIdHeader       = "X-Mitter-Sudo-Application-Id"
        const val SudoUserIdHeader              = "X-Mitter-Sudo-User-Id"

        const val ApplicationAccessKey          = "X-Mitter-Application-Access-Key"
        const val ApplicationAccessKeyLegacy    = "X-Application-Access-Key"
        const val SubscriberAccessKey           = "X-Mitter-Subscriber-Access-Key"
    }

    object Context {
        const val ApplicationIdHeader           = "X-Mitter-Application-Id"
    }

    object Pagination {
        const val EstimatedCountHeader          = "X-Mitter-Paginated-Estimated-Count"
        const val ExposeHeaders                 = "Access-Control-Expose-Headers"
    }
}

object StandardHttpParams {
    object Context {
        const val ApplicationIdQueryParam       = "mitter.x_mitter_application_id"
    }

    object Authorization {
        const val UserAuthorizationQueryParam   = "mitter.x_issued_mitter_user_authorization"
    }

    object DeliveryEndpoints {
        const val LastEventId                   = "mitter.lastEventId"
    }
}

object StandardHttpCookies {
    object Authorization {
        const val UserAuthorizationCookie       = "x_issued_mitter_user_authorization"
    }
}
