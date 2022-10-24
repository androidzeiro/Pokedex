package br.com.raphael.pokedex.ui.view.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.raphael.pokedex.R
import br.com.raphael.pokedex.databinding.FragmentDetailBinding
import br.com.raphael.pokedex.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        val pokemon = args.pokemon

        binding.apply {
            Glide
                .with(ivPokemon)
                .load(pokemon.sprites.other.officialArtwork.frontDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .dontTransform()
                .dontAnimate()
                .into(ivPokemon)
            tvName.text = pokemon.name
            tvId.text = getString(R.string.id, pokemon.id)
            tvHeight.text = getString(R.string.height, pokemon.height)
            tvWeight.text = getString(R.string.weight, pokemon.weight)
            var types = ""
            pokemon.types.forEachIndexed { index, typeResponse ->
                types += if (index == pokemon.types.lastIndex) {
                    typeResponse.type.name
                } else {
                    "${typeResponse.type.name}\n"
                }
            }
            tvTypes.text = getString(R.string.types, types)
            var abilities = ""
            pokemon.abilities.forEachIndexed { index, abilityResponse ->
                abilities += if (index == pokemon.abilities.lastIndex) {
                    abilityResponse.ability.name
                } else {
                    "${abilityResponse.ability.name}\n"
                }
            }
            tvAbilities.text = getString(R.string.abilities, abilities)
        }
    }

    private fun setupListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}