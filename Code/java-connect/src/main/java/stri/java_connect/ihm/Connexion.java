package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class Connexion extends Window implements Application, Bindable
{
	private Window window;
			
	private BoxPane idConnexionBoxPane;
	private TextInput mailTextInput;
	private TextInput mdpTextInput;
		        
    private PushButton submitButton;
    private PushButton retourMenuButton;
    private Label errorLabel;

	public Connexion()
	{
		//
	}

	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(Connexion.class, "/connexion.bxml");
		window.open(display);
	}

    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
        // Saisie id/mdp 
    	idConnexionBoxPane = (BoxPane)namespace.get("idConnexionBoxPane");
    	mailTextInput = (TextInput)namespace.get("mailTextInput");
    	mdpTextInput = (TextInput)namespace.get("mdpTextInput");
        
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

                Form.Flag flag = null;
                // Vérification que tous les champs sont remplis
                if ((mail.length() == 0) || (mdp.length() == 0))
                {
                    flag = new Form.Flag(MessageType.ERROR, "Obligatoire");
                }

                if (flag == null)
                {
                    errorLabel.setText("");
                    //Prompt.prompt("Pretending to submit...", Forms.this);
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
