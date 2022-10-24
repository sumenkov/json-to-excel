package ru.sumenkov.jsontoexcel.mapper;

import org.json.JSONObject;

import java.util.List;

public interface JsonMapper {
    List<Object> map(JSONObject object);
}
