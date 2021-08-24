package com.pokemon.pokedex.data;

import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FIRE;
import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FLYING;

import com.pokemon.pokedex.services.model.Pokemon;
import java.util.List;

public class PokemonData {

  public static final Pokemon POKEMON1 = Pokemon.builder()
    .id(1)
    .name("Pokemon1")
    .types(List.of(FIRE, FLYING))
    .maxCP(43)
    .maxHP(34)
    .build();

  public static final Pokemon POKEMON2 = Pokemon.builder()
    .id(2)
    .name("Pokemon2")
    .types(List.of(FLYING))
    .maxCP(65)
    .maxHP(39)
    .build();

}
