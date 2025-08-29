# Password Generator (Java)

A simple, CLI-based password generator with selectable options (length, lowercase, uppercase, digits, symbols) and a basic entropy-based strength estimate.

## Features
- Choose password length (4–128)
- Toggle character sets (lowercase / UPPERCASE / digits / symbols)
- Avoid look-alike characters (optional)
- Copy multiple passwords in one run
- Strength estimate (Very Weak → Excellent) using entropy approximation

## Quick Start

### Compile
javac -d out $(find src -name "*.java")

### Run
java -cp out com.umair.passwordgen.App

## How strength is estimated
Entropy ≈ `length * log2(poolSize)`. Labels:
- < 28: Very Weak
- < 36: Weak
- < 60: Fair
- < 80: Strong
- ≥ 80: Excellent

> Note: Entropy is a rough guide. For critical security, use a password manager.

## Project Layout
- App.java – CLI and prompts
- PasswordGenerator.java – generation logic
- PasswordStrength.java – entropy + label

## License
MIT
