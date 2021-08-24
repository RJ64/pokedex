package com.pokemon.pokedex.rest.converter;

import static com.pokemon.pokedex.data.PokemonData.POKEMON1;
import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FIRE;
import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FLYING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pokemon.pokedex.data.PokemonData;
import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.rest.resource.PokemonBasicResource;
import com.pokemon.pokedex.services.PokemonsServices;
import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.model.PokemonTypeEnum;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PokemonConverterTest {

  @Test
  void getAllPokemon() {

    //

    List<PokemonBasicResource> result = PokemonConverter.toBasicPokemonList(List.of(POKEMON1));

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(1, pokemon.getId());
    assertEquals("Pokemon1", pokemon.getName());
    assertEquals(2, pokemon.getTypes().size());
    assertTrue(pokemon.getTypes().containsAll(List.of("FIRE", "FLYING")));
    assertFalse(pokemon.isFavorite());

  }

}
