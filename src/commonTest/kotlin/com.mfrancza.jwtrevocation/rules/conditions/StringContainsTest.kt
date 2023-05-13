package com.mfrancza.jwtrevocation.rules.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringContainsTest {

    @Test
    fun testContains() {
        val containsCondition = StringContains(
            "bad"
        )

        assertTrue(containsCondition.isMet("goodbadgood"), "A value containing the string meets it")

        assertFalse(containsCondition.isMet("goodgoodgood"), "A value which does not contain the string does not meet it")

        assertFalse(containsCondition.isMet(null), "Null does not meet it")
    }
}