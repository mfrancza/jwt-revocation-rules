package com.mfrancza.jwtrevocation.rules

import com.mfrancza.jwtrevocation.rules.conditions.DateTimeAfter
import com.mfrancza.jwtrevocation.rules.conditions.DateTimeCondition
import com.mfrancza.jwtrevocation.rules.conditions.StringEquals
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RuleSetTest {
    private val referenceEpochSeconds : Long = 1673123605
    @Test
    fun testIsMetByNoRuleIsMet() {
        val ruleSet = RuleSet(
            rules = listOf(
                Rule(
                    ruleExpires = referenceEpochSeconds + 60,
                    exp = listOf(
                        DateTimeAfter(
                            referenceEpochSeconds + 60 * 60
                        )
                    )
                ),
                Rule(
                    ruleExpires = referenceEpochSeconds + 60,
                    iss = listOf(
                        StringEquals(
                            "bad-iss.mfrancza.com"
                        )
                    )
                ),
                Rule(
                    ruleExpires = referenceEpochSeconds + 60 * 60 * 24,
                    aud = listOf(
                        StringEquals("bad-aud.mfrancza.com")
                    )
                )
            ),
            timestamp = referenceEpochSeconds
        )

        assertNull(
            ruleSet.isMetBy(
                Claims(
                    aud = "good-aud.mfrancza.com",
                    iss = "good-iss.mfrancza.com",
                    exp = referenceEpochSeconds
                )
            ), "No rule should match, so null should be returned"
        )

    }

    @Test
    fun testIsMetByReturnsRuleWhichMeetsIt() {
        val expectedRule = Rule(
            ruleExpires = referenceEpochSeconds + 60,
            iss = listOf(
                StringEquals(
                    "bad-iss.mfrancza.com"
                )
            )
        )

        val ruleSet = RuleSet(
            rules = listOf(
                Rule(
                    ruleExpires = referenceEpochSeconds + 60,
                    exp = listOf(
                        DateTimeAfter(referenceEpochSeconds + 60 * 60)
                    ),
                ),
                expectedRule,
                Rule(
                    ruleExpires = referenceEpochSeconds + 60 * 60 * 24,
                    aud = listOf(
                        StringEquals("bad-aud.mfrancza.com")
                    )
                )
            ),
            timestamp = referenceEpochSeconds
        )

        assertEquals(
            expectedRule, ruleSet.isMetBy(
                Claims(
                    aud = "good-aud.mfrancza.com",
                    iss = "bad-iss.mfrancza.com",
                    exp = referenceEpochSeconds
                )
            ), "The rule that matches should be returned"
        )
    }

    @Test
    fun testIsMet() {
        val ruleSet = RuleSet(
            rules = listOf(
                Rule(
                    ruleExpires = referenceEpochSeconds + 60,
                    exp = listOf(
                        DateTimeAfter(
                            referenceEpochSeconds + 60 * 60
                        )
                    ),
                ),
            ),
            timestamp = referenceEpochSeconds
        )

        assertTrue(
            ruleSet.isMet(
                Claims(
                    exp = referenceEpochSeconds + 60 * 60 * 24
                )
            ), "True should be returned when the claims match a rule"
        )

        assertFalse(
            ruleSet.isMet(
                Claims(
                    exp = referenceEpochSeconds
                )
            ), "False should be returned when the claims do not match a rule"
        )
    }
}