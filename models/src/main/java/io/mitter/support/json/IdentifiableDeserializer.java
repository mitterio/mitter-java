package io.mitter.support.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import io.mitter.data.domain.annotations.EntityIdentifierHolder;
import io.mitter.data.domain.annotations.IdUtils;
import io.mitter.data.domain.annotations.Identifiable;

import java.io.IOException;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class IdentifiableDeserializer extends JsonDeserializer<Identifiable<?>> implements ContextualDeserializer {
    private final static String IDENTIFIER_KEY_NAME = IdentifiableSerializer.IDENTIFIER_KEY_NAME;
    private JavaType entityType = null;

    @SuppressWarnings("unchecked")
    @Override
    public Identifiable deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        Class entityRawType = (entityType != null) ? entityType.getRawClass() : null;
        Identifiable id;

        if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
            String identifier = p.getText();
            return new EntityIdentifierHolder(identifier, entityRawType);
        }

        if (p.getCurrentToken() != JsonToken.START_OBJECT) {
            throw new JsonParseException(p, "Expected object start '{' containing domainId string (or a simple string-based field)");
        }

        if (p.nextFieldName(new SerializedString(IDENTIFIER_KEY_NAME))) {
            String textValue = p.nextTextValue();

            if (textValue == null) {
                throw new JsonParseException(p, "Invalid domainId found `null`");
            }

            id = IdUtils.of(textValue, entityRawType);
        } else {
            throw new JsonParseException(p, "Expected field in object with key `" + IDENTIFIER_KEY_NAME + "`");
        }

        if (p.nextToken() != JsonToken.END_OBJECT) {
            throw new JsonParseException(p, "Expected object end '}' after domainId string");
        }

        return id;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
            throws JsonMappingException {
        if (property == null) {
            return this;
        }

        JavaType identifiableType = property.getType();
        JavaType entityType = (identifiableType.containedTypeCount() == 1) ? identifiableType.containedType(0) : null;

        if (entityType == null) {
            return this;
        } else {
            IdentifiableDeserializer identifiableDeserializer = new IdentifiableDeserializer();
            identifiableDeserializer.entityType = entityType;

            return identifiableDeserializer;
        }
    }
}
