# Automation Framework - Version 1.0

## 1. Overview
This is a modular Test Automation Framework designed to handle both Web and Mobile testing. It supports:
- **Web Testing**: Automates E2E flows for web applications using Selenium.
- **Mobile Testing**: Not done (Will do in next release)
- **Reusable Core Components**: Shared utilities, drivers, and reporting tools.
- **Environment Support**: Easily configurable for Dev, QA, and Staging environments.
- **CI/CD Integration**: Compatible with Jenkins for continuous integration and delivery.

---

## 2. Prerequisites
Before running the tests, ensure the following tools are installed:
- **Java JDK**: Version 24 or higher.
- **Maven**: Version 3.9.9 or higher.
- **Node.js**: Required for Appium server.
- **Appium**: Install globally using `npm install -g appium`.
- **WebDriver**: ChromeDriver, GeckoDriver, or other browser drivers as needed.
- **iOS Simulator**: For mobile testing (if testing on iOS, only supports for MacOS).

---

## 3. Project Structure
automation-framework/
│── core/                    # Shared components for Web and Mobile
│   ├── api/                 # API clients (e.g., RestAssured)
│   ├── utils/               # Utilities (e.g., config readers, DB helpers)
│   ├── web-drivers/         # Selenium WebDriver management
│   ├── mobile-drivers/      # Appium driver management
│   ├── reports/             # Reporting tools (e.g., ExtentReports)
│   ├── pom.xml              # Maven configuration for core
│
│── test-web-ctflearn/       # Web testing module
│   ├── pages/               # Page Object Model for web
│   ├── tests/               # Test cases for web
│   ├── resources/           # Contains data, report, browser driver
│   ├── pom.xml              # Maven configuration for web
│
│── test-app-finance/        # Mobile testing module
│   ├── pages/               # Page Object Model for mobile
│   ├── tests/               # Test cases for mobile
│   ├── pom.xml              # Maven configuration for mobile
│
│── pom.xml                  # Parent POM for managing modules
│── README.md                # Project documentation


## 4. Setting Up the Development Environment

### 4.1. Tools: Visual Studio Code (VSCode)
To work with this framework, we recommend using **Visual Studio Code** as the IDE. Follow these steps to set up your environment:

