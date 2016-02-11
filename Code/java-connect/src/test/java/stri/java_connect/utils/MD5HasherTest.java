package stri.java_connect.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class MD5HasherTest
{
	private final static String str = "test";
	private final static String salt = "salt";

	@Test
	public void testHashStringStringString()
	{
		assertNotNull(MD5Hasher.hashString(str, str));
		assertNotNull(MD5Hasher.hashString(str, salt));
		assertNotEquals(str, MD5Hasher.hashString(str, str));
		assertNotEquals(str, MD5Hasher.hashString(str, salt));
		assertNotEquals(MD5Hasher.hashString(str, str), MD5Hasher.hashString(str, salt));
	}

	@Test
	public void testHashStringString()
	{
		assertNotNull(MD5Hasher.hashString(str));
		assertNotEquals(str, MD5Hasher.hashString(str));
	}

}
