package com.u3f.ethereumsign.domain.usecase.sign

import com.u3f.ethereumsign.domain.model.generate.NemonicModel
import com.u3f.ethereumsign.domain.model.sign.SignMessageModel
import com.u3f.ethereumsign.domain.repository.generate.GenerateRepository
import com.u3f.ethereumsign.domain.repository.sign.SignRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignMessageUseCase @Inject constructor(private val signRepository: SignRepository) {
    fun execute(data: SignMessageModel): Flow<String> {
        return flow {
            emit(signRepository.SignMessage(data))
        }


    }


}