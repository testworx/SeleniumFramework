package org.nvonop.selenium.framework.controls.interfaces;

/**
 * This interface provides functionality for selecting and deselecting a control.
 * @author nvonop
 *
 */
public interface Selectable {

	/**
	 * This method enables the control to be set as selected.
	 */
	void select();

	/**
	 * This method enables the control to be set as deselected.
	 */
	void deSelect();

}
