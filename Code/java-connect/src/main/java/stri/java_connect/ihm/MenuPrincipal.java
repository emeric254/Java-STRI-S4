package stri.java_connect.ihm;

import java.io.IOException;
import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.serialization.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.modele.Utilisateur;

public class MenuPrincipal extends Window implements Application, Bindable
{
    private ClientAnnuaire client;
    private Utilisateur utilisateur;
    
    private Display display;
    private Window window;
    
	@BXML
	private PushButton btConnexion;
	@BXML
	private PushButton btInscription;
	@BXML
	private PushButton btListeProfil;
	@BXML
	private PushButton btRechercherProfil;
	@BXML
	private PushButton btModifProfil;
	@BXML
	private PushButton btMessagerieEmail;
	@BXML
	private PushButton btMessagerieP2P;
	@BXML
	private PushButton btQuitter;
	@BXML
	private BoxPane btBox;

	public MenuPrincipal()
	{
		//client = new ClientAnnuaire();
		utilisateur = null;
	}

	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		//btConnexion.getButtonPressListeners().add(connexionListener);
		btInscription.getButtonPressListeners().add(inscriptionListener);
		btListeProfil.getButtonPressListeners().add(listeProfilListener);
		btRechercherProfil.getButtonPressListeners().add(rechercherProfilListener);
		btModifProfil.getButtonPressListeners().add(modifProfilListener);
		btMessagerieEmail.getButtonPressListeners().add(messagerieMail);
		btMessagerieP2P.getButtonPressListeners().add(messagerieP2P);
		btQuitter.getButtonPressListeners().add(quitter);
		
		// par defaut pas connecter donc pas ces btn
		btModifProfil.setVisible(false);
		btMessagerieEmail.setVisible(false);
		btMessagerieP2P.setVisible(false);
	}

	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		this.display = display;
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(MenuPrincipal.class, "/menuPrincipal.bxml");
		window.open(display);
	}

	private final ButtonPressListener connexionListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			// traitement connexion
			BXMLSerializer bxmlSerializer = new BXMLSerializer();
			try
			{
				((Window)bxmlSerializer.readObject(Connexion.class, "/connexion.bxml")).setVisible(true);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			// connecte
			btConnexion.setVisible(false);
			btInscription.setVisible(false);
			btModifProfil.setVisible(true);
			btMessagerieEmail.setVisible(true);
			btMessagerieP2P.setVisible(true);
		}
	};

	private final ButtonPressListener inscriptionListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Inscription", MenuPrincipal.this);
		}
	};

	private final ButtonPressListener listeProfilListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Liste profils", MenuPrincipal.this);
		}
	};
	
	private final ButtonPressListener rechercherProfilListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Recherche d'un profil", MenuPrincipal.this);
		}
	};

	private final ButtonPressListener modifProfilListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Modification profil", MenuPrincipal.this);
		}
	};

	private final ButtonPressListener messagerieMail = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Messagerie Mail", MenuPrincipal.this);
		}
	};

	private final ButtonPressListener messagerieP2P = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Messagerie peer to peer", MenuPrincipal.this);
		}
	};

	private final ButtonPressListener quitter = new ButtonPressListener() {
		
		public void buttonPressed(final Button button) 
		{
			Alert.alert(MessageType.INFO, "Fermeture de la page", MenuPrincipal.this);	
		}
	};
	public boolean shutdown(final boolean optional) throws Exception
	{
		this.close();
		return false;
	}
	
	public Window load(String fileName) throws SerializationException, IOException
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		return (Window)bxmlSerializer.readObject(MenuPrincipal.class, fileName);
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
