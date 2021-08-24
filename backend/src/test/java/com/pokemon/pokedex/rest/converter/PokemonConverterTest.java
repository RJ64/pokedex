package com.pokemon.pokedex.rest.converter;

import static com.pokemon.pokedex.data.PokemonData.POKEMON1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.pokemon.pokedex.model.PokemonBasicResource;
import java.util.List;
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
