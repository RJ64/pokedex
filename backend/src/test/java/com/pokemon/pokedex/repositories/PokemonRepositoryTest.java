package com.pokemon.pokedex.repositories;

import static com.pokemon.pokedex.services.model.PokemonTypeEnum.FLYING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pokemon.pokedex.services.model.Pokemon;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PokemonRepositoryTest {

  private PokemonRepository pokemonRepository;

  @BeforeEach
  void setUp() {
    pokemonRepository = new PokemonRepository();
  }

  @Test
  void getAllPokemon() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(null, null);

    //

    assertEquals(2, result.size());
    var pokemon = result.get(0);
    assertEquals(1, pokemon.getId());
    assertEquals("Pikachu", pokemon.getName());
    assertEquals(0, pokemon.getType().size());
    assertEquals(0, pokemon.getCp());
    assertEquals(0, pokemon.getHp());

  }

  @Test
  void getPokemonFilteredByName() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered("rai", null);

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(2, pokemon.getId());
    assertEquals("Raichu", pokemon.getName());
    assertEquals(1, pokemon.getType().size());
    assertTrue(pokemon.getType().contains(FLYING));
    assertEquals(0, pokemon.getCp());
    assertEquals(0, pokemon.getHp());

  }

  @Test
  void getPokemonFilteredByType() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(null, FLYING);

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(2, pokemon.getId());

  }

  @Test
  void getPokemonFilteredByNameAndType() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered("rai", FLYING);

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(2, pokemon.getId());

  }

}
