package com.developerscracks.rickmortyapp.ui.characterlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerscracks.rickmortyapp.R
import com.developerscracks.rickmortyapp.databinding.FragmentCharacterListBinding
import com.developerscracks.rickmortyapp.ui.characterlist.adapters.CharacterAdapter
import com.developerscracks.rickmortyapp.ui.characterlist.adapters.CharacterListAdapter
import com.developerscracks.rickmortyapp.ui.characterlist.viewmodel.CharacterListViewModel
import com.developerscracks.rickmortyapp.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

//    private val charactersAdapter: CharacterListAdapter = CharacterListAdapter { idCharacter ->
//        val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(idCharacter)
//        findNavController().navigate(action)
//    }

    val characterAdapter = CharacterAdapter()

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

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null){
                    viewModel.getCharactersByName(query)
                    binding.searchView.hideKeyboard()
                    true
                }else{
                    viewModel.getCharacters()
                    false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText != null){
                    viewModel.getCharactersByName(newText)
                    true
                }else{
                    viewModel.getCharacters()
                    false
                }
            }

        } )

        binding.rvListCharacters.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = characterAdapter
        }
    }

    private fun subscribeCharacters(){
//        viewModel.character.observe(viewLifecycleOwner){
//            charactersAdapter.submit(it)
//        }

        lifecycleScope.launch {
            viewModel.characters.collect{
                characterAdapter.submitData(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}