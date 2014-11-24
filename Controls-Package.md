# Controls Package

## Introduction
The intention here is to model UI controls and provide access to methods that interact with these controls.  The idea is to offer a sub set of the WebElement API in encapsulated methods to make UI interaction easier.

It is hoped that this will enable people to create UI controls with standard ways of interaction by utilising the interfaces that provide the functionality required.  This will also ensure a consistent naming convention throughout tests.

### Interfaces
The interface classes have been created (with more to follow) to allow controls to be modelled based on how we would want to interact with them.  E.g. we might want to click on a particular control.  Therefore we would make use of the **Clickable** interface.  Likewise we might want to read the text of a control.  Therefore we would use the **Readable** interface.

It is worth noting that not all interfaces are applicable to all types of control.

The currently implemented interfaces are as follows:

1. **Clickable** - Providing a standard way of clicking on a visible control.

2. **Detectable** - Providing a standard way of determining if a control is present.

3. **Form** - Providing a standard way of completing a form and submitting it.

4. **Readable** - Providing a standard way of reading the contents control.

5. **Selectable** - Providing a standard way of selecting or deselecting a control.

6. **Writable** - Providing a standard way of writing a value into a control.

### Controls
A number of classes have been created to model common UI elements such as buttons and text boxes.  The intention is for all common types of UI control to be modelled along with more complex custom controls such as calendars etc.  These classes make use of the interface classes above.

The currently implemented controls are as follows:

1. **Alert** - Contains methods for interacting with an Alert control.

2. **BaseControl** - The base class from which the other controls are extended.

3. **Button** - Contains methods for interacting with a Button control.

4. **CheckBox** - Contains methods for interacting with a CheckBox control.

5. **Frame** - Contains methods for interacting with a Frame control.

6. **Link** - Contains methods for interacting with a Link control.

7. **Message** - Contains methods for interacting with a Message control.  E.g. a validation message on a page.

8. **Page** - Contains methods for interacting with a Page control.

9. **RadioButton** - Contains methods for interacting with a RadioButton control.

10. **SelectBox** - Contains methods for interacting with a SelectBox control.

11. **Table** - Contains methods for interacting with a Table control.

12. **TextBox** - Contains methods for interacting with a TextBox control.