package ru.sumenkov.jsontoexcel.service;

import java.util.List;

public interface WriteExcel {
    void write(String file, List<Object> data);
}
