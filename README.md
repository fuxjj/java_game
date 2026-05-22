# SOFTWARE DESIGN AND ARCHITECTURE

Board Game Simulation - README

## Overview
This project is a Java console simulation of a board game loosely based on Snakes and Ladders. Two or Four players start at certain ends of a board (5x5 or 6x6) and take turns moving toward eachothers start positions. This has a number of variations that alter the game rules. 

## How to run
Open the project in IntelliJ IDEA and run `Main.java`. This application runs a sequence of games, each with a different combination of variations with no user input required. Each game is configured in code and outputs its progress to the console.

To test specific dice sequences `FixedDiceShaker` accepts a sequence of predetermined rolls: 

` DiceShaker dice = new FixedDiceShaker(12, 10, 12); `

To use random dice, swap in either:

`DiceShaker dice = new RandomDoubleDiceShaker();`
or
`DiceShaker dice = new RandomSingleDiceShaker();`

## Variations implemented
There were 5 requested variations for this assignment: 
- Single Die
- Exact End
- Hit Rule
- Teleport
- Large board

### Single Die
The `DiceShaker` interface abstracts the dice rolling mechanism, swapping between one and two dice is achieved by using different implementation, in `RandomSingleDiceShaker` or `RandomDoubleDiceShaker`.

### Exact End
Controlled by the `EndRule` strategy. Two implementations are provided: 
- `Overshoot End` - Player wins by landing on or past the end position
- `Exact end`  - If the player rolls more than they require for the end, they bounce back along the track by the amount they overshot.

### Hit Rule
Controlled by the `HitRule` strategy. Two implementations are provided: 
- `IgnoreHit` - Multiple players may occupy the same spot
- `ForfeitHit` - If a player lands on an occupied position, their turn is forfeit and they remain where they were.

### Teleport (Wormholes)
Controlled by the `TeleportRule` strategy. Two implementations are provided: 
- `IgnoreTeleport` - Wormholes have no effect.
- `Teleport` - If a player lands on either end of a wormhole, they are teleported to the other end.

Wormholes are represented as `Wormhole` Value objects and validated on construction to ensure they connect two distinct positions.

### Large Board
The game supports a 6x6 board with four players - Red, Blue, Yellow and Green. Each player follows an Ox-plow track defined in `TrackBuilder`. Because Blue and Green do not follow a simple sequential path (forward and backward), these two players will store their full track as an ordered list of positions, making movement implicit and removing the need for direction logic.

## Design Patterns
### Strategy pattern
The strategy pattern is the primary pattern used throughout this project. It is applied in four places to handle the variations requested by the designers: 

| Strategy Interface | Implementations | Purpose |
|--------------------|-----------------|---------|
| `DiceShaker`       | `RandomSingleDiceShaker`, `RandomDoubleDiceShaker`, `FixedDiceShaker` | Abstract dice rolling |
| `EndRule`          | `OvershootEnd`, `ExactEnd` | Control end position behaviour |
| `HitRule`          | `IgnoreHit`, `ForfeitHit`| Control collision behaviour |
| `TeleportRule`     | `IgnoreTeleport`, `Teleport` | Control wormhole behaviour |

Each strategy is injected into the `Game` class at construction time, meaning any combination of variations can be run without modifying the game logic. This matches the brief requirement that the simulation can be run with any combination of variations. 

## Clean Architecture
The assignment specified using Spring Boot to refactor and achieve clean architecture as follows;
- *Domain layer* - Core game entities and rules: `Game`, `Player`, `Board` and `Wormhole`.
- *Use case layer* - Orchestrates game play: `PlayGame`, `ReplayGame`.
- *Infrastructure layer* - Handles I/O and persistence: console output, `FixedDiceShaker`, file or in memory game storage.

Unfortunately, I didnt get far enough within the assignment brief and lab sheets to be able to implement Clean Architecture with Spring Boot. 

# Evaluation 

## What went well
The strategy pattern proved to be an excellent fit for this problem, because each variation is encapsulated in its own class. This allows for adding or swapping variations without changing the core `Game` class. The fixed dice shaker (`FixedDiceShaker`) was particularly valuable during development as it allowed exact reproduction of the example games from the brief.

Using a track based movement system simplified the movement logic considerably and was absolutely vital for the 4-player board where Blue and Green follow non sequential paths. 

## Limitations and room for improvement
- The `Game` constructor currently takes many parameters, I believe a builder pattern would improve readability and make it easier to add further variations
- Console output is currently mixed into the game logic and strategy  classes, I believe this would have been fixed had I had the time to fully implement the Clean Architecture refactor. 
- Advanced features such as Game states adn Saving or replaying have not been attempted.
