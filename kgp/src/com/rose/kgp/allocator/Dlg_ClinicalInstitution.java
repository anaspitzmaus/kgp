package com.rose.kgp.allocator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Dlg_ClinicalInstitution extends JDialog {

	
	private static final long serialVersionUID = -2324552243858240612L;
	private final JPanel contentPanel = new JPanel();
	private TblAllocators tblAllocators;
	private JTextField txtNotation;
	private JButton btnAddAllocator;
	private JTextField txtShortNotation;
	private JTextField txtStreet;
	private JTextField txtPostalCode;
	private JTextField txtCity;
	private JPanel pnlInput;	
	private JButton btnNewInstitution;

	
	protected JPanel getPnlInput() {
		return pnlInput;
	}

	protected JTextField getTxtNotation() {
		return txtNotation;
	}

	protected void setTxtNotation(JTextField txtNotation) {
		this.txtNotation = txtNotation;
	}
	
	protected JTextField getTxtShortNotation() {
		return txtShortNotation;
	}

	protected void setTxtShortNotation(JTextField txtShortNotation) {
		this.txtShortNotation = txtShortNotation;
	}

	protected JTextField getTxtStreet() {
		return txtStreet;
	}

	protected void setTxtStreet(JTextField txtStreet) {
		this.txtStreet = txtStreet;
	}

	protected JTextField getTxtPostalCode() {
		return txtPostalCode;
	}

	protected void setTxtPostalCode(JTextField txtPostalCode) {
		this.txtPostalCode = txtPostalCode;
	}

	protected JTextField getTxtCity() {
		return txtCity;
	}

	protected void setTxtCity(JTextField txtCity) {
		this.txtCity = txtCity;
	}

	protected TblAllocators getTblAllocators() {
		return tblAllocators;
	}
	
	protected JButton getBtnAddAllocator() {
		return btnAddAllocator;
	}
	
	protected JButton getBtnNewInstitution() {
		return btnNewInstitution;
	}

	/**
	 * Create the dialog.
	 */
	public Dlg_ClinicalInstitution() {
		setTitle("Zuweiser hinzuf\u00FCgen");
		setBounds(100, 100, 534, 474);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setResizeWeight(0.5);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				pnlInput = new JPanel();
				splitPane.setLeftComponent(pnlInput);
				pnlInput.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
				{
					JLabel lblNotation = new JLabel("Bezeichnung:");
					lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(lblNotation, "cell 0 0,alignx left");
				}
				{
					txtNotation = new JTextField();
					txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(txtNotation, "cell 1 0,growx");
					txtNotation.setColumns(10);
				}
				{
					JLabel lblShortNotation = new JLabel("Kurzbezeichnung:");
					lblShortNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(lblShortNotation, "cell 0 1,alignx trailing,aligny bottom");
				}
				{
					txtShortNotation = new JTextField();
					txtShortNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(txtShortNotation, "cell 1 1,growx");
					txtShortNotation.setColumns(10);
				}
				{
					JLabel lblStreet = new JLabel("Stra\u00DFe:");
					lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(lblStreet, "cell 0 2,alignx left");
				}
				{
					txtStreet = new JTextField();
					txtStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(txtStreet, "cell 1 2,growx");
					txtStreet.setColumns(10);
				}
				{
					JLabel lblPostalCode = new JLabel("PLZ:");
					lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(lblPostalCode, "cell 0 3,alignx left");
				}
				{
					txtPostalCode = new JTextField();
					txtPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(txtPostalCode, "cell 1 3,alignx left");
					txtPostalCode.setColumns(5);
				}
				{
					JLabel lblStadt = new JLabel("Stadt:");
					lblStadt.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(lblStadt, "cell 0 4,alignx left");
				}
				{
					txtCity = new JTextField();
					txtCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(txtCity, "cell 1 4,growx");
					txtCity.setColumns(10);
				}
				{
					btnNewInstitution = new JButton("Neu");
					btnNewInstitution.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(btnNewInstitution, "flowx,cell 1 6,alignx right");
				}
				{
					btnAddAllocator = new JButton("Hinzuf\u00FCgen");
					btnAddAllocator.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlInput.add(btnAddAllocator, "cell 1 6,alignx right");
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				splitPane.setRightComponent(scrollPane);
				{
					tblAllocators = new TblAllocators();
					scrollPane.setViewportView(tblAllocators);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void addNotationDocumentListener(DocumentListener l){
		txtNotation.getDocument().addDocumentListener(l);
	}
	
	protected void addShortNotationDocumentListener(DocumentListener l){
		txtShortNotation.getDocument().addDocumentListener(l);
	}
	
	protected void addPostalCodeDocumentListener(DocumentListener l){
		txtPostalCode.getDocument().addDocumentListener(l);
	}
	
	protected void addCityDocumentListener(DocumentListener l){
		txtCity.getDocument().addDocumentListener(l);
	}
	
	protected void addStreetDocumentListener(DocumentListener l){
		txtStreet.getDocument().addDocumentListener(l);
	}
	
	protected void addConfirmNewAllocatorListener(ActionListener l){
		btnAddAllocator.addActionListener(l);
	}
	
	protected void addRowSelectionListener(ListSelectionListener l){
		tblAllocators.getSelectionModel().addListSelectionListener(l);
	}
	
	protected void addNewClinicalInstitutionListener(ActionListener l){
		btnNewInstitution.addActionListener(l);
	}
	
	class TblAllocators extends JTable {

		
		private static final long serialVersionUID = -2287094197866404980L;

		public TblAllocators() {
			setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			setFont(new Font("Tahoma", Font.PLAIN, 14));
			setIntercellSpacing(new Dimension(20, 5));
			setFillsViewportHeight(true); 
			setRowHeight(30);			
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		
		protected void addRowSelectionListener(ListSelectionListener l){
			getSelectionModel().addListSelectionListener(l);
		}
		
		
	}

}
