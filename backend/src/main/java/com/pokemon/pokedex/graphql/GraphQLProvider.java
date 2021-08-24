package com.pokemon.pokedex.graphql;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLProvider {

  @Autowired
  GraphQLPokemonDataFetchers graphQLPokemonDataFetchers;

  private GraphQL graphQL;

  @Bean
  public GraphQL graphQL() {
    return graphQL;
  }

  @PostConstruct
  public void init() throws IOException {
    var url = Resources.getResource("schema.graphql");
    String sdl = Resources.toString(url, Charsets.UTF_8);
    var graphQLSchema = buildSchema(sdl);
    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private GraphQLSchema buildSchema(String sdl) {
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
    RuntimeWiring runtimeWiring = buildWiring();
    var schemaGenerator = new SchemaGenerator();
    return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
  }

  private RuntimeWiring buildWiring() {
    return RuntimeWiring.newRuntimeWiring()
      .type(newTypeWiring("Query")
        .dataFetcher("pokemons", graphQLPokemonDataFetchers.getPokemonsByFilterDataFetcher())
        .dataFetcher("pokemonById", graphQLPokemonDataFetchers.getPokemonByIdDataFetcher())
        .dataFetcher("pokemonByName", graphQLPokemonDataFetchers.getPokemonByNameDataFetcher())
        .dataFetcher("pokemonTypes", graphQLPokemonDataFetchers.getPokemonTypesDataFetcher()))
      .type(newTypeWiring("Mutation")
        .dataFetcher("favoritePokemon", graphQLPokemonDataFetchers.setPokemonFavoriteDataFetcher())
        .dataFetcher("unFavoritePokemon", graphQLPokemonDataFetchers.setPokemonUnfavoriteDataFetcher()))
      .build();
  }

}
