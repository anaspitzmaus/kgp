package com.rose.kgp.material;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import com.rose.kgp.examination.Examination;
import com.rose.kgp.material.CtrlPnlElectrodes.NoticeListener;
import com.rose.kgp.material.CtrlPnlElectrodes.TblElectrodeElectrodeRenderer;
import com.rose.kgp.material.CtrlPnlElectrodes.TblElectrodesModel;
import com.rose.kgp.material.CtrlPnlElectrodes.TypeModel;
import com.rose.kgp.material.CtrlPnlElectrodes.TypeRenderer;
import com.rose.kgp.ui.Pnl_SetDate;

import net.miginfocom.swing.MigLayout;



public class PnlElectrodes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4390121514107828265L;
	private JTextField txtSerialNr;
	private JTable tblElectrodes;
	private JLabel lblExpireDate;
	private JLabel lblModel;
	private JLabel lblSerialNr;
	JComboBox<ElectrodeModel> cbxModel;
	private JTextField txtNotice;
	private JButton btnCreate;
	private JLabel lblNotice;

	/**
	 * Create the panel.
	 */
	public PnlElectrodes() {
		setLayout(new MigLayout("", "[][][][grow][trailing]", "[][][grow]"));
		
		lblModel = new JLabel("lblModel");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblModel, "cell 0 0,alignx left,aligny bottom");
		
		lblSerialNr = new JLabel("lblSerialNr");
		lblSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblSerialNr, "cell 1 0");
		
		lblExpireDate = new JLabel("lblExpireDate");
		lblExpireDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblExpireDate, "cell 2 0");
		
		lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNotice, "cell 3 0");
		
		cbxModel = new JComboBox<ElectrodeModel>();
		cbxModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxModel, "cell 0 1,growx");
		
		txtSerialNr = new JTextField();
		txtSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtSerialNr, "flowx,cell 1 1,growx");
		txtSerialNr.setColumns(10);
		
		txtNotice = new JTextField();
		txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtNotice, "cell 3 1,growx");
		txtNotice.setColumns(10);
		
		btnCreate = new JButton("btnCreate");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnCreate, "cell 4 1");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 2 5 1,grow");
		
		tblElectrodes = new JTable();
		tblElectrodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblElectrodes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tblElectrodes);
		
		

	}
	
	protected JComboBox getCbxModel() {
		return this.cbxModel;
	}
	
	protected void setNoticeTxt(String txt) {
		txtNotice.setText(txt);
	}
	
	protected JTextField getTxtNotice() {
		return this.txtNotice;
	}

	protected void setDatePnl(Pnl_SetDate panel) {
		this.add(panel, "cell 2 1,growx,aligny top");
		
	}

	protected void setLblExpireDateText(String s) {
		lblExpireDate.setText(s);
		
	}

	protected void setLblModelText(String s) {
		lblModel.setText(s);
		
	}

	protected void setLblSerialNrText(String s) {
		lblSerialNr.setText(s);
		
	}

	protected void setTblModel(TblElectrodesModel tblElectrodesModel) {
		tblElectrodes.setModel(tblElectrodesModel);
		
	}
	
	protected void setLblNoticeText(String text) {
		lblNotice.setText(text);
	}

	protected void setCbxTypeModel(TypeModel model) {
		cbxModel.setModel(model);		
	}

	protected void setTypeRenderer(TypeRenderer typeRenderer) {
		cbxModel.setRenderer(typeRenderer);
		
	}

	protected void addTypeListener(ItemListener typeListener) {
		cbxModel.addItemListener(typeListener);
		
	}

	protected void addCreateListener(ActionListener createListener) {
		btnCreate.addActionListener(createListener);
		
	}

	protected void addSerialNrListener(DocumentListener serialNrListener) {
		txtSerialNr.getDocument().addDocumentListener(serialNrListener);
		
	}
	
	protected void setBtnCreateTxt(String txt) {
		btnCreate.setText(txt);
	}
	
	protected ListSelectionModel getTblListSelectionModel() {
		return tblElectrodes.getSelectionModel();
	}
	
	protected JTable getTblElectrodes() {
		return this.tblElectrodes;
	}
	
	protected JTextField getTxtSerialNr() {
		return this.txtSerialNr;
	}
	
	/**
	 * set all components to standard and clear selection of the table
	 */
	protected void setBaselineValues(Integer indexSel) {
		txtSerialNr.setText("");
		txtNotice.setText("");
		cbxModel.setSelectedIndex(indexSel);
		cbxModel.repaint();
		tblElectrodes.getSelectionModel().clearSelection();
	}

	protected void setTblElectrodesIDRenderer(TableCellRenderer renderer) {
		tblElectrodes.setDefaultRenderer(Integer.class, renderer);
		
	}

	protected void setTblElectrodesElectrodeRenderer(TableCellRenderer renderer) {
		tblElectrodes.setDefaultRenderer(Electrode.class, renderer);
		
	}
	
	protected void setTblElectrodesDateRenderer(TableCellRenderer renderer) {
		tblElectrodes.setDefaultRenderer(LocalDate.class, renderer);
	}
	
	protected void setTblElectrodesStringRenderer(TableCellRenderer renderer) {
		tblElectrodes.setDefaultRenderer(String.class, renderer);
	}

	
	protected void setTblElectrodesExamRenderer(TableCellRenderer renderer) {
		tblElectrodes.setDefaultRenderer(Examination.class, renderer);
	}
	
	protected void setTblElectrodesModelRenderer(TableCellRenderer renderer) {
		tblElectrodes.setDefaultRenderer(ElectrodeModel.class, renderer);
	}
	
	protected Integer getSelectedRow() {
		return tblElectrodes.getSelectedRow();
	}

	protected void addNoticeListener(DocumentListener listener) {
		txtNotice.getDocument().addDocumentListener(listener);
		
	}
	
	protected void addTblElectrodesRowSelectionListener(ListSelectionListener listener) {
		tblElectrodes.getSelectionModel().addListSelectionListener(listener);
		
	}

}
