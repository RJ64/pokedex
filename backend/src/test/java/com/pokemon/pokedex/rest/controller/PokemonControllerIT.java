package com.pokemon.pokedex.rest.controller;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.rest.controller.PokemonControllerIT.Configuration;
import com.pokemon.pokedex.services.PokemonsServices;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PokemonController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(Configuration.class)
public class PokemonControllerIT {

  @Autowired
  private MockMvc mvc;

  @Test
  void shouldReturnAllPokemon() throws Exception {

    mvc.perform(get("/pokemons"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)))
      .andExpect(jsonPath("$[0].id", equalTo(1)))
      .andExpect(jsonPath("$[0].name", equalTo("Pikachu")))
      .andExpect(jsonPath("$[0].image", equalTo(null)))
      .andExpect(jsonPath("$[0].types", equalTo(emptyList())))
      .andExpect(jsonPath("$[0].favorite", equalTo(false)))
      .andExpect(jsonPath("$[1].id", equalTo(2)));

  }

  @TestConfiguration
  public static class Configuration {

    @Bean
    public PokemonRepository pokemonRepository() {
      return new PokemonRepository();
    }

    @Bean
    public PokemonsServices pokemonsServices(PokemonRepository pokemonRepository) {
      return new PokemonsServices(pokemonRepository);
    }

  }
}
