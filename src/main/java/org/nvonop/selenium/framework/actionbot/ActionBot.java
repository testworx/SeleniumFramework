/*
The MIT License (MIT)

Copyright (c) 2015 Nicholas Oppersdorff

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package org.nvonop.selenium.framework.actionbot;

import org.nvonop.selenium.framework.controls.BaseControl;
import org.openqa.selenium.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionBot extends BaseControl {

    private static final Logger LOGGER = Logger.getLogger(ActionBot.class
            .getName());

    public ActionBot(WebDriver driver) {
        super.driver = driver;
    }

    /**
     * Clicks the element.
     *
     * @param locator
     */
    public void click(By locator) {
        findControl(locator).click();
    }

    /**
     * Checks visibility
     *
     * @param locator
     */
    public boolean checkVisibility(By locator) {
        try {
            return findControl(locator).isDisplayed();
        } catch (ElementNotVisibleException e) {
            LOGGER.log(Level.INFO,
                    "ElementNotVisibleException in exists() method", e);
            return false;
        } catch (TimeoutException e) {
            LOGGER.log(Level.INFO, "TimeoutException in exists() method", e);
            return false;
        }
    }

    /**
     * Submits an element.
     *
     * @param locator
     */
    public void submit(By locator) {

        findControl(locator).submit();

    }

    /**
     * Reads the text value of an element.
     *
     * @param locator
     */
    public String readText(By locator) {
        return findControl(locator).getText();
    }

    /**
     * Reads the specified attribute of an element.
     *
     * @param locator
     * @param attribute
     */
    public String readAttribute(By locator, String attribute) {
        return findControl(locator).getAttribute("value");
    }


    /**
     * Type something into an input field. WebDriver doesn't normally clear
     * these before typing, so this method does that first. It also sends a
     * return key to move the focus out of the element.
     *
     * @param locator
     */
    public void type(By locator, String text) {
        WebElement element = findControl(locator);
        element.sendKeys(text);
    }
}
