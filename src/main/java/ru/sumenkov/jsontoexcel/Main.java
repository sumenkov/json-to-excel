package ru.sumenkov.jsontoexcel;

import org.apache.commons.cli.*;
import ru.sumenkov.jsontoexcel.mapper.ExcelMapper;
import ru.sumenkov.jsontoexcel.mapper.impl.ExcelMapperImpl;
import ru.sumenkov.jsontoexcel.model.DataModelForExcel;
import ru.sumenkov.jsontoexcel.repository.JSONReader;
import ru.sumenkov.jsontoexcel.repository.impl.JSONReaderImpl;
import ru.sumenkov.jsontoexcel.service.ExcelWriter;
import ru.sumenkov.jsontoexcel.service.impl.ExcelWriterImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new LaunchOptions().launchOptions();
        CommandLine commandLine = commandLineParser.parse(options, args);

        for (Option option: options.getOptions()) {
            if (commandLine.hasOption(option)) {
                String file = commandLine.getOptionValue(option);

                JSONReader readerJSON = new JSONReaderImpl();
                ExcelMapper excelMapper = new ExcelMapperImpl();

                List<DataModelForExcel> data = excelMapper.map(readerJSON.read(file));

                ExcelWriter writeExcel = new ExcelWriterImpl();
                writeExcel.write(file, data);

            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("json-to-excel", options, true);
            }
        }
    }
}
