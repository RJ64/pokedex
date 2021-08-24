package com.pokemon.pokedex.rest.converter;

import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.model.PokemonTypeEnum;
import com.pokemon.pokedex.rest.resource.PokemonBasicResource;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonConverter {

  public static List<PokemonBasicResource> toBasicPokemonList(List<Pokemon> pokemons) {
    return pokemons.stream().map(PokemonConverter::toBasicPokemon).collect(Collectors.toList());
  }

  private static PokemonBasicResource toBasicPokemon(Pokemon pokemon) {
    return new PokemonBasicResource(
      pokemon.getId(),
      pokemon.getName(),
      "urlToImage",
      typesToStringList(pokemon.getTypes()),
      pokemon.isFavorite()
    );
  }

  private static List<String> typesToStringList(List<PokemonTypeEnum> pokemonTypes) {
    return pokemonTypes.stream().map(Enum::name).collect(Collectors.toList());
  }

}
