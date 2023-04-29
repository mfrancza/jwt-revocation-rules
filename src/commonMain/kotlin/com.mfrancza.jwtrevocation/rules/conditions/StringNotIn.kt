package com.mfrancza.jwtrevocation.rules.conditions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val STRING_NOT_IN_OPERATION = "not in"

@Serializable
@SerialName(STRING_NOT_IN_OPERATION)
data class StringNotIn (val value: Set<String>) : StringCondition() {
    override fun isMet(value: String?): Boolean = (!this.value.contains(value))
}