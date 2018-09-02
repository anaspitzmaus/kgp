package com.rose.kgp.administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.rose.kgp.allocator.Allocator;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.examination.Catheter_Intervention;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.examination.LeftHeartCatheter;
import com.rose.kgp.examination.StudyType;
import com.rose.kgp.personnel.Ctrl_PnlPatient;
import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.personnel.Physician;

public class Ctrl_ActivityKind implements Observer{
	protected Pnl_ActivityKind pnlActivityKind;
	private AccountingTypesComboModel accountingTypesModel;
	private AccountingTypeListener accountingTypeListener;
	private StudyTypeComboModel studyTypeModel;
	private StudyTypeListener studyTypeListener;
	private ArrayList<Examination> examKinds;
	private PhysicianComboModel examinerComboModel, examinerAssistComboModel;
	private PhysicianComboCellRenderer examinerComboRenderer, examinerAssistComboRenderer;
	private NurseComboModel nurseSterileComboModel, nurseUnsterileComboModel;
	private NurseComboCellRenderer nurseComboRenderer;
	private Examination examSel;
	private PhysicianListener physicianListener;
	private Ctrl_PnlPatient ctrlPnlPatient;
	private AllocatorComboModel allocatorComboModel;
	private AllocatorListCellRenderer allocatorListCellRenderer;
	
	/**
	 * get the panel that is controlled by this class
	 * @return a JPanel
	 */
	public Pnl_ActivityKind getPanel() {
		return pnlActivityKind;
	}

	public Ctrl_ActivityKind(){
		ctrlPnlPatient = new Ctrl_PnlPatient();
		pnlActivityKind = new Pnl_ActivityKind();
		pnlActivityKind.add(ctrlPnlPatient.getPanel(),BorderLayout.SOUTH);
		
		//create and set the model of the comboBox that displays the types of accounting
		accountingTypesModel = new AccountingTypesComboModel(AccountingType.values());		
		pnlActivityKind.comboAccountingType.setModel(accountingTypesModel);	
		//set the renderer of the comboBox
		pnlActivityKind.comboAccountingType.setRenderer(new AccountingTypeListCellRenderer());		
		
		//create and set the model of this comboBox (therefore convert the arrayList with the items to a plain array)
		studyTypeModel = new StudyTypeComboModel(StudyType.values());		
		pnlActivityKind.comboStudyType.setModel(studyTypeModel);	
		//set the renderer of the comboBox
		pnlActivityKind.comboStudyType.setRenderer(new StudyTypeListCellRenderer());
		examinerComboModel = new PhysicianComboModel();
		examinerAssistComboModel = new PhysicianComboModel();
		pnlActivityKind.comboExaminer.setModel(examinerComboModel);
		pnlActivityKind.comboExaminerAssist.setModel(examinerAssistComboModel);
		examinerComboRenderer = new PhysicianComboCellRenderer();
		pnlActivityKind.comboExaminer.setRenderer(examinerComboRenderer);
		examinerAssistComboRenderer = new PhysicianComboCellRenderer();
		pnlActivityKind.comboExaminerAssist.setRenderer(examinerAssistComboRenderer);
		
		nurseSterileComboModel = new NurseComboModel();
		nurseUnsterileComboModel = new NurseComboModel();
		pnlActivityKind.comboAssist_1.setModel(nurseSterileComboModel);
		pnlActivityKind.comboAssist_2.setModel(nurseUnsterileComboModel);
		nurseComboRenderer = new NurseComboCellRenderer();
		pnlActivityKind.comboAssist_1.setRenderer(nurseComboRenderer);
		pnlActivityKind.comboAssist_2.setRenderer(nurseComboRenderer);
		
		
		allocatorListCellRenderer = new AllocatorListCellRenderer();
		pnlActivityKind.comboAllocator.setRenderer(allocatorListCellRenderer);
		allocatorComboModel = new AllocatorComboModel();
		pnlActivityKind.comboAllocator.setModel(allocatorComboModel);
		setListener();
	}
	
	/**
	 * set the listeners of the components
	 */
	private void setListener(){
		physicianListener = new PhysicianListener();//listener for the examiner
		pnlActivityKind.addPhysicianListener(physicianListener);
		accountingTypeListener = new AccountingTypeListener();
		pnlActivityKind.addAccountingTypeListener(accountingTypeListener);
		studyTypeListener = new StudyTypeListener(); 
		pnlActivityKind.addStudyTypeListener(studyTypeListener);
	}
	
	
	
	
	
	/**
	 * renderer for the comboBox, that displays the kinds of activity
	 * @author Ekki
	 *
	 */
	class AccountingTypeListCellRenderer extends JLabel implements ListCellRenderer<AccountingType>{
		
		private static final long serialVersionUID = 3234890393052420068L;

		public AccountingTypeListCellRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends AccountingType> list, AccountingType accountingType, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(accountingType.name());
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
	class StudyTypeListCellRenderer extends JLabel implements ListCellRenderer<StudyType>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 7127173134396349652L;

