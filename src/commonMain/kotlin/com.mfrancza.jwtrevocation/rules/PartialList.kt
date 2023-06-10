package com.mfrancza.jwtrevocation.rules

import kotlinx.serialization.Serializable

/**
 * A segment of paginated data
 */
@Serializable
data class PartialList<T>(
    /**
     * The list of items in the segment
     */
    val list: List<T>,
    /**
     * A value indicating the start of the next segment of data
     */
    val cursor: String?
)