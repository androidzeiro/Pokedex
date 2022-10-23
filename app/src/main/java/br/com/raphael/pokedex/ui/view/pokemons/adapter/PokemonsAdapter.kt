package br.com.raphael.pokedex.ui.view.pokemons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.raphael.pokedex.R
import br.com.raphael.pokedex.data.model.response.PokemonDetailResponse
import br.com.raphael.pokedex.databinding.ItemPokemonBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PokemonsAdapter(
    private val onItemClicked: (PokemonDetailResponse) -> Unit
) : ListAdapter<PokemonDetailResponse, PokemonsAdapter.CardViewHolder>(PokemonsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class CardViewHolder private constructor(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: PokemonDetailResponse, onCardClicked: (PokemonDetailResponse) -> Unit) {
            Glide
                .with(binding.ivPokemon)
                .load(pokemon.sprites.other.officialArtwork.frontDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .dontTransform()
                .dontAnimate()
                .into(binding.ivPokemon)
            binding.tvName.text = pokemon.name
            binding.root.setOnClickListener {
                onCardClicked.invoke(pokemon)
            }
        }

        companion object {
            fun from(parent: ViewGroup): CardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
                return CardViewHolder(binding)
            }
        }
    }
}

class PokemonsDiffCallback : DiffUtil.ItemCallback<PokemonDetailResponse>() {
    override fun areItemsTheSame(oldItem: PokemonDetailResponse, newItem: PokemonDetailResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonDetailResponse, newItem: PokemonDetailResponse): Boolean {
        return oldItem == newItem
    }
}