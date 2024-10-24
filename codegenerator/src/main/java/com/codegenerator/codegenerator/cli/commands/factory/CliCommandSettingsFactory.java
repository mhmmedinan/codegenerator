package com.codegenerator.codegenerator.cli.commands.factory;

import com.codegenerator.codegenerator.cli.commands.common.CrudCliCommandSettings;
import com.codegenerator.codegenerator.cli.commands.generate.CleanArchGenerateCrudCliCommandSettings;
import com.codegenerator.codegenerator.cli.commands.generate.NLayerArchGenerateCrudCliCommandSettings;

public class CliCommandSettingsFactory {
    public static CrudCliCommandSettings createSettings(String architecture) {
        return switch (architecture.toLowerCase()) {
            case "cleanarch" -> new CleanArchGenerateCrudCliCommandSettings();
            case "nlayerarch" -> new NLayerArchGenerateCrudCliCommandSettings();
            default -> throw new IllegalArgumentException("Unsupported architecture: " + architecture);
        };
    }
}
