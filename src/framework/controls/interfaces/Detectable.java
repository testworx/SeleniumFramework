package framework.controls.interfaces;

/**
 * This interface allows a user to configure UI controls with functionality to 
 * determine whether or not the control is visible.
 * @author nvonop
 *
 */
public interface Detectable {

	/**
	 * Implementing this interface will ensure an object has an "exists()" method.
	 * @return true or false
	 */
	boolean exists();

}
