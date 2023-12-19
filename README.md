# Text-Based Location Mapping Game

This Java project is a text-based location mapping game that allows users to navigate through different locations, each with its own description and available exits.

## Features

- Multiple locations with descriptions
- Dynamic mapping of directions between locations
- Logging functionality (Console and File logging)
- Simple text-based interface

## Table of Contents

1. [Installation](#installation)
2. [Usage](#usage)
3. [Files and Directory Structure](#files-and-directory-structure)
4. [Dependencies](#dependencies)

## Installation

1. Clone the repository to your local machine.
    ```bash
    git clone https://github.com/ramya1907/Maps.git
    ```

2. Open the project in your preferred Java IDE.

## Usage

1. Run the `Mapping` class to start the game.
    ```bash
    java Mapping
    ```

2. Follow on-screen instructions to navigate through locations using specified directions.

## Files and Directory Structure

- `ConsoleLogger.java`: Implementation of the console logging.
- `FileLogger.java`: Implementation of file logging.
- `Location.java`: Represents a location with descriptions and exits.
- `LocationMap.java`: Manages the locations and directions, acts as a map.
- `Mapping.java`: Main class to run the game.
- `Logger.java`: Logger interface.
- `locations.txt`: File containing location details.
- `directions.txt`: File containing direction details.

## Dependencies

- No external dependencies.
