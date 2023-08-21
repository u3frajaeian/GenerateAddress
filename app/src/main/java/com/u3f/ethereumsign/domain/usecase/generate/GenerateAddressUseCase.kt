package com.u3f.ethereumsign.domain.usecase.generate

import com.u3f.ethereumsign.domain.model.generate.NemonicModel
import com.u3f.ethereumsign.domain.repository.generate.GenerateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenerateAddressUseCase @Inject constructor(private val generateRepository: GenerateRepository) {
    fun execute(): Flow<NemonicModel> {
        return flow {
            val nemonicModel = NemonicModel(null, null, null)
            val phrase = generateRepository.generateNemonic()
            nemonicModel.keys = phrase
            nemonicModel.privateKey = generateRepository.generatePrivateKey(phrase)
            nemonicModel.address = generateRepository.generateAddress(phrase)
            emit(nemonicModel)
        }


    }
}