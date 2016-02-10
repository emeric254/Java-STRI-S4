package stri.java_connect.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourrielValidateurTest
{

	@Test
	public void testValider()
	{
		assertFalse(CourrielValidateur.valider("test"));
		assertFalse(CourrielValidateur.valider("@"));
		assertFalse(CourrielValidateur.valider("test@"));
		assertFalse(CourrielValidateur.valider("@test"));
		assertFalse(CourrielValidateur.valider("test@test"));
		//
		assertTrue(CourrielValidateur.valider("test@test.test"));
	}

}
