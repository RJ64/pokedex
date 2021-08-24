package com.pokemon.pokedex.rest.controller;

import com.pokemon.pokedex.rest.converter.PokemonConverter;
import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.PokemonsServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity<?> getPokemons() {

    List<Pokemon> pokemons = pokemonsServices.getPokemons();

    return ResponseEntity
      .status(HttpStatus.OK)
      .contentType(MediaType.APPLICATION_JSON)
      .body(PokemonConverter.toBasicPokemonList(pokemons));
  }

}
