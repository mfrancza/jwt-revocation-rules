package com.mfrancza.jwtrevocation.rules.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringInTest {
    @Test
    fun testIn() {
        val inConditionWithValues = StringIn(
            setOf("BadValue1", "BadValue2", "BadValue3")
        )
        val inConditionWithEmptySet = StringIn(
            emptySet()
        )

        inConditionWithValues.value.forEach {
            assertTrue(
                inConditionWithValues.isMet(it),
                "The values in the set meet it")
        }
        assertFalse(inConditionWithValues.isMet("GoodValue"),
            "A value not in the set does not meet it")
        assertFalse(
            inConditionWithValues.isMet(null),
            "A null value does not meet it")
        assertFalse(
            inConditionWithEmptySet.isMet("AnyValue"),
            "A string value does not meet an empty set condition")
        assertFalse(
            inConditionWithEmptySet.isMet(null),
            "A null value does not meet meets an empty set condition")
    }
}