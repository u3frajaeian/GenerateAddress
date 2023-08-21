package com.u3f.ethereumsign.domain.model.sign

import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize
import wallet.core.jni.PrivateKey

data class SignMessageModel(
    var message: String?,

    var phrase: String?
)
