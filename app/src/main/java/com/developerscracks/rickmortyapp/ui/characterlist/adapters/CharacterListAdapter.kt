package com.developerscracks.rickmortyapp.ui.characterlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developerscracks.rickmortyapp.databinding.ItemCharacterBinding
import com.developerscracks.rickmortyapp.domain.model.Character

class CharacterListAdapter (private val onClick: (String) -> Unit): RecyclerView.Adapter<CharacterListAdapter.CharactersViewHolder>(){

    private var characters: List<Character> = mutableListOf()

    inner class CharactersViewHolder(itemBinding: ItemCharacterBinding): RecyclerView.ViewHolder(itemBinding.root) {
        private val name = itemBinding.tvNameCharacter
        private val image = itemBinding.ivCharacter

        fun bind(character: Character,onClick: (String) -> Unit ){
            name.text = character.name
            Glide.with(itemView.context)
                .load(character.image)
                .into(image)
        }
    }

    fun submit(imageList: List<Character>){
        characters = imageList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharactersViewHolder(
            ItemCharacterBinding.inflate(inflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characters[position], onClick)
    }
}