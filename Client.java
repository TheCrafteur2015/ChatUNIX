package com.github.thecrafteur2015.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Date : 6 avr. 2023
 * 
 * @author  Gabriel ROCHE
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Client {
	
	private Socket         server;
	
	private String         conversation;
	
	private PrintWriter    toServer;
	
	private BufferedReader fromServer;
	
	public Client(int port) throws UnknownHostException, IOException {
		this.server = new Socket(InetAddress.getLocalHost(), port);
		
		this.conversation = null;
		
		this.toServer   = new PrintWriter(this.server.getOutputStream(), true);
		this.fromServer = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
		
		
		
	}
	
	public Socket getSocket() {
		return this.server;
	}

}