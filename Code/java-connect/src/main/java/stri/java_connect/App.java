package stri.java_connect;

import org.apache.pivot.wtk.DesktopApplicationContext;

import stri.java_connect.bdd.Sqlite;
import stri.java_connect.ihm.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Sqlite testControl = new Sqlite("test.db");
        testControl.Init();
        testControl.close();
        
		DesktopApplicationContext.main(Menu.class, args);
		DesktopApplicationContext.main(CreationProfil.class, args);
    }
}
