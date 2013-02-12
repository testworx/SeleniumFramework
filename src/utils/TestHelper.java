package utils;

import java.util.Set;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestHelper {
	
	private WebDriver driver;
	
	public TestHelper(WebDriver passedInDriver) {
		driver = passedInDriver;
	}
	 public void SwitchToWindow(String windowTitle) {
         Set<String> windows = driver.getWindowHandles();

         for (String window : windows) {
             driver.switchTo().window(window);
             if (driver.getTitle().contains(windowTitle)) {
            	 System.out.println("Switching to window: "+driver.getTitle());
                 return;
             }
             else {
            	 System.out.println("No match for window: "+driver.getTitle());
             }
         }
     }

}
