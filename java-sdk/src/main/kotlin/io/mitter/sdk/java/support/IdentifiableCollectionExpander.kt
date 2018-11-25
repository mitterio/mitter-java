package io.mitter.sdk.java.support

import feign.Param

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class IdentifiableCollectionExpander : Param.Expander {
    private val identifiableExpander = IdentifiableExpander()

    override fun expand(p0: Any): String {
        if (p0 is Collection<*>) {
            return p0.joinToString(",") { identifiableExpander.expand(it!!) }
        }

        throw IllegalArgumentException("This expander needs a collection to expand")
    }
}