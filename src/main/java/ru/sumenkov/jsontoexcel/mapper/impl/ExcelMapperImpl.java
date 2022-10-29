package ru.sumenkov.jsontoexcel.mapper.impl;

import org.json.JSONObject;
import ru.sumenkov.jsontoexcel.mapper.ExcelMapper;
import ru.sumenkov.jsontoexcel.model.DataModelForExcel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelMapperImpl implements ExcelMapper {
    List<DataModelForExcel> data = new ArrayList<>();
    DataModelForExcel dataRow = new DataModelForExcel();

    @Override
    public List<DataModelForExcel> map(JSONObject object) {
        for (String day : object.keySet()) {
            try {
                String date = new SimpleDateFormat("dd.MM.yyyy")
                        .format(new SimpleDateFormat("yyyy-MM-dd").parse(day));
                dataRow.setDt1(date);
            } catch (ParseException e) { throw new RuntimeException(e); }

            getId(object.getJSONObject(day));
        }

        return data;
    }

    private void getId(JSONObject day) {
        for(String id: day.keySet()) {
            dataRow.setPtpId(Integer.valueOf(id));
            getTarif(day.getJSONObject(id));
        }
    }

    private void getTarif(JSONObject id) {
        dataRow.setPtpName(id.get("ptpName").toString());

        for(String tarif: id.keySet()) {
            if (!tarif.equalsIgnoreCase("ptpName")) {
                dataRow.setTarif(Double.valueOf(tarif));
                getRouteNum(id.getJSONObject(tarif));
            }
        }
    }

    private void getRouteNum(JSONObject tarif) {
        for (String routeNum: tarif.keySet()) {
            dataRow.setRouteNum(routeNum);
            getPrType(tarif.getJSONObject(routeNum));
        }
    }

    private void getPrType(JSONObject routeNum) {
        for (String prType: routeNum.keySet()) {
            dataRow.setPrType(new DecimalFormat("00.00").format(Double.valueOf(prType)));
            getSCQ(routeNum.getJSONObject(prType));
        }
    }

    private void getSCQ(JSONObject scq) {
        dataRow.setSumm(Double.valueOf(scq.get("summ").toString()));
        dataRow.setCnt((Integer) scq.get("cnt"));
        dataRow.setQCnt((Integer) scq.get("qCnt"));

        data.add(new DataModelForExcel(dataRow));
    }
}
