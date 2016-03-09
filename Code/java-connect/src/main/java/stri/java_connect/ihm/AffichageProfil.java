package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

/**
 * @author emeric
 *
 */
public class AffichageProfil extends Window implements Application, Bindable
{
	private Window window;

	private BoxPane NomBoxPane;
    private TextInput nomTextInput;
    private TextInput prenomTextInput;
    
	private BoxPane MailBoxPane;
    private TextInput mailTextInput;
    
    private BoxPane AdresseBoxPane;
    private TextInput rueTextInput;
    private TextInput villeTextInput;
    private TextInput codePostalTextInput;
    
    private BoxPane telephoneBoxPane;
    private TextInput numTelTextInput;
    
    private BoxPane diplomeBoxPane;
    private TextInput diplomeTextInput;
    
    private BoxPane competencesBoxPane;
    private TextInput competencesTextInput;

    private PushButton cancelButton;
    private Label errorLabel;
    
   	/**
   	 * 
   	 */
   	public AffichageProfil()
   	{
   		//
   	}

   	public void startup(final Display display, final Map<String, String> properties) throws Exception
   	{
   		BXMLSerializer bxmlSerializer = new BXMLSerializer();
   		window = (Window) bxmlSerializer.readObject(AffichageProfil.class, "/affichageProfil.bxml");
   		window.open(display);
   	}

    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
        // Affichage nom/prenom 
    	NomBoxPane = (BoxPane)namespace.get("idConnexionBoxPane");
    	nomTextInput = (TextInput)namespace.get("mailTextInput");
    	prenomTextInput = (TextInput)namespace.get("mdpTextInput");
    	// Affichage adresse mail
    	MailBoxPane = (BoxPane)namespace.get("nameBoxPane");
        mailTextInput = (TextInput)namespace.get("prenomTextInput");
        // adresse
        AdresseBoxPane = (BoxPane)namespace.get("addressBoxPane");
        rueTextInput = (TextInput)namespace.get("rueTextInput");
        villeTextInput = (TextInput)namespace.get("villeTextInput");
        codePostalTextInput = (TextInput)namespace.get("codePostalTextInput");
        // numéro de téléphone
        telephoneBoxPane = (BoxPane)namespace.get("telephoneBoxPane");
        numTelTextInput = (TextInput)namespace.get("numTelTextInput");
        // Date du diplome
        diplomeBoxPane = (BoxPane)namespace.get("diplomeBoxPane");
        errorLabel = (Label)namespace.get("errorLabel");
        // listener
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
		//
	}
 
	public void resume() throws Exception
	{
		//
	}
}
