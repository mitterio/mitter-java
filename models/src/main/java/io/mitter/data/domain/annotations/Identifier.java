package io.mitter.data.domain.annotations;

import java.lang.annotation.*;

/**
 * This is a documentation-only annotation, which can be used for the purposes of code-inspection,
 * AOP or Proxying.
 *
 * This is intended to be used on the field or the getter of the field which serves as a public domainId. DO NOT
 * use this for internal ids or for database row ids. Only use this for entities which are publically adressable.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface Identifier {
}
