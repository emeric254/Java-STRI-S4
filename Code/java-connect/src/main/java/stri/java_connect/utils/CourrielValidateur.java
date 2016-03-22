/**
 * 
 */
package stri.java_connect.utils;

import java.util.regex.Pattern;

/**
 * @author remi, emeric
 *
 */
public abstract class CourrielValidateur
{
	private static final String EMAIL_PATTERN =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Valider un courriel
	 * 
	 * @param courriel le courriel a verifier
	 * @return true pour un courriel valide, false sinon
	 */
	public static boolean valider(final String courriel)
	{
		return Pattern.compile(EMAIL_PATTERN).matcher(courriel).matches();
	}
}
