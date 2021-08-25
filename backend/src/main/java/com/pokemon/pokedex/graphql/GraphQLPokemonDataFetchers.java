package com.pokemon.pokedex.graphql;

import com.pokemon.pokedex.model.ActionFavoriteEnum;
import com.pokemon.pokedex.model.PokemonFilter;
import com.pokemon.pokedex.model.PokemonTypeEnum;
import com.pokemon.pokedex.repositories.PokemonRepository;
import com.pokemon.pokedex.services.PokemonsServices;
import graphql.schema.DataFetcher;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class GraphQLPokemonDataFetchers {

  PokemonsServices pokemonsServices;

  public GraphQLPokemonDataFetchers(PokemonsServices pokemonsServices) {
    this.pokemonsServices = pokemonsServices;
  }

  public DataFetcher getPokemonsByFilterDataFetcher() {
    return dataFetchingEnvironment -> {
      var pokemonFilter = mapFilter(dataFetchingEnvironment.getArgument("query"));
      return pokemonsServices.getPokemons(pokemonFilter);
    };
  }

  public DataFetcher getPokemonByIdDataFetcher() {
    return dataFetchingEnvironment -> {
      var pokemonId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
      return pokemonsServices.getPokemonById(pokemonId).orElse(null);
    };
  }

  public DataFetcher getPokemonByNameDataFetcher() {
    return dataFetchingEnvironment -> {
      String pokemonName = dataFetchingEnvironment.getArgument("name");
      return pokemonsServices.getPokemonByName(pokemonName).orElse(null);
    };
  }

  public DataFetcher getPokemonTypesDataFetcher() {
    return dataFetchingEnvironment -> pokemonsServices.getAllPokemonTypes().stream().map(Enum::name).collect(Collectors.toList());
  }

  public DataFetcher setPokemonFavoriteDataFetcher() {
    return dataFetchingEnvironment -> {
      var pokemonId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
      return pokemonsServices.changeStatusFavorite(pokemonId, ActionFavoriteEnum.ADD);
    };
  }

  public DataFetcher setPokemonUnfavoriteDataFetcher() {
    return dataFetchingEnvironment -> {
      var pokemonId = Integer.parseInt(dataFetchingEnvironment.getArgument("id"));
      return pokemonsServices.changeStatusFavorite(pokemonId, ActionFavoriteEnum.DELETE);
    };
  }

  private PokemonFilter mapFilter(Map<String, Object> data) {
    var pokemonFilter = new PokemonFilter();
    pokemonFilter.setLimit((Integer) data.get("limit"));
    pokemonFilter.setOffset((Integer) data.get("offset"));
    if (data.containsKey("search")) {
      pokemonFilter.setNameSubstring((String) data.get("search"));
    }
    if (data.containsKey("filter")) {
      Map<String, Object> filter = (Map<String, Object>) data.get("filter");
      if (filter.containsKey("type")) {
        pokemonFilter.setType(PokemonTypeEnum.valueOf((String) filter.get("type")));
      }
      if (filter.containsKey("isFavorite")) {
        pokemonFilter.setIsFavorite((Boolean) filter.get("isFavorite"));
      }
    }
    return pokemonFilter;
  }

}
