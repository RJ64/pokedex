package com.pokemon.pokedex.model;

import com.google.gson.annotations.SerializedName;

public enum PokemonTypeEnum {

  @SerializedName("Bug") BUG,
  @SerializedName("Dark") DARK,
  @SerializedName("Dragon") DRAGON,
  @SerializedName("Electric") ELECTRIC,
  @SerializedName("Fairy") FAIRY,
  @SerializedName("Fighting") FIGHTING,
  @SerializedName("Fire") FIRE,
  @SerializedName("Flying") FLYING,
  @SerializedName("Ghost") GHOST,
  @SerializedName("Grass") GRASS,
  @SerializedName("Ground") GROUND,
  @SerializedName("Ice") ICE,
  @SerializedName("Normal") NORMAL,
  @SerializedName("Poison") POISON,
  @SerializedName("Psychic") PSYCHIC,
  @SerializedName("Rock") ROCK,
  @SerializedName("Steel") STEEL,
  @SerializedName("Water") WATER;

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
