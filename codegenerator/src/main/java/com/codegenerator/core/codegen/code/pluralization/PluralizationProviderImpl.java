package com.codegenerator.core.codegen.code.pluralization;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public class PluralizationProviderImpl{

    private static final Supplier<PluralizationApi> lazyPluralApi = ()->{
        PluralizationApiBuilder pluralizationApiBuilder = new PluralizationApiBuilder();
        pluralizationApiBuilder.addEnglishProvider();
        return pluralizationApiBuilder.build();
    };

    private static final Locale culture = Locale.US;
    private static final PluralizationApi pluralization = lazyPluralApi.get();

    public static String pluralize(String word) {
        return Optional.ofNullable(pluralization.pluralize(word,culture)).orElse(word);
    }


    public static String singularize(String word) {
        return Optional.ofNullable(pluralization.singularize(word, culture)).orElse(word);
    }


    public static boolean isPlural(String word) {
       return pluralization.isPlural(word,culture);
    }


    public static boolean isSingular(String word) {
        return pluralization.isSingular(word,culture);
    }
}
