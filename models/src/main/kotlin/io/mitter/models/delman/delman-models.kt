package io.mitter.models.delman

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
enum class DeliveryStatus {
    Success,
    PermanentFailure,
    RetryableFailure,
    Indeterminate
}

data class DeliveryResult(
    @JsonProperty("deliveryStatus")
    val deliveryStatus: DeliveryStatus
)