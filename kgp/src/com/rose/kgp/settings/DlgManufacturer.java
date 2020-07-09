package com.rose.kgp.settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;

public class DlgManufacturer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblManufacturer;
	private JTextField txtNotation;
	private JTextField txtContact;
	JFormattedTextField ftxtMobil;
	JButton okButton;
	JButton cancelButton;
	JButton btnCreate;
	
	

	protected JTextField getTxtNotation() {
		return txtNotation;
	}

	protected JTextField getTxtContact() {
		return txtContact;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgManufacturer dialog = new DlgManufacturer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgManufacturer() {
		setTitle("Hersteller");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setResizeWeight(0.3);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				JPanel pnlManufacturer = new JPanel();
				splitPane.setLeftComponent(pnlManufacturer);
				pnlManufacturer.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
				{
					JLabel lblNotation = new JLabel("Name:");
					lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(lblNotation, "cell 0 0,alignx left");
				}
				{
					txtNotation = new JTextField();
					txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(txtNotation, "cell 1 0,alignx left");
					txtNotation.setColumns(20);
				}
				{
					JLabel lblAddress = new JLabel("Kontakt:");
					lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(lblAddress, "cell 0 1,alignx trailing");
				}
				{
					txtContact = new JTextField();
					txtContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(txtContact, "cell 1 1,alignx left");
					txtContact.setColumns(20);
				}
				{
					JLabel lblMobil = new JLabel("Mobil:");
					lblMobil.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(lblMobil, "cell 0 2,alignx left");
				}
				{
					ftxtMobil = new JFormattedTextField();
					ftxtMobil.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(ftxtMobil, "cell 1 2,growx");
				}
				{
					btnCreate = new JButton("\u00DCbernehmen");
					btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
					pnlManufacturer.add(btnCreate, "cell 1 3,alignx right");
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				splitPane.setRightComponent(scrollPane);
				{
					tblManufacturer = new JTable();
					scrollPane.setViewportView(tblManufacturer);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void addOKListener(ActionListener l) {
		okButton.addActionListener(l);
	}
	
	protected void addCancelListener(ActionListener l) {
		cancelButton.addActionListener(l);
	}
	
	protected void addNotationListener (DocumentListener l){
		txtNotation.getDocument().addDocumentListener(l);
	}
	
	protected void addContactPersonListener (DocumentListener l) {
		txtContact.getDocument().addDocumentListener(l);
	}
	
	protected void addCreateListener (ActionListener l) {
		btnCreate.addActionListener(l);
	}
	
	protected void setTableModel(AbstractTableModel model) {
		tblManufacturer.setModel(model);
	}

}
