package com.rose.kgp.activityInput;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.rose.kgp.examination.Examination;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Pnl_ActivityKind extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8563700723024131689L;

	protected JComboBox<ActivityKind> comboActivityKind;
	protected JComboBox<Examination> comboExamKind;
	private JTextField textField;
	
	public Pnl_ActivityKind() {
		setBorder(new TitledBorder(null, "Activity_Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		/**
		 * create the toolBar
		 */
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JLabel lblActivityKind = new JLabel("Leistungsart:");
		lblActivityKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblActivityKind);
		
		comboActivityKind = new JComboBox<ActivityKind>();
		comboActivityKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(comboActivityKind);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		JLabel lblExamKind = new JLabel("Untersuchung:");
		lblExamKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblExamKind);
		
		comboExamKind = new JComboBox<Examination>();
		comboExamKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(comboExamKind);
		
		JPanel pnl_ActivityKindProps = new JPanel();
		add(pnl_ActivityKindProps, BorderLayout.CENTER);
		pnl_ActivityKindProps.setLayout(new MigLayout("", "[][][]", "[][][][][][][][]"));
		
		JLabel lblExamNr = new JLabel("Nummer:");
		lblExamNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblExamNr, "cell 0 0,alignx left");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(textField, "cell 1 0 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblRoom = new JLabel("Raum:");
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblRoom, "cell 0 1");
		
		JRadioButton rdbtnKGP = new JRadioButton("HKL_Praxis");
		rdbtnKGP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnKGP, "cell 1 1");
		
		JRadioButton rdbtnHospital = new JRadioButton("HKL_Klinik");
		rdbtnHospital.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnHospital, "cell 2 1");
		
		JLabel lblPostOp = new JLabel("post_OP:");
		lblPostOp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblPostOp, "cell 0 2,alignx left");
		
		JComboBox comboPostOp = new JComboBox();
		comboPostOp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboPostOp, "cell 1 2 2 1,growx");
		
		JLabel lblPhysician_1 = new JLabel("1. Untersucher:");
		lblPhysician_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblPhysician_1, "cell 0 3,alignx trailing");
		
		JComboBox comboPhysician_1 = new JComboBox();
		comboPhysician_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboPhysician_1, "cell 1 3 2 1,growx");
		
		JLabel lblPhysician_2 = new JLabel("2. Untersucher:");
		lblPhysician_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblPhysician_2, "cell 0 4,alignx trailing");
		
		JComboBox comboPhysician_2 = new JComboBox();
		comboPhysician_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboPhysician_2, "cell 1 4 2 1,growx");
		
		JLabel lblAssistent_1 = new JLabel("1. Assistent:");
		lblAssistent_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblAssistent_1, "cell 0 5,alignx left");
		
		JComboBox comboAssist_1 = new JComboBox();
		comboAssist_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboAssist_1, "cell 1 5 2 1,growx");
		
		JLabel lblAssist_2 = new JLabel("2. Assistent:");
		lblAssist_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblAssist_2, "cell 0 6,alignx left");
		
		JComboBox comboAssist_2 = new JComboBox();
		comboAssist_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(comboAssist_2, "cell 1 6 2 1,growx");
		
		JLabel lblClosure = new JLabel("Verschluss:");
		lblClosure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(lblClosure, "cell 0 7,alignx left");
		
		JRadioButton rdbtnPressureBandage = new JRadioButton("Druckverband");
		rdbtnPressureBandage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnPressureBandage, "cell 1 7");
		
		JRadioButton rdbtnAngioSeal = new JRadioButton("AngioSeal");
		rdbtnAngioSeal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_ActivityKindProps.add(rdbtnAngioSeal, "cell 2 7");
	}

}
