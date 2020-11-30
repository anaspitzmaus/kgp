package com.rose.kgp.material;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


import com.rose.kgp.ui.Pnl_SetDate;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class FrmElectrodeModel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4960915737433907813L;
	
	private JPanel contentPane;
	private JTextField txtNotation;
	private JTextField txtNotice;
	private JTable tblElectrodeModel;
	private JPanel pnlElectrodeModels;
	private JTabbedPane tabbedPane;
	
	JToggleButton tglMode;
	JButton btnAdd;
	JToggleButton tglFix;
	
	JComboBox<Manufacturer> cbxManufacturer;
	private JSpinner spinLength;
	private JCheckBox checkMRI;
	protected JButton btnDelete;
	private JToolBar toolBar;
	
	
	
	


	protected void createNewTab(String notation, JPanel panel) {
		tabbedPane.addTab(notation, panel);
	}

	/**
	 * Create the frame.
	 */
	public FrmElectrodeModel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 432);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 139), new Color(224, 255, 255)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		//tabbedPane.addTab("Elektroden", pnlElectrodes);
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		
		
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		tglMode = new JToggleButton("tglMode");
		toolBar.add(tglMode);
		tglMode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnDelete = new JButton("btnDelete");
		toolBar.add(btnDelete);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		
	}
	
	
	
	protected void setTglModeText(String text) {
		tglMode.setText(text);
	}
	
	
	
	protected void setBtnDeleteText(String txt) {
		btnDelete.setText(txt);
	}
	
	protected void addBtnDeleteListener (ActionListener l) {
		btnDelete.addActionListener(l);
	}

	protected void enableBtnDelete(boolean b) {
		btnDelete.setEnabled(b);
		
	}
	
	protected void addTabChangeListener(ChangeListener l) {
		tabbedPane.addChangeListener(l);
		
	}
	
	protected void setTabSelected(Integer i) {
		tabbedPane.setSelectedIndex(i);
	}
	
	

	

	
}
