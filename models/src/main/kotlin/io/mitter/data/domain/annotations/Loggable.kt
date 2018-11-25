package io.mitter.data.domain.annotations

import org.slf4j.event.Level

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@Retention(AnnotationRetention.RUNTIME)
annotation class Loggable(
    val logLevel : Level
)
