package com.rose.kgp.administration;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.rose.kgp.allocator.Allocator;
import com.rose.kgp.examination.StudyType;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Physician;
import com.rose.kgp.personnel.Pnl_Patient;


public class Pnl_ActivityKind extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8563700723024131689L;

	protected JComboBox<AccountingType> comboAccountingType;
	protected JComboBox<StudyType> comboStudyType;
	protected JComboBox<Physician> comboExaminer, comboExaminerAssist;
	protected JComboBox<Nurse> comboAssist_1, comboAssist_2;
	private JTextField textField;
	protected JRadioButton rdbtnKGP, rdbtnHospital;
	protected ButtonGroup groupLocality;
	private Pnl_Patient pnlPatient;
	protected JComboBox<Allocator> comboAllocator;
	
	public Pnl_ActivityKind() {
		setBorder(new TitledBorder(null, "Activity_Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		/**
		 * create the toolBar
		 */
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JLabel lblActivityKind = new JLabel("Leistungsart:");
		lblActivityKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblActivityKind);
		
		comboAccountingType = new JComboBox<AccountingType>();
		comboAccountingType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(comboAccountingType);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		JLabel lblExamKind = new JLabel("Untersuchung:");
		lblExamKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblExamKind);
		
		comboStudyType = new JComboBox<StudyType>();
		comboStudyType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(comboStudyType);
		
		JPanel pnl_ActivityKindProps = new JPanel();
		add(pnl_ActivityKindProps, BorderLayout.CENTER);
		pnl_ActivityKindProps.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][][][grow][]"));
		
		JLabel lblExamNr = new JLabel("Nummer:");
		lblExamNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblExamNr, "cell 0 0,alignx left");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(textField, "cell 1 0,alignx left");
		textField.setColumns(10);
		
		JLabel lblAssign = new JLabel("Zuweiser:");
		lblAssign.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblAssign, "cell 2 0,alignx trailing,aligny bottom");
		
		comboAllocator = new JComboBox<Allocator>();
		comboAllocator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboAllocator, "cell 3 0,alignx left");
		
		JLabel lblRoom = new JLabel("Raum:");
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblRoom, "cell 0 1");
		
		rdbtnKGP = new JRadioButton("HKL_Praxis");
		rdbtnKGP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnKGP, "flowx,cell 1 1");
		
		rdbtnHospital = new JRadioButton("HKL_Klinik");
		rdbtnHospital.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnHospital, "cell 1 1");
		
		groupLocality = new ButtonGroup();
		groupLocality.add(rdbtnKGP);
		groupLocality.add(rdbtnHospital);
		
		JLabel lblPostOp = new JLabel("post_OP:");
		lblPostOp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblPostOp, "cell 0 2,alignx left");
		
		JComboBox comboPostOp = new JComboBox();
		comboPostOp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboPostOp, "cell 1 2,alignx left");
		
		JLabel lblPhysician_1 = new JLabel("1. Untersucher:");
		lblPhysician_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblPhysician_1, "cell 0 3,alignx left");
		
		comboExaminer = new JComboBox<Physician>();
		comboExaminer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboExaminer, "cell 1 3,alignx left");
		
		JLabel lblPhysician_2 = new JLabel("2. Untersucher:");
		lblPhysician_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblPhysician_2, "cell 0 4,alignx left");
		
		comboExaminerAssist = new JComboBox<Physician>();
		comboExaminerAssist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboExaminerAssist, "cell 1 4,alignx left");
		
		JLabel lblAssistent_1 = new JLabel("1. Assistent:");
		lblAssistent_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblAssistent_1, "cell 0 5,alignx left");
		
		comboAssist_1 = new JComboBox<Nurse>();
		comboAssist_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboAssist_1, "cell 1 5,alignx left");
		
		JLabel lblAssist_2 = new JLabel("2. Assistent:");
		lblAssist_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblAssist_2, "cell 0 6,alignx left");
		
		comboAssist_2 = new JComboBox<Nurse>();
		comboAssist_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboAssist_2, "cell 1 6,alignx left");
		
		JLabel lblClosure = new JLabel("Verschluss:");
		lblClosure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblClosure, "cell 0 7,alignx left");
		
		JRadioButton rdbtnPressureBandage = new JRadioButton("Druckverband");
		rdbtnPressureBandage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnPressureBandage, "flowx,cell 1 7");
		
		
		
		ButtonGroup groupClosure = new ButtonGroup();
		groupClosure.add(rdbtnPressureBandage);
		
		
		
		JRadioButton rdbtnAngioSeal = new JRadioButton("AngioSeal");
		rdbtnAngioSeal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnAngioSeal, "cell 1 7");
		groupClosure.add(rdbtnAngioSeal);
		
		
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.addItem("Hallo");
//		comboBox.addItem("Hallöchen");
//		comboBox.setOpaque(true);
//		((JTextField)comboBox.getEditor().getEditorComponent()).setBackground(Color.RED);
		
		//pnl_ActivityKindProps.add(comboBox, "cell 1 9,growx");
		
		JButton btnSetActivity = new JButton("Erfassen");
		btnSetActivity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnl_ActivityKindProps.add(btnSetActivity, "cell 1 9");
		
		
		
	}

	
	
	/**
	 * add an ItemListener to the comboBox that shows the first examiner (Physician)
	 * @param l the itemListener
	 */
	protected void addPhysicianListener(ItemListener l){
		comboExaminer.addItemListener(l);
	}
	
	/**
	 * add an ItemListener to the comboBox that shows the examiners assistant (Physician)
	 * @param l the itemListener
	 */
	protected void addAccountingTypeListener(ItemListener l){
		comboAccountingType.addItemListener(l);
	}
	
	/**
	 *  add an ItemListener to the comboBox that shows the kind of the selected study
	 * @param l the item listener
	 */
	protected void addStudyTypeListener(ItemListener l){
		comboStudyType.addItemListener(l);
	}
	
	
}
