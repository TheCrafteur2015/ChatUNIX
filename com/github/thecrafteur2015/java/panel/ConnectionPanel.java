package com.github.thecrafteur2015.java.panel;

import static java.awt.Color.RED;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.io.Serial;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.thecrafteur2015.java.main.Controler;

/**
 * Date : 5 avr. 2023
 * 
 * @author  Gabriel ROCHE
 * @version 1.0
 */
public class ConnectionPanel extends JPanel implements ActionListener, ComponentListener {
	
	@Serial
	private static final long serialVersionUID = -3285288509599633455L;
	
	private Controler  ctrl;
	
	private JTextField fieldUsrn;
	private JTextField fieldPort;
	
	
	private JLabel     lblUsrn;
	private JLabel     lblPort;
	
	private JLabel     lblError;
	
	private JButton    connect;
	
	public ConnectionPanel(Controler c) {
		super(null);
		this.ctrl = c;
		
		
		/*-------------------- Initialization --------------------*/
		
		this.lblUsrn   = new JLabel("Nom : ");
		this.fieldUsrn = new JTextField(15); 
		
		this.lblPort   = new JLabel("Port : ");
		this.fieldPort = new JTextField(15);
		
		this.connect = new JButton("Connection");
		this.connect.setFocusable(false);
		this.connect.addActionListener(this);
		
		this.lblError = new JLabel("");
		this.lblError.setForeground(RED);
		
		/*-------------------- Activation --------------------*/
		
		this.ctrl.addComponentListener(this);
		
		/*-------------------- Placement --------------------*/
		
		this.updateBounds();
		
		this.add(this.lblUsrn);
		this.add(this.fieldUsrn);
		
		this.add(this.lblPort);
		this.add(this.fieldPort);
		
		this.add(this.connect);
		
		this.add(this.lblError);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.lblError.setText("");
		if (isEmpty(this.fieldPort.getText()))
			this.lblError.setText("Port de connection manquant !");
		else if (!isInt(this.fieldPort.getText())) {
			this.lblError.setText("Veuillez entrer un port valide !");
		} else {
			if (!this.connect(this.fieldPort.getText()))
				this.lblError.setText("Échec de la connection");
		}
			
	}
	
	private boolean connect(String sPort) {
		int port = Integer.parseInt(sPort);
		if (isEmpty(this.fieldUsrn.getText()))
			this.ctrl.setUsername(this.randomUsername());
		else
			this.ctrl.setUsername(this.fieldUsrn.getText());
		this.ctrl.setPage("Console");
		
		
		
		try {
			this.ctrl.connectToClient(port);
		} catch (IOException e) {
			try {
				this.ctrl.openServer(port);
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static boolean isEmpty(String s) {
		return s.isEmpty() || s.isBlank();
	}
	
	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) { 
			return false;
		}
	}
	
	private void updateBounds() {
		this.lblUsrn.setBounds(
			(int) (this.ctrl.getWidth() / 2 - this.lblUsrn.getPreferredSize().getWidth()) - 50,
			(int) (this.ctrl.getHeight() / 8 - this.lblUsrn.getPreferredSize().getHeight()) + 40,
			50,
			20
		);
		this.fieldUsrn.setBounds(
			(int) ((this.ctrl.getWidth() / 2) - (this.fieldUsrn.getPreferredSize().getWidth() / 4)),
			(int) (this.ctrl.getHeight() / 8 - this.lblUsrn.getPreferredSize().getHeight()) + 40,
			100,
			25
		);
		this.lblPort.setBounds(
			(int) (this.ctrl.getWidth() / 2 - this.lblPort.getPreferredSize().getWidth()) - 50,
			(int) (this.ctrl.getHeight() / 8 - this.lblPort.getPreferredSize().getHeight()) + 70,
			50,
			20
		);
		this.fieldPort.setBounds(
			(int) ((this.ctrl.getWidth() / 2) - (this.fieldPort.getPreferredSize().getWidth() / 4)),
			(int) (this.ctrl.getHeight() / 8 - this.lblPort.getPreferredSize().getHeight()) + 70,
			100,
			25
		);
		this.connect.setBounds(
			(int) ((this.ctrl.getWidth() / 2) - (this.connect.getPreferredSize().getWidth() / 2)),
			(int) (this.ctrl.getHeight() / 8 - this.connect.getPreferredSize().getHeight()) + 110,
			100,
			25
		);
		this.lblError.setBounds(
			(int) (this.ctrl.getWidth() / 2 - this.lblError.getPreferredSize().getWidth()) - 50 - this.lblError.getText().length() / 2,
			(int) (this.ctrl.getHeight() / 8 - this.lblError.getPreferredSize().getHeight()) + 160,
			200,
			20
		);
	}
	
	private String randomUsername() {
		return "user" + (new Random().nextInt(100, 1_000));
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		this.updateBounds();
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
	}
	
	@Override
	public void componentShown(ComponentEvent e) {
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
	}
	
}
