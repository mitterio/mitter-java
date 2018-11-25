package io.mitter.support.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.mitter.models.acolyte.AclPrivilegeKt;
import io.mitter.models.acolyte.AppliedAclList;

import java.io.IOException;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class DummyAppliedAclListDeserializer extends JsonDeserializer<AppliedAclList> {
    @Override
    public AppliedAclList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonToken currentToken = jsonParser.currentToken();
        int i;

        if (currentToken == JsonToken.START_OBJECT) {
            i = 1;
        } else {
            throw new JsonParseException(jsonParser, "An applied acl list must be an object");
        }

        while (i > 0) {
            currentToken = jsonParser.nextToken();

            if (currentToken == JsonToken.START_OBJECT) {
                i++;
            } else if (currentToken == JsonToken.END_OBJECT) {
                i--;
            }
        }

        return AclPrivilegeKt.emptyAclList();
    }
}
