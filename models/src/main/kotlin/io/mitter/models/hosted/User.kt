package io.mitter.models.hosted

import io.mitter.data.domain.user.ScreenName
import io.mitter.data.domain.user.User

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
//FIXME This needs to be removed, but cannot because client apps are deployed with it
data class GetUsersScreenNameResponse (
        val screenNames: List<ScreenName>? = null
)

data class PutUserScreenNameRequest(
        val screenName: String? = null
)
