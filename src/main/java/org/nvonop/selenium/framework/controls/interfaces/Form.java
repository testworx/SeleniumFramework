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

package org.nvonop.selenium.framework.controls.interfaces;

/**
 * This interface allows a user to configure UI controls with functionality to
 * fill in a form with data. Typically this interface would be implemented on a
 * page object.
 *
 * @author nvonop
 */
public interface Form {

    /**
     * This method enables data to be written into the form.
     *
     * @param args a String[] of data to write into each field within a form
     */
    void fillForm(String[] args);

    /**
     * This method enables the form data to be submitted. Typically this would
     * be by clicking the "Submit" button or similar.
     *
     * @return an Object. This would typically be another page object
     */
    Object submitForm();
}
