package com.developerscracks.rickmortyapp.ui.characterfavorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerscracks.rickmortyapp.R
import com.developerscracks.rickmortyapp.databinding.FragmentCharacterListFavoritesBinding
import com.developerscracks.rickmortyapp.ui.characterfavorites.viewmodel.CharacterListFavoritesViewModel
import com.developerscracks.rickmortyapp.ui.characterlist.adapters.CharacterListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFavoritesFragment : Fragment() {

    private var _binding: FragmentCharacterListFavoritesBinding? = null
    private val binding get() = _binding!!

    private val charactersAdapter: CharacterListAdapter = CharacterListAdapter {idCharacter ->
        val action = CharacterListFavoritesFragmentDirections.actionCharacterListFavoritesFragmentToCharacterDetailFragment(idCharacter)
        findNavController().navigate(action)
    }

    private val viewModel by viewModels<CharacterListFavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribeCharacters()
        _binding = FragmentCharacterListFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharactersFavorites()

        binding.rvListCharactersFavorites.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = charactersAdapter
        }
    }

    private fun subscribeCharacters(){
        viewModel.characterFavorites.observe(viewLifecycleOwner){
            charactersAdapter.submit(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}