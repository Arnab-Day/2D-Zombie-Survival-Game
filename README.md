# 🧟 2D Zombie Survival Game

A simple 2D Zombie Survival Game developed in **Java Swing** as a university project. The player moves around, shoots zombies, earns points, manages health, and survives against continuously spawning zombies.

## 🎮 Features

* Player movement using W/A/S/D
* Zombie spawning and movement
* Shooting system
* Bullet-zombie collision detection
* Score system
* Player health system
* Zombie-player collision
* Continuous zombie spawning
* Dynamic difficulty
* Game Over system
* Restart system

## 🛠️ Technologies

* **Java**
* **Java Swing**
* **Java AWT**
* **ArrayList**
* **Java Timer**
* **Random**

## 💻 Requirements

* JDK 17 or newer
* Any Java IDE such as NetBeans, IntelliJ IDEA, or Eclipse

## 🚀 How to Run

1. Clone the repository:

```bash
git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
```

2. Open the project in a Java IDE.
3. Locate `Main.java`.
4. Run the `main()` method.
5. The game window will open.

## 🎮 Controls

| Key     | Action                  |
| ------- | ----------------------- |
| `W`     | Move Up                 |
| `S`     | Move Down               |
| `A`     | Move Left               |
| `D`     | Move Right              |
| `SPACE` | Shoot                   |
| `R`     | Restart after Game Over |

## 📁 Project Structure

```text
src/
└── pkg2dzombiesurvivalgame/
    ├── Main.java
    ├── GamePanel.java
    ├── Player.java
    ├── Zombie.java
    └── Bullet.java
```

### Main Classes

* **Main.java** — Starts the game window.
* **GamePanel.java** — Controls the main game logic, game loop, collisions, score, health, spawning, and Game Over.
* **Player.java** — Handles player properties and movement.
* **Zombie.java** — Handles zombie movement and properties.
* **Bullet.java** — Handles bullet movement and collision area.

## 💥 Collision Detection

Collision detection is handled in `GamePanel.java` using Java's `Rectangle.intersects()` method.

```java
bullet.getBounds().intersects(zombie.getBounds())
```

This is used for both:

* Bullet → Zombie collision
* Zombie → Player collision

## 📅 Weekly Development

### Week 1 — Planning & Research

Planned the game concept, gameplay mechanics, required classes, and development timeline.

### Week 2 — Flowchart & Game Logic

Created the game flowchart and planned player, zombie, bullet, collision, score, health, and Game Over logic.

### Week 3 — Game Design & Setup

Created the Java project, game window, `GamePanel`, and basic project classes.

### Week 4 — Player Development

Created the player character, starting position, properties, and on-screen display.

### Week 5 & 6 — Movement & Zombie System

Added W/A/S/D player movement, boundary checking, multiple zombies, random spawning, and zombie movement toward the player.

### Week 7 — Shooting & Collision

Added shooting, bullet movement, bullet-zombie collision, zombie removal, and score.

### Week 8 & 9 — Health, Game Over & Difficulty

Added health, zombie-player collision, continuous zombie spawning, dynamic difficulty, Game Over, and restart functionality.

### Week 10 — Documentation & Presentation

Prepared final documentation, organized the project, updated the graphics, tested the game, and prepared it for presentation.

## 🏆 Game Rules

* Shoot a zombie → **Score +1**
* Zombie touches player → **Health decreases**
* Every 10 points → **Zombies spawn faster**
* Health reaches 0 → **Game Over**
* Press `R` → **Restart**

## 🔮 Future Improvements

* Better graphics and animations
* Multiple weapons
* Different zombie types
* Sound effects and music
* Power-ups
* Multiple levels
* High-score system
* Main menu and pause system

## 👨‍💻 Project Information

**Project:** 2D Zombie Survival Game
**Language:** Java
**GUI:** Java Swing / AWT
**Type:** University Project
**Status:** Completed
