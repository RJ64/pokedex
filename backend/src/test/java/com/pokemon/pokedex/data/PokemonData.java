package com.pokemon.pokedex.data;

import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FIRE;
import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FLYING;

import com.pokemon.pokedex.services.model.Pokemon;
import java.util.List;

public class PokemonData {

  public static final Pokemon POKEMON1 = Pokemon.builder()
    .id(1)
    .name("Pokemon1")
    .image("image1")
    .type(List.of(FIRE, FLYING))
    .cp(43)
    .hp(34)
    .build();

  public static final Pokemon POKEMON2 = Pokemon.builder()
    .id(2)
    .name("Pokemon2")
    .image("image2")
    .type(List.of(FLYING))
    .cp(65)
    .hp(39)
    .build();

}
