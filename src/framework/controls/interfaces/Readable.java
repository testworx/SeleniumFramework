package framework.controls.interfaces;

/**
 * This interface allows a user to configure UI controls with functionality to read the text of the control.
 * @author nvonop
 *
 */
public interface Readable {

	/**
	 * Implementing this interface will ensure an object has a "read()" method.
	 * @return an object representing the text of the control.  Typically this would be a String.
	 */
	Object read();

}
