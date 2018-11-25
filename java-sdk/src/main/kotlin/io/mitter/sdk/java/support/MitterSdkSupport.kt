package io.mitter.sdk.java.support

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.acolyte.AppliedAcl
import io.mitter.models.acolyte.AppliedAclList
import io.mitter.support.json.DummyAppliedAclListDeserializer
import io.mitter.support.json.IdentifiableBeanSerializableModifier
import io.mitter.support.json.IdentifiableDeserializer

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterSdkSupport {
    companion object {
        @JvmStatic
        fun configureJackson(objectMapper: ObjectMapper) : ObjectMapper {
            val objectMapperConfigModule = SimpleModule()

            objectMapperConfigModule.setSerializerModifier(IdentifiableBeanSerializableModifier())
            objectMapperConfigModule.addDeserializer(Identifiable::class.java, IdentifiableDeserializer())

            objectMapperConfigModule.addSerializer(AppliedAcl::class.java, object : JsonSerializer<AppliedAcl>() {
                override fun serialize(value: AppliedAcl, gen: JsonGenerator, serializers: SerializerProvider) {
                    gen.writeString(value.serialize())
                }
            })

            objectMapperConfigModule.addDeserializer(AppliedAclList::class.java, DummyAppliedAclListDeserializer())

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

            objectMapper.registerModule(objectMapperConfigModule)

            return objectMapper
        }
    }
}
