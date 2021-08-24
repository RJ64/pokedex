package com.pokemon.pokedex.services;

import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FIRE;
import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FLYING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.services.model.Pokemon;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonsServicesTest {

  private PokemonRepository pokemonRepositoryMock;
  private PokemonsServices pokemonsServices;

  @BeforeEach
  void setUp() {
    pokemonRepositoryMock = mock(PokemonRepository.class);
    pokemonsServices = new PokemonsServices(pokemonRepositoryMock);
  }

  @Test
  void getAllPokemon() {

    var pokemon1 = Pokemon.builder()
      .id(1)
      .name("Pokemon1")
      .type(List.of(FIRE, FLYING))
      .cp(43)
      .hp(34)
      .build();

    when(pokemonRepositoryMock.getAllPokemon()).thenReturn(List.of(pokemon1));

    //

    List<Pokemon> result = pokemonsServices.getPokemons();

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(1, pokemon.getId());
    assertEquals("Pokemon1", pokemon.getName());
    assertEquals(2, pokemon.getType().size());
    assertTrue(pokemon.getType().containsAll(List.of(FIRE, FLYING)));
    assertEquals(43, pokemon.getCp());
    assertEquals(34, pokemon.getHp());

  }

}
