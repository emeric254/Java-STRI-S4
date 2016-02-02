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
	public static String hashString(String str, String salt)
	{
		StringBuffer temp = new StringBuffer();
		try // hash md5, not really safe ... must look at this --> java.security.SecureRandom
		{
			MessageDigest md5gen = MessageDigest.getInstance("MD5");
			md5gen.update((str+salt).getBytes());	// "UTF-8" needed or not ??
			byte[] md5digest = md5gen.digest();
			for (byte b : md5digest) // hexa conversion
			{
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
	
	public static String hashString(String str)
	{
		return hashString(str, "");
	}
}
