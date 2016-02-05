package stri.java_connect.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class MD5HasherTest
{

	@Test
	public void testHashStringStringString()
	{
		assertNotNull(MD5Hasher.hashString("test", "test"));
		assertNotNull(MD5Hasher.hashString("test", "salt"));
		assertNotEquals("test", MD5Hasher.hashString("test", "test"));
		assertNotEquals("test", MD5Hasher.hashString("test", "salt"));
		assertNotEquals(MD5Hasher.hashString("test", "test"), MD5Hasher.hashString("test", "salt"));
	}

	@Test
	public void testHashStringString()
	{
		assertNotNull(MD5Hasher.hashString("test"));
		assertNotEquals("test", MD5Hasher.hashString("test"));
	}

}
