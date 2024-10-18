package com.codegenerator.core.codegen.templateengine.freemarker;

import com.codegenerator.core.codegen.code.StringUtils;
import freemarker.template.*;

import java.util.List;

public class FreemarkerBuiltinFunctionsExtensions {
    private final Configuration configuration;

    public FreemarkerBuiltinFunctionsExtensions() {
        this.configuration = new Configuration(Configuration.VERSION_2_3_31);
    }


    public static class StringFunctionsModel implements TemplateMethodModelEx {
        @Override
        public TemplateModel exec(List arguments) throws TemplateModelException {
            String functionName = ((SimpleScalar) arguments.get(0)).getAsString();
            String input = ((SimpleScalar) arguments.get(1)).getAsString();
            switch (functionName) {
                case "camelcase":
                    return new SimpleScalar(StringUtils.toCamelCase(input));
                case "pascalcase":
                    return new SimpleScalar(StringUtils.toPascalCase(input));
                case "snakecase":
                    return new SimpleScalar(StringUtils.toSnakeCase(input));
                case "kebabcase":
                    return new SimpleScalar(StringUtils.toKebabCase(input));
                case "abbreviation":
                    return new SimpleScalar(StringUtils.toAbbreviation(input));
                case "words":
                    return new SimpleScalar(String.join(" ", StringUtils.getWords(input)));
                default:
                    throw new TemplateModelException("Unknown function: " + functionName);
            }
        }
    }
}
