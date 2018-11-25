package io.mitter.support.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.annotations.IdentifiableEntity;

import java.io.IOException;

/**
 * A JSON serializer for {@link Identifiable} types. This class uses the default bean serializer for any classes that
 * implement {@link IdentifiableEntity}, serializing specially only those which implement only the {@link Identifiable}
 * interface.
 *
 * This is important as this serializer serializes based on the presence of a single method, and any other information
 * that may be present in a subclass otherwise will be lost.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class IdentifiableSerializer extends StdSerializer<Object> {
    final static String IDENTIFIER_KEY_NAME = "identifier";

    /**
     * @param t The class as required by {@link StdSerializer}
     */
    @SuppressWarnings("unchecked")
    private IdentifiableSerializer(Class<Object> t) {
        super(t);
    }

    public IdentifiableSerializer() {
        this(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Object identifiable, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(IDENTIFIER_KEY_NAME, ((Identifiable) identifiable).domainId());
        gen.writeEndObject();
    }
}
