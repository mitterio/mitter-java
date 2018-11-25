package io.mitter.models.mardle.accesscontrol

import io.mitter.models.acolyte.AclAccessorSelector
import io.mitter.models.acolyte.SelectorName

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
fun AclAccessorSelector.stringify() : String {
    return if (this::class.java.getAnnotation(SelectorName::class.java) != null) {
        "${this::class.java.getAnnotation(SelectorName::class.java).value}(${this.serialize()})"
    } else {
        "(${this.serialize()})"
    }
}