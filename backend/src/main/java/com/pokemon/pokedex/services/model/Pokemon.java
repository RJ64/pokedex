package com.pokemon.pokedex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  private int id;
  private String name;
  private String classification;
  private List<PokemonTypeEnum> types;
  private List<PokemonTypeEnum> resistant;
  private List<PokemonTypeEnum> weaknesses;
  private PokemonWeight weight;
  private PokemonHeight height;
  private float fleeRate;
  private EvolutionRequirements evolutionRequirements;
  @JsonProperty("Pokémon Class")
  private String pokemonClass;
  @JsonProperty("Common Capture Area")
  private String commonCaptureArea;
  @JsonProperty("Previous evolution(s)")
  private List<PokemonEvolution> previousEvolutions;
  private List<PokemonEvolution> evolutions;
  private int maxCP;
  private int maxHP;
  private Attacks attacks;

  @Builder.Default
  private boolean isFavorite = false;

}
