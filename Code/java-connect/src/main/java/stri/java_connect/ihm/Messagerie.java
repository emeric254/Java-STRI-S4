package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class Messagerie extends Window implements Application, Bindable
{
	private Window window = null;
		
	
	//private TextInput nomInput = null;
 
	public Messagerie()
	{
	}
 
	
		
 
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(Messagerie.class, "/messagerie.bxml");
		window.open(display);
	}
	
	
	
	
	
	
		private BoxPane NomBoxPane = null;
	    private TextInput nomTextInput = null;
	    private TextInput prenomTextInput = null;
		private BoxPane MailBoxPane = null;
	    private TextInput mailTextInput = null;


        
        
        private PushButton submitButton = null;
        private Label errorLabel = null;


        public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
    	{
            // Affichage nom/prenom 
        	NomBoxPane = (BoxPane)namespace.get("idConnexionBoxPane");
        	nomTextInput = (TextInput)namespace.get("mailTextInput");
        	prenomTextInput = (TextInput)namespace.get("mdpTextInput");
        	// Affichage adresse mail
        	MailBoxPane = (BoxPane)namespace.get("nameBoxPane");
            mailTextInput = (TextInput)namespace.get("prenomTextInput");

            
            submitButton = (PushButton)namespace.get("submitButton");
            errorLabel = (Label)namespace.get("errorLabel");

            submitButton.getButtonPressListeners().add(new ButtonPressListener() {
            
            	public void buttonPressed(Button button) {
                    String mail = mailTextInput.getText();
            		String prenom = prenomTextInput.getText();
                    String nom = nomTextInput.getText();

                    
                    Form.Flag flag = null;
                    if (prenom.length() == 0
                        || nom.length() == 0) {
                        flag = new Form.Flag(MessageType.ERROR, "Onligatoire");
                    }

                    Form.setFlag(NomBoxPane, flag);

                    if (flag == null) {
                        errorLabel.setText("");
                        //Prompt.prompt("Pretending to submit...", Forms.this);
                    } else {
                        errorLabel.setText("Some required information is missing.");
                    }
                }
            });
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
