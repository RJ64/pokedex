package com.pokemon.pokedex.repositories;

import static java.util.Collections.emptyList;

import com.pokemon.pokedex.services.model.Pokemon;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokemonRepository {

  private static final Pokemon pokemon1 = Pokemon.builder().id(1).name("Pikachu").type(emptyList()).build();
  private static final Pokemon pokemon2 = Pokemon.builder().id(2).name("Raichu").type(emptyList()).build();
  private static final List<Pokemon> POKEMONS = List.of(pokemon1, pokemon2);

  public List<Pokemon> getAllPokemon() {
    return POKEMONS;
  }

  public List<Pokemon> getPokemonsFiltered(String nameContainsSubstring) {
    return getAllPokemon().stream()
      .filter(filterByNameContainingSubstring(nameContainsSubstring))
      .collect(Collectors.toList());
  }

  private Predicate<Pokemon> filterByNameContainingSubstring(String substring) {
    return pokemon -> pokemon.getName().toLowerCase().contains(substring);
  }

}
