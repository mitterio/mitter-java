package io.mitter.data.domain.support;

/**
 * For Java8-like features in Java7
 *
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public interface Function<T, R> {
    R apply(T t);
}
