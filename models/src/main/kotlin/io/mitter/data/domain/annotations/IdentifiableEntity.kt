package io.mitter.data.domain.annotations

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
interface IdentifiableEntity<out T: Identifiable<T>> : Identifiable<T>
