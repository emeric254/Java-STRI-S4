package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class AffichageProfil extends Window implements Application, Bindable
{
	private Window window = null;
		
	
	//private TextInput nomInput = null;
 
	public AffichageProfil()
	{
	}
 
	
		
 
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(AffichageProfil.class, "/affichageProfil.bxml");
		window.open(display);
	}
	
	
	
	
	
	
		private BoxPane NomBoxPane = null;
	    private TextInput nomTextInput = null;
	    private TextInput prenomTextInput = null;
		private BoxPane MailBoxPane = null;
	    private TextInput mailTextInput = null;
        private BoxPane AdresseBoxPane = null;
        private TextInput rueTextInput = null;
        private TextInput villeTextInput = null;
        private TextInput codePostalTextInput = null;
        private BoxPane telephoneBoxPane = null;
        private TextInput numTelTextInput = null;
        private BoxPane diplomeBoxPane = null;
        private TextInput diplomeTextInput = null;
        private BoxPane competencesBoxPane = null;
        private TextInput competencesTextInput = null;

        
        
        private PushButton submitButton = null;
        private PushButton cancelButton = null;
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
            // Saisie adresse
            AdresseBoxPane = (BoxPane)namespace.get("addressBoxPane");
            rueTextInput = (TextInput)namespace.get("rueTextInput");
            villeTextInput = (TextInput)namespace.get("villeTextInput");
            codePostalTextInput = (TextInput)namespace.get("codePostalTextInput");
            // Saisie numéro de téléphone
            telephoneBoxPane = (BoxPane)namespace.get("telephoneBoxPane");
            numTelTextInput = (TextInput)namespace.get("numTelTextInput");
            
            // Date du diplome
            diplomeBoxPane = (BoxPane)namespace.get("diplomeBoxPane");
            
            submitButton = (PushButton)namespace.get("submitButton");
            errorLabel = (Label)namespace.get("errorLabel");

            submitButton.getButtonPressListeners().add(new ButtonPressListener() {
            
            	public void buttonPressed(Button button) {
                    String mail = mailTextInput.getText();
            		String prenom = prenomTextInput.getText();
                    String nom = nomTextInput.getText();
                    String rue = rueTextInput.getText();
                    String ville = villeTextInput.getText();
                    String codePostal = codePostalTextInput.getText();
                    String numeroTel = numTelTextInput.getText();
                    String dateDiplome = diplomeTextInput.getText();
                    
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