		public StudyTypeListCellRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends StudyType> list, StudyType studyType, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			//setText(examKind.getNotation());
			this.setFont(new Font("Tahoma", Font.PLAIN, 14));
			this.setText(studyType.name());
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
	 * class to define the model of the comboBox, that displays the types of accounting
	 * @author Ekki
	 *
	 */
	class AccountingTypesComboModel extends DefaultComboBoxModel<AccountingType>{

		
		private static final long serialVersionUID = 52993953405090042L;
		
		public AccountingTypesComboModel(AccountingType[] items){
			super (items);
		}
		
		public AccountingType getSelectedItem(){
			AccountingType accountingTypeSel = (AccountingType) super.getSelectedItem();
			return accountingTypeSel;
		}
	}
	
	/**
	 * class to define the model of the comboBox, that displays the kinds of examination
	 * @author Ekki
	 *
	 */
	class StudyTypeComboModel extends DefaultComboBoxModel<StudyType>{

		
		/**
		 * 
		 */
		private static final long serialVersionUID = 889135147109929594L;

		public StudyTypeComboModel(StudyType[] items){
			super (items);
		}
		
		public StudyType getSelectedItem(){
			StudyType studyType= (StudyType) super.getSelectedItem();
			return studyType;
		}
	}
	
	class PhysicianComboModel extends DefaultComboBoxModel<Physician>{

		private static final long serialVersionUID = -7819865982422683747L;
		public PhysicianComboModel() {
			ArrayList<Physician> physicians = SQL_SELECT.activePhysicians(LocalDate.now());
			for(Physician physician: physicians){
				addElement(physician);
			}			
		}
	}
	
	class PhysicianComboCellRenderer extends JPanel implements ListCellRenderer<Physician>{
		/**
		 * 
		 */
		private static final long serialVersionUID = -5010326835630750838L;
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends Physician> list, Physician physician, int index,
				boolean isSelected, boolean cellHasfocus) {
			
			JLabel renderer = (JLabel)defaultRenderer.getListCellRendererComponent(list, physician, index, isSelected, cellHasfocus);
			//renderer.setBackground(Color.RED);
			//list.setSelectionBackground(Color.RED);
			if(physician instanceof Physician){
				renderer.setText(physician.getAlias());
				
			}else{
				renderer.setText("keine Angaben");				
			}
			
			if(!isSelected){
//				renderer.setForeground(Color.BLUE);
				//list.setSelectionBackground(Color.RED);
			}else{
				//renderer.setForeground(Color.BLUE);
				//list.setSelectionBackground(Color.RED);
			}
			//renderer = (JLabel)defaultRenderer.getListCellRendererComponent(list, physician, index, isSelected, cellHasfocus);
			return renderer;
		}
		
	}
	
	class NurseComboModel extends DefaultComboBoxModel<Nurse>{

		private static final long serialVersionUID = 8096510093285581010L;
		public NurseComboModel(){
			ArrayList<Nurse> nurses = SQL_SELECT.activeNurses(LocalDate.now());
			for(Nurse nurse: nurses){
				addElement(nurse);
			}
		}
	}
	
