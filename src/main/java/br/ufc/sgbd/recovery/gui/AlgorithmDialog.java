package br.ufc.sgbd.recovery.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * Dialog to Show about the Algorithm
 */
public class AlgorithmDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlgorithmDialog dialog = new AlgorithmDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlgorithmDialog() {
		setResizable(false);
		setBounds(100, 100, 581, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			Box verticalBox = Box.createVerticalBox();
			contentPanel.add(verticalBox);
			{
				JLabel lblSss = new JLabel("Postponed Recovery");
				lblSss.setFont(new Font("Lucida Grande", Font.PLAIN, 31));
				verticalBox.add(lblSss);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				verticalBox.add(scrollPane);
				{
					JTextArea txtrTheAlgorithmIs = new JTextArea();
					txtrTheAlgorithmIs.setText("The algorithm is a implementation from the ARIES protocol.\n1)Analyze\nCheck all the log looking for transactions that has already been committed.\n2)Redo\nThis is the main point. The algorithm output must be this redo list.\n3)Undo\nWe dont't have a undo list, cause We don't have a undo List.");
					scrollPane.setViewportView(txtrTheAlgorithmIs);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
