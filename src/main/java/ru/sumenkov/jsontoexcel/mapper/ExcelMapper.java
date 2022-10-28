package ru.sumenkov.jsontoexcel.mapper;

import org.json.JSONObject;
import ru.sumenkov.jsontoexcel.model.DataModelForExcel;

import java.util.List;

public interface ExcelMapper {
    List<DataModelForExcel> map(JSONObject object);
}
