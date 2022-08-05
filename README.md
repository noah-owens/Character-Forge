# Character Forge - v0.5.0

![Build Status](https://github.com/noah-owens/Character-Forge/actions/workflows/java-ci.yml/badge.svg)
[![codecov](https://codecov.io/gh/noah-owens/Character-Forge/branch/main/graph/badge.svg?token=4P24PAUOWH)](https://codecov.io/gh/noah-owens/Character-Forge)

## Progress Note

After a lot of consideration, this project of 5 months is being restructured from the ground up to provide more robust support for multiclassing, spells, and higher level play.
I have also decided to focus on creating more readable and organized boilerplate code, rather than avoiding it entirely with Lombok. Lombok has been incredibly convenient, but writing
my own accessor and mutator methods has so far led to more thoughtful and cohesive design.

## About

Character Forge is an app for creating D&amp;D characters and NPCs based on a completely hands-off approach. Certain features may be specified, but it's never a requirement! Character Forge has been designed with accessibility and ease of use in mind, so that anyone on any system can install, run, and have a random character or NPC statblock ready to go at a moment's notice.

### Built With
- [Java](https://www.java.com/en/) (JDK 16.0.2)
- [Gradle 7.2](https://gradle.org/install/)  
- [IntelliJ IDEA 2020.3.4](https://www.jetbrains.com/idea/download/other.html) (This version is bundled with the lombok plugin)
- [Gluon Scene Builder](https://gluonhq.com/products/scene-builder/)
- Windows 10

## Getting Started

Until I reach my first production release, I will not be providing a JAR. That doesn't mean you can't try it out though! To install the program, first open your terminal and clone the repository to your machine using:

```
git clone https://github.com/noah-owens/Character-Forge.git
```

Next, make sure you have an up-to-date [Gradle installation](https://gradle.org/install/) and [JDK](https://docs.oracle.com/en/java/javase/11/install/). Navigate to the project directory in your command prompt, then you're all good to build and run the app using Gradle:

```
gradle build
gradle run
```

## Contributing

Character Forge is not currently accepting contributions. 

## Contact 

Noah Owens - [shiroitachistudio@gmail.com](mailto:shiroitachistudio@gmail.com)

Project Link - [https://github.com/noah-owens/Character-Forge](https://github.com/noah-owens/Character-Forge)

## Support

If you like what's going on here, feel free to support me on [Ko-Fi](https://ko-fi.com/shiroitachistudio)! I'd really appreciate it.

## Acknowledgements

- [Jeroen van Erp](https://github.com/hierynomus) - For their [license plugin](https://github.com/noah-owens/Character-Forge) for Gradle
- [Othneil Drew](https://github.com/othneildrew) - For the [README template](https://github.com/othneildrew/Best-README-Template/blob/master/BLANK_README.md) I pulled heavy inspiration from
- [Gitmoji](https://gitmoji.dev/) - For an easy reference to keep commits organized
- [InversifyJS](https://github.com/inversify/inversify-basic-example) - For the [pull request template](https://github.com/inversify/inversify-basic-example/blob/master/PULL_REQUEST_TEMPLATE.md) I edited to suit this project

## License

Copyright (c) 2022 Noah Owens

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
