package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class CreationProfil extends Window implements Application, Bindable
{
	private Window window;
	@BXML
	private BoxPane idConnexionBoxPane;
	@BXML
    private TextInput mailTextInput;
	@BXML
    private TextInput mdpTextInput;
	@BXML    
	private BoxPane nameBoxPane;
	@BXML
    private TextInput prenomTextInput;
	@BXML
    private TextInput nomTextInput;
	@BXML
    private BoxPane addressBoxPane;
	@BXML
    private TextInput rueTextInput;
	@BXML
    private TextInput villeTextInput;
	@BXML
    private TextInput codePostalTextInput;
	@BXML
	private BoxPane telephoneBoxPane;
	@BXML
    private TextInput numTelTextInput;
	@BXML
	private BoxPane anneeDiplomationBoxPane;
	@BXML
    private TextInput anneeDiplomationTextInput;
	@BXML    
    private BoxPane competencesBoxPane;
	@BXML
    private TextInput competencesTextInput;
	@BXML
    private PushButton submitButton;
	@BXML
    private PushButton retourMenuButton;
	@BXML
    private Label errorLabel;

	public CreationProfil()
	{
		//
	}

	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(CreationProfil.class, "/creationProfil.bxml");
		window.open(display);
	}

    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
        // Saisie id/mdp 
    	idConnexionBoxPane = (BoxPane)namespace.get("idConnexionBoxPane");
    	mailTextInput = (TextInput)namespace.get("mailTextInput");
    	mdpTextInput = (TextInput)namespace.get("mdpTextInput");
    	// Saisie nom
    	nameBoxPane = (BoxPane)namespace.get("nameBoxPane");
        prenomTextInput = (TextInput)namespace.get("prenomTextInput");
        nomTextInput = (TextInput)namespace.get("nomTextInput");
        // Saisie adresse
        addressBoxPane = (BoxPane)namespace.get("addressBoxPane");
        rueTextInput = (TextInput)namespace.get("rueTextInput");
        villeTextInput = (TextInput)namespace.get("villeTextInput");
        codePostalTextInput = (TextInput)namespace.get("codePostalTextInput");
        // Saisie numéro de téléphone
        telephoneBoxPane = (BoxPane)namespace.get("telephoneBoxPane");
        numTelTextInput = (TextInput)namespace.get("numTelTextInput");
        // Saisie Année de diplomation
        anneeDiplomationBoxPane = (BoxPane)namespace.get("annneeDipomationBoxPane");
        anneeDiplomationTextInput = (TextInput)namespace.get("anneeDiplomationTextInput");
        // Saisie des compétences
        competencesBoxPane = (BoxPane)namespace.get("competencesBoxPane");
        competencesTextInput = (TextInput)namespace.get("competencesTextInput");
        
        submitButton = (PushButton)namespace.get("submitButton");
        retourMenuButton = (PushButton)namespace.get("retourMenuButton");
        errorLabel = (Label)namespace.get("errorLabel");
        
        retourMenuButton.getButtonPressListeners().add(new ButtonPressListener()
        {
        	public void buttonPressed(Button button)
        	{
        		
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
                
                Form.Flag flag = null;
                // Vérification que tous les champs sont remplis
                if ( mail.length() == 0 || mdp.length() == 0 || prenom.length() == 0  || nom.length() == 0 || rue.length() == 0 || ville.length() == 0 || codePostal.length() == 0 || numeroTel.length() == 0)
                {
                    flag = new Form.Flag(MessageType.ERROR, "Obligatoire");
                }

                Form.setFlag(idConnexionBoxPane, flag);
                Form.setFlag(nameBoxPane, flag);
                Form.setFlag(addressBoxPane, flag);
                Form.setFlag(telephoneBoxPane, flag);
                Form.setFlag(competencesBoxPane, flag);
                

                if (flag == null)
                {
                    errorLabel.setText("");
                    Prompt.prompt("Pretending to submit...", CreationProfil.this);
                }
                else
                {
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
		//
	}
 
	public void resume() throws Exception
	{
		//
	}
}
