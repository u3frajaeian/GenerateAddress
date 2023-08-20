package com.u3f.ethereumsign.domain.repository.generate

import wallet.core.jni.PrivateKey
import wallet.core.jni.PublicKey


interface GenerateRepository {
    suspend fun generateNemonic(): String
    suspend fun generatePrivateKey(nemonic:String): PrivateKey
    suspend fun generateAddress(nemonic: String): String
    suspend fun generatePublicKey(nemonic:String): PublicKey
}