package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;


@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "OrcamentoTest.csv")


public class OrcamentoTest {
    private WebDriver driver;

    @Rule
    public TestName test = new TestName();


    @Before
    public void Setup() {
        driver = Web.createChorme();

    }

    @Test
    public void testPreencherFormulario(@Param(name = "tipo") String tipo, @Param(name = "make") String make, @Param(name = "model") String model,
                                        @Param(name = "cylindercapacity") String cylindercapacity, @Param(name = "engineperformance") String engineperformance,
                                        @Param(name = "dateofmanufacture") String dateofmanufacture, @Param(name = "numberofseatsmotorcycle") String numberofseatsmotorcycle,
                                        @Param(name = "listprice") String listprice, @Param(name = "annualmileage") String annualmileage,
                                        @Param(name = "lastname") String lastname, @Param(name = "firstname") String firstname,
                                        @Param(name = "birthdate") String birthdate,@Param(name = "streetaddress") String streetaddress,
                                        @Param(name = "country") String country, @Param(name = "zipcode") String zipcode,
                                        @Param(name = "city") String city, @Param(name = "occupation") String occupation,
                                        @Param(name = "website") String website,@Param(name = "startdate") String startdate,
                                        @Param(name = "insurancesum") String insurancesum, @Param(name = "damageinsurance") String damageinsurance,
                                        @Param(name = "email") String email,@Param(name = "phone") String phone,
                                        @Param(name = "username") String username, @Param(name = "password") String password,
                                        @Param(name = "confirmpassword") String confirmpassword) throws IOException {
        //Clicar na opção Motorcycle
        //Aba Enter Vehicle Data

        driver.findElement(By.id(tipo)).click();

        Select selectMake = new Select(driver.findElement(By.id("make")));
        selectMake.selectByValue(make);

        Select selectModel = new Select(driver.findElement(By.id("model")));
        selectModel.selectByValue(model);

        driver.findElement(By.id("cylindercapacity")).sendKeys(cylindercapacity);
        driver.findElement(By.id("engineperformance")).sendKeys(engineperformance);
        driver.findElement(By.id("dateofmanufacture")).sendKeys(dateofmanufacture);

        Select selectNumber = new Select(driver.findElement(By.id("numberofseatsmotorcycle")));
        selectNumber.selectByValue(numberofseatsmotorcycle);

        driver.findElement(By.id("listprice")).sendKeys(listprice);
        driver.findElement(By.id("annualmileage")).sendKeys(annualmileage);
        driver.findElement(By.id("nextenterinsurantdata")).click();


        //Aba Enter Insurant Data

        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("birthdate")).sendKeys(birthdate);
        driver.findElement(By.xpath("//form[@id='insurance-form']/div/section[2]/div[4]/p/label[2]/span")).click();
        driver.findElement(By.id("streetaddress")).sendKeys(streetaddress);

        Select selectCountry = new Select(driver.findElement(By.id("country")));
        selectCountry.selectByValue(country);

        driver.findElement(By.id("zipcode")).sendKeys(zipcode);
        driver.findElement(By.id("city")).sendKeys(city);

        Select selectOcupation = new Select(driver.findElement(By.id("occupation")));
        selectOcupation.selectByValue(occupation);

        driver.findElement(By.xpath("//form[@id='insurance-form']/div/section[2]/div[10]/p/label[3]/span")).click();
        driver.findElement(By.id("website")).sendKeys(website);
        driver.findElement(By.id("nextenterproductdata")).click();

        //Aba Enter Product Data

        driver.findElement(By.id("startdate")).sendKeys(startdate);

        Select selectInsurance = new Select(driver.findElement(By.id("insurancesum")));
        selectInsurance.selectByValue(insurancesum);

        Select selectDamage = new Select(driver.findElement(By.id("damageinsurance")));
        selectDamage.selectByValue(damageinsurance);

        driver.findElement(By.xpath("//label[contains(.,'Euro Protection')]")).click();
        driver.findElement(By.id("nextselectpriceoption")).click();

        //Aba Select Price Option
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/form/div/section[4]/section/div[1]/table/tfoot/tr/th[2]/label[2]/span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.id("nextsendquote")).click();

        //Aba Send Quote

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("phone")).sendKeys(phone);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmpassword")).sendKeys(confirmpassword);
        driver.findElement(By.id("sendemail")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        WebElement messageAlert = driver.findElement(By.xpath("/html/body/div[4]/h2\n"));
        String textMessageAlert = messageAlert.getText();
        assertEquals("Sending e-mail success!", textMessageAlert);

        String screenshotArquivo = "C:\\Users\\Vagner\\driver\\Print\\" + Generator.dataHoraParaArquivo() + ".jpg";
        Screenshot.take(driver, screenshotArquivo);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
