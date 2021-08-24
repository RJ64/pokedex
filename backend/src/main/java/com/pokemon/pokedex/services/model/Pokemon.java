package com.pokemon.pokedex.services.model;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Pokemon {
  int id;
  String name;
  List<PokemonTypeEnum> type;
  String image;
  String sound;
  int cp;
  int hp;
  PokemonWeight weight;
  PokemonHeight height;
  List<Pokemon> evolutions;
  boolean isFavorite;
}
