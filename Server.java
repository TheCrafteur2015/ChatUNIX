package com.github.thecrafteur2015.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Date : 6 avr. 2023
 * 
 * @author  Gabriel ROCHE
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Server {
	
	private ServerSocket   ss;
	
	private Socket         client;
	
	private PrintWriter    toClient;
	
	private BufferedReader fromClient;
	
	private String         conversation;
	
	public Server(int port) throws IOException, InterruptedException {
		this.ss = new ServerSocket(port);
		
		
		this.conversation = null;
		this.toClient     = null;
		this.fromClient   = null;
		
		this.client = this.ss.accept();
		System.out.println("Connected!");
		
		this.toClient   = new PrintWriter(this.client.getOutputStream(), true);
		this.fromClient = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		
	}
	
	public void send(String s) {
		this.toClient.println(s);
	}
	
	public void setContent(String s) {
		this.conversation = s;
	}
	
	public String getContent() {
		return this.conversation;
	}
	
	public boolean isOpen() {
		return !this.ss.isClosed();
	}

	
}