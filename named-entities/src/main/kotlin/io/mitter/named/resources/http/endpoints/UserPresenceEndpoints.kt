package io.mitter.named.resources.http.endpoints

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
object UserPresenceEndpointsV1 {
    ///v1/users/{userId}
    const val Base = UserEndpointsV1.Specified.ByUserId + "/presence"
}