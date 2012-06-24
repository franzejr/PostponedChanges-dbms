package br.ufc.sgbd.recovery.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufc.sgbd.recovery.algorithm.ARIES;
import br.ufc.sgbd.recovery.algorithm.DiskImage;
import br.ufc.sgbd.recovery.algorithm.PostponedChanges;
import br.ufc.sgbd.recovery.parser.Log;
import br.ufc.sgbd.recovery.parser.LogFileParser;
import br.ufc.sgbd.recovery.parser.LogRecord;
/*
 * Main User Interface
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilePath;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("Postponed Recovery - Database Systems");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmTeam = new JMenuItem("Team");
		mntmTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamFrame team = new TeamFrame();
				team.setVisible(true);
			}
		});
		mnAbout.add(mntmTeam);
		
		JMenuItem mntmAlgorithm = new JMenuItem("Algorithm");
		mntmAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlgorithmDialog dialog = new AlgorithmDialog();
				dialog.setVisible(true);
			}
		});
		mnAbout.add(mntmAlgorithm);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setToolTipText("Algorithm Output");
		scrollPane.setViewportView(textArea);
		
		JButton btnRecover = new JButton("Recover!");
		btnRecover.setToolTipText("Let's start the Algorithm! ; - )");
		btnRecover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = txtFilePath.getText();

				LogFileParser fileParser = new LogFileParser();

				Log log;
				PostponedChanges algorithm = new PostponedChanges();
				List<LogRecord> recordList;
				DiskImage finalDiskImage ;
				try {
					
					textArea.setText("");
					log = fileParser.parseLogFileAndReturnLogObject(filePath);
					algorithm.analyze(log);
					textArea.append("DIRTY PAGE: \n");
					textArea.append(ARIES.dirtyPageTable.toString() + "\n");
					textArea.append("TRANSACTION PAGE: \n");
					textArea.append(ARIES.transactionTable.toString()+ "\n\n\n");
					recordList = algorithm.buildRedoRecordList(log);
					
					textArea.append("REDO-LIST:\n");
					textArea.append("------------------------\n");
					for (LogRecord logRecord : recordList) {	
						textArea.append(logRecord.toString()+ "\n");
					}
					textArea.append("------------------------\n\n");
					
					finalDiskImage = algorithm.redo(recordList);
					textArea.append("Final States:\n\n");
					textArea.append(finalDiskImage.toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		contentPane.add(btnRecover, BorderLayout.NORTH);
		
		Box horizontalBox = Box.createHorizontalBox();
		contentPane.add(horizontalBox, BorderLayout.SOUTH);
		
		JLabel lblPath = new JLabel("Path:");
		horizontalBox.add(lblPath);
		
		txtFilePath = new JTextField();
		txtFilePath.setToolTipText("Where is your log ?");
		txtFilePath.setText("log path");
		horizontalBox.add(txtFilePath);
		txtFilePath.setColumns(10);
	}

}
