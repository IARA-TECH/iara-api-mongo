package com.exemplo.iara_apimongo.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory(System.getProperty("user.dir"))
            .ignoreIfMissing()
            .load();

    public static String get(String key) {
        String value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }

        return dotenv.get(key);
    }
}
