/**
 * 
 */
package stri.java_connect.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import stri.java_connect.protocol.ProtocoleMessagerie;

/**
 * @author emeric, thomas
 *
 */
public class ClientMessagerieDirecte extends Thread
{
	private final static int portDefaut = 12347;
	private final static int bufferSize = 4096;

    private DatagramSocket socket;
    private byte[] temp = new byte[bufferSize];
    private boolean fini = false;

	/**
	 * Creation d'un socket sur le port par defaut
	 * @throws SocketException si le socket ne peut pas s'ouvrir
	 */
	public ClientMessagerieDirecte() throws SocketException
	{
		this(portDefaut);
	}

	/**
	 * Creation d'un objet ClientMessagerieDirecte sur un port fournit
	 * @param pPort
	 * @throws SocketException
	 */
	public ClientMessagerieDirecte(int pPort) throws SocketException
	{
		socket = new DatagramSocket(pPort);
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		while (!socket.isClosed() && !fini)
		{
			DatagramPacket pac = new DatagramPacket(temp, bufferSize);
			try
			{
				socket.receive(pac);
				System.out.println(ProtocoleMessagerie.extraireMessageMessageDirect(new String(pac.getData())));
			}
			catch (IOException e)
			{
				System.err.println("erreur reception");
			}
			temp = new byte[bufferSize];
		}
	}


	/**
	 * Permet l'envoi des messages avec l'adresse et le port sur une variable
	 * @param addrport
	 * @param msg
	 */
	public void emettreMsg(String addrport, String msg)
	{
		String addr = addrport.split(":")[0];
		int port = Integer.parseInt(addrport.split(":")[1]);
		emettreMsg(addr, port, msg);
	}

	// TODO
	/**
	 * Permet l'envoi des messages avec l'adresse et le port separe
	 * @param addr
	 * @param port
	 * @param msg
	 */
	public void emettreMsg(String addr, int port, String msg)
	{
		String msgenvoi = ProtocoleMessagerie.requeteMessageDirect(msg);
		byte[] buf = msgenvoi.getBytes();
		DatagramPacket pac = null;
		try {
			pac = new DatagramPacket(buf, buf.length, new InetSocketAddress(addr, port));
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			socket.send(pac);
		}
		catch (IOException e)
		{
			System.err.println("echec de l'envoi @ " + addr + ":" + port + " du message : " + msgenvoi);
		}
	}
	
	/**
	 * Deconnexion, fermeture du socket
	 */
	public void deconnexion()
	{
		socket.close();
		fini = true;
	}
}
