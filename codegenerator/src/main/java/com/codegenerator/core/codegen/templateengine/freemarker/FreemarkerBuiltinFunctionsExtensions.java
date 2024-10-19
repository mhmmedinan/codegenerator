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
                case "toWrapperType":
                    return new SimpleScalar(toWrapperType(input));
                default:
                    throw new TemplateModelException("Unknown function: " + functionName);
            }

        }
    }

    private static String toWrapperType(String inputType) {
        switch (inputType) {
            case "int":
                return "Integer";
            case "float":
                return "Float";
            case "double":
                return "Double";
            case "char":
                return "Character";
            case "boolean":
                return "Boolean";
            case "long":
                return "Long";
            case "short":
                return "Short";
            case "byte":
                return "Byte";
            default:
                return inputType;
        }
    }
}
