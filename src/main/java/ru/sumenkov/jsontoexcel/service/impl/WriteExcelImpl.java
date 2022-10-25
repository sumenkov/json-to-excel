package ru.sumenkov.jsontoexcel.service.impl;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import ru.sumenkov.jsontoexcel.service.WriteExcel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteExcelImpl implements WriteExcel {
    private static final Logger log = Logger.getLogger(WriteExcelImpl.class.getName());
    private static final int SLASH_CHARACTER = 92;     // char: 92 - равно знаку '/'

    @Override
    public void write(String file, List<Object> data) throws RuntimeException {
        try {
            // Создаем книгу и рабочий лист
            WritableWorkbook book = Workbook.createWorkbook(new File(newFile(file)));
            WritableSheet sheet = book.createSheet(fileName(file), 0);
            // Заполняем рабочий лист
            fillWorksheet(sheet, data);
            // Записываем файл
            book.write();
            book.close();
        } catch (IOException | WriteException e) {
            log.log(Level.SEVERE, "Fail write Excel file", e);
        }
    }

    private void fillWorksheet(WritableSheet sheet, List<Object> data) throws WriteException {
        String[] heads = new String[]{"DT1", "PTP_ID", "PTP_NAME", "TARIF", "ROUTE", "PRTYPE", "SUMM", "CNT", "QCNT"};
        for (int i = 0; i < heads.length; i++) {
            // label: номер столбца, номер строки, содержимое
            Label label = new Label(i, 0, heads[i]);
            sheet.addCell(label);
        }
        int num = 0;
        for (int j = 0; j < data.size(); j++) {
            System.out.println(num++);
            String[] rowObjects = (String[]) data.get(j);
            System.out.println(Arrays.toString(rowObjects));
            for (int k = 0; k < rowObjects.length; k++) {
                String dataString = rowObjects[k] == null ? "" : rowObjects[k];
                System.out.println(dataString);
                // label: номер столбца, номер строки, содержимое
                Label label = new Label(k, j + 1, dataString);
                sheet.addCell(label);
            }
        }
    }

    private String newFile(String file){
        return  Path.of(file).getParent() + "\\" + fileName(file) + ".xls";
    }

    private String fileName(String file) {
        return file.substring(file.lastIndexOf(SLASH_CHARACTER) + 1, file.lastIndexOf("."));
    }
}
