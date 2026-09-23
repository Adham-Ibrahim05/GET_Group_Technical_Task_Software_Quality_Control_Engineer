# GET Group – Software Quality Control Engineer Technical Assessment

## Overview

This repository contains my solution for the **GET Group Software Quality Control Engineer Technical Assessment**.

The project is a Maven-based Java Selenium automation framework for testing the [SauceDemo](https://www.saucedemo.com/) application.

The assessment covers the following business flows:

* Login
* Product inventory
* Product sorting
* Product details
* Shopping cart
* End-to-end checkout
* Logout
* Cart/session behavior
* Positive, negative, and edge-case scenarios

---

## Project Structure

```text
GET_Group_Technical_Task_Software_Quality_Control_Engineer/
│
├── Document/
│   └── Manual Test Cases.xlsx
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Driver_Factory/
│   │   │   │   └── Driver_Factory.java
│   │   │   │
│   │   │   ├── Pages/
│   │   │   │   ├── Login_Page.java
│   │   │   │   ├── Inventory_Page.java
│   │   │   │   ├── Cart_Page.java
│   │   │   │   ├── Checkout_Page.java
│   │   │   │   └── Logout_Page.java
│   │   │   │
│   │   │   └── Utilities/
│   │   │       ├── DataUtility.java
│   │   │       ├── GeneralUtility.java
│   │   │       └── LogsUtility.java
│   │   │
│   │   └── resources/
│   │       ├── allure.properties
│   │       └── log4j2.properties
│   │
│   └── test/
│       ├── java/
│       │   ├── Listeners/
│       │   │   ├── IInvokedMethodListenerClass.java
│       │   │   └── ITestResultMethodListenerClass.java
│       │   │
│       │   └── Test_Scripts/
│       │       ├── BaseTest.java
│       │       ├── TC01_Login.java
│       │       ├── TC02_Product_Inventory_Sorting.java
│       │       ├── TC03_Shopping_Cart.java
│       │       ├── TC04_E2E_Checkout.java
│       │       ├── TC05_Logout.java
│       │       └── TC06_Cart_State.java
│       │
│       └── resources/
│           ├── Dataset.json
│           ├── Test Suite.xml
│           └── environment.properties
│
├── test-outputs/
│   ├── Logs/
│   ├── Screenshots/
│   └── allure-report/
│
├── .gitignore
└── pom.xml
```


## Framework Design

The automation framework follows the **Page Object Model (POM)** to separate page interactions from test logic.

### Main Components

**Driver Factory**

Responsible for WebDriver creation and browser management.

**Base Test**

Provides common test setup and teardown functionality.

**Page Objects**

Encapsulate page locators and reusable actions for the application's main pages.

**General Utility**

Contains reusable Selenium actions and synchronization methods, including explicit waits.

**Data Utility**

Reads test data from the external JSON dataset.

**Listeners**

Handle test execution events, logging, and failure-related evidence.

**Allure**

Provides the test execution reporting.

**Log4j**

Provides execution logging.

---

## Automated Test Scenarios

The automation suite contains the following test classes:

| Test Class                       | Coverage                                        |
| -------------------------------- | ----------------------------------------------- |
| `TC01_Login`                     | Valid and invalid login scenarios               |
| `TC02_Product_Inventory_Sorting` | Product listing, sorting, and product details   |
| `TC03_Shopping_Cart`             | Adding, removing, and verifying cart contents   |
| `TC04_E2E_Checkout`              | End-to-end checkout and order confirmation      |
| `TC05_Logout`                    | Logout and protected-page behavior              |
| `TC06_Cart_State`                | Cart state during navigation and login sessions |

Each test is designed to be independently executable and performs its own required setup rather than depending on the execution order of another test.

---

## Manual Test Cases

The manual testing deliverable is available at:

```text
Document/Manual Test Cases.xlsx
```

The manual test cases cover:

* Login
* Product listing
* Product sorting
* Product details
* Shopping cart
* Checkout
* Logout
* Cart/session behavior
* Positive scenarios
* Negative scenarios
* Edge cases

Each test case includes:

* Test Case ID
* Title
* Preconditions
* Test Steps
* Expected Result
* Priority

---

## Configuration

Application and browser configuration is maintained separately in:

```text
src/test/resources/environment.properties
```

Example:

```properties
URL=https://www.saucedemo.com/
Browser=chrome
```

The framework reads these settings so that the application URL and browser do not need to be hard-coded in individual test cases.

### Supported Browsers

The browser can be configured for:

```properties
Browser=chrome
```

```properties
Browser=edge
```

```properties
Browser=firefox
```

---

## Test Data Management

Test data is maintained separately in:

```text
src/test/resources/Dataset.json
```

This keeps test data separated from the test implementation and allows test data to be maintained without changing the test logic.

---

## Selenium Synchronization

The framework uses Selenium explicit waits where synchronization is required.

Reusable wait and interaction logic is centralized in:

```text
src/main/java/Utilities/GeneralUtility.java
```

This reduces duplicated synchronization code across Page Objects and helps improve test reliability.

---

## Running the Tests

To run the automated test suite, execute the following command from the project root directory:

mvn clean test

The Maven Surefire plugin is configured to execute the TestNG suite defined in:

src/test/resources/Test Suite.xml

## Prerequisites

Before running the automation project, make sure the following are installed:

* **JDK 23**
* **Apache Maven 3.9.8**
* **Git**
* **Allure Commandline** — required for generating or opening the Allure report

Verify the Java installation:

```bash
java -version
```

Expected:

```text
java version "23"
```

Verify Maven:

```bash
mvn -version
```

Expected:

```text
Apache Maven 3.9.8
Java version: 23
```

The project uses **Java 23** and **Maven 3.9.8**. Maven automatically downloads the project dependencies defined in `pom.xml` during the build.

## Generate Allure Report

After the tests finish, generate the Allure report:

allure generate allure-results --clean -o allure-report

Open the generated report:

allure open allure-report

## Cross-Browser Execution

The browser can be changed through:

```text
src/test/resources/environment.properties
```

For example:

```properties
Browser=chrome
```

or:

```properties
Browser=edge
```

or:

```properties
Browser=firefox
```

## Assessment Capabilities Implemented

The project demonstrates the following additional capabilities requested by the assessment:

### 1. Cross-Browser Execution

The browser can be configured through:

```text
src/test/resources/environment.properties
```

### 2. Configurable Application/Environment Settings

The application URL and browser configuration are externalized from the test implementation.

### 3. Automatic Screenshots on Test Failure

Test listeners provide screenshot evidence for applicable test failures.

### 4. Test Reporting

Allure is integrated for test execution reporting.

### 5. Reusable Driver Factory and Test Base

WebDriver creation and common test setup are centralized through the Driver Factory and Base Test architecture.

### 6. Improved Test Data Management

Test data is separated from test implementation and maintained through an external JSON dataset.
