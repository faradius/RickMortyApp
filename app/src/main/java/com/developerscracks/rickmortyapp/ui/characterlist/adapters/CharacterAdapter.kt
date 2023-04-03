package com.developerscracks.rickmortyapp.ui.characterlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.databinding.ItemCharacterBinding

class CharacterAdapter: PagingDataAdapter<CharacterDTO, CharacterAdapter.CharacterViewHolder>(CHARACTER_COMPARATOR) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(character: CharacterDTO){
            binding.tvNameCharacter.text = character.name
            Glide.with(itemView.context)
                .load(character.image)
                .into(binding.ivCharacter)
        }
    }

    companion object{
        private val CHARACTER_COMPARATOR = object: DiffUtil.ItemCallback<CharacterDTO>(){
            override fun areItemsTheSame(oldItem: CharacterDTO, newItem: CharacterDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterDTO, newItem: CharacterDTO): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}