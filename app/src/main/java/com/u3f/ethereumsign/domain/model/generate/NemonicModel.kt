package com.u3f.ethereumsign.domain.model.generate

import wallet.core.jni.PrivateKey

data class NemonicModel(
    var keys: String?,
    var privateKey: PrivateKey?,
    var address: String?
)
