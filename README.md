# AsteroidsMNST 
Asteroids game for the Component based systems course (T510035101)

## How to start the game.
1. Run `mvn clean install`
2. You need to run the Score service first, before starting the application. 
   2. move to `cd .\Scoring\` and run the service with `mvn spring-boot:run`.
3. In a new terminal, start the application with `java --module-path mods-mvn --class-path "libs/*" --add-modules ALL-SYSTEM --module=Core/dk.sdu.core.Main` -command.

