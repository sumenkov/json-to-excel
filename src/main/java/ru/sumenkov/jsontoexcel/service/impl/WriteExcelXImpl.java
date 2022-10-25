package ru.sumenkov.jsontoexcel.service.impl;

import ru.sumenkov.jsontoexcel.service.WriteExcel;

import java.util.List;

public class WriteExcelXImpl implements WriteExcel {
    @Override
    public void write(String file, List<Object> data) {
        System.out.println("Run WriteExcelXImpl");
    }
}
