/**
 * 
 */
package stri.java_connect.modele;

import java.util.Date;

/**
 * @author emeric
 *
 */
public class Message
{
	private String auteur;
	private String contenu;
	private Long timestamp;
	
	/**
	 * @param pAuteur
	 * @param pContenu
	 */
	public Message(String pAuteur, String pContenu)
	{
		this(pAuteur, pContenu, new Date().getTime());
	}
	
	/**
	 * @param pAuteur
	 * @param pContenu
	 * @param pTimestamp
	 */
	public Message(String pAuteur, String pContenu, Long pTimestamp)
	{
		auteur = pAuteur;
		contenu = pContenu;
		timestamp = pTimestamp;
	}

	/**
	 * @return
	 */
	public String getAuteur()
	{
		return auteur;
	}

	/**
	 * @param auteur
	 */
	public void setAuteur(String auteur)
	{
		this.auteur = auteur;
	}

	/**
	 * @return
	 */
	public String getContenu()
	{
		return contenu;
	}

	/**
	 * @param contenu
	 */
	public void setContenu(String contenu)
	{
		this.contenu = contenu;
	}

	/**
	 * @return
	 */
	public Long getTimestamp()
	{
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(Long timestamp)
	{
		this.timestamp = timestamp;
	}
}
