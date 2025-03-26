package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Add_customer {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://testffc.nimapinfotech.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Valid login credentials
        String username = "seemanstiwari@gmail.com";
        String password = "12345678";
        String expectedMessage = "Dashboard";

        // Perform login
        performLogin(driver, username, password);

        // Validate response message
        String actualMessage = getResponseMessage(driver);

        // Check if actual message contains the expected text
        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Login successful!");

            // Add two customers if login is successful
            String[][] customerData = {
                    {"Seemans Tiwari Pvt Ltd", "Seemans Tiwari", "9004381990", "seemanstiwari1@gmail.com"},
                    {"Testing Pvt Ltd", "Automation", "9000050000", "automation@gmail.com"}
            };
            addMultipleCustomers(driver, customerData);
        } else {
            System.out.println("Login failed!");
        }

        // Keep browser open after execution
        while (true) {
            Thread.sleep(1000);
        }
    }

    // Method to perform login
    public static void performLogin(WebDriver driver, String username, String password) throws InterruptedException {
        // Clear and enter email/username
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email Id / Mobile No']"));
        emailField.clear();
        emailField.sendKeys(username);
        Thread.sleep(1000);

        // Clear and enter password
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
        Thread.sleep(1000);

        // Click on Sign In button
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();
        Thread.sleep(2000); // Wait for response
    }

    // Method to get response message after login
    public static String getResponseMessage(WebDriver driver) {
        try {
            Thread.sleep(2000); // Wait for page to load
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("/dashboard")) {
                return "Dashboard";
            } else {
                WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@class, 'toast-message')]"));
                return toastMessage.getText();
            }
        } catch (Exception e) {
            return "No message displayed";
        }
    }

    // Method to add multiple customers after successful login
    public static void addMultipleCustomers(WebDriver driver, String[][] customerData) throws InterruptedException {
        Thread.sleep(2000);

        // Click on "My Customers"
        driver.findElement(By.xpath("//span[text()='My Customers']")).click();
        Thread.sleep(2000);

        // Loop through each customer to add
        for (int i = 0; i < customerData.length; i++) {
            addNewCustomer(driver, customerData[i][0], customerData[i][1], customerData[i][2], customerData[i][3]);
            Thread.sleep(1000);

            // Refresh the page after adding each customer
            driver.navigate().refresh();
            Thread.sleep(2000);
        }
    }

    // Method to add a new customer
    public static void addNewCustomer(WebDriver driver, String leadName, String personName, String mobileNo, String email) throws InterruptedException {
        Thread.sleep(2000);

        // Click on "New Customer"
        driver.findElement(By.cssSelector("button[mattooltip='Create new customer']")).click();
        Thread.sleep(2000);

        // Fill in New Customer Details
        driver.findElement(By.cssSelector("input[formcontrolname='LeadName']")).sendKeys(leadName);
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("input[formcontrolname='PersonName']")).sendKeys(personName);
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("input[formcontrolname='MobileNo']")).sendKeys(mobileNo);
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("input[formcontrolname='Email']")).sendKeys(email);
        Thread.sleep(1000);

        // Click on Save Button
        driver.findElement(By.cssSelector("button[mattooltip='Save changes']")).click();
        Thread.sleep(2000);

        System.out.println("Customer added successfully: " + leadName);
    }
}
