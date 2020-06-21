package com.rose.kgp.echo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import net.miginfocom.swing.MigLayout;

public class PnlValves extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4009900485424560105L;
	JComboBox<String> cbxStenGrading;
	
	

	protected JComboBox<String> getCbxStenGrading() {
		return cbxStenGrading;
	}



	/**
	 * Create the panel.
	 */
	public PnlValves() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(800, 480));
		JSplitPane splitPaneLeftRight = new JSplitPane();
		splitPaneLeftRight.setResizeWeight(0.5);
		add(splitPaneLeftRight, BorderLayout.CENTER);
		
		JPanel pnlRightHeart = new JPanel();
		splitPaneLeftRight.setLeftComponent(pnlRightHeart);
		pnlRightHeart.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPaneRightHeart = new JSplitPane();
		splitPaneRightHeart.setResizeWeight(0.5);
		splitPaneRightHeart.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlRightHeart.add(splitPaneRightHeart, BorderLayout.CENTER);
		
		JPanel pnlTVBase = new JPanel();
		splitPaneRightHeart.setLeftComponent(pnlTVBase);
		pnlTVBase.setLayout(new BorderLayout(0, 0));
		
		JToolBar tbrTV = new JToolBar();
		pnlTVBase.add(tbrTV, BorderLayout.NORTH);
		
		JLabel lblTV = new JLabel("Trikuspidalklappe");
		lblTV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbrTV.add(lblTV);
		
		JPanel pnlTV = new JPanel();
		pnlTVBase.add(pnlTV, BorderLayout.CENTER);
		pnlTV.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel pnlPVBase = new JPanel();
		splitPaneRightHeart.setRightComponent(pnlPVBase);
		pnlPVBase.setLayout(new BorderLayout(0, 0));
		
		JToolBar tbrPV = new JToolBar();
		pnlPVBase.add(tbrPV, BorderLayout.NORTH);
		
		JLabel lblPV = new JLabel("Pulmonalklappe");
		lblPV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbrPV.add(lblPV);		
		
		JPanel pnlPV = new JPanel();
		pnlPVBase.add(pnlPV, BorderLayout.CENTER);
		pnlPV.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel pnlLeftHeart = new JPanel();
		splitPaneLeftRight.setRightComponent(pnlLeftHeart);
		pnlLeftHeart.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPaneLeftHeart = new JSplitPane();
		splitPaneLeftHeart.setResizeWeight(0.5);
		splitPaneLeftHeart.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlLeftHeart.add(splitPaneLeftHeart, BorderLayout.CENTER);
		
		JPanel pnlMVBase = new JPanel();
		splitPaneLeftHeart.setLeftComponent(pnlMVBase);
		pnlMVBase.setLayout(new BorderLayout(0, 0));
		
		JToolBar tbrMV = new JToolBar();
		pnlMVBase.add(tbrMV, BorderLayout.NORTH);
		
		JLabel lblMV = new JLabel("Mitralklappe");
		lblMV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbrMV.add(lblMV);
		
		JPanel pnlMV = new JPanel();
		pnlMVBase.add(pnlMV, BorderLayout.CENTER);
		pnlMV.setLayout(new MigLayout("", "[][][][][][][]", "[][][][]"));
		
		JLabel lblStenosis = new JLabel("Stenose:");
		lblStenosis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(lblStenosis, "cell 0 0,alignx left");
		
		JLabel lblPGMean = new JLabel("PGMean:");
		lblPGMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(lblPGMean, "cell 1 0,alignx left");
		
		JFormattedTextField ftxtPGMean = new JFormattedTextField();
		ftxtPGMean.setColumns(5);
		ftxtPGMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(ftxtPGMean, "cell 2 0,alignx left");
		
		JLabel lblPGMax = new JLabel("PGMax:");
		lblPGMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(lblPGMax, "cell 3 0,alignx trailing");
		
		JFormattedTextField ftxtPGMax = new JFormattedTextField();
		ftxtPGMax.setColumns(5);
		ftxtPGMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(ftxtPGMax, "cell 4 0,alignx left");
		
		JLabel lblMOA = new JLabel("M\u00D6F:");
		lblMOA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(lblMOA, "cell 5 0,alignx trailing");
		
		JFormattedTextField ftxtMOA = new JFormattedTextField();
		ftxtMOA.setColumns(5);
		ftxtMOA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(ftxtMOA, "cell 6 0,growx");
		
		JLabel lblGrading = new JLabel("Schweregrad:");
		lblGrading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(lblGrading, "cell 1 1,alignx left");
		
		cbxStenGrading = new JComboBox<String>();
		cbxStenGrading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(cbxStenGrading, "cell 2 1,alignx left");
		
		JLabel lblReg = new JLabel("Regurgitation:");
		lblReg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(lblReg, "cell 0 2,alignx trailing");
		
		JComboBox cbxRegGrading = new JComboBox();
		cbxRegGrading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMV.add(cbxRegGrading, "cell 2 3,alignx left");
		
		JPanel pnlAVBase = new JPanel();
		splitPaneLeftHeart.setRightComponent(pnlAVBase);
		pnlAVBase.setLayout(new BorderLayout(0, 0));
		
		JToolBar tbrAV = new JToolBar();
		pnlAVBase.add(tbrAV, BorderLayout.NORTH);
		
		JLabel lblAV = new JLabel("Aortenklappe");
		lblAV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbrAV.add(lblAV);
		
		JPanel pnlAV = new JPanel();
		pnlAVBase.add(pnlAV, BorderLayout.CENTER);
		pnlAV.setLayout(new MigLayout("", "[]", "[]"));

	}
	
	protected void setStenosisModel(ComboBoxModel<String> model) {
		cbxStenGrading.setModel(model);
	}
	
	protected void addStenosisListener(ItemListener listener) {
		cbxStenGrading.addItemListener(listener);
	}

}
