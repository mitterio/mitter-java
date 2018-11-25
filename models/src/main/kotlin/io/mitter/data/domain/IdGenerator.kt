package io.mitter.data.domain

import org.apache.commons.lang3.RandomStringUtils
import java.util.*

object IdGenerator {
    @JvmStatic
    fun generateDomainId(entity: Class<*>? = null): String {
        return RandomStringUtils.randomAlphanumeric(5) + "-" +
            RandomStringUtils.randomAlphanumeric(5) + "-" +
            RandomStringUtils.randomAlphanumeric(5) + "-" +
            RandomStringUtils.randomAlphanumeric(5)
    }

    @JvmStatic
    fun generateInternalId(entity: Class<*>): String {
        return generateUuid()
    }

    @JvmStatic
    fun generateFastId(length: Int = 16) = RandomStringUtils.random(length, true, true)

    @JvmStatic
    fun generateUuid() = UUID.randomUUID().toString()
}