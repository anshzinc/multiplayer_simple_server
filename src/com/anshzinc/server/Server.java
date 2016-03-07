package com.anshzinc.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	
	private static ArrayList<Socket> sockets;
	
	public static void main(String[] args) {
		
		sockets = new ArrayList<Socket>();
		
		try {
			serverSocket = new ServerSocket(5555);
			
			while (true) {
				
				 System.out.println("Clients: " + sockets.size());
				
				 clientSocket = serverSocket.accept();
				 
				 sockets.add(clientSocket);
				 
				 new ClientThread(clientSocket, sockets, String.valueOf(Math.random() * 10)).start();
				 
				 System.out.println("HERE");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

