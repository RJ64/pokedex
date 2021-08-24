package com.pokemon.pokedex.repositories;

import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FLYING;
import static java.util.Collections.emptyList;

import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.model.PokemonTypeEnum;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokemonRepository {

  private static final Pokemon pokemon1 = Pokemon.builder().id(1).name("Pikachu").type(emptyList()).build();
  private static final Pokemon pokemon2 = Pokemon.builder().id(2).name("Raichu").type(List.of(FLYING)).build();
  private static final List<Pokemon> POKEMONS = List.of(pokemon1, pokemon2);

  public List<Pokemon> getPokemonsFiltered(String nameContainsSubstring, PokemonTypeEnum type) {
    return POKEMONS.stream()
      .filter(filterByNameContainingSubstring(nameContainsSubstring))
      .filter(filterByType(type))
      .collect(Collectors.toList());
  }

  private Predicate<Pokemon> filterByNameContainingSubstring(String substring) {
    return pokemon -> substring == null || pokemon.getName().toLowerCase().contains(substring);
  }

  private Predicate<Pokemon> filterByType(PokemonTypeEnum type) {
    return pokemon -> type == null || pokemon.getType().contains(type);
  }

}
