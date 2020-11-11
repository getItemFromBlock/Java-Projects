package server;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server extends Thread {
   private ServerSocket serverSocket;
   
   public Server(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(50000);
   }

   public void run() {
	   boolean state = true;
	   Scanner sc = new Scanner(System.in);
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            
            
            System.out.println("You can now send some messages to the client. ");
            while (state) {
            	DataInputStream in = new DataInputStream(server.getInputStream());
            	String result = in.readUTF();
            	if (!result.isEmpty()) {
            		System.out.println("You got a message! --> :  " + result);
            	}
            	DataOutputStream out = new DataOutputStream(server.getOutputStream());
            	String text = sc.nextLine();
         		if (!text.isEmpty()) {
         			out.writeUTF(text);
         		} else if (text.equals("Quit")) {
         			state = false;
         		}
            	try {
    		        Thread.sleep(10);
    		      } catch (InterruptedException e) {
    		        e.printStackTrace();
    		        break;
    		      }
            }
            
            server.close();
            sc.close();
         } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
         } catch (IOException e) {
            e.printStackTrace();
         }
   }
   
   public static void main(String [] args) {
      int port = Integer.parseInt(args[0]);
      try {
         Thread t = new Server(port);
         t.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}