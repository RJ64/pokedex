package com.pokemon.pokedex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Attack {
  private String name;
  private PokemonTypeEnum type;
  private int damage;
}
