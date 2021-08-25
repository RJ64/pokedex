package com.pokemon.pokedex.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

  private String id;
  private String name;
  private PokemonDimension weight;
  private PokemonDimension height;
  private String classification;
  private List<PokemonTypeEnum> types;
  private List<PokemonTypeEnum> resistant;
  private Attacks attacks;
  private List<PokemonTypeEnum> weaknesses;
  private float fleeRate;
  private int maxCP;
  private List<Pokemon> evolutions;
  private EvolutionRequirements evolutionRequirements;
  private int maxHP;
  @Builder.Default
  private boolean isFavorite = false;

  public int getNumber() {
    return Integer.parseInt(id);
  }

  public String getImage() {
    String nameDecoded = name.toLowerCase().replaceAll("[&\\\\/\\\\\\\\#,+()$~%.'\":*?<>\\{\\}]", "").replace(" ", "-");
    return "https://img.pokemondb.net/artwork/" + nameDecoded + ".jpg";
  }

  public String getSound() {
    return "http://localhost:4000/sounds/" + getNumber();
  }

}
