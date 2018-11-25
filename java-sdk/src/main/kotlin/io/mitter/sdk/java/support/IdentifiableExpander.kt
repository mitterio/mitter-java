package io.mitter.sdk.java.support

import feign.Param
import io.mitter.data.domain.annotations.Identifiable

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class IdentifiableExpander : Param.Expander {
    override fun expand(p0: Any): String {
        if (p0 is Identifiable<*>) {
            return try {
                p0.domainId()
            } catch (e: IllegalStateException) {
                ""
            }
        } else {
            throw IllegalArgumentException("Cannot expand non-identifiable")
        }
    }
}