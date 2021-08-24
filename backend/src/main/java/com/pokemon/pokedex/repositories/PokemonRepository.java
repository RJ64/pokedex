package com.pokemon.pokedex.repositories;

import static java.util.Collections.emptyList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.model.PokemonTypeEnum;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokemonRepository {

  private final String path;
  private final List<Pokemon> pokemons;

  public PokemonRepository(String path) {
    this.path = path;
    pokemons = getAllPokemon();
  }

  public List<Pokemon> getPokemonsFiltered(String nameContainsSubstring, PokemonTypeEnum type) {
    return pokemons.stream()
      .filter(filterByNameContainingSubstring(nameContainsSubstring))
      .filter(filterByType(type))
      .collect(Collectors.toList());
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
