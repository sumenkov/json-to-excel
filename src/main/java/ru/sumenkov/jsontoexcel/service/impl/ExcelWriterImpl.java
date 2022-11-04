package ru.sumenkov.jsontoexcel.service.impl;

import ru.sumenkov.jsontoexcel.model.DataModelForExcel;
import ru.sumenkov.jsontoexcel.service.ExcelWriter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelWriterImpl implements ExcelWriter {

    private static final Logger log = Logger.getLogger(ExcelWriterImpl.class.getName());

    private static final char SLANT = 47;            // char: 47 - знак '/', Slant (forward slash, divide)
    private static final char REVERSE_SLANT = 92;    // char: 92 - равно знаку '\', Reverse slant (Backslash)
    @Override
    public void write(String file, List<DataModelForExcel> data) {
        // create a new Workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new Sheet
            Sheet sheet = workbook.createSheet(fileName(file));

            // Create header row
            String[] headers = new String[]{"DT1", "PTP_ID", "PTP_NAME","TARIF", "ROUTE_NUM", "PRTYPE", "SUMM", "CNT", "QCNT"};
            createHeaderRow(workbook, sheet, headers);

            // Create rows
            for(int i = 0; i < data.size(); i++) {
                // row index equals i + 1 because the first row of Excel file is the header row.
                int rowIndex = i + 1;
                createNewRow(sheet, rowIndex, data.get(i));
            }

            // Adjusts 3 columns to set the width to fit the contents.
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            workbook.write(new FileOutputStream(newFile(file)));

        } catch (IOException e) {
            log.log(Level.SEVERE, "Fail create Excel file", e);
        }
    }
    private String fileName(String file) {
        int indexSlant = file.lastIndexOf(SLANT) >= 0 ? file.lastIndexOf(SLANT) + 1: file.lastIndexOf(REVERSE_SLANT) + 1;
        return file.substring(indexSlant, file.lastIndexOf("."));
    }

    private void createHeaderRow(Workbook workbook, Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void createNewRow(Sheet sheet, int rowIndex, DataModelForExcel dataRow) {
        Row row = sheet.createRow(rowIndex);

        Cell cell = row.createCell(0);
        cell.setCellValue(dataRow.getDt1());

        cell = row.createCell(1);
        cell.setCellValue(dataRow.getPtpId());

        cell = row.createCell(2);
        cell.setCellValue(dataRow.getPtpName());

        cell = row.createCell(3);
        cell.setCellValue(dataRow.getTarif());

        cell = row.createCell(4);
        cell.setCellValue(dataRow.getRouteNum());

        cell = row.createCell(5);
        cell.setCellValue(dataRow.getPrType());

        cell = row.createCell(6);
        cell.setCellValue(dataRow.getSumm());

        cell = row.createCell(7);
        cell.setCellValue(dataRow.getCnt());

        cell = row.createCell(8);
        cell.setCellValue(dataRow.getQCnt());
    }

    private String newFile(String file){
        String newFile;
        Path path = Path.of(file);
        if(path.getParent() != null) {
            char slant = file.lastIndexOf(SLANT) >= 0 ? SLANT: REVERSE_SLANT;
            newFile = String.format("%s%s%s.xlsx", path.getParent(), slant, fileName(file));
        } else {
            newFile = fileName(file) + ".xlsx";
        }
        return newFile;
    }
}
