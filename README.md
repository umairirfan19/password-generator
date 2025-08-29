[![Build and Run Password Generator](https://github.com/umairirfan19/password-generator/actions/workflows/run.yml/badge.svg)](https://github.com/umairirfan19/password-generator/actions/workflows/run.yml)
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

### Sample run

```text
=== Java Password Generator ===
#1) nuXRVHe7e44zDa6prCkT   Strength: Excellent (Entropy≈112.9 bits)
#2) GMyAa7KvDfacQggqKkbN   Strength: Excellent (Entropy≈112.9 bits)
#3) RTYRQ6imuMw3HG7svUHH   Strength: Excellent (Entropy≈112.9 bits)
#4) izuG4WJVfPNcju69LRFR   Strength: Excellent (Entropy≈112.9 bits)
#5) LWJviCuLbENbiqztqbqe   Strength: Excellent (Entropy≈112.9 bits)


## How strength is estimated
Entropy ≈ `length * log2(poolSize)`. Labels:
- < 28: Very Weak
- < 36: Weak
- < 60: Fair
- < 80: Strong
- ≥ 80: Excellent


## Project Layout
- App.java – CLI and prompts
- PasswordGenerator.java – generation logic
- PasswordStrength.java – entropy + label

## License
MIT
