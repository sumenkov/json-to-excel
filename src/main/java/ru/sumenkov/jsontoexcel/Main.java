package ru.sumenkov.jsontoexcel;

import org.apache.commons.cli.*;
import ru.sumenkov.jsontoexcel.mapper.JsonMapper;
import ru.sumenkov.jsontoexcel.mapper.impl.JsonMapperImpl;
import ru.sumenkov.jsontoexcel.repository.ReadJSON;
import ru.sumenkov.jsontoexcel.repository.impl.ReadJSONImpl;
import ru.sumenkov.jsontoexcel.service.WriteExcel;
import ru.sumenkov.jsontoexcel.service.impl.WriteExcelImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new LaunchOptions().launchOptions();
        CommandLine commandLine = commandLineParser.parse(options, args);

        if (commandLine.hasOption("file")) {
            String file = commandLine.getOptionValue("file");
            ReadJSON readerJSON = new ReadJSONImpl();
            JsonMapper jsonMapper = new JsonMapperImpl();

            WriteExcel writeExcel = new WriteExcelImpl();
            writeExcel.write(file, jsonMapper.map(readerJSON.read(file)));

        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("json-to-excel", options, true);
        }
    }
}
