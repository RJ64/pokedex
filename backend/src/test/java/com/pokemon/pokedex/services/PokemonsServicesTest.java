package com.pokemon.pokedex.services;

import static com.pokemon.pokedex.data.PokemonData.POKEMON1;
import static com.pokemon.pokedex.data.PokemonData.POKEMON2;
import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FIRE;
import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FLYING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.services.model.Pokemon;
import com.pokemon.pokedex.services.model.PokemonFilter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PokemonsServicesTest {

  private PokemonRepository pokemonRepositoryMock;
  private PokemonsServices pokemonsServices;

  @BeforeEach
  void setUp() {
    pokemonRepositoryMock = mock(PokemonRepository.class);
    pokemonsServices = new PokemonsServices(pokemonRepositoryMock);
  }

  @Test
  void getAllPokemon() {

    when(pokemonRepositoryMock.getPokemonsFiltered(null, null)).thenReturn(List.of(POKEMON1));

    //

    List<Pokemon> result = pokemonsServices.getPokemons(PokemonFilter.builder().build());

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

  @Test
  void getPokemonFilteredByName() {

    PokemonFilter filter = PokemonFilter.builder().nameSubstring("n2").build();

    when(pokemonRepositoryMock.getPokemonsFiltered(filter.getNameSubstring(), null)).thenReturn(List.of(POKEMON1, POKEMON2));

    //

    List<Pokemon> result = pokemonsServices.getPokemons(filter);

    //

    assertEquals(2, result.size());
    var pokemon = result.get(1);
    assertEquals(2, pokemon.getId());
    assertEquals("Pokemon2", pokemon.getName());
    assertEquals(1, pokemon.getType().size());
    assertTrue(pokemon.getType().contains(FLYING));
    assertEquals(65, pokemon.getCp());
    assertEquals(39, pokemon.getHp());

  }

}
