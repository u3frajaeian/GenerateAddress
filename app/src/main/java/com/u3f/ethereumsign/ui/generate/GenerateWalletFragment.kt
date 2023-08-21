package com.u3f.ethereumsign.ui.generate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.u3f.ethereumsign.R
import com.u3f.ethereumsign.base.UiState
import com.u3f.ethereumsign.base.delegate.viewBinding
import com.u3f.ethereumsign.base.extension.toast
import com.u3f.ethereumsign.databinding.FragmentGenerateWalletBinding
import com.u3f.ethereumsign.domain.model.sign.SignMessageModel
import com.u3f.ethereumsign.ui.generate.GenerateWalletFragmentDirections.ActionGenerateWalletFragmentToSignFragment
import com.u3f.ethereumsign.ui.generate.GenerateWalletFragmentDirections.actionGenerateWalletFragmentToSignFragment
import com.u3f.ethereumsign.ui.generate.list.MnemonicAdapter
import com.u3f.ethereumsign.ui.sign.SignFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import wallet.core.jni.CoinType
import wallet.core.jni.Curve
import wallet.core.jni.Hash
import wallet.core.jni.PrivateKey

@AndroidEntryPoint
class GenerateWalletFragment : Fragment(R.layout.fragment_generate_wallet) {


    private val viewModel: GenerateWalletViewModel by viewModels()
    private val binding: FragmentGenerateWalletBinding by viewBinding()
    private val nemonicAdapter: MnemonicAdapter by lazy { MnemonicAdapter() }

    private  var phrase=""

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
                        val private = "0x${
                            it.data.privateKey!!.data()
                                .joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
                        }"
                        phrase = it.data.keys!!
                        Timber.i("private ${private}")
                        Timber.i("address ${it.data.address}")
                        nemonicAdapter.mnemonic = it.data.keys!!.split(" ")
                        nemonicAdapter.notifyDataSetChanged()
                        binding.addressValue.setText(it.data.address)
                        binding.privateValue.setText(private)


                    }
                }
            }
        }
    }

    private fun initView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = nemonicAdapter
        }
        binding.generate.setOnClickListener { viewModel.generateMnemonic() }
        binding.sign.setOnClickListener {
            if(phrase.isNotEmpty()) {
                val action = actionGenerateWalletFragmentToSignFragment(phrase)
                findNavController().navigate(
                    action
                )
            }else{
                requireContext().toast("Please generate a address at first!")
            }

        }
    }

}