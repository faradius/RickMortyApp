package com.developerscracks.rickmortyapp.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.developerscracks.rickmortyapp.R
import com.developerscracks.rickmortyapp.databinding.FragmentCharacterDetailBinding
import com.developerscracks.rickmortyapp.databinding.FragmentCharacterListBinding
import com.developerscracks.rickmortyapp.ui.characterdetail.viewmodel.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CharacterDetailViewModel>()

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribeCharacterDetail()
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterDetail(args.id)
    }

    fun subscribeCharacterDetail(){
        viewModel.characterDetail.observe(viewLifecycleOwner){character ->
            binding.tvNameCharacterDetail.text = character?.name

            Glide.with(requireContext())
                .load(character?.image)
                .into(binding.ivCharacterDetail)

            binding.tvCharacterSpecieDetail.text = character?.species
            binding.tvCharacterStatusDetail.text = character?.status

            binding.tvCharacterOriginDetail.text = character?.origin?.name
            binding.tvCharacterLocationDetail.text = character?.location?.name

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}