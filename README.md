# AI Crate Digger

[![Java CI with Maven](https://github.com/mcorr98/music-sample-finder/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/mcorr98/music-sample-finder/actions/workflows/maven.yml)

This project is a personal experiment in music discovery/DJing and software development.
It retrieves track information from the [Discogs API](https://www.discogs.com/developers/).

## Features
- Search tracks by artist/genre
- Get AI-powered recommendations for similar tracks
- Export results or store in database

## Tech Stack
- Java
- Maven
- External APIs

## Roadmap
- [x] Basic project setup
- [x] Discogs API integration
- [ ] Database
- [ ] Containerisation
- [ ] AI recommendations

## Note
Not production ready - this is a demo project to showcase problem solving, APIs, AI integration, and Java ability.

## How to run

### Prerequisites
Java 17+ installed (java -version to check)
Maven 3.8+ installed (mvn -v to check) 

### Clone the repository
git clone https://github.com/mcorr98/music-sample-finder.git
cd music-sample-finder

### Run the tests
mvn clean test

### Run the CLI app
mvn clean package
java -cp target/music-sample-finder-1.0-SNAPSHOT.jar cli.App


## Acknowledgements

- Music data powered by the [Discogs API](https://www.discogs.com/developers/).
- Thanks to the Discogs community for maintaining such a rich database.

