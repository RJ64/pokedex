package com.pokemon.pokedex.services;

import com.pokemon.pokedex.model.ActionFavoriteEnum;
import com.pokemon.pokedex.model.Pokemon;
import com.pokemon.pokedex.model.PokemonTypeEnum;
import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.model.PokemonFilter;
import com.pokemon.pokedex.rest.resource.PokemonConnection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PokemonsServices {

  private final PokemonRepository pokemonRepository;

  public PokemonsServices(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  public PokemonConnection getPokemons(PokemonFilter filters) {
    return pokemonRepository.getPokemonsFiltered(filters);
  }

  public Optional<Pokemon> getPokemonById(int pokemonId) {
    return pokemonRepository.getPokemonById(pokemonId);
  }

  public Optional<Pokemon> getPokemonByName(String pokemonName) {
    return pokemonRepository.getPokemonByName(pokemonName);
  }

  public Set<PokemonTypeEnum> getAllPokemonTypes() {
    return pokemonRepository.getAllPokemon().stream().flatMap(p -> p.getTypes().stream()).collect(Collectors.toSet());
  }

  public Optional<Pokemon> changeStatusFavorite(int pokemonId, ActionFavoriteEnum action) {
    if (action.equals(ActionFavoriteEnum.ADD)) {
      pokemonRepository.addToFavorite(pokemonId);
    }
    else {
      pokemonRepository.removeFromFavorite(pokemonId);
    }
    return pokemonRepository.getPokemonById(pokemonId);
  }

}
