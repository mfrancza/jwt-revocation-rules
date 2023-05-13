package com.mfrancza.jwtrevocation.rules.conditions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val STRING_CONTAINS_OPERATION = "contains"

@Serializable
@SerialName(STRING_CONTAINS_OPERATION)
data class StringContains (val value: String) : StringCondition() {
    override fun isMet(value: String?): Boolean = (value != null && value.contains(this.value))
}