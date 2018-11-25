package io.mitter.models.auth.issuance.entities

import io.mitter.models.auth.issuance.TokenIssuable
import io.mitter.data.domain.annotations.IdUtils
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.data.domain.user.User
import io.mitter.models.auth.issuance.StandardTokenIssuables

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class UserTokenIssuable(userId: Identifiable<User>, appId: Identifiable<Application>) :
        TokenIssuable<User>(
                userId.domainId() + UserIdAppSeparator + appId.domainId(),
                User::class.java,
                StandardTokenIssuables.User
) {
    companion object {
        private const val UserIdAppSeparator = "/"
    }

    constructor(serializedId: String): this(IdUtils.of(serializedId.split(UserIdAppSeparator)[0], User::class.java),
            IdUtils.of(serializedId.split(UserIdAppSeparator)[1], Application::class.java))

    val userId: Identifiable<User>
            get() = IdUtils.of(this.entityId.split(UserIdAppSeparator)[0], User::class.java)

    val appId: Identifiable<Application>
            get() = IdUtils.of(this.entityId.split(UserIdAppSeparator)[1], Application::class.java)
}
