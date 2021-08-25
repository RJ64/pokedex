package com.pokemon.pokedex.rest.resource;

import com.pokemon.pokedex.model.Pokemon;
import java.util.List;
import lombok.Value;

@Value
public class PokemonConnection {
  int limit;
  int offset;
  int count;
  List<Pokemon> edges;
}
