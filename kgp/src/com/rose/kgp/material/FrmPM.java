package com.rose.kgp.material;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.kgp.examination.PM_Implant;
import com.rose.kgp.material.Ctrl_Frm_PM.TblBooleanRenderer;
import com.rose.kgp.material.Ctrl_Frm_PM.TblPMModelIDRenderer;
import com.rose.kgp.ui.Pnl_SetDate;

import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrmPM extends JFrame {

	private JPanel contentPane;
	private JTextField txtSerialNr;
	JFormattedTextField ftxtExpire;
	JComboBox<PM_Type> cbxType;
	JComboBox<Manufacturer> cbxManufacturer;
	JCheckBox checkRA, checkRV, checkLV;
	JButton btnCreate;
	private JLabel lblModel;
	private JLabel lblModelText;
	private JScrollPane scrTblPM;
	private JTable tblPM;
	private JLabel lblFilter;
	private Pnl_SetDate pnlSetDate;
	JButton btnAdd;
	private JLabel lblNotice;
	private JTextField txtNotice;
	private JButton btnShowAll;
	private JScrollPane scrollPane;
	private JTable tblPMModel;
	JPanel pnlPM;
	private JToggleButton tglMode;
	private JLabel lblMode;
	private JToolBar toolBar;
	private JLabel lblNewLabel;
	private JMenu menuWindow;
	private JMenuItem mntmPM;
	private JMenuItem mntmICD;
	
	
	
	protected JMenuItem getMntmPM() {
		return mntmPM;
	}

	protected JMenuItem getMntmICD() {
		return mntmICD;
	}

	/**
	 * returns the table that displays the models of pacemakers
	 * @return
	 */
	protected JTable getTblPMModel() {
		return tblPMModel;
	}

	/**
	 * returns the table, that displays the pacemakers
	 * @return
	 */
	protected JTable getTblPM() {
		return tblPM;
	}

	protected JTextField getTxtSerialNr() {
		return txtSerialNr;
	}
	
	protected JTextField getTxtNotice() {
		return txtNotice;
	}
	
	protected JButton getBtnAdd() {
		return btnAdd;
	}
	
	protected JButton getBtnShowAll() {
		return btnShowAll;
	}
	
	public JLabel getLblPMSel() {		
		return lblModelText;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPM frame = new FrmPM();
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
	public FrmPM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 626);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuWindow = new JMenu("Ansicht");
		menuBar.add(menuWindow);
		
		mntmPM = new JMenuItem("Schrittmacher");
		menuWindow.add(mntmPM);
		
		mntmICD = new JMenuItem("ICD");
		menuWindow.add(mntmICD);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlStatus = new JPanel();
		contentPane.add(pnlStatus, BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel pnlPMKind = new JPanel();
		splitPane.setLeftComponent(pnlPMKind);
		pnlPMKind.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		lblFilter = new JLabel("Filter:");
		lblFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPMKind.add(lblFilter, "cell 0 0");
		
		
		scrollPane = new JScrollPane();
		pnlPMKind.add(scrollPane, "cell 0 1 2 1,grow");
		
		tblPMModel = new JTable();
		tblPMModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tblPMModel);
		
		// Pnl_PM starts here
		pnlPM = new JPanel();
		splitPane.setRightComponent(pnlPM);
		pnlPM.setLayout(new MigLayout("", "[][][][][][grow][]", "[][][][grow]"));
		
		toolBar = new JToolBar();
		pnlPM.add(toolBar, "flowx,cell 0 0 7 1");
		
		lblMode = new JLabel("Modus:");
		toolBar.add(lblMode);
		
		tglMode = new JToggleButton("Neu");
		toolBar.add(tglMode);
		
		lblModel = new JLabel("Modell:");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(lblModel, "cell 0 1,alignx left,aligny center");
		
		btnShowAll = new JButton("Alle Anzeigen");
		btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(btnShowAll, "cell 6 1");
		
		JLabel lblSerialNr = new JLabel("Seriennummer:");
		lblSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(lblSerialNr, "cell 0 2,alignx left,aligny center");
		
		txtSerialNr = new JTextField();
		txtSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(txtSerialNr, "cell 1 2,alignx left,aligny center");
		txtSerialNr.setColumns(10);
		
		JLabel lblExpire = new JLabel("Verfallsdatum:");
		lblExpire.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(lblExpire, "cell 2 2,alignx left,aligny center");
		
				
		lblNotice = new JLabel("Notiz:");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(lblNotice, "flowx,cell 4 2");
		
		txtNotice = new JTextField();
		txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(txtNotice, "cell 5 2,growx");
		txtNotice.setColumns(10);
		
		btnAdd = new JButton("Hinzuf\u00FCgen");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(btnAdd, "cell 6 2,growx,aligny top");
		
		lblModelText = new JLabel("");
		lblModelText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPM.add(lblModelText, "flowx,cell 1 1 5 1,alignx left,aligny center");
		
		scrTblPM = new JScrollPane();
		pnlPM.add(scrTblPM, "cell 0 3 7 1,grow");
		
		tblPM = new JTable();
		tblPM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrTblPM.setViewportView(tblPM);
	}
	
	protected void setPMModelTableModel(AbstractTableModel model) {
		tblPMModel.setModel(model);
	}
	
	protected void setTablePMTypeRenderer(Class<PM_Type> colclass, TableCellRenderer r) {
		tblPMModel.setDefaultRenderer(colclass, r);
	}
	
	protected void setTblPMModelIDRenderer(Class<AggregatModel> idClass, TblPMModelIDRenderer r) {
		tblPMModel.setDefaultRenderer(idClass, r);
		
	}
	protected void setTblStringRenderer(Class<String> stringClass, TableCellRenderer r) {
		tblPMModel.setDefaultRenderer(stringClass, r);
	}
	
	protected void addTblPMModelRowSelectionListener (ListSelectionListener l) {
		tblPMModel.getSelectionModel().addListSelectionListener(l);
	}
	
	protected void setDatePnl(Pnl_SetDate pnlSetDate) {
		pnlPM.add(pnlSetDate, "cell 3 2,growx,aligny top");
	}
	
	protected void addNewPmListener (ActionListener l) {
		btnAdd.addActionListener(l);
	}
	
	protected void addSerialNrListener(DocumentListener l) {
		txtSerialNr.getDocument().addDocumentListener(l);
	}

	public String getSerialNr() {
		return txtSerialNr.getText();
	}
	
	public String getNotice() {
		return txtNotice.getText();
	}
	
	protected void addNoticeListener(DocumentListener l) {
		txtNotice.getDocument().addDocumentListener(l);
	}
	
	protected void setPMTableModel(AbstractTableModel model) {
		tblPM.setModel(model);
	}

	protected void setTblPMExamRenderer(Class<PM_Implant> pmImplantClass, TableCellRenderer r) {
		tblPM.setDefaultRenderer(pmImplantClass, r);
		
	}
	
	protected void setTblPMIDRenderer(Class<PM> idClass, TableCellRenderer r) {
		tblPM.setDefaultRenderer(idClass, r);
		
	}
	
	protected void setTblPMDateRenderer(Class<LocalDate> date, TableCellRenderer r) {
		tblPM.setDefaultRenderer(date, r);
	}
	
	protected void setTblPMDefaultRenderer(Class<String> stringClass, TableCellRenderer r) {
		tblPM.setDefaultRenderer(stringClass, r);
	}
	
	protected void addShowAllListener(ActionListener l) {
		btnShowAll.addActionListener(l);
	}
	
	/**
	 * clears the selection of the table that shows the models of pacemakers
	 */
	protected void clearSelectionOfTblPMKind() {
		tblPMModel.getSelectionModel().clearSelection();
		lblModelText.setText("");
	}
	
	/**
	 * clears the selection of the table that shows the pacemakers
	 */
	protected void clearSelectionOfTblPM() {
		tblPM.getSelectionModel().clearSelection();
		txtSerialNr.setText("");
		txtNotice.setText("");		
	}

	protected void setBooleanRenderer(Class<Boolean> booleanclass, TableCellRenderer r) {
		tblPMModel.setDefaultRenderer(booleanclass, r);
		
	}
	
	protected void addModeListener(ActionListener l) {
		tglMode.addActionListener(l);
	}
	
	protected void addPMSelectionListener(ListSelectionListener l) {
		tblPM.getSelectionModel().addListSelectionListener(l);
	}
	
	protected void addMenuItemPMListener(ActionListener l) {
		mntmPM.addActionListener(l);
	}
	
	protected void addMenuItemICDListener(ActionListener l) {
		mntmICD.addActionListener(l);
	}
	
	

}
