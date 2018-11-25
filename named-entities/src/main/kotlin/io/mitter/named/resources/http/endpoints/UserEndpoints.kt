package io.mitter.named.resources.http.endpoints

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object UserEndpointsV1 {
    const val Base = EndpointsV1.VersionPrefix + "/users"
    const val UserIdParam  = "userId"
    const val UserIdsParam = "userIds"

    object Specified {
        const val ByUserId  = Base + "/{userId}"
        const val ByParticipantId = Base + "/{participantId}"
        const val ByUserIds = Base + "/{userIds}"
        const val Me        = Base + "/me"
        const val ByLocators = Base + "?locators={locators}"
        const val locatorsParam = "locators"
    }

    object ScreenName {
        const val ByUserIds = UserEndpointsV1.Specified.ByUserIds + "/screenname"
        const val ByUserId  = UserEndpointsV1.Specified.ByUserId + "/screenname"
    }

    object Locators {
        const val LocatorIdParam = "locatorId"

        const val ByUserId  = UserEndpointsV1.Specified.ByUserId + "/locators"
        const val ForMe    = UserEndpointsV1.Specified.Me + "/locators"

        object Specified {
            const val ByLocatorId = UserEndpointsV1.Locators.ByUserId + "/{locatorId}"
        }
    }

    object MetaData {
        const val ForUserId = UserEndpointsV1.Specified.ByUserId + "/metadata"
    }

    object Tokens {
        const val TokenIdsParam = "tokenIds"

        const val Base      = UserEndpointsV1.Base + "/tokens"
        const val ForUserId = UserEndpointsV1.Specified.ByUserId + "/tokens"
        const val ForMe     = UserEndpointsV1.Specified.Me       + "/tokens"

        object Specified {
            const val ForMe     = Tokens.ForMe + "/{tokenIds}"
            const val ForUserId = Tokens.ForUserId + "/{tokenIds}"
        }

        object Ancillary {
            const val Logout    = UserEndpointsV1.Specified.Me + "/logout"
        }
    }

    object DeliveryEndpoints {
        const val SerializedEndpointParam = "serializedEndpoint"

        const val ForMe = UserEndpointsV1.Specified.Me + "/delivery-endpoints"
        const val ForUserId = UserEndpointsV1.Specified.ByUserId + "/delivery-endpoints"

        object Specified {
            const val ForMeByEndpoint     = DeliveryEndpoints.ForMe +     "/{serializedEndpoint}"
            const val ForUserIdByEndpoint = DeliveryEndpoints.ForUserId + "/{serializedEndpoint}"
        }
    }

    object Channels {
        const val ForUserId = UserEndpointsV1.Specified.ByUserId + "/channels"
        const val ForMe     = UserEndpointsV1.Specified.Me + "/channels"
    }
}
