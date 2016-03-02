package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class ConsultListeProfil extends Window implements Application, Bindable
{
	private Window window;
	 
	@BXML
	private PushButton btRetour;
 
	public ConsultListeProfil()
	{
		//
	}
 
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		btRetour.getButtonPressListeners().add(retourListener);
	}
 
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(ConsultListeProfil.class, "/consultListeProfil.bxml");
		window.open(display);
	}
 
	private final ButtonPressListener retourListener = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Retour acceuil", ConsultListeProfil.this);
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
