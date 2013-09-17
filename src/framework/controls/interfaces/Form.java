package framework.controls.interfaces;

/**
 * This interface allows a user to configure UI controls with functionality to 
 * fill in a form with data.  Typically this interface would be implemented on
 *  a page object.
 * @author nvonop
 *
 */
public interface Form {

	/**
	 * Implementing the interface will provide a "fillForm()" method that takes
	 * a String[] of data to write into each field within a form.
	 * @param args
	 */
	void fillForm(String[] args);

	/**
	 * Implementing the interface will provide a "submitForm()" method that
	 * would submit the form data in some way.  Typically this would be by 
	 * clicking the "Submit" button or similar.
	 * @return an Object.  This would typically be another page object
	 */
	Object submitForm();
}
