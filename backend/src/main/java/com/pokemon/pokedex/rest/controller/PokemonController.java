package com.pokemon.pokedex.rest.controller;

import com.pokemon.pokedex.rest.converter.PokemonConverter;
import com.pokemon.pokedex.services.model.ActionFavoriteEnum;
import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.PokemonsServices;
import com.pokemon.pokedex.services.model.PokemonFilter;
import com.pokemon.pokedex.services.model.PokemonTypeEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

  private final PokemonsServices pokemonsServices;

  @Autowired
  public PokemonController(PokemonsServices pokemonsServices) {
    this.pokemonsServices = pokemonsServices;
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<?> getPokemons(
    @RequestParam(name = "name", required = false) String pokemonNameContainsSubstring,
    @RequestParam(name = "type", required = false) String pokemonWithType
  ) {

    if (!validType(pokemonWithType)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    var filters = createFilter(pokemonNameContainsSubstring, pokemonWithType);
    List<Pokemon> pokemons = pokemonsServices.getPokemons(filters);

    return ResponseEntity
      .status(HttpStatus.OK)
      .contentType(MediaType.APPLICATION_JSON)
      .body(PokemonConverter.toBasicPokemonList(pokemons));
  }

  @PostMapping(value = "/{pokemonId}/favorite/{action}")
  public ResponseEntity<?> markUnmarkPokemonAsFavorite(
    @PathVariable("pokemonId") int pokemonId,
    @PathVariable("action") String action
  ) {

    if (!validAction(action)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    pokemonsServices.changeStatusFavorite(pokemonId, ActionFavoriteEnum.valueOf(action.toUpperCase()));

    return ResponseEntity
      .status(HttpStatus.OK)
      .build();
  }

  private boolean validAction(String action) {
    return action.toUpperCase().equals(ActionFavoriteEnum.ADD.name()) || action.toUpperCase().equals(ActionFavoriteEnum.DELETE.name());
  }

  private boolean validType(String type) {
    return type == null || PokemonTypeEnum.toEnum(type) != null;
  }

  private PokemonFilter createFilter(String nameSubstring, String type) {
    var nameSubstringLowercase = Optional.ofNullable(nameSubstring).map(String::toLowerCase).orElse(null);
    return PokemonFilter.builder()
      .nameSubstring(nameSubstringLowercase)
      .type(PokemonTypeEnum.toEnum(type))
      .build();
  }

}
