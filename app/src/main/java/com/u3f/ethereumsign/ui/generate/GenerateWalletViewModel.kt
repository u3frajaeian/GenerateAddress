package com.u3f.ethereumsign.ui.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.ethereumsign.base.UiState
import com.u3f.ethereumsign.domain.model.generate.NemonicModel
import com.u3f.ethereumsign.domain.usecase.generate.GenerateAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateWalletViewModel @Inject constructor(private val generateAddressUseCase: GenerateAddressUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<NemonicModel>>(UiState.Loading)

    val uiState: StateFlow<UiState<NemonicModel>> = _uiState
    fun generateMnemonic() {
        viewModelScope.launch {
             generateAddressUseCase.execute().catch {
                _uiState.value=UiState.Error(it.toString())
            }.collect{

                _uiState.value=UiState.Success(it)
            }

        }
    }
}