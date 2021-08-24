package com.pokemon.pokedex.model;

import java.util.List;
import lombok.Value;

@Value
public class PokemonBasicResource {
  int id;
  String name;
  String image;
  List<String> types;
  boolean isFavorite;
}
