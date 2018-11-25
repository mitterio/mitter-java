package io.mitter.data.domain.annotations;

import io.mitter.data.domain.support.Function;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Utilities for creation and management of {@link Identifiable} entities.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class IdUtils {
    /**
     * Extract an domainId from an object. It searches the object for a getter method with the {@link Identifier}
     * annotation, if not found it then looks for a field with the {@link Identifier} annotation set and gets the value
     * either by getting it from the field or looking for an appropriate getter.
     *
     * @param entity The entity to extract the domainId from it
     * @return Returns the domainId from the object, or null if not found
     */
    static String extractIdentifier(final Object entity) {
        String identifier = scanAnnotations(entity.getClass().getMethods(), new Function<Method, String>() {
            @Override
            public String apply(Method method) {
                return extract(method, entity);
            }
        });

        if (identifier == null) {
            identifier = scanAnnotations(entity.getClass().getFields(), new Function<Field, String>() {
                @Override
                public String apply(Field field) {
                    return extract(field, entity);
                }
            });
        }

        return identifier;
    }

    /**
     * Do not use this method unless there is absolutely no way to abide by the type system
     *
     * @param identifier
     * @param <T>
     * @return
     */
    public static <T extends Identifiable<T>> Identifiable<T> of(String identifier) {
        return new EntityIdentifierHolder<>(identifier, null);
    }

    public static <T extends Identifiable<T>> Identifiable<T> simpleIdentifiable(Identifiable<T> identifiable) {
        return IdUtils.of(identifiable.domainId());
    }

    /**
     * Generate an {@link Identifiable}. Allows to explicitly mention the type of the {@link Identifiable} in question
     * for enforcing type-safety. As much as possible, it is recommended to use this method.
     *
     * @param domainId The string domainId to create an {@link Identifiable} object from
     * @param clazz The class of the object being referred to
     * @param <T> The type of the object being referred to
     * @return  An {@link Identifiable} with the provided id
     */
    public static <T extends Identifiable<T>> Identifiable<T> of(String domainId, Class<T> clazz) {
        return new EntityIdentifierHolder<>(domainId, clazz);
    }

    public static <T extends Identifiable<T>> Identifiable<T> of(String domainId, String internalId, Class<T> clazz) {
        return new EntityIdentifierHolder<>(domainId, clazz)
                        .setInternalId(internalId);
    }

    /**
     * Extract a string value from a particular field on an object, either by directly getting the value (if accessible)
     * or by calling an appropriate getter method (a method with the name "get" + field-name).
     *
     * @param field The field to fetch the value from
     * @param entity The object whose field is being looked up
     * @return The field value
     */
    private static String extract(Field field, Object entity) {
        if (field.isAccessible()) {
            try {
                return (String) field.get(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        for (Method method : entity.getClass().getMethods()) {
            if (method.getName().startsWith("get")
                    && (method.getName().length() == field.getName().length() + 3)
                    && (method.getName().equals("get" + field.getName()))
                    && (method.getParameterTypes().length == 0)) {
                return extract(method, entity);
            }
        }

        return "";
    }

    /**
     * Get the string return value of a method on an object
     *
     * @param method The method to call to fetch the string value
     * @param entity The object to call the method on
     * @return The return value of the method
     */
    private static String extract(Method method, Object entity) {
        try {
            return (String) method.invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Search annotations on a list of elements for a particular annotation and execute a particular action on it.
     *
     * @param scanTarget The list of elements to search for annotations
     * @param action The action to execute on the found element
     * @param <R> The return type of the action
     * @param <T> The type of the elements in the list that is to be scanned
     * @return The return value of the applied action
     */
    private static <R, T extends AnnotatedElement> R scanAnnotations(T[] scanTarget, Function<T, R> action) {
        for (T target : scanTarget) {
            Annotation annotation = target.getAnnotation(Identifier.class);

            if (annotation != null) {
                return action.apply(target);
            }
        }

        return null;
    }
}
