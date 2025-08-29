#!/usr/bin/env bash
set -euo pipefail
rm -rf out && mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.umair.passwordgen.App
