package br.com.raphael.pokedex.ui.view.pokemons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import br.com.raphael.pokedex.R
import br.com.raphael.pokedex.data.model.dto.State
import br.com.raphael.pokedex.data.model.response.PokemonDetailResponse
import br.com.raphael.pokedex.databinding.FragmentPokemonsBinding
import br.com.raphael.pokedex.ui.base.BaseFragment
import br.com.raphael.pokedex.ui.view.pokemons.adapter.PokemonsAdapter
import br.com.raphael.pokedex.ui.viewmodel.PokemonsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonsFragment : BaseFragment<FragmentPokemonsBinding>(FragmentPokemonsBinding::inflate) {

    private val pokemonsViewModel: PokemonsViewModel by viewModels()
    private val pokemonsAdapter: PokemonsAdapter by lazy {
        PokemonsAdapter { pokemon -> onItemClicked(pokemon) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvPokemons)
        binding.rvPokemons.adapter = pokemonsAdapter
    }

    private fun setupObservers() {
        pokemonsViewModel.pokemonsState.observe(viewLifecycleOwner) { state ->
            state?.let {
                binding.progressBar.visibility = View.INVISIBLE
                when (state) {
                    is State.Success -> {
                        pokemonsAdapter.submitList(state.value)
                    }
                    is State.Error -> {
                        val snackbar = Snackbar.make(binding.root, state.message, Snackbar.LENGTH_INDEFINITE)
                        snackbar.setAction(R.string.try_again) {
                            pokemonsViewModel.getPokemons()
                            binding.progressBar.visibility = View.VISIBLE
                            snackbar.dismiss()
                        }.show()
                    }
                }
            }
        }
    }

    private fun onItemClicked(pokemon: PokemonDetailResponse) {}
}