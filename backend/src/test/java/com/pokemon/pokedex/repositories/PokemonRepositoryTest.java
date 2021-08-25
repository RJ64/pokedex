package com.pokemon.pokedex.repositories;

import static com.pokemon.pokedex.model.PokemonTypeEnum.ELECTRIC;
import static com.pokemon.pokedex.model.PokemonTypeEnum.FLYING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pokemon.pokedex.model.Pokemon;
import com.pokemon.pokedex.model.PokemonFilter;
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

    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setLimit(155);

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(pokemonFilter).getEdges();

    //

    assertEquals(151, result.size());
    var pokemon = result.get(24);
    assertEquals(25, pokemon.getNumber());
    assertEquals("Pikachu", pokemon.getName());
    assertEquals(1, pokemon.getTypes().size());
    assertEquals(777, pokemon.getMaxCP());
    assertEquals(887, pokemon.getMaxHP());

  }

  @Test
  void getPokemonFilteredByName() {

    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setNameSubstring("rai");

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(pokemonFilter).getEdges();

    //

    assertEquals(1, result.size());
    var pokemon = result.get(0);
    assertEquals(26, pokemon.getNumber());
    assertEquals("Raichu", pokemon.getName());
    assertEquals(1, pokemon.getTypes().size());
    assertTrue(pokemon.getTypes().contains(ELECTRIC));
    assertEquals(1859, pokemon.getMaxCP());
    assertEquals(2028, pokemon.getMaxHP());

  }

  @Test
  void getPokemonFilteredByType() {

    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setLimit(155);
    pokemonFilter.setType(FLYING);

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(pokemonFilter).getEdges();

    //

    assertEquals(19, result.size());

  }

  @Test
  void getPokemonFilteredByNameAndType() {

    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setNameSubstring("pid");
    pokemonFilter.setType(FLYING);

    //

    List<Pokemon> result = pokemonRepository.getPokemonsFiltered(pokemonFilter).getEdges();

    //

    assertEquals(3, result.size());

  }

  @Test
  void addAndRemovePokemonFromFavorite() {

    removeAllPokemonFromFavorite();

    pokemonRepository.addToFavorite(25);
    List<Pokemon> favoritePokemons = getFavoritePokemon();
    assertEquals(1, favoritePokemons.size());
    assertEquals(25, favoritePokemons.get(0).getNumber());

    pokemonRepository.removeFromFavorite(25);
    favoritePokemons = getFavoritePokemon();
    assertEquals(0, favoritePokemons.size());

  }

  private List<Pokemon> getFavoritePokemon() {
    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setIsFavorite(true);
    return pokemonRepository.getPokemonsFiltered(pokemonFilter).getEdges();
  }

  private void removeAllPokemonFromFavorite() {
    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setLimit(152);
    List<Pokemon> pokemons = pokemonRepository.getPokemonsFiltered(pokemonFilter).getEdges();
    pokemons.forEach(pokemon -> pokemonRepository.removeFromFavorite(pokemon.getNumber()));
  }

}
