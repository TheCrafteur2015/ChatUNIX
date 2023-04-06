package com.github.thecrafteur2015.java.main;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.Serial;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.thecrafteur2015.java.panel.ConnectionPanel;
import com.github.thecrafteur2015.java.panel.ConsolePanel;
import com.github.thecrafteur2015.java.server.Client;
import com.github.thecrafteur2015.java.server.Server;

/**
 * Date : 4 avr. 2023
 * 
 * @author  Gabriel ROCHE
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Controler extends JFrame {
	
	@Serial
	private static final long serialVersionUID = 4589947579956239559L;
	
	/**  The server to connect to  */
	private Server     currentServer;
	
	/**  The client that connect to the server  */
	private Client     currentClient;
	
	/**  The name of the current user  */
	private String     username;
	
	/**  The card layout for multiple pages  */
	private CardLayout crdLt;
	
	private ConnectionPanel connPanel;
	
	private ConsolePanel    consPanel;
	
	public Controler() {
		super("Console");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setMinimumSize(new Dimension(400, 400));
		this.setLocationRelativeTo(null);
		this.setLayout(this.crdLt = new CardLayout());
		
		this.username = null;
		
		this.currentServer = null;
		this.currentClient = null;
		
		this.add("Connection", this.connPanel = new ConnectionPanel(this));
		this.add("Console", this.consPanel = new ConsolePanel(this));
		
		this.setJMenuBar(new Menu(this));
		
		this.setVisible(true);
	}
	
	public void setPage(String name) {
		this.crdLt.show(this.getContentPane(), name);
		this.setTitle(name);
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void openServer(int port) throws IOException, InterruptedException {
		this.currentServer = new Server(port);
	}
	
	public void connectToClient(int port) throws UnknownHostException, IOException {
		this.currentClient = new Client(port);
	}
	
	public void setServer(Server s) {
		this.currentServer = s;
	}
	
	public void setClient(Client c) {
		this.currentClient = c;
	}
	
	public Server getServer() {
		return this.currentServer;
	}
	
	public Client getClient() {
		return this.currentClient;
	}
	
	public void append(String str) {
		this.consPanel.setText(str);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Controler::new);
	}
	
}
