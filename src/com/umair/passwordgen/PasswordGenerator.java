package com.umair.passwordgen;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:',.<>/?`~|\\";
    private static final String LOOKALIKE_CHARS = "0Ool1I5S8B2Z";

    private final boolean useLower;
    private final boolean useUpper;
    private final boolean useDigits;
    private final boolean useSymbols;
    private final boolean avoidLookAlikes;

    private final SecureRandom rng = new SecureRandom();
    private String pool;

    public PasswordGenerator(boolean useLower, boolean useUpper, boolean useDigits, boolean useSymbols, boolean avoidLookAlikes) {
        this.useLower = useLower;
        this.useUpper = useUpper;
        this.useDigits = useDigits;
        this.useSymbols = useSymbols;
        this.avoidLookAlikes = avoidLookAlikes;
        this.pool = buildPool();
        if (this.pool.isEmpty()) {
            throw new IllegalArgumentException("At least one character set must be enabled.");
        }
    }

    private String buildPool() {
        StringBuilder sb = new StringBuilder();
        if (useLower) sb.append(LOWER);
        if (useUpper) sb.append(UPPER);
        if (useDigits) sb.append(DIGITS);
        if (useSymbols) sb.append(SYMBOLS);

        String raw = sb.toString();
        if (!avoidLookAlikes) return raw;

        StringBuilder filtered = new StringBuilder();
        for (char c : raw.toCharArray()) {
            if (LOOKALIKE_CHARS.indexOf(c) < 0) {
                filtered.append(c);
            }
        }
        return filtered.toString();
    }

    public String generate(int length) {
        if (length <= 0) throw new IllegalArgumentException("Length must be > 0.");

        char[] out = new char[length];

        int idx = 0;
        if (useLower) out[idx++] = pickChar(LOWER);
        if (useUpper) out[idx++] = pickChar(UPPER);
        if (useDigits) out[idx++] = pickChar(DIGITS);
        if (useSymbols) out[idx++] = pickChar(SYMBOLS);

        for (; idx < length; idx++) {
            out[idx] = pickChar(pool);
        }

        shuffle(out);
        return new String(out);
    }

    private char pickChar(String s) {
        if (s.isEmpty()) {
            return pickChar(pool);
        }
        int i = rng.nextInt(s.length());
        char c = s.charAt(i);
        if (avoidLookAlikes && PasswordGenerator.LOOKALIKE_CHARS.indexOf(c) >= 0) {
            return pickChar(pool);
        }
        return c;
    }

    private void shuffle(char[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public int getPoolSize() {
        return pool.length();
    }
}
