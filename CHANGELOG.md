# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.1] - 2022-03-29
### Added
- gradle-ci.yml action which runs gradle build and uploads code coverage report to codecov.io
### Changed
- All instances of "HP" changed to "Hp"
- CI migrated from Travis CI to Github Actions

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

[0.2.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.2.0
[0.1.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.1.0
[0.0.0]: https://github.com/noah-owens/Character-Forge/releases/tag/v0.0.0