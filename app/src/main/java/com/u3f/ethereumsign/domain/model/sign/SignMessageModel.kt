package com.u3f.ethereumsign.domain.model.sign

import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize
import wallet.core.jni.PrivateKey
@Parcelize
data class SignMessageModel(
    var message:String?,

    var privateKey:@kotlinx.parcelize.RawValue PrivateKey?
):Parcelable
