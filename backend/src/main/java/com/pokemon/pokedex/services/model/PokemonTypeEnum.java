package com.pokemon.pokedex.services.model;

public enum PokemonTypeEnum {

  GRASS, POISON, FIRE, FLYING;

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
