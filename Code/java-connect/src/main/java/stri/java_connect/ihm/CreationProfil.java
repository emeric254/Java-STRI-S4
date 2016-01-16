package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class CreationProfil extends Window implements Application, Bindable
{
	private Window window = null;
		
	@BXML
	private CalendarButton btPickDate;
 
	public CreationProfil()
	{
	}
 
	public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
		btPickDate.getCalendarButtonSelectionListeners().add(calendarButtonSelectListener);
	}
 
	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(Menu.class, "/creationProfil.bxml");
		window.open(display);
	}
		
	private final CalendarButtonSelectionListener calendarButtonSelectListener = new CalendarButtonSelectionListener()
	{
		public void selectedDateChanged(CalendarButton calendarButton, CalendarDate previousSelectedDate)
		{
			System.out.println(calendarButton.getSelectedDate().toString());
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
