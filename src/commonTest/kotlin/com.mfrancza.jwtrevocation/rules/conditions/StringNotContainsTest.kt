package com.mfrancza.jwtrevocation.rules.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringNotContainsTest {

    @Test
    fun testNotContains() {
        val containsCondition = StringNotContains(
            "required"
        )

        assertFalse(containsCondition.isMet("!required!"), "A value containing the string does not meet it")

        assertTrue(containsCondition.isMet("missing"), "A value which does not contain the string does meet it")
        
        assertTrue(containsCondition.isMet(null), "Null does meet it")
    }
}