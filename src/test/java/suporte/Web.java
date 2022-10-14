package suporte;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChorme() {
        WebDriver driver;
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);


        driver.get("http://sampleapp.tricentis.com/101/app.php");
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        return driver;
    }
}
