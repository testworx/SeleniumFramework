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
	 * This method enables data to be written into the form.   
	 * @param args a String[] of data to write into each field within a form
	 */
	void fillForm(String[] args);

	/**
	 * This method enables the form data to be submitted.
	 * Typically this would be by clicking the "Submit" button or similar.
	 * @return an Object.  This would typically be another page object
	 */
	Object submitForm();
}
