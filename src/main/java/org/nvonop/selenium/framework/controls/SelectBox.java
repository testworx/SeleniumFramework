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

package org.nvonop.selenium.framework.controls;

import org.nvonop.selenium.framework.controls.interfaces.Clickable;
import org.nvonop.selenium.framework.controls.interfaces.Readable;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class comprises of functionality that would be used when interacting
 * with a typical select box control.
 *
 * @author nvonop
 */
public class SelectBox extends BaseControl implements Clickable, Readable {

    private static final Logger LOGGER = Logger.getLogger(SelectBox.class
            .getName());

    Select selectBox;

    /**
     * Constructor that takes a WebDriver object and By object. These are then
     * set in the base class.
     *
     * @param driver
     * @param locator
     */
    public SelectBox(WebDriver driver, By locator) {
        this.driver = driver;
        setLocator(locator);

    }

    /*
     * (non-Javadoc)
     *
     * @see framework.controls.interfaces.Clickable#click()
     */
    @Override
    public void click() {
        getUnderlyingWebElement().click();
    }

    /**
     * This method gets all selected options in a select list
     *
     * @return List of all selected options
     */
    public List<WebElement> getSelectedOptions() {
        selectBox = new Select(getUnderlyingWebElement());
        return selectBox.getAllSelectedOptions();
    }

    /**
     * This method gets all available options in a select list
     *
     * @return List of all options
     */
    public List<WebElement> getOptions() {
        selectBox = new Select(getUnderlyingWebElement());
        return selectBox.getOptions();
    }

    /*
         * (non-Javadoc)
         *
         * @see framework.controls.interfaces.Readable#read()
         */
    @Override
    public Object read() {
        selectBox = new Select(getUnderlyingWebElement());
        WebElement selectedOption = null;
        String selectedOptionString = null;
        try {
            selectedOption = selectBox.getFirstSelectedOption();
            selectedOptionString = selectedOption.getText();
        } catch (StaleElementReferenceException e) {
            LOGGER.log(Level.INFO,
                    "StaleElementReferenceException in read() method", e);
            read();
        }
        return selectedOptionString;
    }

    /**
     * This method enables a select box option to be selected by it's value.
     *
     * @param option The String value of the option to be selected
     */
    public void selectOptionByValue(String option) {
        selectBox = new Select(getUnderlyingWebElement());

        try {
            selectBox.selectByValue(option);
        } catch (StaleElementReferenceException e) {
            LOGGER.log(
                    Level.INFO,
                    "StaleElementReferenceException in selectOptionByValue() method",
                    e);
            selectOptionByValue(option);
        }
    }

    /**
     * This method enables a select box option to be selected by it's visible
     * text.
     *
     * @param option The String value of the visible text for the option
     */
    public void selectOptionByVisibleText(String option) {
        selectBox = new Select(getUnderlyingWebElement());

        try {
            selectBox.selectByVisibleText(option);
        } catch (StaleElementReferenceException e) {
            LOGGER.log(
                    Level.INFO,
                    "StaleElementReferenceException in selectOptionByVisibleText() method",
                    e);
            selectOptionByVisibleText(option);
        }
    }

    /**
     * This method enables a select box option to be selected by it's index.
     *
     * @param index The index of the option to be selected
     */
    public void selectOptionByIndex(int index) {
        selectBox = new Select(getUnderlyingWebElement());
        try {
            selectBox.selectByIndex(index);
        } catch (StaleElementReferenceException e) {
            LOGGER.log(
                    Level.INFO,
                    "StaleElementReferenceException in selectOptionByIndex() method",
                    e);
            selectOptionByIndex(index);
        }
    }
}
