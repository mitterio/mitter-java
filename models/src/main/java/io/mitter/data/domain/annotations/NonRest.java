package io.mitter.data.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to denote controller methods that explicitly do not follow the REST paradigm.
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface NonRest {
    String value() default "";
}
