package ru.sumenkov.jsontoexcel.repository;

import org.json.JSONObject;

public interface ReadJSON {

    JSONObject read(String file);
}
