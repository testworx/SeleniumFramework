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

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This abstract class will be extended by various UI control classes. It simply
 * acts as a container for functionality that could be considered common to all
 * UI controls.
 *
 * @author nvonop
 */
public abstract class BaseBot {

    private static final Logger LOGGER = Logger.getLogger(BaseBot.class
            .getName());
    protected WebDriver driver;
    WebElement baseWebElement;
    private long delay = Long.valueOf(System.getProperty("DELAY", "10"))
            .longValue();
    private long timeout = Long.valueOf(System.getProperty("TIMEOUT", "10"))
            .longValue();
    private By locator;

    /*
     * This method wraps the findElement method and returns a WebElement using
     * locator parameter. By default it will attempt to wait for an element to
     * be clickable and stationary before returning it. If this fails then after
     * 10 seconds it will simply match the element if possible and return it.
     */
    protected WebElement findControl(By theLocator) {
        try {

            setLocator(theLocator);
            LOGGER.log(Level.FINEST,
                    "Looking for WebElement: " + locator.toString());

            waitForElementThenInitialise();
            return baseWebElement;

        } catch (NoSuchElementException e) {
            LOGGER.log(Level.SEVERE,
                    "NoSuchElementException in findControl() method for: "
                            + locator.toString(), e);
            throw new NoSuchElementException(locator.toString());
        }
    }

    private void setLocator(By theLocator) {
        locator = theLocator;
    }

    private void waitForElementThenInitialise() {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            baseWebElement = wait.until(ExpectedConditions
                    .elementToBeClickable(locator));
            waitForControlToStopMoving();
        } catch (InterruptedException e) {
            LOGGER.log(Level.INFO,
                    "InterruptedException in findControl() method", e);
        } catch (TimeoutException e) {
            LOGGER.log(Level.INFO, "TimeoutException in findControl() method",
                    e);
        }
        if (baseWebElement == null) {
            baseWebElement = driver.findElement(locator);
        }
    }

    private void waitForControlToStopMoving() throws InterruptedException {
        Point elementStartLocation = baseWebElement.getLocation();
        Thread.sleep(delay);
        Point elementCurrentLocation = baseWebElement.getLocation();
        while (!elementCurrentLocation.equals(elementStartLocation)) {
            elementStartLocation = elementCurrentLocation;
            Thread.sleep(delay);
            elementCurrentLocation = baseWebElement.getLocation();
        }
    }
}
