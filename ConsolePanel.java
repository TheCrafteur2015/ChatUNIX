package com.github.thecrafteur2015.java.panel;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.ComponentOrientation.LEFT_TO_RIGHT;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.LONG_FORMAT;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Locale.FRENCH;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.thecrafteur2015.java.main.Controler;

/**
 * Date : 6 avr. 2023
 * 
 * @author  Gabriel ROCHE
 * @version 1.0
 */
@SuppressWarnings("unused")
public class ConsolePanel extends JPanel implements ActionListener, KeyListener {
	
	@Serial
	private static final long serialVersionUID = 6166439875625836338L;
	
	private static final Cursor DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);
	
	private static final Cursor TEXT = new Cursor(Cursor.TEXT_CURSOR);
	
	private Controler  ctrl;
	
	private JTextArea  chat;
	
	private JTextField entry;
	
	public ConsolePanel(Controler c) {
		super(new BorderLayout());
		
		this.ctrl = c;
		
		/*-------------------- Components --------------------*/
		
		this.chat = new JTextArea();
		this.chat.setForeground(Color.WHITE);
		this.chat.setCaretColor(Color.WHITE);
		this.chat.setBackground(Color.BLACK);
		this.chat.setCursor(DEFAULT);
		this.chat.setEditable(false);
		this.chat.setLineWrap(true);
		
//		this.chat.setComponentOrientation(RIGHT_TO_LEFT);
		
		this.entry = new JTextField();
		this.entry.setPreferredSize(new Dimension(0, 50));
		this.entry.setCaretPosition(0);
		this.entry.setPreferredSize(new Dimension(0, 50));
		this.entry.addKeyListener(this);
		
		this.add(this.chat,  CENTER);
		this.add(this.entry, SOUTH);
		
	}
	
	public void setText(String s) {
		this.chat.setText(s);
	}
	
	public String getText() {
		return this.chat.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == VK_ENTER)
			if (!this.entry.getText().isBlank() && !this.entry.getText().isEmpty()) {
				GregorianCalendar gc = new GregorianCalendar(FRENCH);
				
				String res = this.getText();
				
				res += gc.getDisplayName(DAY_OF_WEEK, LONG_FORMAT, FRENCH) + " ";
				res += gc.get(DAY_OF_MONTH) + " ";
				res += gc.getDisplayName(MONTH, LONG_FORMAT, FRENCH) + ", ";
				res += String.format("%1$02d:%2$02d:%3$02d\n", gc.get(HOUR_OF_DAY), gc.get(MINUTE), gc.get(SECOND));
				res += this.ctrl.getUsername() + " >> " + this.entry.getText() + "\n\n";
				
				this.setText(res);
				
				if (this.entry.getText().contains("Test"))
					if (this.chat.getComponentOrientation().equals(RIGHT_TO_LEFT))
						this.chat.setComponentOrientation(LEFT_TO_RIGHT);
					else
						this.chat.setComponentOrientation(RIGHT_TO_LEFT);
				this.entry.setText("");
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}