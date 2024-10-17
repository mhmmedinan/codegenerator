package com.codegenerator.core.codegen.code.pluralization;

import java.util.Locale;

public interface PluralizationApi {
    String pluralize(String word, Locale locale);
    String singularize(String word, Locale locale);
    boolean isPlural(String word, Locale locale);
    boolean isSingular(String word, Locale locale);
}
