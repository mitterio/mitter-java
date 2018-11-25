package io.mitter.models.services.tokenstate

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class SingleActionToken(
    val tokenId: String?,
    val token: String,
    val action: String?,
    val entity: String?
)
