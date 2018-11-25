package io.mitter.models.hosted

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.entity.Attribute

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on Mar / 2018
 *
 */

data class PatchEntityProfile(
    @JsonProperty("attributes")
    val attributes: List<Attribute>,
    @JsonProperty("patchType")
    var patchType: String
)
