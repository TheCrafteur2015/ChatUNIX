package com.github.thecrafteur2015.java.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Date : 6 avr. 2023
 * 
 * @author  Gabriel ROCHE
 * @version 1.0
 */
public class Menu extends JMenuBar implements ActionListener {
	
	@Serial
	private static final long serialVersionUID = -4268119420682010238L;
	
	private Controler ctrl;
	
	private JMenuItem connectBtn;
	
	public Menu(Controler c) {
		this.ctrl = c;
		
		this.connectBtn = new JMenuItem("Page de connexion");
		this.connectBtn.addActionListener(this);
		
		this.add(this.connectBtn);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.ctrl.setPage("Connection");
	}
	
}
