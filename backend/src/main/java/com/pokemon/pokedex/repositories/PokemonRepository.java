package com.pokemon.pokedex.repositories;

import static java.util.Collections.emptyList;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.model.PokemonTypeEnum;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokemonRepository {

  private final String path;
  private final Map<Integer, Pokemon> pokemons;

  public PokemonRepository(String path) {
    this.path = path;
    pokemons = getAllPokemon().stream().collect(toMap(Pokemon::getId, identity()));
  }

  public List<Pokemon> getPokemonsFiltered(String nameContainsSubstring, PokemonTypeEnum type) {
    return pokemons.values().stream()
      .filter(filterByNameContainingSubstring(nameContainsSubstring))
      .filter(filterByType(type))
      .collect(Collectors.toList());
  }

  public void addToFavorite(int pokemonId) {
    pokemons.get(pokemonId).setFavorite(true);
  }

  public void removeFromFavorite(int pokemonId) {
    pokemons.get(pokemonId).setFavorite(false);
  }

  private Predicate<Pokemon> filterByNameContainingSubstring(String substring) {
    return pokemon -> substring == null || pokemon.getName().toLowerCase().contains(substring);
  }

  private Predicate<Pokemon> filterByType(PokemonTypeEnum type) {
    return pokemon -> type == null || pokemon.getTypes().contains(type);
  }

  private List<Pokemon> getAllPokemon() {
    return Optional.ofNullable(readFileWithPokemons()).map(this::parseJsonStringToPokemonList).orElse(emptyList());
  }

  private List<Pokemon> parseJsonStringToPokemonList(String jsonString) {
    try {
      var mapper = new ObjectMapper();
      return mapper.readValue(jsonString, new TypeReference<List<Pokemon>>(){});
    } catch (JsonProcessingException e) {
      return emptyList();
    }
  }

  private String readFileWithPokemons() {
    try {
      return String.join("", Files.readAllLines(Path.of(path)));
    } catch (IOException e) {
      return null;
    }
  }

}
