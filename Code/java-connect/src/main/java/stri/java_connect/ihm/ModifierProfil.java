package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class ModifierProfil extends Window implements Application, Bindable
{
	private Window window;

	private BoxPane idConnexionBoxPane;
    private TextInput mailTextInput;
    private TextInput mdpTextInput;
    
	private BoxPane nameBoxPane;
    private TextInput prenomTextInput;
    private TextInput nomTextInput;
    
    private BoxPane addressBoxPane;
    private TextInput rueTextInput;
    private TextInput villeTextInput;
    private TextInput codePostalTextInput;
    
    private BoxPane telephoneBoxPane;
    private TextInput numTelTextInput;
    
    private BoxPane competencesBoxPane;
    private TextInput competencesTextInput;
    
    private PushButton submitButton;
    private PushButton retourMenuButton;
    private Label errorLabel;
 
	public ModifierProfil()
	{
		//
	}
	
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(ModifierProfil.class, "/modifierProfil.bxml");
		window.open(display);
	}
 
    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
        // affichage id/mdp 
    	idConnexionBoxPane = (BoxPane)namespace.get("idConnexionBoxPane");
    	mailTextInput = (TextInput)namespace.get("mailTextInput");
    	mdpTextInput = (TextInput)namespace.get("mdpTextInput");
    	// Affichage nom
    	nameBoxPane = (BoxPane)namespace.get("nameBoxPane");
        prenomTextInput = (TextInput)namespace.get("prenomTextInput");
        nomTextInput = (TextInput)namespace.get("nomTextInput");
        // Affichage adresse
        addressBoxPane = (BoxPane)namespace.get("addressBoxPane");
        rueTextInput = (TextInput)namespace.get("rueTextInput");
        villeTextInput = (TextInput)namespace.get("villeTextInput");
        codePostalTextInput = (TextInput)namespace.get("codePostalTextInput");
        // Affichage numéro de téléphone
        telephoneBoxPane = (BoxPane)namespace.get("telephoneBoxPane");
        numTelTextInput = (TextInput)namespace.get("numTelTextInput");
        // Affichage des compétences
        competencesBoxPane = (BoxPane)namespace.get("competencesBoxPane");
        competencesTextInput = (TextInput)namespace.get("competencesTextInput");
        
        submitButton = (PushButton)namespace.get("submitButton");
        retourMenuButton = (PushButton)namespace.get("retourMenuButton");
        errorLabel = (Label)namespace.get("errorLabel");
        
        retourMenuButton.getButtonPressListeners().add(new ButtonPressListener()
        {
        	public void buttonPressed(Button button)
        	{
        		DesktopApplicationContext.replaceSplashScreen(getDisplay());
        	}
        });
        
        submitButton.getButtonPressListeners().add(new ButtonPressListener()
        {
        	public void buttonPressed(Button button)
        	{
                String mail = mailTextInput.getText();
                String mdp = mdpTextInput.getText();
        		String prenom = prenomTextInput.getText();
                String nom = nomTextInput.getText();
                String rue = rueTextInput.getText();
                String ville = villeTextInput.getText();
                String codePostal = codePostalTextInput.getText();
                String numeroTel = numTelTextInput.getText();
            }
        });
    }

    
    
 
	
 
	private final ButtonPressListener retourListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Retour acceuil", ModifierProfil.this);
		}
	};
	
	
	

	public boolean shutdown(final boolean optional) throws Exception
	{
		this.close();
		return false;
	}
 
	public void suspend() throws Exception
	{
		//
	}
 
	public void resume() throws Exception
	{
		//
	}
}
