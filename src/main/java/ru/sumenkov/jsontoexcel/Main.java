package ru.sumenkov.jsontoexcel;

import org.apache.commons.cli.*;
import ru.sumenkov.jsontoexcel.mapper.JsonMapper;
import ru.sumenkov.jsontoexcel.mapper.impl.JsonMapperImpl;
import ru.sumenkov.jsontoexcel.repository.ReadJSON;
import ru.sumenkov.jsontoexcel.repository.impl.ReadJSONImpl;
import ru.sumenkov.jsontoexcel.service.WriteExcel;
import ru.sumenkov.jsontoexcel.service.impl.WriteExcelImpl;
import ru.sumenkov.jsontoexcel.service.impl.WriteExcelXImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new LaunchOptions().launchOptions();
        CommandLine commandLine = commandLineParser.parse(options, args);

        if (commandLine.hasOption("file")) {
            String file = commandLine.getOptionValue("file");
            ReadJSON readerJSON = new ReadJSONImpl();
            JsonMapper jsonMapper = new JsonMapperImpl();
            List<Object> data = jsonMapper.map(readerJSON.read(file));
            // количество допустимых строк на одном листе 65535, ДА - в этой либе именно столько.
            WriteExcel writeExcel = data.size() > 65535 ? new WriteExcelXImpl(): new WriteExcelImpl();
            writeExcel.write(file, data);

        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("json-to-excel", options, true);
        }
    }
}
