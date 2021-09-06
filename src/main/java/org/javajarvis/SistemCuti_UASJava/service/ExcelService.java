package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.model.PengajuanCuti;
import org.javajarvis.SistemCuti_UASJava.repository.EmployeeRepository;
import org.javajarvis.SistemCuti_UASJava.repository.PengajuanCutiRepository;
import org.javajarvis.SistemCuti_UASJava.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    PengajuanCutiRepository pengajuanCutiRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    // Store File Data to Database
    public void store(MultipartFile file) {
        try {
            List<Employee> lstEmployee = ExcelUtils.parseExcelFile(file.getInputStream());
            // Save Customers to DataBase
            employeeRepository.saveAll(lstEmployee);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    // Load Data to Excel File
    public ByteArrayInputStream loadFile() {
        List<PengajuanCuti> pengajuancuti = (List<PengajuanCuti>) pengajuanCutiRepository.getCuti();

        try {
            ByteArrayInputStream in = ExcelUtils.employeeToExcel(pengajuancuti);
            return in;
        } catch (IOException e) {}

        return null;
    }
}
