package io.mitter.support.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.annotations.IdentifiableEntity;
import io.mitter.data.domain.user.User;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class IdentifiableBeanSerializableModifier extends BeanSerializerModifier {
    private JsonSerializer<Object> jsonSerializer = new IdentifiableSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                     BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {
        beanProperties.forEach(new Consumer<BeanPropertyWriter>() {
            @Override
            public void accept(BeanPropertyWriter x) {
                if (Identifiable.class.isAssignableFrom(x.getType().getRawClass()) &&
                        !IdentifiableEntity.class.isAssignableFrom(x.getType().getRawClass())) {
                    x.assignSerializer(jsonSerializer);
                }
            }
        });

        return beanProperties;
    }

    /**
     * So, what we are doing is here is we are using the regular bean serialization for an {@link IdentifiableEntity},
     * and custom serialization for {@link Identifiable}. So, for instance {@code () -> "hello"} will serialize using the custom
     * serializer, but {@link User} will serialize using the default bean serializer.
     * {@inheritDoc}
     */
    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config,
                                              BeanDescription beanDesc,
                                              JsonSerializer<?> serializer) {
        if (Identifiable.class.isAssignableFrom(beanDesc.getBeanClass()) &&
                !IdentifiableEntity.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new IdentifiableSerializer();
        }

        return serializer;
    }
}
