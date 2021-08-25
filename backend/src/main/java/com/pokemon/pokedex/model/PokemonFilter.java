package com.pokemon.pokedex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonFilter {
  String nameSubstring;
  PokemonTypeEnum type;
  Boolean isFavorite;
  int limit = 10;
  int offset = 0;
}
