# Duck Calculator

A fun, stylish calculator desktop application built with Java Swing! Features a duck-themed GUI and playful sound effects for every button press.

---

## Features
- **Basic arithmetic:** Addition, subtraction, multiplication, and division
- **Modern GUI:** Stylish buttons, pastel backgrounds, and a clean layout
- **Duck theme:** Duck image and window icon
- **Sound effects:** Plays an "OINK" (pig grunt) MP3 on every button click
- **Error handling:** Division by zero and invalid operations are handled gracefully

---

## Screenshots
> _Add your own screenshot here!_

---

## How to Run

### Prerequisites
- Java 8 or newer
- [JLayer](http://www.javazoom.net/javalayer/javalayer.html) (MP3 playback library)

### Setup
1. **Clone or download this repository.**
2. **Ensure the following files are present:**
   - `src/resources/duck.png` (duck image)
   - `src/resources/pig-grunt-100272.mp3` (sound effect)
   - `lib/jlayer-1.0.1.jar` (JLayer library)
3. **Add `lib/jlayer-1.0.1.jar` to your project classpath.**
   - In IntelliJ IDEA: Right-click the JAR > "Add as Library..."
   - Or, if running from terminal:
     ```sh
     javac -cp "lib/jlayer-1.0.1.jar:src" src/Main.java
     java -cp "lib/jlayer-1.0.1.jar:src" Main
     ```

---

## Customization
- **Change the duck image:** Replace `src/resources/duck.png` with your own PNG.
- **Change the sound:** Replace `src/resources/pig-grunt-100272.mp3` with any short MP3 sound.

---

## Credits
- Duck icon: _Your source here_
- Pig grunt sound: [freesound.org](https://freesound.org/people/InspectorJ/sounds/100272/) (by InspectorJ, Creative Commons)
- MP3 playback: [JLayer](http://www.javazoom.net/javalayer/javalayer.html)

---

## License
> _Add your license here (MIT, Apache, etc.)_

---

Enjoy your ducky calculator! 🦆

