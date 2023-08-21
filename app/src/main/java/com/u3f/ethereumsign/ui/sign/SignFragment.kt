package com.u3f.ethereumsign.ui.sign

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.u3f.ethereumsign.R
import com.u3f.ethereumsign.base.UiState
import com.u3f.ethereumsign.base.delegate.viewBinding
import com.u3f.ethereumsign.base.extension.toast
import com.u3f.ethereumsign.databinding.FragmentGenerateWalletBinding
import com.u3f.ethereumsign.databinding.FragmentSignBinding
import com.u3f.ethereumsign.domain.model.sign.SignMessageModel
import com.u3f.ethereumsign.ui.generate.GenerateWalletViewModel
import com.u3f.ethereumsign.ui.generate.list.MnemonicAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import wallet.core.jni.Hash
import wallet.core.jni.PrivateKey

@AndroidEntryPoint
class SignFragment : Fragment(R.layout.fragment_sign) {

    private val viewModel: SignViewModel by viewModels()
    private val binding: FragmentSignBinding by viewBinding()
    private val args: SignFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when (it) {
                    is UiState.Error -> Unit
                    UiState.Loading -> Unit
                    is UiState.Success -> {
                        val signText = "0x" + it.data
                        binding.privateValue.setText(signText)
                        Timber.d(it.data)
                    }
                }
            }
        }


    }

    private fun initView() {
        binding.btnSign.setOnClickListener {
            if (binding.messageValue.text.isNotBlank()) {

                val signModel = SignMessageModel(
                    binding.messageValue.text.toString(),
                    args.phrase
                )
                viewModel.signMessage(signModel)
            }else{
                requireContext().toast("Please Enter message!!")
            }

        }
    }
}