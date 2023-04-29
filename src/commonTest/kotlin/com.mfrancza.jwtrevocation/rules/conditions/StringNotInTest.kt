package com.mfrancza.jwtrevocation.rules.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringNotInTest {
    @Test
    fun testNotIn() {
        val notInConditionWithValues = StringNotIn(
            setOf("GoodValue1", "GoodValue2", "GoodValue3")
        )
        val notInConditionWithEmptySet = StringNotIn(
            emptySet()
        )

        notInConditionWithValues.value.forEach {
            assertFalse(
                notInConditionWithValues.isMet(it),
                "The values in the set do not meet it")
        }
        assertTrue(notInConditionWithValues.isMet("BadValue"),
            "A value not in the set does meet it")
        assertTrue(
            notInConditionWithValues.isMet(null),
            "A null value meets a not in set condition")
        assertTrue(
            notInConditionWithEmptySet.isMet("AnyValue"),
            "Any string value does meet the not in empty set condition")
        assertTrue(
            notInConditionWithEmptySet.isMet(null),
            "A null value does meet the not in empty set condition")
    }
}