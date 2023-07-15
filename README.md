## Snake Game

This is a simple Snake game implemented in Java using the Swing library. The game allows players to control a snake and navigate it around the game board to eat apples and grow longer. The objective of the game is to eat as many apples as possible without colliding with the snake's own body or the game board's boundaries.

## How to Play

1. Use the arrow keys (left, right, up, and down) to control the snake's movement.
2. The snake will move in the direction indicated by the arrow key pressed.
3. The snake will continue to move until it collides with its own body or the boundaries of the game board.
4. The snake can eat apples by moving over them. Each apple eaten will increase the snake's length.
5. The game ends when the snake collides with its own body or the boundaries of the game board.
6. The score is calculated based on the number of apples eaten (each apple is worth 100 points).

## How to Run

To run the Snake game, follow these steps:

1. Compile the Java source files.
2. Execute the compiled program.

## Dependencies

This game has the following dependencies:

- Java Swing library

## Files

The game consists of the following files:

- `Board.java`: This class represents the game board and handles the game logic and user input.
- `SnakeGame.java`: This class is the entry point of the game and creates the game window.

## Usage

To use this code, follow these steps:

1. Copy the code and save it into a Java file with the same name as the class (`Board.java` and `SnakeGame.java`).
2. Compile the Java source files using a Java compiler.
3. Execute the compiled program to start the Snake game.

## Customization

You can customize certain aspects of the game by modifying the following variables in the `Board` class:

- `WIDTH` and `HEIGHT`: The dimensions of the game board.
- `MAX_DOTS`: The maximum length of the snake.
- `DELAY`: The delay between each game update (in milliseconds).
- `startButton`: The appearance and position of the "Play" button.
- `text`: The appearance and position of the game title.

You can also replace the images used in the game (`body.png`, `head.png`, and `apple.png`) with your own images. Make sure to update the file paths in the `loadImages` method accordingly.

## Acknowledgements

This game was created by Mayur Hanwate and is provided as an open-source project. Feel free to modify, distribute, and use this code for learning or personal purposes.

Have fun playing the Snake game!
