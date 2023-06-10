package com.mfrancza.jwtrevocation.rules.conditions

import com.mfrancza.jwtrevocation.rules.PartialList
import com.mfrancza.jwtrevocation.rules.Rule
import com.mfrancza.jwtrevocation.rules.canBeSerializedAndDeserialized
import kotlin.test.Test

class PartialListTest {

    @Test
    fun testSerialization() {
        val sampleObjects = listOf(
            PartialList(
                list = listOf(
                    Rule(
                        ruleExpires =  1686433017,
                        exp = listOf(
                            DateTimeAfter(1686433017)
                        )
                    ),
                    Rule(
                        ruleExpires = 1686433017,
                        iss = listOf(
                            StringEquals("bad-iss.mfrancza.com")
                        )
                    ),
                    Rule(
                        ruleExpires = 1686433017,
                        aud = listOf(
                            StringEquals("bad-aud.mfrancza.com")
                        )
                    )
                ),
                cursor = "3"
            )
        )

        canBeSerializedAndDeserialized(sampleObjects)
    }
}