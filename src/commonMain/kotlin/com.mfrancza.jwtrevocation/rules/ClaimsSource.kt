package com.mfrancza.jwtrevocation.rules

/**
 * A source of claims which can be evaluated against Rules
 */
interface ClaimsSource {
    fun issValue(): String?
    fun subValue(): String?
    fun audValue(): String?
    fun expValue(): Long?
    fun nbfValue(): Long?
    fun iatValue(): Long?
    fun jtiValue(): String?
}
