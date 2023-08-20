package com.u3f.ethereumsign.ui.generate.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.u3f.ethereumsign.databinding.NemonicListItemBinding

class MnemonicAdapter :
    RecyclerView.Adapter<MnemonicAdapter.ViewHolder>() {

    var mnemonic: List<String> = arrayListOf()

    private var onClickListener: ((users: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NemonicListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mnemonic[position])
    }

    override fun getItemCount(): Int = mnemonic.size

    fun setClickListener(listener: (user: String) -> Unit) {
        this.onClickListener = listener
    }

    inner class ViewHolder(private val binding: NemonicListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(phrase: String) {
            itemView.setOnClickListener { onClickListener?.invoke(phrase) }
            binding.tvPhrase.text = phrase

        }
    }
}
