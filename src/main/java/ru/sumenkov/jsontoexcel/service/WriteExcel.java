package ru.sumenkov.jsontoexcel.service;

import ru.sumenkov.jsontoexcel.model.DataModelForExcel;

import java.util.List;

public interface WriteExcel {
    void write(String file, List<DataModelForExcel> data);
}
