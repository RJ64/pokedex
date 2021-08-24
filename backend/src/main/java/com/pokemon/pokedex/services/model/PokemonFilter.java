package com.pokemon.pokedex.services.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PokemonFilter {
  String nameSubstring;
  PokemonTypeEnum type;
}