1. **Install VSCode**: Download and install VSCode from [https://code.visualstudio.com/](https://code.visualstudio.com/).
2. **Install Extensions**:
    - **Java Extension Pack**: Provides support for Java development.
    - **Maven for Java**: Helps manage Maven projects.
    - **Test Runner for Java**: Enables running and debugging tests.
    - **Prettier**: For consistent formatting of code and markdown files.

---

### 4.2. Installing Java and Maven
1. **Install Java**:
    - Download and install the **Java JDK** (version 24 or higher) from [https://www.oracle.com/java/technologies/javase-downloads.html](https://www.oracle.com/java/technologies/javase-downloads.html).
    - Set the `JAVA_HOME` environment variable:
      - On Windows:
         1. Go to **System Properties** > **Environment Variables**.
         2. Add a new system variable named `JAVA_HOME` and set its value to the JDK installation path (e.g., `C:\Program Files\Java\jdk-24`).
         3. Add `%JAVA_HOME%\bin` to the `Path` variable.
      - On Mac/Linux:
         ```bash
         export JAVA_HOME=/path/to/jdk
         export PATH=$JAVA_HOME/bin:$PATH
         ```
    - Verify installation:
      ```bash
      java -version
      ```

2. **Install Maven**:
    - Download and install **Apache Maven** (version 3.9.9 or higher) from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).
    - Set the `MAVEN_HOME` environment variable:
      - On Windows:
         1. Add a new system variable named `MAVEN_HOME` and set its value to the Maven installation path (e.g., `C:\Program Files\Apache\Maven`).
         2. Add `%MAVEN_HOME%\bin` to the `Path` variable.
      - On Mac/Linux:
         ```bash
         export MAVEN_HOME=/path/to/maven
         export PATH=$MAVEN_HOME/bin:$PATH
         ```
    - Verify installation:
      ```bash
      mvn -version
      ```

---

### 4.3. Building the Code
To build the project, follow these steps:

1. Open a terminal and navigate to the root directory of the project:
    ```bash
    cd /automation-framework
    ```

2. Run the Maven build command:
    ```bash
    mvn clean install -DskipTests
    ```
    This will clean the project, compile the code, and package it.

---

## 5. Configuring `config.properties` and `login.json` before run test for web ctflearn

Before running the tests, ensure the following configurations are set up:

### 1. Open `resource\config.properties`
This file contains essential configurations for the framework. Below is an example configuration:

```properties
# Browser and WebDriver
browser.name=chrome
browser.is.headless=false
webdriver.remote.url=
webdriver.chromedriver.path=src/main/resources/browserdrivers/chromedriver.exe
webdriver.edgedriver.path=

# Application URL
web.url=https://ctflearn.com

# Report
report.path=src/main/resources/reports/report.html
```

- **`browser.name`**: Specify the browser to use (e.g., `chrome`, `edge`).
- **`browser.is.headless`**: Set to `true` for headless mode or `false` for normal mode.
- **`webdriver.chromedriver.path`**: Path to the ChromeDriver executable.
- **`webdriver.remote.url`**: If you want to connect to a remote Selenium Grid, specify the URL of the Selenium Grid server (e.g., `http://localhost:4444/wd/hub` or the remote server's address). Leave it empty if not using a remote grid.
```properties
webdriver.remote.url=http://localhost:4444/wd/hub
```
- If using a cloud-based Selenium Grid (e.g., BrowserStack or Sauce Labs), provide the respective URL and authentication details as required.
- **`web.url`**: The URL of the application under test.
- **`report.path`**: Path where the test report will be generated.

```markdown
/**
 * Note:
 * - The WebDriverManager library currently only supports downloading ChromeDriver version 114.
 * - For higher versions, please refer to: 
 *   https://googlechromelabs.github.io/chrome-for-testing
 * - Ensure consistency between the Chrome version on your machine and the ChromeDriver version you intend to use.
 * - Other browsers may have similar issues; please check their official websites for more details.
 * 
 * Ex: I run with chromedriver 134.0.6998.165 https://storage.googleapis.com/chrome-for-testing-public/134.0.6998.165/win64/chrome-win64.zip
 * Thank you.
 */
```

### 2. Open `resource\accounts\login.json`
This file stores login credentials for the application. Below is an example structure:

```json
{
    "account_create_challenge": {
        "username": "<your_username>",
        "password": "<your_password>"
    }
}

```

- Replace `your_username` and `your_password` with valid credentials for the application.


### 3. Running Tests for the Web Module
To execute the test cases for the `test-web-ctflearn` module:

1. Navigate to the `test-web-ctflearn` directory:
    ```bash
    cd test-web-ctflearn
    ```

2. Run the tests using Maven:
    ```bash
    mvn test
    ```

3. View the test results:
    - Test results will be available in the `target/surefire-reports` directory.
    - If a reporting tool like ExtentReports is configured, the HTML report will be generated in the `resources/reports` folder.

4. Running Tests with Environment-Specific Configurations

    You can run tests for different environments by passing properties through the command line. This allows you to dynamically set configurations like browser type, environment URL, and report paths without modifying the `config.properties` file.

    #### Example Command:
    ```bash
    mvn test -Dbrowser.name=firefox -Dbrowser.is.headless=true -Dweb.url=https://staging.ctflearn.com -Dreport.path=target/reports/staging-report.html
    ```

    #### Explanation of Parameters:
    - **`-Dbrowser.name`**: Specifies the browser to use (e.g., `chrome`, `firefox`, `edge`).
    - **`-Dbrowser.is.headless`**: Enables or disables headless mode (`true` or `false`).
    - **`-Dweb.url`**: Sets the application URL for the specific environment (e.g., `https://staging.ctflearn.com` for staging).
    - **`-Dreport.path`**: Defines the path where the test report will be generated.

    #### Benefits:
    - Easily switch between environments (e.g., Dev, QA, Staging) without modifying the configuration files.
    - Customize test runs for different browsers or headless execution.
    - Generate separate reports for each environment.

    By using this approach, you can make your test execution more flexible and adaptable to various scenarios.
--- 

By following these steps, you can set up your environment, build the project, and run the web module tests successfully.