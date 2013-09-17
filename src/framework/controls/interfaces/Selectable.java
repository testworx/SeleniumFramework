package framework.controls.interfaces;

/**
 * This interface provides functionality for selecting and deselecting a control.
 * @author nvonop
 *
 */
public interface Selectable {

	/**
	 * Implementing this interface will ensure an object has an "select()" method.
	 */
	void select();

	/**
	 * Implementing this interface will ensure an object has a "deSelect()" method.
	 */
	void deSelect();

}
