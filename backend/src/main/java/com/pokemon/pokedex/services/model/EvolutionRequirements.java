package com.pokemon.pokedex.services.model;

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
