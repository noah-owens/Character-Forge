# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.5.0] - 2022-05-26
### Removed
- All Classes except App.java temporarily removed and moved to `/'legacy code'` 

## [0.4.0] - 2022-04-07
### Added
- Methods for determining random starting equipment
### Changed
- Structural elements of various data classes (Spells, Race, CharClass)
### Removed
- Equipment data class

## [0.3.1] - 2022-04-02
### Added
- jsonRead() added to deserialize from json files
- DataLoader.java and RandomHelper.java
### Changed
- IOManager<T> now takes a generic type
- jsonWrite() and appendFile() work with generic types

## [0.3.0] - 2022-04-02
### Added
- Gson and Log4j dependencies 
- IOManager class for handling gson serialization/deserialization
### Changed
- Package structure
- Add new directories in src/main/resources/

## [0.2.1] - 2022-03-29
### Added
- gradle-ci.yml action which runs gradle build and uploads code coverage report to codecov.io
### Changed
- All instances of "HP" changed to "Hp"
- CI migrated from Travis CI to Github Actions
### Removed
- .travis.yml workflow file

## [0.2.0] - 2022-03-27
### Added
- Documentation markdown files CONTRIBUTING and CODE_OF_CONDUCT
- UI Classes, resources, and dependencies/plugins
### Changed
- Unit testing expanded to more classes
- Some classes received functionality tweaks.

## [0.1.0] - 2022-01-7
### Added
- Continuous integration set up. Added .travis.yml to handle updating README badges
- Unit tests and methods for PlayerCharacter and Stat
### Changed
- README contains actual information now, as well as build status and code coverage badges

## [0.0.0] - 2022-01-5
### Added
- CHANGELOG.md to track relevant changes to the project
- Background.java, CharClass.java, PlayerCharacter.java, Race.java, Stat.java
### Changed
- Lombok dependencies added to build.gradle
- Plugins for JavaFX and Jacoco added to build.gradle

[0.5.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.5.0
[0.4.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.4.0
[0.3.1]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.3.1
[0.3.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.3.0
[0.2.1]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.2.1
[0.2.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.2.0
[0.1.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.1.0
[0.0.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.0.0