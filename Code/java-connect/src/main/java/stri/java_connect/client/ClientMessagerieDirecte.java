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
 * @author emeric
 *
 */
public class ClientMessagerieDirecte extends Thread
{
	private final static int portDefaut = 12347;
	private final static int bufferSize = 4096;

	private DatagramSocket socket;
	private byte[] temp = new byte[bufferSize];
	private boolean fini = false;

	public ClientMessagerieDirecte() throws SocketException
	{
		this(portDefaut);
	}

	public ClientMessagerieDirecte(int pPort) throws SocketException
	{
		socket = new DatagramSocket(pPort);
	}

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

	// TODO
	public void emettreMsg(String addrport, String msg)
	{
		String addr = addrport.split(":")[0];
		int port = Integer.parseInt(addrport.split(":")[1]);
		emettreMsg(addr, port, msg);
	}

	// TODO
	public void emettreMsg(String addr, int port, String msg)
	{
		String msgenvoi = ProtocoleMessagerie.requeteMessageDirect(msg);
		byte[] buf = msgenvoi.getBytes();
		DatagramPacket pac = new DatagramPacket(buf, buf.length, new InetSocketAddress(addr, port));
		try
		{
			socket.send(pac);
		}
		catch (IOException e)
		{
			System.err.println("echec de l'envoi @ " + addr + ":" + port + " du message : " + msgenvoi);
		}
	}
	
	public void deconnexion()
	{
		socket.close();
		fini = true;
	}
}
