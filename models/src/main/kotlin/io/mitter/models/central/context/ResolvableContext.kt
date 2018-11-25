package io.mitter.models.central.context

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ResolvableContext(val value: String)