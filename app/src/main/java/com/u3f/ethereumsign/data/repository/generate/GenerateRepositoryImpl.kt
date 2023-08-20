package com.u3f.ethereumsign.data.repository.generate

import com.u3f.ethereumsign.domain.repository.generate.GenerateRepository
import timber.log.Timber
import wallet.core.jni.AnyAddress
import wallet.core.jni.CoinType
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
import wallet.core.jni.PublicKey
import javax.inject.Inject

class GenerateRepositoryImpl @Inject constructor() : GenerateRepository {
    override suspend fun generateNemonic(): String {
        val phrase = HDWallet(256, "").mnemonic()
        Timber.d(phrase)

        return phrase
    }

    override suspend fun generatePrivateKey(nemonic: String): PrivateKey {
        val hdWallet = HDWallet(nemonic, "")
        return hdWallet.getKey(CoinType.ETHEREUM, CoinType.ETHEREUM.derivationPath())
    }

    override suspend fun generateAddress(nemonic: String): String {
        val publicKey = generatePublicKey(nemonic)
        return AnyAddress(publicKey, CoinType.ETHEREUM).description()
    }

    override suspend fun generatePublicKey(nemonic: String): PublicKey {
        val hdWallet = HDWallet(nemonic, "")
        return hdWallet.getKey(CoinType.ETHEREUM, CoinType.ETHEREUM.derivationPath())
            .getPublicKeySecp256k1(false)
    }


}