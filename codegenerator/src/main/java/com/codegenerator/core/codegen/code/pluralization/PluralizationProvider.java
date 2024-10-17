package com.codegenerator.core.codegen.code.pluralization;

import java.util.Locale;

public interface PluralizationProvider {
    boolean supports(Locale locale);
    String pluralize(String word);
    String singularize(String word);
    boolean isPlural(String word);
    boolean isSingular(String word);
}
