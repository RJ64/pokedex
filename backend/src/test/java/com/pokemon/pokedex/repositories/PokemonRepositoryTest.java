package com.pokemon.pokedex.repositories;

import static com.pokemon.pokedex.services.model.PokemonTypeEnum.ELECTRIC;
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
    pokemonRepository = new PokemonRepository("./src/test/java/com/pokemon/pokedex/data/pokemons.json");
  }

  @Test
  void getAllPokemon() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(null, null);

    //

    assertEquals(151, result.size());
    var pokemon = result.get(24);
    assertEquals(25, pokemon.getId());
    assertEquals("Pikachu", pokemon.getName());
    assertEquals(1, pokemon.getTypes().size());
    assertEquals(777, pokemon.getMaxCP());
    assertEquals(887, pokemon.getMaxHP());

  }

  @Test
  void getPokemonFilteredByName() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered("rai", null);

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(26, pokemon.getId());
    assertEquals("Raichu", pokemon.getName());
    assertEquals(1, pokemon.getTypes().size());
    assertTrue(pokemon.getTypes().contains(ELECTRIC));
    assertEquals(1859, pokemon.getMaxCP());
    assertEquals(2028, pokemon.getMaxHP());

  }

  @Test
  void getPokemonFilteredByType() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(null, FLYING);

    //

    assertEquals(19, result.size());

  }

  @Test
  void getPokemonFilteredByNameAndType() {

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered("pid", FLYING);

    //

    assertEquals(3, result.size());

  }

}
