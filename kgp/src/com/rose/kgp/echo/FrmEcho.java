package com.rose.kgp.echo;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class FrmEcho extends JFrame {

	
	private static final long serialVersionUID = 5365072135029013507L;
	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEcho frame = new FrmEcho();
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
	public FrmEcho() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[424px]", "[251px]"));
		
		PnlMMAoLA pnlMMAoLA = new PnlMMAoLA();
		contentPane.add(pnlMMAoLA, "cell 0 0,grow");
		
		PnlMMLV pnlMMLV = new PnlMMLV();
		contentPane.add(pnlMMLV, "cell 0 1,grow");
		
		
	}

}
