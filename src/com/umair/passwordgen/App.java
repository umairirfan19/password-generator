package com.umair.passwordgen;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Java Password Generator ===");

        int length = promptInt(sc, "Length [12]", 12, 4, 128);
        boolean useLower = promptBool(sc, "Include lowercase? (y/n) [y]", true);
        boolean useUpper = promptBool(sc, "Include UPPERCASE? (y/n) [y]", true);
        boolean useDigits = promptBool(sc, "Include digits? (y/n) [y]", true);
        boolean useSymbols = promptBool(sc, "Include symbols? (y/n) [y]", true);
        boolean avoidLookAlikes = promptBool(sc, "Avoid look-alike chars (O/0, l/1, etc.)? (y/n) [y]", true);
        int count = promptInt(sc, "How many passwords? [1]", 1, 1, 100);

        if (!(useLower || useUpper || useDigits || useSymbols)) {
            System.out.println("You must select at least one character set. Exiting.");
            return;
        }

        PasswordGenerator gen = new PasswordGenerator(
                useLower, useUpper, useDigits, useSymbols, avoidLookAlikes
        );

        for (int i = 1; i <= count; i++) {
            String pwd = gen.generate(length);
            double entropy = PasswordStrength.estimateEntropyBits(pwd.length(), gen.getPoolSize());
            String label = PasswordStrength.label(entropy);
            System.out.printf("#%d) %s   Strength: %s (Entropy≈%.1f bits)%n", i, pwd, label, entropy);
        }
    }

    private static boolean promptBool(Scanner sc, String prompt, boolean defaultVal) {
        System.out.print(prompt + ": ");
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return defaultVal;
        char c = Character.toLowerCase(line.charAt(0));
        if (c == 'y') return true;
        if (c == 'n') return false;
        System.out.println("Invalid input. Using default: " + (defaultVal ? "y" : "n"));
        return defaultVal;
    }

    private static int promptInt(Scanner sc, String prompt, int def, int min, int max) {
        System.out.print(prompt + ": ");
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return def;
        try {
            int val = Integer.parseInt(line);
            if (val < min || val > max) {
                System.out.printf("Please enter a value between %d and %d. Using default %d.%n", min, max, def);
                return def;
            }
            return val;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Using default " + def + ".");
            return def;
        }
    }
}
