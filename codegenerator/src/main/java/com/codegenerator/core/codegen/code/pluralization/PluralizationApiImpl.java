package com.codegenerator.core.codegen.code.pluralization;

import java.util.List;
import java.util.Locale;

public class PluralizationApiImpl implements PluralizationApi {
    private final List<PluralizationProvider> providers;

    public PluralizationApiImpl(List<PluralizationProvider> providers){
        this.providers = providers;
    }
    @Override
    public String pluralize(String word, Locale locale) {
        for (PluralizationProvider provider : providers) {
            if (provider.supports(locale)) {
                return provider.pluralize(word);
            }
        }
        return word + "s";
    }

    @Override
    public String singularize(String word, Locale locale) {
        for (PluralizationProvider provider : providers) {
            if (provider.supports(locale)) {
                return provider.singularize(word);
            }
        }
        return word;
    }

    @Override
    public boolean isPlural(String word, Locale locale) {
        for (PluralizationProvider provider : providers) {
            if (provider.supports(locale)) {
                return provider.isPlural(word);
            }
        }
        return word.endsWith("s");
    }

    @Override
    public boolean isSingular(String word, Locale locale) {
        for (PluralizationProvider provider : providers) {
            if (provider.supports(locale)) {
                return provider.isSingular(word);
            }
        }
        return !word.endsWith("s");
    }
}
