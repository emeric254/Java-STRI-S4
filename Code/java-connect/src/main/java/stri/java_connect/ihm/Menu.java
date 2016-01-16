package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class Menu extends Window implements Application, Bindable
{
	private Window window = null;
 
	@BXML
	private PushButton btConnexion;
	
	@BXML
	private PushButton btInscription;
	
	@BXML
	private PushButton btListeProfil;
	
	@BXML
	private PushButton btModifProfil;
 
	public Menu()
	{
	}
 
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		btConnexion.getButtonPressListeners().add(connexionListener);
		btInscription.getButtonPressListeners().add(inscriptionListener);
		btListeProfil.getButtonPressListeners().add(listeProfilListener);
		btModifProfil.getButtonPressListeners().add(modifProfilListener);
	}
 
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(Menu.class, "/menu.bxml");
		window.open(display);
	}
 
	private final ButtonPressListener connexionListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Connexion", Menu.this);
		}
	};
 
	private final ButtonPressListener inscriptionListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Inscription", Menu.this);
		}
	};
 
	private final ButtonPressListener listeProfilListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Liste profils", Menu.this);
		}
	};
 
	private final ButtonPressListener modifProfilListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Modification profil", Menu.this);
		}
	};
 
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
