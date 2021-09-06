package org.javajarvis.SistemCuti_UASJava.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.model.PengajuanCuti;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static List<Employee> parseExcelFile(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheet("Employee");
            Iterator<Row> rows = sheet.iterator();

            List<Employee> lstEmployee = new ArrayList<Employee>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = (Row) rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Employee employee = new Employee();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = (Cell) cellsInRow.next();

                    if (cellIndex == 0) { // role
                        employee.setRole(currentCell.getNumericCellValue());
                    } else if (cellIndex == 1) { // NIP
                        employee.setNip(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) { // Nama
                        employee.setNamaLengkap(currentCell.getStringCellValue());
                    } else if (cellIndex == 3) { // Divisi
                        employee.setDivisi(currentCell.getStringCellValue());
                    } else if (cellIndex == 4) { // Email
                        employee.setEmail(currentCell.getStringCellValue());
                    }else if (cellIndex == 5) { // username
                        employee.setUsername(currentCell.getStringCellValue());
                    }else if (cellIndex == 6) { // password
                        employee.setPassword(currentCell.getStringCellValue());
                    }

                    cellIndex++;
                }

                lstEmployee.add(employee);
            }

            // Close WorkBook
            workbook.close();

            return lstEmployee;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static ByteArrayInputStream employeeToExcel(List<PengajuanCuti> pengajuanCutis) throws IOException {
        String[] COLUMNs = { "Pengajuan Cuti Id", "Employee Id", "Status cuti ", "pengganti", "hrd", "alamat", "Telepon", "Keterangan", "lama"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Pengajuan Cuti");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }


            int rowIdx = 1;
            for (PengajuanCuti pengajuanCuti : pengajuanCutis) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pengajuanCuti.getId());
                row.createCell(1).setCellValue((RichTextString) pengajuanCuti.getEmployee());
                row.createCell(2).setCellValue((RichTextString) pengajuanCuti.getStatusCuti());
                row.createCell(3).setCellValue(pengajuanCuti.getPenggantiId());
                row.createCell(4).setCellValue(pengajuanCuti.getHrdId());
                row.createCell(5).setCellValue(pengajuanCuti.getAlamat());
                row.createCell(6).setCellValue(pengajuanCuti.getNoTelp());
                row.createCell(7).setCellValue(pengajuanCuti.getKeterangan());
                row.createCell(8).setCellValue(pengajuanCuti.getLamaCuti());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static boolean isExcelFile(MultipartFile file) {

        if(!EXCELTYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

}
