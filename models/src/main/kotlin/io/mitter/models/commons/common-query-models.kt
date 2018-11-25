package io.mitter.models.commons

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object PatchType {
    const val Set  = "Set"
    const val Diff = "Diff"
}

data class PatchResult<T>(
    val oldValue: T?,
    val newValue: T
)
