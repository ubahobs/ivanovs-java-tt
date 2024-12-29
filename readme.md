# Project README

## Overview
Test automation senior with Java and Selenium. Framework uses Selenium, TestNG, Rest-Assured, and Allure for robust and scalable testing. The project is configured for local execution and CI/CD pipelines on GitHub Actions.

## Prerequisites

- **Java JDK** (11 or higher)
- **Maven** (3.6 or higher)
- **Allure Command Line** (for generating reports)
- **MongoDB** (for api testing)

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-folder>
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

## Maven Commands

### Run Tests in Headless Mode
To execute tests in headless mode, use the following command:
```bash
mvn clean test -Dheadless=true
```
This passes the `headless` property to enable headless execution.

### Execute Only Smoke Tests
To run only tests tagged as `Smoke`, use:
```bash
mvn clean test -Dgroups=Smoke
```
This uses TestNG groups to filter and execute only `Smoke` tests.

### Execute One Specific Test
To run a specific test class or method:
```bash
mvn clean test -Dtest=ClassName#methodName
```
For example:
```bash
mvn clean test -Dtest=GoogleMeetTest#googleMeetTest
```
This runs the `googleMeetTest` method in the `GoogleMeetTest` class.

## Configuring Tests to Run in Parallel
Tests can be configured to run in parallel using one of the following methods:

### 1. Using the POM File
Modify the `maven-surefire-plugin` configuration in `pom.xml`:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M8</version>
    <configuration>
        <parallel>classes</parallel>
        <threadCount>4</threadCount>
    </configuration>
</plugin>
```
This executes tests in parallel by class with 4 threads.

### 2. Using Command Line
Override the parallel configuration via the command line:
```bash
mvn clean test -Dparallel=methods -DthreadCount=4
```
This runs test methods in parallel using 4 threads.

### 3. Using TestNG XML File
Configure parallel execution in the `testng.xml` file:
```xml
<suite name="Parallel Tests" parallel="methods" thread-count="4">
    <test name="Test Suite">
        <classes>
            <class name="com.example.tests.LoginTest" />
            <class name="com.example.tests.RegistrationTest" />
        </classes>
    </test>
</suite>
```
This configuration runs test methods in parallel with 4 threads.

## Allure Reporting

### Generate Allure Report
To generate the Allure report, use the following Maven command:
```bash
allure generate allure-results -o allure-report
```
The report will be available in the current directory.

### Open Allure Report
To open the report in a browser, use:
```bash
allure open allure-report
```

## CI/CD Integration

### GitHub Actions
GitHub Actions workflow is pre-configured in `.github/workflows/test.yml`. It includes:
- Running tests in CI pipelines
- Generating Allure reports
- Uploading reports as artifacts
## Directory Structure

- **`src/test/java`**: Contains test and utility classes.
- **`src/test/resources`**: Contains test data and configuration files.
- **`target`**: Maven's build directory.
- **`allure-results`**: Directory for Allure test execution results.
- **`pom.xml`**: Maven configuration file.
