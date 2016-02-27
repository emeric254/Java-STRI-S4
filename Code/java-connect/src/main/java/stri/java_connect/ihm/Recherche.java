package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class Recherche extends Window implements Application, Bindable
{
	private Window window = null;
		
	
	//private TextInput nomInput = null;
 
	public Recherche()
	{
	}
 
	
		
 
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(Recherche.class, "/recherche.bxml");
		window.open(display);
	}
	
	
	
	
	
	

		private BoxPane MailBoxPane = null;
	    private TextInput mailTextInput = null;
	    private TextInput listeTextInput = null;


        
        
        private PushButton submitButton = null;
        private PushButton cancelButton = null;
        private Label errorLabel = null;


        public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
    	{


        	
        	// Affichage adresse mail
        	MailBoxPane = (BoxPane)namespace.get("nameBoxPane");
            mailTextInput = (TextInput)namespace.get("mailTextInput");
            listeTextInput = (TextInput)namespace.get("listeTextInput");


            
            submitButton = (PushButton)namespace.get("submitButton");
            errorLabel = (Label)namespace.get("errorLabel");

            submitButton.getButtonPressListeners().add(new ButtonPressListener() {
            
            	public void buttonPressed(Button button) {
                    String mail = mailTextInput.getText();
                    String message = listeTextInput.getText();

                    
                    Form.Flag flag = null;
                    if (mail.length() == 0) {
                        flag = new Form.Flag(MessageType.ERROR, "Obligatoire");
                    }

                    Form.setFlag(MailBoxPane, flag);

                    if (flag == null) {
                        errorLabel.setText("");
                        //Prompt.prompt("Pretending to submit...", Forms.this);
                    } else {
                        errorLabel.setText("Some required information is missing.");
                    }
                }
            });
            
            cancelButton = (PushButton)namespace.get("cancelButton");
            //Faire retour menu            
        }
    
	
	
	 
	public boolean shutdown(final boolean optional) throws Exception
	{
		this.close();
		return false;
	}
 
	public void suspend() throws Exception
	{
	}
 
	public void resume() throws Exception
	{
	}
}
