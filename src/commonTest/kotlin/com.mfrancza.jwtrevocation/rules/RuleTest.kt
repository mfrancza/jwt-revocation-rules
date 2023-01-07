package com.mfrancza.jwtrevocation.rules

import com.mfrancza.jwtrevocation.rules.conditions.DateTimeBefore
import com.mfrancza.jwtrevocation.rules.conditions.StringEquals
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue


class RuleTest {

    private val referenceEpochSeconds : Long = 1673123605
    @Test
    fun testIsNotMetByReturnsNullIfAllMatch() {
        val rule = Rule(
            ruleExpires = referenceEpochSeconds + 60,
            iss = listOf(
                StringEquals(
                    "bad-iss.mfrancza.com"
                )
            ),
            iat = listOf(
                DateTimeBefore(referenceEpochSeconds)
            )
        )

        assertNull(
            rule.isNotMetBy(Claims(
                iss = "bad-iss.mfrancza.com",
                iat = referenceEpochSeconds - 60 * 60,
                jti = "test-token"
            )),
            "Both of the conditions match, so null should be returned"
        )
    }

    @Test
    fun testIsNotMetByReturnsTheRuleThatDoesNotMatch() {
        val rule = Rule(
            ruleExpires = referenceEpochSeconds + 60,
            iss = listOf(
                StringEquals(
                    "bad-iss.mfrancza.com"
                )
            ),
            iat = listOf(
                DateTimeBefore(referenceEpochSeconds)
            )
        )

        assertEquals(
            rule.iss.first(),
            rule.isNotMetBy(Claims(
                iss = "good-iss.mfrancza.com",
                iat = referenceEpochSeconds - 60 * 60,
                jti = "test-token"
            )),
            "The issuer condition which doesn't match should be returned"
        )
    }

    @Test
    fun testIsMet() {
        val rule = Rule(
            ruleExpires = referenceEpochSeconds + 60,
            iss = listOf(
                StringEquals(
                    "bad-iss.mfrancza.com"
                )
            )
        )

        assertTrue(rule.isMet(Claims(
            iss = "bad-iss.mfrancza.com",
            jti = "test-token"
        )))

        assertFalse(rule.isMet(Claims(
            iss = "good-iss.mfrancza.com",
            jti = "test-token"
        )))
    }
}