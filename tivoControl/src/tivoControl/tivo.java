package tivoControl;

import java.io.*;
import java.net.*;

/**
 * @author Garrett
 *
 * Class to control tivo box on network
 * Execute jar via command line and pass the command via the variable
 * "tivo-cmd"
 * 
 * eg java -Dtivo-cmd="SETCHANNEL" -jar tivo.jar
 * 
 *
 */

public class tivo {
	

	/**
	 * @param args "tivo command" and "command argument" eg SETCH 150
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		//New Socket
		Socket s = new Socket();
		String host = "192.168.0.8";
		PrintWriter s_out = null;
		BufferedReader s_in = null;
		// System property/variable passed from command line
		//String command = System.getProperty("tivo-cmd");
		String command = args[0];
		String argument = args[1];
		try
		{
			s.connect(new InetSocketAddress(host, 31339));
			System.out.println("Connected");
			//Writer for socket
			s_out = new PrintWriter (s.getOutputStream(), true);
			s_in = new BufferedReader (new InputStreamReader(s.getInputStream()));
		}
		//Handle Host not found error
		catch (UnknownHostException e)
		{
			System.err.println("Don't know the host " + host);
			System.exit(1);
		}
		
		//Send message to server 
		String message = command + " " + argument + "\r"; 
		s_out.println(message); 
		System.out.println("Message send on next line");
		System.out.println(message);
		System.out.println(s_in.readLine()); 
		s_out.close(); 
		s_in.close(); 
		s.close(); 
	} 
}