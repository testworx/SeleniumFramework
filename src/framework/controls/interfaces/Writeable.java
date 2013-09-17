package framework.controls.interfaces;

/**
 * This interface provides functionality for writing data into a control.
 * @author nvonop
 *
 */
public interface Writeable {

	/**
	 * Implementing this interface will ensure an object has a "write()" method.  
	 * This method will write "value" into the control.
	 * @param value
	 */
	void write(String value);
}
