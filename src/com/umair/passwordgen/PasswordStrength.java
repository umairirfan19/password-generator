package com.umair.passwordgen;

public class PasswordStrength {
    /** Estimate entropy in bits as length * log2(poolSize). */
    public static double estimateEntropyBits(int length, int poolSize) {
        if (length <= 0 || poolSize <= 1) return 0.0;
        double log2 = Math.log(poolSize) / Math.log(2);
        return length * log2;
    }

    public static String label(double entropyBits) {
        if (entropyBits < 28)  return "Very Weak";
        if (entropyBits < 36)  return "Weak";
        if (entropyBits < 60)  return "Fair";
        if (entropyBits < 80)  return "Strong";
        return "Excellent";
    }
}
