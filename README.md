Fighting Wildfires

A Java console game where the player fights a spreading forest fire by dropping water on a grid, one turn at a time.

This was a mini-project for Essential Computing at Roskilde University. The board is a 10×8 grid randomly populated with trees, rocks, and water. Some trees start on fire, and each turn the player chooses a cell to drop water into:

- Water extinguishes fire on the chosen cell and spreads outward in all four directions until it hits a rock, existing water, or the edge of the board
- After the player's move, fire spreads: any tree adjacent to a burning cell has a 75% chance of catching fire
- Burning cells turn to empty ground the turn after they burn
- The game ends when there are no fires left; the number of surviving trees is the final score

Design:
The project is split into two classes:

- GridGame: manages the grid's state, including fire spread logic (with a random probability check per tree), water propagation in all four cardinal directions, and cell counting

- FightingFire: handles the game loop, random grid generation (using ArrayList and Collections.shuffle()), and player input, with validation to catch invalid or out-of-range input and re-prompt the player

How to Run

Requirements:
- Java JDK 8+

Compile:
javac FightingFire.java GridGame.java

Run:
java FightingFire
