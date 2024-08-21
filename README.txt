This is a spring boot application that retrieves statistics for tennis players.

To run the application you need to have maven installed and added to your environment variables.
You can then navigate to the root of the project from the command line and execute mvn spring-boot:run.
From your browser go to http://localhost:2020/api/players in order to call the endpoints (API provided in players-api.yaml)

Next steps:
    - Add an authentication layer to the API
    - Configure a database to the project
    - Add an endpoints to create, update and delete a player
    - Enrich the /statistics endpoint to compute stats over specified players by id
