package com.codegenerator.core.codegen.code.pluralization;

import java.util.ArrayList;
import java.util.List;

public class PluralizationApiBuilder {
    private final List<PluralizationProvider> providers = new ArrayList<>();

    public void addEnglishProvider() {
        providers.add(new EnglishPluralizationProvider());
    }

    public PluralizationApi build() {
        return new PluralizationApiImpl(providers);
    }
}
