package com.pokemon.pokedex.services;

import com.pokemon.pokedex.services.model.ActionFavoriteEnum;
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
    return getPokemonsFiltered(filters);
  }

  public void changeStatusFavorite(int pokemonId, ActionFavoriteEnum action) {
    if (action.equals(ActionFavoriteEnum.ADD)) {
      pokemonRepository.addToFavorite(pokemonId);
    }
    else {
      pokemonRepository.removeFromFavorite(pokemonId);
    }
  }

  private List<Pokemon> getPokemonsFiltered(PokemonFilter filters) {
    return pokemonRepository.getPokemonsFiltered(filters.getNameSubstring(), filters.getType());
  }

}
