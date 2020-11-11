package client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {

   public static void main(String [] args) {
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);
      boolean state = true;
      try {
    	 Scanner sc = new Scanner(System.in);
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
         
         
         System.out.println("You can now send some messages to the server. ");
         while (state) {
        	OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            
     		String text = sc.nextLine();
     		if (!text.isEmpty()) {
     			out.writeUTF(text);
     		} else if (text.equals("Quit")) {
     			state = false;
     		}
     		InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String result = in.readUTF();
            if (!result.isEmpty()) {
            	System.out.println("You got a message! --> :  " + result);
            }
            
            try {
		        Thread.sleep(10);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		        break;
		      }
     		
         }
         
         //out.writeUTF("Hello from " + client.getLocalSocketAddress());
         
         
         client.close();
         sc.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}