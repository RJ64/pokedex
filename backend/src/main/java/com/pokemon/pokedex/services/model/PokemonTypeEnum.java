package com.pokemon.pokedex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PokemonTypeEnum {

  @JsonProperty("Bug")
  BUG,
  @JsonProperty("Dark")
  DARK,
  @JsonProperty("Dragon")
  DRAGON,
  @JsonProperty("Electric")
  ELECTRIC,
  @JsonProperty("Fairy")
  FAIRY,
  @JsonProperty("Fighting")
  FIGHTING,
  @JsonProperty("Fire")
  FIRE,
  @JsonProperty("Flying")
  FLYING,
  @JsonProperty("Ghost")
  GHOST,
  @JsonProperty("Grass")
  GRASS,
  @JsonProperty("Ground")
  GROUND,
  @JsonProperty("Ice")
  ICE,
  @JsonProperty("Normal")
  NORMAL,
  @JsonProperty("Poison")
  POISON,
  @JsonProperty("Psychic")
  PSYCHIC,
  @JsonProperty("Rock")
  ROCK,
  @JsonProperty("Steel")
  STEEL,
  @JsonProperty("Water")
  WATER;

  public static PokemonTypeEnum toEnum(String typeSearching) {

    if (typeSearching == null) {
      return null;
    }

    String typeSearchingLowerCase = typeSearching.toLowerCase();
    for (PokemonTypeEnum type : PokemonTypeEnum.values()) {
      if (type.name().toLowerCase().equals(typeSearchingLowerCase)) {
        return type;
      }
    }
    return null;

  }

}
