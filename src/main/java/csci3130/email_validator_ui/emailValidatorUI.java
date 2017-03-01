/* emailValidatorUI.java
 * CSCI3130 Assignment 3
 * 
 * By Connor Foran
 * B00649015
 */

package csci3130.email_validator_ui;

import csci3130.email_validator_ui.emailvalidator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * CSCI3130 Assignment 3 - Part 3
 * Email Validator UI
 * @author Connor Foran - B00649015
 */
@Theme("emailValidatorTheme")
public class emailValidatorUI extends UI {

	final emailvalidator EV = new emailvalidator();
	final VerticalLayout layout = new VerticalLayout();
	final Label text = new Label("");
	final TextField inputField = new TextField();
	
    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
    	Button button = new Button("Validate");
    	
    	inputField.setCaption("Type an email address to validate:");

        button.addClickListener( e -> {
        	validateString(inputField.getValue());
        });
        
        layout.addComponents(inputField, button, text);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }
    
    protected void validateString(String input)
    {
    	int result = EV.validate(input); //Call validation function
    	String output = "This string passed "+result+" rules, ";
        if(result == 6) //Check if it passed
        	output += "so it passed the validation check!";
        else
        	output += "so it did NOT pass the validation check...";
        text.setCaption(output); //Display text
    }

    @WebServlet(urlPatterns = "/*", name = "emailValidatorUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = emailValidatorUI.class, productionMode = false)
    public static class emailValidatorUIServlet extends VaadinServlet {}
}
