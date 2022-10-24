package ru.sumenkov.jsontoexcel;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class LaunchOptions {
    public Options launchOptions() {
        Options options = new Options();

        options.addOption(Option.builder("f")
                .longOpt("file")
                .argName("file")
                .hasArg(true)
                .desc("Полный путь до файла.")
                .build());

        return options;
    }
}
