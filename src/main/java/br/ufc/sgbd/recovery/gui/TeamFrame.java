package br.ufc.sgbd.recovery.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

/*
 * TeamFrame UI
 */
public class TeamFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamFrame frame = new TeamFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeamFrame() {
		setResizable(false);
		setBounds(100, 100, 277, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBackground(new Color(220, 220, 220));
		contentPane.add(verticalBox, BorderLayout.CENTER);
		
		JLabel lblTeam = new JLabel("TEAM");
		lblTeam.setFont(new Font("Calibri", Font.BOLD, 31));
		verticalBox.add(lblTeam);
		
		JLabel lblFranciscoJoseLins = new JLabel("Francisco Jose Lins Magalhaes");
		verticalBox.add(lblFranciscoJoseLins);
		
		JLabel lblVictorFontenele = new JLabel("Victor Fontenele");
		verticalBox.add(lblVictorFontenele);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JSeparator separator = new JSeparator();
		verticalBox.add(separator);
		verticalBox.add(btnOk);
	}

}
