package com.u3f.ethereumsign.domain.usecase.sign

import com.u3f.ethereumsign.domain.model.generate.NemonicModel
import com.u3f.ethereumsign.domain.model.sign.SignMessageModel
import com.u3f.ethereumsign.domain.repository.generate.GenerateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignMessageUseCase @Inject constructor(private val generateRepository: GenerateRepository) {
    fun execute(data: SignMessageModel): Flow<String> {
        return flow {
            val privateKey=generateRepository.generatePrivateKey(data.phrase!!)
            emit(generateRepository.signMessage(data.message!!,privateKey))
        }


    }


}