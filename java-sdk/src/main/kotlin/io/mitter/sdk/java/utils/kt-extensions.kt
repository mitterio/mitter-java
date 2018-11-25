package io.mitter.sdk.java.utils

import io.mitter.data.domain.annotations.EntityIdentifierHolder
import io.mitter.data.domain.annotations.Identifiable

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
inline fun <reified T:Identifiable<T>> String.identifiable() : Identifiable<T> =
    EntityIdentifierHolder(this, T::class.java)
