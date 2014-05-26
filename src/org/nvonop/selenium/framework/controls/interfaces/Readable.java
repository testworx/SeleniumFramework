package org.nvonop.selenium.framework.controls.interfaces;

/**
 * This interface allows a user to configure UI controls with functionality to
 * read the text of the control.
 * 
 * @author nvonop
 * 
 */
public interface Readable {

	/**
	 * This method enables the text value of the control to be read.
	 * 
	 * @return an object representing the text of the control. Typically this
	 *         would be a String.
	 */
	Object read();

}
