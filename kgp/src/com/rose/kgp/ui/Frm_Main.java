package com.rose.kgp.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.rose.kgp.settings.CtrlSettings;
import com.rose.kgp.settings.PnlSettings;

public class Frm_Main extends JFrame {

	
	private static final long serialVersionUID = 3314501781684163488L;
	private JPanel contentPane;
	private JPanel pnlNorth;
	private JPanel pnlSouth;	
	private CtrlSettings ctrlSettings;
	
	/**
	 * Launch the application.
	 */	

	public JPanel getPnlNorth() {
		return this.pnlNorth;
	}
	
	public JPanel getPnlSouth(){
		return this.pnlSouth;
	}


	/**
	 * Create the frame.
	 */
	public Frm_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JPanel panel = new JPanel();
		tabbedPane.addTab("Leistungserfassung", panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		ctrlSettings = new CtrlSettings(); // create the controller for the settings panels
		tabbedPane.addTab("Einstellungen", ctrlSettings.getPnlSettings()); //add panel for the settings to the tab		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);//set resizeWeight to 1.0 to commit enough space to the north panel
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, BorderLayout.CENTER);
		
		pnlNorth = new JPanel();
		splitPane.setLeftComponent(pnlNorth);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		pnlSouth = new JPanel();
		splitPane.setRightComponent(pnlSouth);
		pnlSouth.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

}
