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
            for(String id: allId.keySet()) {
                JSONObject allTariff = allId.getJSONObject(id);
                for(String tarif: allTariff.keySet()){
                    if (!tarif.equalsIgnoreCase("ptpName")){
                        JSONObject allRoute = allTariff.getJSONObject(tarif);
                        for (String route: allRoute.keySet()) {
                            JSONObject allPrType = allRoute.getJSONObject(route);
                            for (String prType: allPrType.keySet()) {
                                JSONObject etc = allPrType.getJSONObject(prType);
                                String[] list = new String[9];
                                try {
                                    list[0] = new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(day));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                list[1] = id;
                                list[2] = allTariff.get("ptpName").toString();
                                list[3] = tarif;
                                list[4] = route;
                                list[5] = prType;
                                list[6] = etc.get("summ").toString();
                                list[7] = etc.get("cnt").toString();
                                list[8] = etc.get("qCnt").toString();
                                data.add(list);
                            }
                        }
                    }
                }
            }
        }
        return data;
    }
}
