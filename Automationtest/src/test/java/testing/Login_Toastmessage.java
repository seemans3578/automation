package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login_Toastmessage {

    public static void main(String[] args) throws InterruptedException {
    	WebDriver driver=new ChromeDriver();
		driver.get("https://testffc.nimapinfotech.com/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
        // Define login data (username, password, expected message)
        String[][] loginData = {
               
                {"wrongemail@gmail.com", "12345678", "Invalid Credentials"},
                {"seemanstiwari@gmail.com", "11112222", "Invalid Credentials"},
                {"", "", "Please enter your credentials"},
                {"seemanstiwari@gmail.com", "12345678", "Dashboard"}
        };

        // Loop through each set of credentials
        for (int i = 0; i < loginData.length; i++) {
            String username = loginData[i][0];
            String password = loginData[i][1];
            String expectedMessage = loginData[i][2];

            // Perform login
            performLogin(driver, username, password);

            // Validate response message
            String actualMessage = getResponseMessage(driver);

            // Check if actual message contains the expected text
            if (actualMessage.contains(expectedMessage)) {
                System.out.println("Test Case Passed for: " + username);
            } else {
                System.out.println("Test Case Failed for: " + username);
            }

            // Wait for 2 seconds before next iteration
            Thread.sleep(2000);
        }

        // Close browser
        driver.quit();
    }

    // Method to perform login
    public static void performLogin(WebDriver driver, String username, String password) throws InterruptedException {
        // Clear and enter email/username
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email Id / Mobile No']"));
        emailField.clear();
        emailField.sendKeys(username);

        // Clear and enter password
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click on Sign In button
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();
        Thread.sleep(2000); // Wait for response
    }

    // Method to get response message after login
 // Method to validate successful login by checking URL
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

}
