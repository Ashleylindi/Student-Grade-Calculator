package za;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class GradeCalculator {

    private static final String FILE_PATH = "src/main/resources/Student Grades.xlsx";

    public static void main(String[] args) {
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(FILE_PATH));

            // Example: Calculate and display class averages
            displayClassAverages(workbook, "Class A");
            displayClassAverages(workbook, "Class B");
            displayClassAverages(workbook, "Class C");

            workbook.close();
        } catch (IOException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
        }
    }

    public static void displayClassAverages(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("Sheet " + sheetName + " not found!");
            return;
        }

        List<Double> averages = new ArrayList<>();
        for (Row row : sheet) {
            // Skip header row
            if (row.getRowNum() == 0) continue;

            double total = 0;
            int count = 0;

            // Read grades from columns 3 to 6 (English, Maths, Life Skills, Mandarin)
            for (int i = 2; i <= 5; i++) {
                Cell cell = row.getCell(i);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    total += cell.getNumericCellValue();
                    count++;
                }
            }

            double average = count > 0 ? total / count : 0;
            averages.add(average);
            System.out.println("Student " + row.getCell(0).getStringCellValue() + " (ID: " +
                    row.getCell(1).getStringCellValue() + ") Average: " + average);
        }

        // Calculate class average
        BigDecimal classAverage = new BigDecimal(averages.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0))
                .setScale(2, RoundingMode.HALF_UP);

        System.out.println("---------------------------------------");
        System.out.println("Class - " + sheetName + " Average: " + classAverage);
        System.out.println("---------------------------------------");
    }
}
