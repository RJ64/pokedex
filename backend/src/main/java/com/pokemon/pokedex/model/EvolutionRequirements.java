package com.pokemon.pokedex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EvolutionRequirements {
  private int amount;
  private String name;
}
