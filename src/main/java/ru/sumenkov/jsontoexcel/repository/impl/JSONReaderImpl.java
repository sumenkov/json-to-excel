package ru.sumenkov.jsontoexcel.repository.impl;

import org.json.JSONObject;
import org.json.JSONTokener;
import ru.sumenkov.jsontoexcel.repository.JSONReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONReaderImpl implements JSONReader {
    private static final Logger log = Logger.getLogger(JSONReaderImpl.class.getName());

    public JSONObject read(String file) {
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(new FileReader(file));
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "Файл не найден", e);
            System.exit(0);
        }
//        assert tokener != null;
        return new JSONObject(tokener);
    }
}
