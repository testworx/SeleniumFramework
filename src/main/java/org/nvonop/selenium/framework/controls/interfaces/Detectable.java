package org.nvonop.selenium.framework.controls.interfaces;

/**
 * This interface allows a user to configure UI controls with functionality to
 * determine whether or not the control is visible.
 * 
 * @author nvonop
 * 
 */
public interface Detectable {

	/**
	 * This method determines whether or not the control is visible.
	 * 
	 * @return true or false
	 */
	boolean exists();

}
