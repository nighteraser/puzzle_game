# 8 Puzzle Game

This is a simple JavaFX project created as a freshman Object-Oriented Programming (OOP) project. The game involves arranging numbers in a puzzle to form a specific order.

## How to Play

1. Click on the "Start" button to begin the game.
2. Click on the numbers within the puzzle to move them around.
3. Continue rearranging the numbers until you achieve the correct order.

## How to Run

Make sure you have JavaFX installed before running the application.

### Installing JavaFX

[Download JavaFX](https://openjfx.io/) and follow the installation instructions for your operating system.

## Running the Game with Eclipse

1. Open Eclipse and navigate to the "Run" menu.
2. Select "Run Configurations."
3. Under "Java Application," locate your project configuration.
4. Go to the "Arguments" tab.
5. In the "VM arguments" section, paste the following:

```bash
--module-path /path/to/javafx-sdk-20/lib --add-modules=javafx.controls,javafx.fxml

