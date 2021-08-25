package com.pokemon.pokedex.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Attacks {
  private List<Attack> fast;
  private List<Attack> special;
}
