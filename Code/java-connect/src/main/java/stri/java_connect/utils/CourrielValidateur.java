/**
 * 
 */
package stri.java_connect.utils;

import java.util.regex.Pattern;

/**
 * @author emeric
 *
 */
public abstract class CourrielValidateur
{
	private static final String EMAIL_PATTERN =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Validate courriel with regular expression
	 * 
	 * @param courriel
	 * @return true valide courriel, false invalide courriel
	 */
	public static boolean valider(final String courriel) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		return pattern.matcher(courriel).matches();

	}
}
