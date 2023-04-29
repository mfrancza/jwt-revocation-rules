package com.mfrancza.jwtrevocation.rules.conditions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val STRING_IN_OPERATION = "in"

@Serializable
@SerialName(STRING_IN_OPERATION)
data class StringIn (val value: Set<String>) : StringCondition() {
    override fun isMet(value: String?): Boolean = (this.value.contains(value))
}