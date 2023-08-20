package com.u3f.ethereumsign.data.repository.sign

import com.u3f.ethereumsign.domain.model.sign.SignMessageModel
import com.u3f.ethereumsign.domain.repository.sign.SignRepository
import wallet.core.jni.CoinType
import wallet.core.jni.Curve
import wallet.core.jni.Hash

class SignRepositoryImpl : SignRepository {

    override suspend fun SignMessage(data: SignMessageModel): String {
        val message = Hash.keccak256(data.message?.toByteArray())
        val sign = data.privateKey!!.sign(message, Curve.SECP256K1)
//        val result = data.privateKey!!.getPublicKey(CoinType.ETHEREUM)
//            .verify(sign, message)
        return sign.joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
    }
}