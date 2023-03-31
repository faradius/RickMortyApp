package com.developerscracks.rickmortyapp.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.developerscracks.rickmortyapp.R
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity
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
        checkbtn()
        subscribeCharacterDetail()
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterDetail(args.id)

    }

    private fun subscribeCharacterDetail(){
        viewModel.characterDetail.observe(viewLifecycleOwner){character ->
            binding.tvNameCharacterDetail.text = character?.name

            Glide.with(requireContext())
                .load(character?.image)
                .into(binding.ivCharacterDetail)

            binding.tvCharacterSpecieDetail.text = character?.species
            binding.tvCharacterStatusDetail.text = character?.status

            binding.tvCharacterOriginDetail.text = character?.origin?.name
            binding.tvCharacterLocationDetail.text = character?.location?.name

            binding.fabFavorite.setOnClickListener {

                viewModel.getCharacterFavoriteById(args.id)
                viewModel.characterFavorite.observe(viewLifecycleOwner){
                    if (it?.id == args.id){
                        viewModel.deleteCharacterFavorite(it.id)
                        binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                    }else{
                        viewModel.saveCharacterFavorite(
                            CharacterEntity(
                                id = args.id,
                                image = character?.image!!,
                                location = character.location.name,
                                name = character.name,
                                origin = character.origin.name,
                                species = character.species,
                                status = character.status
                            )
                        )
                        binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }



            }
        }
    }

    private fun checkbtn(){
        viewModel.getCharacterFavoriteById(args.id)
        viewModel.characterFavorite.observe(viewLifecycleOwner){
            if (it?.id == null) binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
            else binding.fabFavorite.setImageResource(R.drawable.ic_favorite)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}