package io.mitter.models.acolyte

import io.mitter.data.domain.annotations.MitterDomainEntity

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
interface AclEntity<out T: AclEntity<T>> : MitterDomainEntity<T> {
    val appliedAcls : AppliedAclList
}
