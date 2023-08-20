package com.u3f.ethereumsign.domain.repository.sign

import com.u3f.ethereumsign.domain.model.sign.SignMessageModel
import wallet.core.jni.PrivateKey
import wallet.core.jni.PublicKey


interface SignRepository {
    suspend fun SignMessage(data:SignMessageModel): String

}