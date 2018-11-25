package io.mitter.sdk.java.utils

import io.mitter.data.domain.user.ScreenName
import io.mitter.data.domain.user.User

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterUsersHelper {
    companion object {
        @JvmStatic
        fun newUser(userId: String, screenName: String) = User().apply {
            this.userId = userId
            this.screenName = ScreenName().apply { this.screenName = screenName }
        }

        @JvmStatic
        fun newUser(screenName: String) = User().apply {
            this.screenName = ScreenName().apply { this.screenName = screenName }
        }
    }
}
