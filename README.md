# 🧪 TestDemo — Selenium Test Automation Framework

## 📘 Overview
**TestDemo** is a Java-based automated testing framework built using **Selenium WebDriver**, **TestNG**, and **Maven**.  
It follows the **Page Object Model (POM)** design pattern to promote code reusability, readability, and maintainability.

The project also integrates **GitHub Actions** for continuous integration (CI) and **Qodana** for static code quality checks.

---

## 📂 Project Structure

```
TestDemo/
├── .github/
│   └── workflows/
│       ├── maven-tests.yml          # CI workflow for running tests automatically
│       └── qodana_code_quality.yml  # Qodana workflow for static code analysis
│
├── src/
│   └── test/
│       └── java/
│           ├── pages/               # Page Object classes
│           │   ├── BasePage.java
│           │   └── LoginPage.java
│           │
│           ├── tests/               # Test classes
│           │   └── TestLogin.java
│           │
│           └── utils/               # Utility and setup classes
│               └── DriverSetup.java
│
├── target/                          # Generated test reports and build output
├── .env                             # Environment variables (e.g., base URL, credentials)
├── .gitignore                       # Ignored files for Git
├── pom.xml                          # Maven build configuration and dependencies
```

---

## ⚙️ Tech Stack

| Component | Description |
|------------|-------------|
| **Language** | Java |
| **Build Tool** | Maven |
| **Test Framework** | TestNG |
| **Automation Tool** | Selenium WebDriver |
| **Design Pattern** | Page Object Model (POM) |
| **CI/CD** | GitHub Actions |
| **Reports | Allure / Extent Reports |

---

## 🚀 Getting Started

### ✅ Prerequisites
- Java 11 or higher
- Maven 3.8+
- Chrome or any other supported browser
- Git

### 📥 Clone the Repository
```bash
git clone https://github.com/your-username/TestDemo.git
cd TestDemo
```

### ⚡ Install Dependencies
```bash
mvn clean install
```

### ▶️ Run Tests
```bash
mvn test
```

Or run a specific test class:
```bash
mvn -Dtest=TestLogin test
```

---

## 🧩 Project Components

### 🧱 BasePage.java
Contains common reusable WebDriver actions such as:
- Click elements
- Type text
- Wait for elements
- Get text or attributes

### 🔐 LoginPage.java
Implements login page locators and methods such as:
- `enterUsername()`
- `enterPassword()`
- `clickLoginButton()`
- `getLoginErrorMessage()`

### 🧪 TestLogin.java
Contains login test cases verifying:
- Successful login with valid credentials
- Failed login with invalid credentials

### ⚙️ DriverSetup.java
Handles browser setup and teardown logic using WebDriver.  
Supports multiple browsers if extended.

---

## 🧰 GitHub Actions (CI)

This project includes two workflows under `.github/workflows/`:

- **maven-tests.yml** → Runs all test cases automatically on push or pull request.
- **qodana_code_quality.yml** → Analyzes code using JetBrains Qodana for static analysis.

---


## 📊 Reporting (Optional Enhancement)

To integrate **Allure Reports**:

1. Add the following Maven dependency to `pom.xml`:
   ```xml
   <dependency>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-testng</artifactId>
       <version>2.24.0</version>
   </dependency>
   ```
2. After running tests, generate the report:
   ```bash
   allure serve target/allure-results
   ```

---

## 🧩 Future Enhancements
- Add support for multiple environments (dev, staging, prod).
- Integrate Allure or ExtentReports for detailed HTML reporting.
- Add parallel test execution using TestNG or Maven Surefire.
- Implement ConfigManager for managing environment variables.
- Include Docker support for headless browser execution.

---

## 👨‍💻 Author
**GM Imran**  
QA Engineer | Vivasoft Limited

---

## 🏁 License
This project is licensed under the **MIT License** — feel free to modify and reuse.