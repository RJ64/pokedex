package com.pokemon.pokedex.config;

import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.services.PokemonsServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class Config {

  @Bean
  public PokemonRepository pokemonRepository() {
    return new PokemonRepository("./src/main/resources/data/pokemons.json");
  }

  @Bean
  public PokemonsServices pokemonsServices(PokemonRepository pokemonRepository) {
    return new PokemonsServices(pokemonRepository);
  }

}
