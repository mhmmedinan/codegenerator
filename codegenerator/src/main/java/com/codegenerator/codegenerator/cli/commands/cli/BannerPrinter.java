package com.codegenerator.codegenerator.cli.commands.cli;

public class BannerPrinter {
    public static void printBanner() {
        System.out.println("""
                 ####   ####  #####  ######  ####  ###### #    # ###### #####    ##   #####  ####  ##### \s
                #    # #    # #    # #      #    # #      ##   # #      #    #  #  #    #   #    # #    #\s
                #      #    # #    # #####  #      #####  # #  # #####  #    # #    #   #   #    # #    #\s
                #      #    # #    # #      #  ### #      #  # # #      #####  ######   #   #    # ##### \s
                #    # #    # #    # #      #    # #      #   ## #      #   #  #    #   #   #    # #   # \s
                 ####   ####  #####  ######  ####  ###### #    # ###### #    # #    #   #    ####  #    #\s
                Welcome to CodeGenerator CLI!
                Version: 1.0
                """);
    }
}
