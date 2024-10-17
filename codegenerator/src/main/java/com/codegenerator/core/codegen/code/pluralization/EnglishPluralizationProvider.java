package com.codegenerator.core.codegen.code.pluralization;

import java.util.Locale;

public class EnglishPluralizationProvider implements PluralizationProvider {
    @Override
    public boolean supports(Locale locale) {
        return Locale.ENGLISH.equals(locale);
    }

    @Override
    public String pluralize(String word) {
        // Pluralization logic for English
        return word + "s";
    }

    @Override
    public String singularize(String word) {
        // Singularization logic for English
        return word.endsWith("s") ? word.substring(0, word.length() - 1) : word;
    }

    @Override
    public boolean isPlural(String word) {
        // Check if word is plural
        return word.endsWith("s");
    }

    @Override
    public boolean isSingular(String word) {
        // Check if word is singular
        return !word.endsWith("s");
    }
}

