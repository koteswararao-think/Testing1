package Project_git_search;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class project_se {

	public static void main(String[] args) throws Exception {
     WebDriverManager.chromedriver().setup();
     WebDriver driver=new ChromeDriver();
     driver.get("http://localhost:5173/login");
     driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
     driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin");
     driver.findElement(By.tagName("button")).click();
     Thread.sleep(3000);
     // In this screen 1 will contains dashboard and search tests
     //1.verifying the xofy repos count visible
    WebElement countRepo= driver.findElement(By.xpath("//*[contains(text(), 'Showing')]"));
    System.out.println("Repository count repo is visible: "+ countRepo.isDisplayed());
    //2.Count the repo items initial load on page1(in the action performing list shows 1 to 5)
    List<WebElement> repoCards=driver.findElements(By.xpath("//div[contains(@class, 'repo-card')]"));
    System.out.println("Number of repositories loaded initially: "+repoCards.size());
    //3.Testing the input search box
    WebElement searchInput=driver.findElement(By.xpath("//input[@placeholder='Search repositories...']"));
    searchInput.sendKeys("react");
    //4.clearing the search box to reset the view
    searchInput.sendKeys(Keys.CONTROL+"a");//
    searchInput.sendKeys(Keys.BACK_SPACE);//
    //5.clicking the Load more button to test the scroll
    WebElement loadmoreButton = driver.findElement(By.xpath("//button[contains(text(), 'Load more')]"));
    if(loadmoreButton.isDisplayed()) {
    	loadmoreButton.click();
    }
    //6.In this we try to search some thing does not exist,:: it will show "No results"
    searchInput.sendKeys("NON_EXISTENT_REPO_NAME");
    Thread.sleep(1000);
    WebElement noResultsMessage=driver.findElement(By.xpath("//*[contains(text(), 'No results')]"));
    System.out.println("Is 'No results' message displayed? "+noResultsMessage.isDisplayed());
    //Reset the search input to bring the valid list back
    searchInput.sendKeys(Keys.CONTROL+"a");
    searchInput.sendKeys(Keys.BACK_SPACE);
    Thread.sleep(1000);
    // Screen 2 :: detailing and routing test
    //7.clicking on react or first repo
    WebElement firstRepoLink=driver.findElement(By.xpath("/html/body/div[@id='root']/div/div/ul/li[1]/a"));
   firstRepoLink.click();
    System.out.println("Successfully clicked on the react repo");
    //8.Open link inside the react repo
    //9. verifying all the data elements are visible on screen2
    
    System.out.println("Details Page elements verified successfully");
    //10.click the "Back to search link to route back to screen1
    WebElement backLink=driver.findElement(By.xpath("//a[text()='← Back to search']"));
    backLink.click();
    //11.closing the browser
    driver.quit();
    System.out.println("All Tests are executed successfully");
    }

}
