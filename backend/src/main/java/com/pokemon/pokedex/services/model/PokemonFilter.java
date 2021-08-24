package com.pokemon.pokedex.services.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.util.StringUtils;

@Value
@Builder
public class PokemonFilter {

  String nameSubstring;

  public boolean isEmpty() {
    return !StringUtils.hasText(nameSubstring);
  }

}
