/**
 * 
 */
package stri.java_connect.utils;

import java.security.MessageDigest;

/**
 * @author emeric
 *
 */
public abstract class MD5Hasher
{
	/**
	 * Hash une chaine grace a l'algorithme MD5
	 * retourne null en cas d'erreur !
	 * 
	 * @param str la chaine a hasher
	 * @param salt la chaine qui sert de "sel"
	 * @return la chaine hashée représentée en hexadecimal
	 */
	public static String hashString(final String str, final String salt)
	{
		StringBuffer temp = new StringBuffer();
		try // hash md5, not really safe ... must look at this --> java.security.SecureRandom
		{
			MessageDigest md5gen = MessageDigest.getInstance("MD5");
			md5gen.update((str+salt).getBytes());	// "UTF-8" needed or not ??
			byte[] md5digest = md5gen.digest();
			for (byte b : md5digest) 
			{
				// conversion hexa
				temp.append(String.format("%02x", b & 0xff));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;			// fail return null
		}
		return temp.toString();
	}
	
	/**
	 * Hash une chaine grace a l'algorithme MD5
	 * retourne null en cas d'erreur !
	 * 
	 * @param str la chaine a hasher
	 * @return la chaine hashée représentée en hexadecimal
	 */
	public static String hashString(final String str)
	{
		// cette fonction est un hash MD5 sans sel
		return hashString(str, "");
	}
}
