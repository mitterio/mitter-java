package io.mitter.data.domain.annotations;

import io.mitter.data.domain.application.properties.StandardApplicationProperty;

import java.lang.annotation.*;

/**
 * The instance name for ApplicationProperty
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface ApplicationPropertySystemName {
    String value() default StandardApplicationProperty.NonStandardProperty;
}
