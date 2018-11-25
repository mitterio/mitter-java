package io.mitter.named.resources.http.endpoints

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object SubscriberEndpointsV1 {
    object Subscribers {
        const val Base = EndpointsV1.VersionPrefix + "/subscribers"
        const val SubscriberEmailParam = "subscriberEmail"
        const val SubscriberIdParam = "subscriberId"

        object Specified {
            const val Me = "$Base/me"
        }

        object Auth {
            const val Logout = "$Base/logout"
        }

        object Internal {
            const val FederatedAuthRequest = "$Base/federated"
        }

        object Password {
            object Specified {
                const val ResetByEmail = Subscribers.Base + "/{subscriberEmail}/password/reset"
                const val Base         = Subscribers.Base + "/{subscriberId}/password"
            }
        }

        object Token {
            const val Base = Subscribers.Base + "/token"
        }

        object ApiAccess {
            const val AccessKeyIdParam = "accessKeyId"

            const val Base = Subscribers.Specified.Me + "/api-access"
            const val Specified  = "$Base/{$AccessKeyIdParam}"
        }

        object Ancillary {
            const val Signup = Subscribers.Base + "/signup"
        }
    }
}
