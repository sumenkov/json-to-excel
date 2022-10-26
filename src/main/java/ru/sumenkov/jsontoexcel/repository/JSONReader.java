package ru.sumenkov.jsontoexcel.repository;

import org.json.JSONObject;

public interface JSONReader {

    JSONObject read(String file);
}
