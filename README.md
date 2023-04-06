# Bananarang Game

## Creators

- Ehsan Emadi
- Michelle Kwok

## Overview

Bananarang is a fun and challenging 2D game where players control a monkey who throws banana boomerangs at moving chocolates to earn points. Inspired by elements of Super Mario Party, Boomerang Fu, and Fruit Ninja, the game features various types of chocolates, obstacles, and increasing levels of difficulty.

## How to Play

- Move the monkey left and right to aim.
- Throw the banana boomerang at moving chocolates to earn points.
- Some chocolates are worth more points than regular ones.
- Certain chocolates grant the ability to throw two bananas at once for a limited time.
- Reach the target chocolate count for each level within the time limit to progress.
- As levels increase, the speed of moving objects will also increase.
- Players have 3 lives (bananas). Avoid hitting chili peppers, as they will cause a loss of one life.

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

