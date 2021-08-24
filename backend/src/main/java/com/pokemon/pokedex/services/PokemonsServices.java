package com.pokemon.pokedex.services;

import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.repositories.PokemonRepository;
import java.util.List;

public class PokemonsServices {

  private final PokemonRepository pokemonRepository;

  public PokemonsServices(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  public List<Pokemon> getPokemons() {
    return pokemonRepository.getAllPokemon();
  }

}
