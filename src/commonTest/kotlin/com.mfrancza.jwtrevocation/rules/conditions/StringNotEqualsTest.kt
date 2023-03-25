package com.mfrancza.jwtrevocation.rules.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringNotEqualsTest {
    @Test
    fun testEquals() {
        val equalsConditionWithValue = StringNotEquals(
            value = "GoodValue"
        )
        val equalsConditionWithNull = StringNotEquals(
            value = null
        )
        assertFalse(
            equalsConditionWithValue.isMet(equalsConditionWithValue.value),
            "The same value does not meets it")
        assertTrue(equalsConditionWithValue.isMet(
            "BadValue"),
            "A different value does meet it")
        assertTrue(
            equalsConditionWithValue.isMet(null),
            "A null value does meet a non-null value condition")
        assertTrue(
            equalsConditionWithNull.isMet("AnyValue"),
            "A non-null value meets a null value condition")
        assertFalse(
            equalsConditionWithNull.isMet(null),
            "A null value does not meet a null value condition")
    }
}