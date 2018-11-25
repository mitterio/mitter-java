package io.mitter.models.acolyte

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.reflect.KClass

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object StandardGlobalPrivileges {
    const val ModifyAclPrivilege = "modify_acl"

    val values = listOf(ModifyAclPrivilege)
}
annotation class PrivilegeName(
    val value : String
)

annotation class SelectorName(
    val value : String
)

abstract class AclPrivilege<out E:AclEntity<E>>(
    val applicableEntity: KClass<out E>
) {
    val privilegeName = this.javaClass.getAnnotation(PrivilegeName::class.java).value
}

@PrivilegeName(StandardGlobalPrivileges.ModifyAclPrivilege)
class ModifyAclPrivilege : AclPrivilege<AclEntity<*>>(AclEntity::class) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ModifyAclPrivilege) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

abstract class AclAccessorSelector {
    private val selectorName = this.javaClass.getAnnotation(SelectorName::class.java).value

    abstract fun serialize() : String

    fun representation() = "$selectorName(${serialize()})"
}

data class AppliedAcl(
    val aclPrivilege: AclPrivilege<*>,
    val accessorSelector: AclAccessorSelector
) {
    fun serialize() = "${aclPrivilege.privilegeName}:${accessorSelector.representation()}"
}

operator fun AppliedAclList.plus(other: AppliedAclList) : AppliedAclList {
    return AppliedAclList(
        plusAppliedAcls = this.plusAppliedAcls + other.plusAppliedAcls,
        minusAppliedAcls = this.minusAppliedAcls + other.minusAppliedAcls
    )
}

data class AppliedAclList(
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val plusAppliedAcls: List<AppliedAcl>,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val minusAppliedAcls: List<AppliedAcl>
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun constructionSupport(
            @JsonProperty("plusAppliedAcls")
            plusAppliedAcls: List<AppliedAcl>?,

            @JsonProperty("minusAppliedAcls")
            minusAppliedAcls: List<AppliedAcl>?
        ) = AppliedAclList(
                plusAppliedAcls = plusAppliedAcls ?: emptyList(),
                minusAppliedAcls = minusAppliedAcls ?: emptyList()
        )
    }
}

fun emptyAclList() = AppliedAclList(emptyList(), emptyList())

fun AppliedAclList.toAclDescriptor() : String =
    (this.plusAppliedAcls.map { "+" to it } + this.minusAppliedAcls.map { "-" to it })
        .joinToString(",") {
            "${it.first}${it.second.serialize()}"
        }
