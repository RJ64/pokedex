# How to run
Go to the path containing this file in the terminal and execute:
```shell
gradle build
java -jar build/libs/pokedex-0.0.1-SNAPSHOT.jar
```

# What can you do
A GraphQL version of the API is available with all the actions defined in schema.graphql

and a 'classic' REST API is available were you have the endpoints:
```shell
GET /pokemons?name=<pokemonNameContainsSubstring>&type=<pokemonWithType> 
```
Returns a list of pokemon. You can filter the list searching for part of a pokemons name
(query parameter `name`) and/or by the type of pokemon looking for (query parameter `type`)
```shell
POST /pokemons/{pokemonId}/favorite/{action:ADD|DELETE} 
```
Sets or unsets a pokemon as favorite


# Development

At first i started the project creating a "typical" REST API using Spring. I created the
Controller, the Service and the Repository. In the first iteration, the repository only
contained 2 pokemon hardcoded and the controller could only return the list of pokemon.

I continue expanding the controller, service and repository to return the list of
pokemon filtering by a substring of the name and/or type.

To continue with setting a pokemon as favorite, i had first to iterate the repository.
I opted for reading the whole list of pokemons from a json file parsing it to a class. 
Once done this, an endpoint to mark or unmark a pokemon as favorite was created in 
the controller.

The time was running out so after checking the features, i opted to continue with creating
a GraphQL version of the API. I had never worked with GraphQL so curiosity got the
better of me. It took some time to understand how it worked and how to implement it
but after some coding and testing i was able to created the GraphQL version with all
the queries and mutations that are set in schema.graphql.


# Comments after exercise

Because i am totally new to GraphQL, it took me some time to understand it and make it 
work but by the time i finished the implementations it was late, so i opted to end it, but
i would've liked to check corner cases and create more tests for the GraphQL version.

I couldn't get in time to use a real database. For the type of data we have and how
it is being used, i would probably go with a non-relational table such as mongoDB
because there are not that many pokemons and right now it is all loaded in memory on 
load so it would be in muy opinion the best option.

I had fun doing this exercise and i enjoyed a lot learning and working on GraphQL. I had
never used it and maybe in the future i will use it in another project.
