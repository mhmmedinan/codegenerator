package com.codegenerator.core.codegen.code;

import com.codegenerator.core.codegen.code.pluralization.PluralizationProviderImpl;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {
    private static final Pattern PATTERN = Pattern.compile("(?<!^)(?=[A-Z0-9])|\\s+|_|-");

    public static String[] getWords(String value) {
        return Arrays.stream(PATTERN.split(value))
                .filter(word -> !word.isEmpty())
                .toArray(String[]::new);
    }

    public static String toCamelCase(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String[] words = getWords(value);
        if (words.length == 1) {
            return value.toLowerCase(Locale.ENGLISH);
        }
        return words[0].toLowerCase(Locale.ENGLISH)
                + Arrays.stream(words, 1, words.length)
                .map(word -> word.substring(0, 1).toUpperCase(Locale.ENGLISH) + word.substring(1).toLowerCase(Locale.ENGLISH))
                .collect(Collectors.joining());
    }

    public static String toPascalCase(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String[] words = getWords(value);
        if (words.length == 1) {
            return value.substring(0, 1).toUpperCase(Locale.ENGLISH) + value.substring(1);
        }
        return Arrays.stream(words)
                .map(word -> word.substring(0, 1).toUpperCase(Locale.ENGLISH) + word.substring(1).toLowerCase(Locale.ENGLISH))
                .collect(Collectors.joining());
    }

    public static String toSnakeCase(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String[] words = getWords(value);
        if (words.length == 1) {
            return value.toLowerCase(Locale.ENGLISH);
        }
        return Arrays.stream(words)
                .map(word -> word.toLowerCase(Locale.ENGLISH))
                .collect(Collectors.joining("_"));
    }

    public static String toKebabCase(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String[] words = getWords(value);
        if (words.length == 1) {
            return value.toLowerCase(Locale.ENGLISH);
        }
        return Arrays.stream(words)
                .map(word -> word.toLowerCase(Locale.ENGLISH))
                .collect(Collectors.joining("-"));
    }

    public static String toAbbreviation(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        String[] words = getWords(value);
        if (words.length == 1) {
            return value.substring(0, 1).toLowerCase(Locale.ENGLISH);
        }
        return Arrays.stream(words)
                .map(word -> word.substring(0, 1).toLowerCase(Locale.ENGLISH))
                .collect(Collectors.joining());
    }

    public static String toPlural(String value) {
        return PluralizationProviderImpl.pluralize(value);
    }

    public static String toSingular(String value) {
        return PluralizationProviderImpl.singularize(value);

    }
}

