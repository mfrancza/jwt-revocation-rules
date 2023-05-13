package com.mfrancza.jwtrevocation.rules.conditions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val STRING_NOT_CONTAINS_OPERATION = "not contains"

@Serializable
@SerialName(STRING_NOT_CONTAINS_OPERATION)
data class StringNotContains (val value: String) : StringCondition() {
    override fun isMet(value: String?): Boolean = !(value != null && value.contains(this.value))
}