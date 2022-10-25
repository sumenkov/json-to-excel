package ru.sumenkov.jsontoexcel.mapper.impl;

import org.json.JSONObject;
import ru.sumenkov.jsontoexcel.mapper.JsonMapper;
import ru.sumenkov.jsontoexcel.model.DataModelForExcel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonMapperImpl implements JsonMapper {

    @Override
    public List<DataModelForExcel> map(JSONObject object) {
        List<DataModelForExcel> data = new ArrayList<>();

        for (String day: object.keySet()) {
            JSONObject allId = object.getJSONObject(day);
            for(String id: allId.keySet()) {
                JSONObject allTariff = allId.getJSONObject(id);
                for(String tarif: allTariff.keySet()){
                    if (!tarif.equalsIgnoreCase("ptpName")){
                        JSONObject allRouteNum = allTariff.getJSONObject(tarif);
                        for (String routeNum: allRouteNum.keySet()) {
                            JSONObject allPrType = allRouteNum.getJSONObject(routeNum);
                            for (String prType: allPrType.keySet()) {
                                JSONObject etc = allPrType.getJSONObject(prType);
                                DataModelForExcel dataRow = new DataModelForExcel();
                                try {
                                    String date = new SimpleDateFormat("dd.MM.yyyy")
                                            .format(new SimpleDateFormat("yyyy-MM-dd").parse(day));
                                    dataRow.setDt1(date);
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                dataRow.setPtpId(Integer.valueOf(id));
                                dataRow.setPtpName(allTariff.get("ptpName").toString());
                                dataRow.setTarif(Double.valueOf(tarif));
                                dataRow.setRouteNum(routeNum);
                                dataRow.setPrType(new DecimalFormat("00.00").format(Double.valueOf(prType)));
                                dataRow.setSumm(Double.valueOf(etc.get("summ").toString()));
                                dataRow.setCnt((Integer) etc.get("cnt"));
                                dataRow.setQCnt((Integer) etc.get("qCnt"));

                                data.add(dataRow);
                            }
                        }
                    }
                }
            }
        }
        return data;
    }
}