	class NurseComboCellRenderer implements ListCellRenderer<Nurse>{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends Nurse> list, Nurse nurse, int index,
				boolean isSelected, boolean cellHasFocus) {
			JLabel renderer = (JLabel)defaultRenderer.getListCellRendererComponent(list, nurse, index, isSelected, cellHasFocus);
			if(nurse instanceof Nurse){
				renderer.setText(nurse.getAlias());
				
			}else{
				renderer.setText("keine Angaben");				
			}
			return renderer;
		}
		
	}
	
	class AllocatorComboModel extends DefaultComboBoxModel<Allocator>{

		private static final long serialVersionUID = 2682815872178398469L;
		public AllocatorComboModel(){
			ArrayList<Allocator> allocators = SQL_SELECT.Allocators(LocalDate.now());
			for(Allocator allocator: allocators){
				addElement(allocator);
			}
			//addElement(new Allocator("sonstige"));
		}
	}
	
	/**
	 * renderer for the comboBox, that displays the allocators
	 * @author Ekki
	 *
	 */
	class AllocatorListCellRenderer extends JLabel implements ListCellRenderer<Allocator>{		

		/**
		 * 
		 */
		private static final long serialVersionUID = -2957262204171896627L;

		public AllocatorListCellRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends Allocator> list, Allocator allocator, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(allocator.getClinicalInstitution().getShortNotation());
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
	
	class PhysicianListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent ev) {
			Physician physician = (Physician) examinerComboModel.getSelectedItem();
			examSel.setExaminer(physician);			
		}		
	}
	
	/**
	 * listener for the comboBox that shows the accounting types
	 * @author Administrator
	 *
	 */
	class AccountingTypeListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(examSel instanceof Examination){			
				examSel.getTreatmentCase().setAccountingType(accountingTypesModel.getSelectedItem());	
			}
		}
		
	}
	
	class StudyTypeListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			switch (studyTypeModel.getSelectedItem()) {
			case LeftHeartCatheter:				
				break;
			case PM_Implantation:
				break;
			case Koronar_Diagnostisch:
				examSel = new LeftHeartCatheter(examSel.getRawData());
				break;
			default:
				break;
			}
			
		}
		
	}

	@Override
	/**
	 * this method is called if an examination was selected in the table that shows all examinations
	 */
	public void update(Observable arg0, Object examSel) {
		this.examSel = (Examination) examSel;
				
		
		//check if examSel and physician/nurse are not null
		pnlActivityKind.rdbtnKGP.setSelected(true);
		
		if(this.examSel.getTreatmentCase().getPatient() instanceof Patient){
			ctrlPnlPatient.setPatient(this.examSel.getTreatmentCase().getPatient());
		}
		
		//show the examiners data
		if(this.examSel.getExaminer() instanceof Physician){
			setComboExaminer();	
		}else{
			//Error Message, that alias doesn't match an existing physician
			examinerComboModel.setSelectedItem(null);			
			//pnlActivityKind.comboPhysician_1.repaint();
		}
		
		//show the examiners assistant data
		if(this.examSel instanceof Catheter_Intervention){
			if(((Catheter_Intervention)this.examSel).getSecondExaminer() instanceof Physician){
				setComboExaminerAssistant();	
			}else{
				examinerAssistComboModel.setSelectedItem(null);
			}
			
			if(((Catheter_Intervention)this.examSel).getNurseSterile() instanceof Nurse){
				setComboNurseSterile();
			}else{
				nurseSterileComboModel.setSelectedItem(null);
			}
			
			if(((Catheter_Intervention)this.examSel).getNurseUnsterile() instanceof Nurse){
				setComboNurseUnsterile();
			}else{
				nurseUnsterileComboModel.setSelectedItem(null);
			}
		}
		
		
		accountingTypesModel.setSelectedItem(this.examSel.getTreatmentCase().getAccountingType());
		studyTypeModel.setSelectedItem(this.examSel.getStudyType());
		
	}
	
	private void setStudyType(){
		
	}
	
	/**
	 * set the selected item at the comboBox that shows the examining physician
	 * @param examSel the selected examination
	 */
	private void setComboExaminer(){
		String alias = examSel.getExaminer().getAlias();
		for(int i=0; i< examinerComboModel.getSize(); i++){
			if(alias.equals(examinerComboModel.getElementAt(i).getAlias())){
				examinerComboModel.setSelectedItem(examinerComboModel.getElementAt(i));
//				Object child = pnlActivityKind.comboPhysician_1.getAccessibleContext().getAccessibleChild(0);
//				BasicComboPopup popup = (BasicComboPopup)child;
//				JList list = popup.getList();
//				list.setSelectionBackground(Color.RED);
				//pnlActivityKind.comboPhysician_1.
				return;
			}
			
		}		
	}
	
	/**
	 * set the selected item at the comboBox that shows the examining physicians first assistant
	 * @param examSel the selected examination
	 */
	private void setComboExaminerAssistant(){
		if(((Catheter_Intervention)examSel).getSecondExaminer() instanceof Physician){
			String alias = ((Catheter_Intervention)examSel).getSecondExaminer().getAlias();//get the first assistant
			for(int i=0; i< examinerAssistComboModel.getSize(); i++){//check if the assistant matches a physician in the comboBox Model
				if(alias.equals(examinerAssistComboModel.getElementAt(i).getAlias())){
					examinerAssistComboModel.setSelectedItem(examinerAssistComboModel.getElementAt(i));
	//				Object child = pnlActivityKind.comboPhysician_1.getAccessibleContext().getAccessibleChild(0);
	//				BasicComboPopup popup = (BasicComboPopup)child;
	//				JList list = popup.getList();
	//				list.setSelectionBackground(Color.RED);
					//pnlActivityKind.comboPhysician_1.
					return;
				}
			}
		}
	}
	
	/**
	 * set the selected item at the comboBox that shows the sterile nurse
	 * @param examSel the selected examination
	 */
	private void setComboNurseSterile(){
		if(((Catheter_Intervention)examSel).getNurseSterile() instanceof Nurse){
			String alias = ((Catheter_Intervention)examSel).getNurseSterile().getAlias();
			for(int i=0; i< nurseSterileComboModel.getSize(); i++){
				if(alias.equals(nurseSterileComboModel.getElementAt(i).getAlias())){
					nurseSterileComboModel.setSelectedItem(nurseSterileComboModel.getElementAt(i));

					return;
				}
			}
			
		}		
	}
	
	/**
	 * set the selected item at the comboBox that shows the unsterile nurse
	 * @param examSel the selected examination
	 */
	private void setComboNurseUnsterile(){
		if(((Catheter_Intervention)examSel).getNurseUnsterile() instanceof Nurse){
			String alias = ((Catheter_Intervention)examSel).getNurseUnsterile().getAlias();
			for(int i=0; i< nurseUnsterileComboModel.getSize(); i++){
				if(alias.equals(nurseUnsterileComboModel.getElementAt(i).getAlias())){
					nurseUnsterileComboModel.setSelectedItem(nurseUnsterileComboModel.getElementAt(i));

					return;
				}
			}
			
		}		
	}
	
	
	
}
