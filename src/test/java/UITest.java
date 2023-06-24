import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.proj1.service.CurrencyConvertService;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {

    WebDriver driver;
    static ExtentReports extent;
    static ExtentTest currencyConvertTest, convertCurrencyEmptyTest, convertCurrencyBlankTest, convertCurrencyNotFoundTest;

    @BeforeAll
    static void initAll() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/SparkReport/Spark.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        currencyConvertTest = extent.createTest("Currency Convert Test", "Description of the test");
        convertCurrencyEmptyTest = extent.createTest("Convert Currency Empty Test", "Description of the test");
        convertCurrencyBlankTest = extent.createTest("Convert Currency Blank Test", "Description of the test");
        convertCurrencyNotFoundTest = extent.createTest("Convert Currency NotFound Test", "Description of the test");
    }

    @BeforeEach
    void init() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void convertCurrencyEmptyTest() {
        String from = "";
        String to = "";
        int amount = 100;

        CurrencyConvertService service = new CurrencyConvertService();
        double[] amount$rate = service.convertCurrencyUsingApi(from, to, amount);

        driver.get("http://localhost:8080/");

        WebElement amountElement = driver.findElement(By.name("amount"));
        amountElement.sendKeys(String.valueOf(amount));

        Select fromSelect = new Select(driver.findElement(By.name("from")));
        fromSelect.selectByValue(from);

        Select toSelect = new Select(driver.findElement(By.name("to")));
        toSelect.selectByValue(to);

        WebElement submitElement = driver.findElement(By.className("btn-convert"));
        submitElement.submit();

        WebElement convertedAmountElement = driver.findElement(By.id("convertedAmount"));
        WebElement rateElement = driver.findElement(By.id("rate"));

        convertCurrencyEmptyTest.log(Status.INFO, "Starting Currency Convert Empty Test");
        convertCurrencyEmptyTest.log(Status.INFO, "Amount: " + amount);
        convertCurrencyEmptyTest.log(Status.INFO, "From: " + from);
        convertCurrencyEmptyTest.log(Status.INFO, "To: " + to);
        convertCurrencyEmptyTest.log(Status.INFO, "Expected Converted Amount: " + amount$rate[0]);
        convertCurrencyEmptyTest.log(Status.INFO, "Expected Rate: " + amount$rate[1]);


        try {
            assertEquals((int) amount$rate[0], Integer.valueOf(convertedAmountElement.getText()));
            assertEquals(amount$rate[1], Double.valueOf(rateElement.getText()));
            convertCurrencyEmptyTest.log(Status.PASS, "Currency Convert Empty Test passed");

        } catch (AssertionError e) {
            convertCurrencyEmptyTest.log(Status.FAIL, "Currency Convert Empty Test Fail");
        }
    }

    @Test
    void convertCurrencyBlankTest() {
        String from = " ";
        String to = " ";
        int amount = 100;

        CurrencyConvertService service = new CurrencyConvertService();
        double[] amount$rate = service.convertCurrencyUsingApi(from, to, amount);

        driver.get("http://localhost:8080/");

        String scriptFromSelect = "var selectElement = document.getElementById('from'); " +
                "var newOption = document.createElement('option'); " +
                "newOption.value = ' ';" +
                "newOption.text = ' ';" +
                "selectElement.appendChild(newOption); ";

        String scriptToSelect = "var selectElement = document.getElementById('to'); " +
                "var newOption = document.createElement('option'); " +
                "newOption.value = ' ';" +
                "newOption.text = ' ';" +
                "selectElement.appendChild(newOption); ";

        ((JavascriptExecutor) driver).executeScript(scriptFromSelect);
        ((JavascriptExecutor) driver).executeScript(scriptToSelect);

        WebElement amountElement = driver.findElement(By.name("amount"));
        amountElement.sendKeys(String.valueOf(amount));

        Select fromSelect = new Select(driver.findElement(By.name("from")));
        fromSelect.selectByValue(from);

        Select toSelect = new Select(driver.findElement(By.name("to")));
        toSelect.selectByValue(to);

        WebElement submitElement = driver.findElement(By.className("btn-convert"));
        submitElement.submit();

        WebElement convertedAmountElement = driver.findElement(By.id("convertedAmount"));
        WebElement rateElement = driver.findElement(By.id("rate"));

        convertCurrencyBlankTest.log(Status.INFO, "Starting Currency Convert Blank Test");
        convertCurrencyBlankTest.log(Status.INFO, "Amount: " + amount);
        convertCurrencyBlankTest.log(Status.INFO, "From: " + from);
        convertCurrencyBlankTest.log(Status.INFO, "To: " + to);
        convertCurrencyBlankTest.log(Status.INFO, "Expected Converted Amount: " + amount$rate[0]);
        convertCurrencyBlankTest.log(Status.INFO, "Expected Rate: " + amount$rate[1]);


        try {
            assertEquals((int) amount$rate[0], Integer.valueOf(convertedAmountElement.getText()));
            assertEquals(amount$rate[1], Double.valueOf(rateElement.getText()));
            convertCurrencyBlankTest.log(Status.PASS, "Currency Convert Blank Test passed");

        } catch (AssertionError e) {
            convertCurrencyBlankTest.log(Status.FAIL, "Currency Convert Blank Test Fail");
        }
    }

    @Test
    public void currencyConvertTest() {

        String from = "USD";
        String to = "ILS";
        int amount = 100;

        CurrencyConvertService service = new CurrencyConvertService();
        double[] amount$rate = service.convertCurrencyUsingApi(from, to, amount);

        driver.get("http://localhost:8080/");

        WebElement amountElement = driver.findElement(By.name("amount"));
        amountElement.sendKeys(String.valueOf(amount));

        Select fromSelect = new Select(driver.findElement(By.name("from")));
        fromSelect.selectByValue(from);

        Select toSelect = new Select(driver.findElement(By.name("to")));
        toSelect.selectByValue(to);

        WebElement submitElement = driver.findElement(By.className("btn-convert"));
        submitElement.submit();

        WebElement convertedAmountElement = driver.findElement(By.id("convertedAmount"));
        WebElement rateElement = driver.findElement(By.id("rate"));

        // Log test steps and status
        currencyConvertTest.log(Status.INFO, "Starting Currency Convert Test");
        currencyConvertTest.log(Status.INFO, "Amount: " + amount);
        currencyConvertTest.log(Status.INFO, "From: " + from);
        currencyConvertTest.log(Status.INFO, "To: " + to);
        currencyConvertTest.log(Status.INFO, "Expected Converted Amount: " + amount$rate[0]);
        currencyConvertTest.log(Status.INFO, "Expected Rate: " + amount$rate[1]);

        // Assertions
        int convertedAmount = Integer.parseInt(convertedAmountElement.getText());
        double rate = Double.parseDouble(rateElement.getText());

        try {
            assertEquals((int) amount$rate[0], convertedAmount);
            assertEquals(amount$rate[1], rate);

            currencyConvertTest.log(Status.PASS, "Currency Convert Test passed");
        } catch (AssertionError e) {
            currencyConvertTest.log(Status.FAIL, "Currency Convert Test Fail");
        }
    }

    @Test
    void convertCurrencyNotFoundTest() {
        String from = "wael";
        String to = "ziada";
        int amount = 100;

        driver.get("http://localhost:8080/");

        String scriptFromSelect = "var selectElement = document.getElementById('from'); " +
                "var newOption = document.createElement('option'); " +
                "newOption.value = 'wael';" +
                "newOption.text = 'Hello';" +
                "selectElement.appendChild(newOption); ";

        String scriptToSelect = "var selectElement = document.getElementById('to'); " +
                "var newOption = document.createElement('option'); " +
                "newOption.value = 'ziada';" +
                "newOption.text = 'world';" +
                "selectElement.appendChild(newOption); ";

        ((JavascriptExecutor) driver).executeScript(scriptFromSelect);
        ((JavascriptExecutor) driver).executeScript(scriptToSelect);

        WebElement amountElement = driver.findElement(By.name("amount"));
        amountElement.sendKeys(String.valueOf(amount));

        Select fromSelect = new Select(driver.findElement(By.name("from")));
        fromSelect.selectByValue(from);

        Select toSelect = new Select(driver.findElement(By.name("to")));
        toSelect.selectByValue(to);

        WebElement submitElement = driver.findElement(By.className("btn-convert"));
        submitElement.submit();

        convertCurrencyNotFoundTest.log(Status.INFO, "Starting Currency Convert NotFound Test");
        convertCurrencyNotFoundTest.log(Status.INFO, "Amount: " + amount);
        convertCurrencyNotFoundTest.log(Status.INFO, "From: " + from);
        convertCurrencyNotFoundTest.log(Status.INFO, "To: " + to);
        convertCurrencyNotFoundTest.log(Status.INFO, "Expected Error Message: " +"Currency NotFound");


        try {
            WebElement errorElement = driver.findElement(By.id("errorMessage"));
            assertEquals("Currency NotFound", errorElement.getText());
            convertCurrencyNotFoundTest.log(Status.PASS, "Currency Convert Test passed");
        } catch (AssertionError e) {
            convertCurrencyNotFoundTest.log(Status.FAIL, "Currency Convert Test Fail");
        }

    }

    @AfterEach
    void closeDriver() {
        driver.close();
    }

    @AfterAll
    static void tearDownAll() {
        extent.flush();
    }
}

