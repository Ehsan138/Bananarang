# Bananarang Game

## Creators

- Ehsan Emadi
- Michelle Kwok

## Overview

In this game, you take control of a monkey aiming to collect as many chocolates as possible. Be careful not to touch the chili peppers, as they will cost you lives! Navigate the monkey using the A and D keys and start the game by pressing the Play button.

## How To Play

1. Press the Play button to start the game.
2. Move the monkey left by pressing the A key.
3. Move the monkey right by pressing the D key.
4. Collect chocolates by touching them with the monkey.
5. Avoid chili peppers, as they will cost you lives.
6. Accumulate as many points as possible by collecting chocolates.

## Setup and Configuration

After cloning the project, you need to update the paths of images and sound files based on your operating system:

### Windows

Replace all image and sound file paths with the absolute path. To do this, right-click on the desired file, choose "Select Path / Reference," and then click "Absolute Path."

### macOS

For images, replace the path with the following format: `/nameOfTheFile`. For the mp3 file, follow the same steps as for Windows users.

## Language

Bananarang is developed in Java, utilizing JavaFX for the graphical user interface. The game's code demonstrates the use of inheritance and various classes, such as:

## Classes

The following classes are included in the Bananarang game:

### GameObject.java

A base class for all game objects, including the monkey, chocolates, and chili peppers. It handles rendering game objects on the screen.

### DynamicGameObject.java

A subclass of GameObject, representing dynamic game objects (i.e., chocolates and chili peppers) that move across the screen.

### Monkey.java

A subclass of GameObject representing the monkey character. The monkey moves left and right using the 'A' and 'D' keys, respectively.

### Chocolate.java

A subclass of DynamicGameObject representing falling chocolates. The monkey scores points by catching them.

### ChiliPepper.java

A subclass of DynamicGameObject representing falling chili peppers. The monkey loses a life (chili counter) if it catches one.

### Main.java

The main class, responsible for launching the JavaFX application and setting up the primary stage.

### GameViewController.java

The controller class for the game view, responsible for handling game logic, user input, and UI updates.

## Game Mechanics

- The monkey moves left and right to catch falling chocolates and avoid chili peppers.
- Catching a chocolate increases the score by one point.
- Catching a chili pepper decreases the chili counter by one. When the chili counter reaches zero, the game is over.
- The game can be paused and resumed using a button on the screen.
- The speed of falling chocolates and chili peppers increases over time.

## Assets

- monkey.png: The monkey character's image.
- chocolate.png: The chocolate image.
- chili_pepper.png: The chili pepper image.
- jungle.jpg: The background image for the game.
- In-the-Past.mp3: Background music for the game.
- Jungle Land.ttf: Custom font used for game labels and buttons.


