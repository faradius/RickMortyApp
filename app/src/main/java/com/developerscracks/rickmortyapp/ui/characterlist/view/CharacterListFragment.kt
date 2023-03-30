package com.developerscracks.rickmortyapp.ui.characterlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerscracks.rickmortyapp.R
import com.developerscracks.rickmortyapp.databinding.FragmentCharacterListBinding
import com.developerscracks.rickmortyapp.ui.characterlist.adapters.CharacterListAdapter
import com.developerscracks.rickmortyapp.ui.characterlist.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val charactersAdapter: CharacterListAdapter = CharacterListAdapter {  }

    private val viewModel by viewModels<CharacterListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribeCharacters()
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacters()

        binding.rvListCharacters.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = charactersAdapter
        }
    }

    fun subscribeCharacters(){
        viewModel.character.observe(viewLifecycleOwner){
            charactersAdapter.submit(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}