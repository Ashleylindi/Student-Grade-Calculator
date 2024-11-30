# Grade Calculator

This Java program reads student grade data from an Excel file and calculates the average grade for each student as well as the average for each class. 
The grades are expected to be in a specific format, and the program supports multiple classes, such as "Class A", "Class B", and "Class C".

## Features

- Reads student grades from an Excel file (.xlsx format).
- Calculates individual student averages.
- Calculates class averages for different classes.
- Supports multiple subjects, including English, Maths, Life Skills, and Mandarin.
- Handles missing or invalid data gracefully.

## Prerequisites
Before running the program, ensure you have the following installed:

- Java 8 or higher: The program is written in Java.
- Apache POI Library: The program uses Apache POI to read .xlsx files.
- To include Apache POI in your project, add the following dependencies to your pom.xml (if using Maven):

## Setup

- Clone this repository or download the project to your local machine.
- Ensure that you have the Excel file containing the student grades at the following path: src/main/resources/Student Grades.xlsx. The file should have the following structure:

| Student Name | Student ID | English | Maths | Life Skills | Mandarin |
|--------------|------------|---------|-------|-------------|----------|
| John Doe     | 12345      | 85      | 90    | 75          | 88       |
| Jane Smith   | 67890      | 78      | 85    | 80          | 92       |

## How to Use
- Input File: Ensure the Excel file is correctly formatted with the student data as described above.
- Run the Program: The program will read the grades from the Excel file, calculate the average grade for each student, and then calculate the class average for each class.
- Output: The output will be printed to the console, showing each student's average as well as the class average for each class.

Example output:
----------------------------------------------
Student John Doe (ID: 12345) Average: 84.5
Student Jane Smith (ID: 67890) Average: 83.75
Class - Class A Average: 84.13
----------------------------------------------


## Code Explanation
- GradeCalculator Class: This class is responsible for reading the Excel file and processing the student grade data.
- displayClassAverages Method: This method calculates the average grade for each student in a given class and prints out the result.
- Apache POI: The program uses Apache POI to read .xlsx Excel files.

## How to Customize
- You can add additional subjects by updating the range of columns in the displayClassAverages method.
- You can change the sheet names (e.g., "Class A", "Class B") by modifying the displayClassAverages method calls in the main method.

## Developer
- Name: Ashley Lindi Xaba
- github: https://github.com/Ashleylindi
- linkedin: www.linkedin.com/in/ashleylindi