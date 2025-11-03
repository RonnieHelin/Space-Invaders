# Space Invaders - JavaFX Edition

A classic Space Invaders game modernized with JavaFX. Originally based on the Java 2D games tutorial from [ZetCode](https://zetcode.com/javagames/), this project has been completely migrated from Swing to JavaFX for improved performance and modern UI capabilities.

## 🚀 Features

- **Modern JavaFX Graphics**: Hardware-accelerated rendering for smooth gameplay
- **Classic Gameplay**: Defend Earth from waves of alien invaders
- **Responsive Controls**: Arrow keys for movement, spacebar to fire
- **Retro Pixel Art**: Nostalgic graphics with modern performance

## 🎮 How to Play

- **Move Left/Right**: Use arrow keys to move your spaceship
- **Fire**: Press spacebar to shoot at the invaders
- **Objective**: Destroy all aliens before they reach the ground
- **Win Condition**: Eliminate all 24 aliens to win the game

## 📋 Prerequisites

- **Java 17+**: Required for JavaFX support
- **Maven 3.6+**: For dependency management and running the application

## 🏃‍♂️ Quick Start

### Running the Game

```bash
# Clone the repository
git clone https://github.com/RonnieHelin/Space-Invaders.git
cd Space-Invaders

# Run with Maven JavaFX plugin (recommended)
mvn javafx:run

# Alternative: Run with Maven exec plugin
mvn exec:java
```

### Building the Project

```bash
# Compile the project
mvn clean compile

# Package as JAR
mvn clean package
```

## 🔧 Technical Details

### Migration from Swing to JavaFX

This project has been completely modernized from Java Swing to JavaFX:

- **UI Framework**: Migrated from `JFrame/JPanel` to JavaFX `Application/Canvas`
- **Graphics**: Replaced AWT `Graphics2D` with JavaFX `GraphicsContext`
- **Animation**: Upgraded from manual `Thread` loops to JavaFX `AnimationTimer`
- **Event Handling**: Modernized from Swing `KeyAdapter` to JavaFX event handlers
- **Image Loading**: Updated from `ImageIcon` to JavaFX `Image` with stream-based loading

### Key Technologies

- **JavaFX 17**: Modern UI toolkit with hardware acceleration
- **Maven**: Build automation and dependency management
- **Java 17**: Latest LTS version with enhanced performance

### Project Structure

```
Space-Invaders/
├── src/main/java/spaceinvaders/
│   ├── SpaceInvaders.java    # Main JavaFX Application class
│   ├── Board.java            # Game canvas and main game logic
│   ├── Player.java           # Player spaceship implementation
│   ├── Alien.java            # Alien invader with bomb functionality
│   ├── Shot.java             # Player projectile
│   ├── Sprite.java           # Base class for all game objects
│   └── Commons.java          # Game constants and dimensions
├── resources/
│   ├── spacepix/            # Game sprites and images
│   ├── collisionpic/        # Explosion graphics
│   └── pacpic/              # Additional game assets
├── pom.xml                  # Maven configuration
└── README.md               # This file
```

## 🎨 Game Assets

The game includes pixel art sprites for:
- Player spaceship
- Multiple alien types
- Projectiles and explosions
- UI elements

All assets are included in the `resources` directory and loaded dynamically.

## 🔄 Version History

- **v2.0** (2025): Complete JavaFX migration with modern architecture
- **v1.0** (Original): Swing-based implementation following ZetCode tutorial

## 🤝 Contributing

Feel free to contribute to this project! Some ideas for improvements:

- Add sound effects and background music
- Implement power-ups and special weapons
- Add multiple difficulty levels
- Create animated backgrounds
- Add particle effects for explosions

## 📝 License

This project is open source and available under the MIT License.

## 🙏 Acknowledgments

- Original tutorial and concept from [ZetCode Java Games Tutorial](https://zetcode.com/javagames/)
- JavaFX community for excellent documentation and examples
- Modern Java ecosystem for providing robust development tools

---

**Enjoy defending Earth from the alien invasion!** 👾🚀