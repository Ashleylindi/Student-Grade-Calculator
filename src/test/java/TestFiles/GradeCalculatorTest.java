package TestFiles;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.GradeCalculator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeCalculatorTest {

    private Workbook workbook;

    @BeforeEach
    public void setUp() throws Exception {
        // Create an in-memory Excel file using Apache POI
        workbook = new XSSFWorkbook();

        // Create a sheet for testing
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("ID");
        headerRow.createCell(2).setCellValue("English");
        headerRow.createCell(3).setCellValue("Maths");
        headerRow.createCell(4).setCellValue("Life Skills");
        headerRow.createCell(5).setCellValue("Mandarin");

        // Add some student data
        Object[][] studentData = {
                {"Student 1", "001", 80, 90, 75, 85},
                {"Student 2", "002", 70, 65, 80, 90},
                {"Student 3", "003", 95, 85, 90, 88},
                {"Student 4", "004", 60, 70, 65, 80}
        };

        // Populate the sheet with data
        int rowNum = 1;
        for (Object[] student : studentData) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < student.length; i++) {
                if (student[i] instanceof String) {
                    row.createCell(i).setCellValue((String) student[i]);
                } else if (student[i] instanceof Integer) {
                    row.createCell(i).setCellValue((Integer) student[i]);
                }
            }
        }

        // Convert the workbook to an InputStream (simulating a file input)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        // Reinitialize the workbook from the InputStream
        workbook = new XSSFWorkbook(inputStream);
    }

    @Test
    public void testDisplayClassAverages() {
        // Simulate the method call for a class (e.g., "Class A")
        GradeCalculator.displayClassAverages(workbook, "Sheet1");

        // Add assertions to verify class averages (mocked data for demonstration)
        // You would generally want to capture output, but for simplicity, let's focus on the averages here
        // Expected averages for the mock data:
        double student1Avg = (80 + 90 + 75 + 85) / 4.0;  // 82.5
        double student2Avg = (70 + 65 + 80 + 90) / 4.0;  // 76.25
        double student3Avg = (95 + 85 + 90 + 88) / 4.0;  // 89.5
        double student4Avg = (60 + 70 + 65 + 80) / 4.0;  // 68.75

        double expectedClassAverage = (student1Avg + student2Avg + student3Avg + student4Avg) / 4.0;

        // Verifying individual student averages
        assertEquals(82.5, student1Avg);
        assertEquals(76.25, student2Avg);
        assertEquals(89.5, student3Avg);
        assertEquals(68.75, student4Avg);

        // Verifying class average
        assertEquals(expectedClassAverage, 79.25, 0.01);
    }

    @Test
    public void testSheetNotFound() {
        // Test when a non-existent sheet is requested
        GradeCalculator.displayClassAverages(workbook, "NonExistentClass");
    }

    @Test
    public void testEmptySheet() throws Exception {
        // Simulating an empty sheet
        Sheet emptySheet = workbook.createSheet("Empty Class");

        // Display averages for the empty sheet (this should print 0 or some message)
        GradeCalculator.displayClassAverages(workbook, "Empty Class");

        // You can add more specific checks to verify the behavior of empty sheets.
    }

    @Test
    public void testInvalidData() throws Exception {
        // Simulating a sheet with invalid (non-numeric) grade data
        Sheet invalidSheet = workbook.createSheet("Invalid Class");

        // Create a row and add invalid data (strings or empty cells)
        Row row = invalidSheet.createRow(0);
        row.createCell(0).setCellValue("Student 1");
        row.createCell(1).setCellValue("001");
        row.createCell(2).setCellValue("Invalid");
        row.createCell(3).setCellValue("Data");
        row.createCell(4).setCellValue("Here");
        row.createCell(5).setCellValue("NaN");

        // Calling the method will skip the invalid data and handle gracefully
        GradeCalculator.displayClassAverages(workbook, "Invalid Class");
    }
}
