Kotlin-springboot-demo
---

* This is a demo project for learning how to write a springboot app using Kotlin
* Stack:
  * Kotlin
  * Springboot
  * GraphQL
  * MongoDB
  * Docker

* Environment variables:
  * ```bash
    export MONGO_USER=somevalue
    export MONGO_PASSWORD=someothervalue 
    ```
* Setup:
  1. Have docker installed and running
  2. Directly export environment variables or create a .mongoenv file in your home directory then run `source ~/.mongoenv` to set the variables
  3. Run `./docker-run.bash` in this directory to start the mongodb container

> Will probably change the env file from being in the home directory eventually

### Webapp

Challenge run tracker

Entities
  - User
  - Game
    - name
    - platform
  - Challenge run
    - game
    - rules
    - difficulty
    - time