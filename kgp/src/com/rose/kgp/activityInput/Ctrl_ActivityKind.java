package com.rose.kgp.activityInput;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.rose.kgp.examination.ExaminationKind;

public class Ctrl_ActivityKind {
	protected Pnl_ActivityKind pnlActivityKind;
	private ArrayList<ActivityKind> activityKinds;
	private ArrayList<ExaminationKind> examKinds;
	/**
	 * get the panel that is controlled by this class
	 * @return a JPanel
	 */
	public Pnl_ActivityKind getPnlActivityKind() {
		return pnlActivityKind;
	}

	public Ctrl_ActivityKind(){
		pnlActivityKind = new Pnl_ActivityKind();
		setActivityKinds(); //fill the kinds of activity to an arrayList
		//create and set the model of this comboBox (therefore convert the arrayList with the items to a plain array)
		ActivityKindComboModel activityModel = new ActivityKindComboModel(activityKinds.toArray(new ActivityKind[activityKinds.size()]));		
		pnlActivityKind.comboActivityKind.setModel(activityModel);	
		//set the renderer of the comboBox
		pnlActivityKind.comboActivityKind.setRenderer(new ActivityKindListCellRenderer());
		
		setExaminationKinds(); //fill the kinds of examination to an arrayList
		//create and set the model of this comboBox (therefore convert the arrayList with the items to a plain array)
		ExaminationKindComboModel examKindModel = new ExaminationKindComboModel(examKinds.toArray(new ExaminationKind[examKinds.size()]));		
		pnlActivityKind.comboExamKind.setModel(examKindModel);	
		//set the renderer of the comboBox
		pnlActivityKind.comboExamKind.setRenderer(new ExaminationKindListCellRenderer());
		
			
		
	}
	
	/**
	 * add the kinds of activity to an arrayList
	 */
	private void setActivityKinds(){
		activityKinds = new ArrayList<ActivityKind>();
		activityKinds.add(new ActivityKind("CardioIntegral"));
		activityKinds.add(new ActivityKind("ambulant"));
		activityKinds.add(new ActivityKind("stationär"));
	}
	
	private void setExaminationKinds(){
		examKinds = new ArrayList<ExaminationKind>();
		examKinds.add(new ExaminationKind("Koro"));
		examKinds.add(new ExaminationKind("Koro mit PCI"));
		examKinds.add(new ExaminationKind("DR-SM-Implantation"));
	}
	
	/**
	 * renderer for the comboBox, that displays the kinds of activity
	 * @author Ekki
	 *
	 */
	class ActivityKindListCellRenderer extends JLabel implements ListCellRenderer<ActivityKind>{
		
		private static final long serialVersionUID = 3234890393052420068L;

		public ActivityKindListCellRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends ActivityKind> list, ActivityKind activityKind, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(activityKind.getNotation());
			this.setFont(new Font("Tahoma", Font.PLAIN, 14));
			if (isSelected) {
				// set the font color of the selected element     
			      this.setForeground(Color.BLACK);
			      // set the background color      
			      this.setBackground(Color.RED);
		    } else {
		         setForeground(list.getForeground());
		         setBackground(list.getBackground());
		    }
			
			return this;
		}
	}
	
	/**
	 * renderer for the comboBox, that displays the kinds of examination
	 * @author Ekki
	 *
	 */
	class ExaminationKindListCellRenderer extends JLabel implements ListCellRenderer<ExaminationKind>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 7127173134396349652L;

		public ExaminationKindListCellRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends ExaminationKind> list, ExaminationKind examKind, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(examKind.getNotation());
			this.setFont(new Font("Tahoma", Font.PLAIN, 14));
			if (isSelected) {
				// set the font color of the selected element     
			      this.setForeground(Color.BLACK);
			      // set the background color      
			      this.setBackground(Color.RED);
		    } else {
		         setForeground(list.getForeground());
		         setBackground(list.getBackground());
		    }
			
			return this;
		}
	}
	
	/**
	 * class to define the model of the comboBox, that displays the kinds of activity
	 * @author Ekki
	 *
	 */
	class ActivityKindComboModel extends DefaultComboBoxModel<ActivityKind>{

		
		private static final long serialVersionUID = 52993953405090042L;
		
		public ActivityKindComboModel(ActivityKind[] items){
			super (items);
		}
		
		public ActivityKind getSelectedItem(){
			ActivityKind activityKindSel = (ActivityKind) super.getSelectedItem();
			return activityKindSel;
		}
	}
	
	/**
	 * class to define the model of the comboBox, that displays the kinds of examination
	 * @author Ekki
	 *
	 */
	class ExaminationKindComboModel extends DefaultComboBoxModel<ExaminationKind>{

		
		/**
		 * 
		 */
		private static final long serialVersionUID = 889135147109929594L;

		public ExaminationKindComboModel(ExaminationKind[] items){
			super (items);
		}
		
		public ExaminationKind getSelectedItem(){
			ExaminationKind examKindSel = (ExaminationKind) super.getSelectedItem();
			return examKindSel;
		}
	}
}
