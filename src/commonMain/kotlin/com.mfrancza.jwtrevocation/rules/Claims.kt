package com.mfrancza.jwtrevocation.rules

import kotlinx.serialization.Serializable

/**
 * Basic implementation of a ClaimsSource as data class
 */
@Serializable
data class Claims  (
    val iss: String? = null,
    val sub: String? = null,
    val aud: String? = null,
    val exp: Long? = null,
    val nbf: Long? = null,
    val iat: Long? = null,
    val jti: String? = null
) : ClaimsSource {
    override fun issValue() = iss
    override fun subValue() = sub
    override fun audValue() = aud
    override fun expValue() = exp
    override fun nbfValue() = nbf
    override fun iatValue() = iat
    override fun jtiValue() = jti
}