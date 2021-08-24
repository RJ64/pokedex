package com.pokemon.pokedex.repositories;

import static java.util.Collections.emptyList;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.pokemon.pokedex.rest.resource.PokemonConnection;
import com.pokemon.pokedex.model.Pokemon;
import com.pokemon.pokedex.model.PokemonFilter;
import com.pokemon.pokedex.model.PokemonTypeEnum;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    pokemons = loadPokemon().stream().collect(toMap(Pokemon::getNumber, identity()));
  }

  public List<Pokemon> getAllPokemon() {
    return new ArrayList<>(pokemons.values());
  }

  public PokemonConnection getPokemonsFiltered(PokemonFilter filter) {

    List<Pokemon> pokemonsFiltered = pokemons.values().stream()
      .filter(filterByNameContainingSubstring(filter.getNameSubstring()))
      .filter(filterByType(filter.getType()))
      .filter(filterByFavorite(filter.getIsFavorite()))
      .collect(Collectors.toList());

    int fromIndex = filter.getOffset();
    int toIndex = fromIndex + filter.getLimit();
    if (pokemonsFiltered.size() <= fromIndex) {
      fromIndex = 0;
      toIndex = 0;
    }
    if (toIndex > pokemonsFiltered.size()) {
      toIndex = pokemonsFiltered.size();
    }

    return new PokemonConnection(
      filter.getLimit(),
      filter.getOffset(),
      pokemonsFiltered.size(),
      pokemonsFiltered.subList(fromIndex, toIndex)
    );

  }

  public Optional<Pokemon> getPokemonById(int pokemonId) {
    return Optional.ofNullable(pokemons.get(pokemonId));
  }

  public Optional<Pokemon> getPokemonByName(String pokemonName) {
    String nameLowerCase = pokemonName.toLowerCase();
    return pokemons.values().stream().filter(p -> p.getName().toLowerCase().equals(nameLowerCase)).findFirst();
  }

  public void addToFavorite(int pokemonId) {
    var pokemonModifying = pokemons.get(pokemonId);
    if (pokemonModifying != null) {
      pokemonModifying.setFavorite(true);
    }
  }

  public void removeFromFavorite(int pokemonId) {
    var pokemonModifying = pokemons.get(pokemonId);
    if (pokemonModifying != null) {
      pokemonModifying.setFavorite(false);
    }
  }

  private Predicate<Pokemon> filterByNameContainingSubstring(String substring) {
    return pokemon -> substring == null || pokemon.getName().toLowerCase().contains(substring);
  }

  private Predicate<Pokemon> filterByType(PokemonTypeEnum type) {
    return pokemon -> type == null || pokemon.getTypes().contains(type);
  }

  private Predicate<Pokemon> filterByFavorite(Boolean isFavorite) {
    return pokemon -> isFavorite == null || pokemon.isFavorite() == isFavorite;
  }

  private List<Pokemon> loadPokemon() {
    return Optional.ofNullable(readFileWithPokemons()).map(this::parseJsonStringToPokemonList).orElse(emptyList());
  }

  private List<Pokemon> parseJsonStringToPokemonList(String jsonString) {
    var listType = new TypeToken<ArrayList<Pokemon>>(){}.getType();
    return new Gson().fromJson(jsonString, listType);
  }

  private String readFileWithPokemons() {
    try {
      return String.join("", Files.readAllLines(Path.of(path)));
    } catch (IOException e) {
      return null;
    }
  }

}
