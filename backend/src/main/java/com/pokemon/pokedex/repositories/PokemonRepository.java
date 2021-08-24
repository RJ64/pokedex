package com.pokemon.pokedex.repositories;

import static java.util.Collections.emptyList;

import com.pokemon.pokedex.services.model.Pokemon;
import java.util.List;

public class PokemonRepository {

  private static final Pokemon pokemon1 = Pokemon.builder().id(1).name("Pikachu").type(emptyList()).build();
  private static final Pokemon pokemon2 = Pokemon.builder().id(2).name("Raichu").type(emptyList()).build();
  private static final List<Pokemon> POKEMONS = List.of(pokemon1, pokemon2);

  public List<Pokemon> getAllPokemon() {
    return POKEMONS;
  }

}
