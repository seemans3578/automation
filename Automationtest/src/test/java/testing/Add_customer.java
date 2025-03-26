package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Add_customer {

    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Array of data sets for registration and reset
        String[][] data = {
                {"Seemans Tiwari", "9004381990", "seemanstiwari.model@gmail.com", "4884", "NewPassword123"},
                {"Manasi", "9988776655", "manasi@gmail.com", "1234", "SecurePass456"}
        };

        // Loop through both data sets
        for (int i = 0; i < data.length; i++) {
            // Open Registration Page
            driver.get("https://testffc.nimapinfotech.com/auth/register");
            Thread.sleep(2000);

            // Fill Registration Details with Parametrized Data
            driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(data[i][0]);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@placeholder='Mobile No']")).sendKeys(data[i][1]);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@placeholder='Email Id']")).sendKeys(data[i][2]);
            Thread.sleep(2000);

            // Click on Submit
            driver.findElement(By.xpath("//button[text()='Submit']")).click();
            Thread.sleep(3000);

            // Fill OTP and Reset Password Details
            driver.findElement(By.xpath("//input[@placeholder='OTP']")).sendKeys(data[i][3]);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(data[i][4]);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@placeholder='Confirm Password']")).sendKeys(data[i][4]);
            Thread.sleep(2000);

            // Click on Submit
            driver.findElement(By.xpath("//button[text()='Submit']")).click();
            Thread.sleep(3000);

            // Validate Successful Submission
            String successMessage = "";
            try {
                WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@class, 'toast-message')]"));
                successMessage = toastMessage.getText();
            } catch (Exception e) {
                successMessage = "No confirmation message displayed, enter valid OTP.";
            }

            // Print result for each set
            System.out.println("Result for " + data[i][0] + ": " + successMessage);
        }

        // Close the browser
        driver.quit();
    }
}
