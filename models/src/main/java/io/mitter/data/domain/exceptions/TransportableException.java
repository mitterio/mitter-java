package io.mitter.data.domain.exceptions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that denotes whether an Exception can be transported over the wire
 *
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransportableException {
    String  errorCode()            default StandardTransportationExceptionValues.ErrorCode;
    int     httpStatus()           default StandardTransportationExceptionValues.StatusCode;
    String  genericMessage()       default StandardTransportationExceptionValues.ErrorDescription;
    boolean transportableMessage() default false;
}
