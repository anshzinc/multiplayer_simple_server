package com.anshzinc.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {
	
	private Socket clientSocket;
	private String name;
	private ArrayList<Socket> sockets;
	
	public ClientThread(Socket clientSocket, ArrayList<Socket> sockets, String name) {
		System.out.println("Thread started");
		this.clientSocket = clientSocket; 
		this.name = name;
		this.sockets = sockets;
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(
			        new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 String inputLine;
		 try {
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				 
				 String outLine = inputLine + "\n";
				 for (Socket socket: sockets) {
					 if (!socket.equals(clientSocket)) {
						 OutputStream out = socket.getOutputStream();
						 out.write(outLine.getBytes());
					 }
				 }
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}