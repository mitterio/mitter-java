package io.mitter.data.domain.media

import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.annotations.IdentifiableEntity
import io.mitter.data.domain.annotations.Identifier
import io.mitter.data.domain.application.Application

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MediaReference(
    mediaKey: String,
    val mediaMimeType: String,
    val representationType: String,
    val mediaStorage: MediaStorage,
    var mediaStoreLocator: String? = null,
    val impressionPending: Boolean = false
) : IdentifiableEntity<MediaReference> {
    override fun type(): Class<out MediaReference> {
        return this.javaClass
    }

    override fun domainId(): String {
        return mediaKey
    }

    override fun internalId(): String? {
        return mediaKey
    }

    lateinit var applicationId: Identifiable<Application>

    val mediaKey = mediaKey
        @Identifier get
}
