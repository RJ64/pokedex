package com.pokemon.pokedex.services;

import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.services.model.PokemonFilter;
import java.util.List;

public class PokemonsServices {

  private final PokemonRepository pokemonRepository;

  public PokemonsServices(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  public List<Pokemon> getPokemons(PokemonFilter filters) {
    if (filters.isEmpty()) {
      return getAllPokemons();
    }
    return getPokemonsFiltered(filters);
  }

  public List<Pokemon> getPokemons() {
    return getPokemons(PokemonFilter.builder().build());
  }

  private List<Pokemon> getAllPokemons() {
    return pokemonRepository.getAllPokemon();
  }

  private List<Pokemon> getPokemonsFiltered(PokemonFilter filters) {
    return pokemonRepository.getPokemonsFiltered(filters.getNameSubstring());
  }

}
