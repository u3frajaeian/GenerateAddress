package com.u3f.ethereumsign.ui.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.ethereumsign.base.UiState
import com.u3f.ethereumsign.domain.model.generate.NemonicModel
import com.u3f.ethereumsign.domain.model.sign.SignMessageModel
import com.u3f.ethereumsign.domain.usecase.generate.GenerateAddressUseCase
import com.u3f.ethereumsign.domain.usecase.sign.SignMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(private val signMessageUseCase: SignMessageUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Loading)

    val uiState: StateFlow<UiState<String>> = _uiState
    fun signMessage(data: SignMessageModel) {
        viewModelScope.launch {
            signMessageUseCase.execute(data).catch {
                _uiState.value = UiState.Error(it.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }

    }
}