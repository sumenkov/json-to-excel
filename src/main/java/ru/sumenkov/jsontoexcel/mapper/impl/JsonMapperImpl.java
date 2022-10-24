package ru.sumenkov.jsontoexcel.mapper.impl;

import org.json.JSONObject;
import ru.sumenkov.jsontoexcel.mapper.JsonMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonMapperImpl implements JsonMapper {

    @Override
    public List<Object> map(JSONObject object) {
        List<Object> data = new ArrayList<>();

        for (String day: object.keySet()) {
            JSONObject allId = object.getJSONObject(day);
//                allId.keys().forEachRemaining(System.out::println);
            for(String id: allId.keySet()) {
                JSONObject allTariff = allId.getJSONObject(id);
                String[] list = new String[8];
                try {
                    list[0] = new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(day));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                list[1] = id;
                list[2] = allTariff.get("ptpName").toString();
                data.add(list);
            }
        }
        return data;
//        return new ArrayList<>();
    }
}
