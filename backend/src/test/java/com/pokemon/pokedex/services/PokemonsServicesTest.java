package com.pokemon.pokedex.services;

import static com.pokemon.pokedex.data.PokemonData.POKEMON1;
import static com.pokemon.pokedex.data.PokemonData.POKEMON2;
import static com.pokemon.pokedex.model.PokemonTypeEnum.FIRE;
import static com.pokemon.pokedex.model.PokemonTypeEnum.FLYING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.rest.resource.PokemonConnection;
import com.pokemon.pokedex.model.Pokemon;
import com.pokemon.pokedex.model.PokemonFilter;
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
  void getAllPokemonWithPokemonFilter() {

    var pokemonFilter = new PokemonFilter();
    when(pokemonRepositoryMock.getPokemonsFiltered(pokemonFilter)).thenReturn(new PokemonConnection(0, 0, 0, List.of(POKEMON1)));

    //

    List<Pokemon> result = pokemonsServices.getPokemons(pokemonFilter).getEdges();

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(1, pokemon.getNumber());
    assertEquals("Pokemon1", pokemon.getName());
    assertEquals(2, pokemon.getTypes().size());
    assertTrue(pokemon.getTypes().containsAll(List.of(FIRE, FLYING)));
    assertEquals(43, pokemon.getMaxCP());
    assertEquals(34, pokemon.getMaxHP());

  }

  @Test
  void getPokemonFilteredByName() {

    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setNameSubstring("n2");

    when(pokemonRepositoryMock.getPokemonsFiltered(pokemonFilter)).thenReturn(new PokemonConnection(0, 0, 0, List.of(POKEMON1, POKEMON2)));

    //

    List<Pokemon> result = pokemonsServices.getPokemons(pokemonFilter).getEdges();

    //

    assertEquals(2, result.size());
    var pokemon = result.get(1);
    assertEquals(2, pokemon.getNumber());
    assertEquals("Pokemon2", pokemon.getName());
    assertEquals(1, pokemon.getTypes().size());
    assertTrue(pokemon.getTypes().contains(FLYING));
    assertEquals(65, pokemon.getMaxCP());
    assertEquals(39, pokemon.getMaxHP());

  }

}
