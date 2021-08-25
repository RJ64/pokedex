package com.pokemon.pokedex.rest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.rest.controller.PokemonControllerIT.Configuration;
import com.pokemon.pokedex.services.PokemonsServices;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PokemonController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(Configuration.class)
class PokemonControllerIT {

  @Autowired
  private MockMvc mvc;

  @Test
  void shouldReturnAllPokemon() throws Exception {

    mvc.perform(get("/pokemons"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(151)))
      .andExpect(jsonPath("$[24].id", equalTo(25)))
      .andExpect(jsonPath("$[24].name", equalTo("Pikachu")))
      .andExpect(jsonPath("$[24].types", equalTo(List.of("ELECTRIC"))))
      .andExpect(jsonPath("$[24].favorite", equalTo(false)));

  }

  @Test
  void shouldReturnOnePokemonFilteredByName() throws Exception {

    mvc.perform(get("/pokemons").param("name", "rai"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].id", equalTo(26)));

  }

  @Test
  void shouldReturnOnePokemonFilteredByType() throws Exception {

    mvc.perform(get("/pokemons").param("type", "electric"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(9)));

  }

  @Test
  void typeFilteringNotValid() throws Exception {

    mvc.perform(get("/pokemons").param("type", "invented"))
      .andExpect(status().isBadRequest());

  }

  @Test
  void shouldReturnOnePokemonFilteredByTypeAndName() throws Exception {

    mvc.perform(get("/pokemons").param("type", "electric").param("name", "CHU"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)));

  }

  @Test
  void addRemoveFromFavorite() throws Exception {

    mvc.perform(post("/pokemons/25/favorite/add"))
      .andExpect(status().isOk());

    mvc.perform(get("/pokemons"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.[?(@.favorite==true)]", hasSize(1)));

    mvc.perform(post("/pokemons/25/favorite/delete"))
      .andExpect(status().isOk());

    mvc.perform(get("/pokemons"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.[?(@.favorite==true)]", hasSize(0)));

  }

  @TestConfiguration
  public static class Configuration {

    @Bean
    public PokemonRepository pokemonRepository() {
      return new PokemonRepository("./src/test/java/com/pokemon/pokedex/data/pokemons.json");
    }

    @Bean
    public PokemonsServices pokemonsServices(PokemonRepository pokemonRepository) {
      return new PokemonsServices(pokemonRepository);
    }

  }
}
