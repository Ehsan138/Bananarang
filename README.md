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

## Language and Important Classes

Bananarang is developed in Java, utilizing JavaFX for the graphical user interface. The game's code demonstrates the use of inheritance and various classes, such as:

- GameObject: An abstract class representing game objects.
- DynamicGameObject: An abstract class extending GameObject, representing dynamic game objects.
- Chocolate and ChiliPepper: Classes extending DynamicGameObject for their respective objects.

