package com.rose.kgp.personnel;


import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

/**
 * abstract class that controls an inherited panel of the abstract panel 'Pnl_NewStaff' 
 * @author Ekkehard Rose
 *
 */
public abstract class Ctrl_PnlNewStaff {

	protected Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	protected SexModel sexModel;
	protected Pnl_NewStaff pnlNewStaff;
	protected Staff staff; 
	protected SurnameListener surnameListener;
	protected FirstnameListener firstnameListener;
	protected AliasListener aliasListener;
	protected SexListener sexListener;
	
	protected Pnl_NewStaff getPanel(){
		return this.pnlNewStaff;
	}
	
	protected Ctrl_PnlSetDate getCtrlPnlSetBirthDate(){
		return this.ctrlPnlSetBirthDate;
	}
	
	protected Ctrl_PnlSetDate getCtrlPnlSetOnsetDate(){
		return this.ctrlPnlSetOnsetDate;
	}
	
	/**
	 * standard constructor
	 * @param ctrlPnlSetBirthDate
	 * @param ctrlPnlSetOnsetDate
	 * @param pnlNewStaff the JPanel that has to be controlled by this controller class
	 */
	public Ctrl_PnlNewStaff(Ctrl_PnlSetDate ctrlPnlSetBirthDate, Ctrl_PnlSetDate ctrlPnlSetOnsetDate, Pnl_NewStaff pnlNewStaff) {
		staff = null;
		this.pnlNewStaff = pnlNewStaff;
		this.ctrlPnlSetBirthDate = ctrlPnlSetBirthDate;
		this.ctrlPnlSetOnsetDate = ctrlPnlSetOnsetDate;
		this.pnlNewStaff.getTxtFirstname().setEnabled(false);
		this.pnlNewStaff.getTxtSurname().setEnabled(false);
		this.pnlNewStaff.getComboSex().setEnabled(false);
		sexModel = new SexModel();
		this.pnlNewStaff.getComboSex().setModel(this.sexModel);
		this.pnlNewStaff.getComboSex().setRenderer(new SexComboRenderer());
		this.pnlNewStaff.getTxtAlias().setEnabled(false);
		this.ctrlPnlSetBirthDate.setPnlEnabled(false);
		this.ctrlPnlSetOnsetDate.setPnlEnabled(false);
		this.pnlNewStaff.getBtnSetStaff().setEnabled(false);
		initializeListeners();
		setListener();
	}
	
	private void initializeListeners(){
		surnameListener = new SurnameListener();
		firstnameListener = new FirstnameListener();
		aliasListener = new AliasListener();
		sexListener = new SexListener();
	}
	
	/**
	 * add all listeners of input fields of that panel
	 */
	protected void setListener(){
		
		pnlNewStaff.addSurnameListener(surnameListener);
		pnlNewStaff.addFirstnameListener(firstnameListener);		
		pnlNewStaff.addSexListener(sexListener);
		pnlNewStaff.addAliasListener(aliasListener);
		
	}
	
	/**
	 * removes all listeners of input fields of that panel
	 */
	protected void removeListener(){
		pnlNewStaff.removeSurnameListener(surnameListener);
		pnlNewStaff.removeFirstnameListener(firstnameListener);
		pnlNewStaff.removeAliasListener(aliasListener);		
		pnlNewStaff.removeSexListener(sexListener);
		
	}
	
	/**
	 * prepares the panel before a new staff is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewStaff(Staff staff){
		pnlNewStaff.getTxtFirstname().setEnabled(true);
		pnlNewStaff.getTxtSurname().setEnabled(true);
		pnlNewStaff.getComboSex().setEnabled(true);
		pnlNewStaff.getTxtAlias().setEnabled(true);
		pnlNewStaff.getBtnSetStaff().setEnabled(true);
		this.ctrlPnlSetBirthDate.setPnlEnabled(true);
		this.ctrlPnlSetOnsetDate.setPnlEnabled(true);
		//when activating the dateSetPnl: set the Date of the DateSetPanel to a specific date
		this.ctrlPnlSetBirthDate.setDate(LocalDate.now().minusYears(40));
		this.ctrlPnlSetOnsetDate.setDate(LocalDate.now());
		pnlNewStaff.getTxtSurname().setText("");
		pnlNewStaff.getTxtFirstname().setText("");
		pnlNewStaff.getTxtAlias().setText("");
		pnlNewStaff.getComboSex().setSelectedItem(null);
		pnlNewStaff.getComboSex().repaint();
		this.staff = staff;
		
	}
	
	/**
	 * model for the comboBox that shows the address of a new physician
	 * @author Ekkehard Rose
	 *
	 */
	class SexModel extends AbstractListModel<Sex> implements ComboBoxModel<Sex>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -5723909430414256587L;
		
		Sex selection = null;
		List<Sex> sexList;
		
		public SexModel() {
			sexList = Arrays.asList(Sex.values());			
		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub			
		}

		@Override
		public Sex getElementAt(int index) {
			return sexList.get(index);
		}

		@Override
		public int getSize() {
			return sexList.size();
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getSelectedItem() {
			return selection;
		}

		@Override
		public void setSelectedItem(Object sex) {
			selection= (Sex) sex;
			
		}
		
	}
	
	/**
	 * Document Listener for changing the surname of the staff member
	 * @author Administrator
	 *
	 */
	class SurnameListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent evt) {
			staff.setSurname(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staff.setSurname(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staff.setSurname(getText(evt));			
		}
		
		private String getText(DocumentEvent event){
			Document source = event.getDocument();
			int length = source.getLength();
			String txt = "";
			try {
				txt = source.getText(0, length);
			} catch (BadLocationException e) {
				txt = "";
			}
			return txt;
		}
		
	}
	
	/**
	 * Document Listener for changing the firstname of the staff member
	 * @author Administrator
	 *
	 */
	class FirstnameListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent evt) {
			staff.setFirstname(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staff.setFirstname(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staff.setFirstname(getText(evt));			
		}
		
		private String getText(DocumentEvent event){
			Document source = event.getDocument();
			int length = source.getLength();
			String txt = "";
			try {
				txt = source.getText(0, length);
			} catch (BadLocationException e) {
				txt = "";
			}
			return txt;
		}
		
	}
	
	/**
	 * listener when creating the alias of the staff
	 * the alias is a short form of the full staffs name
	 * like 'Dr. Rose' instead of 'Dr. med. Ekkehard Rose'
	 * @author Ekkehard Rose
	 *
	 */
	class AliasListener implements DocumentListener{
		@Override
		public void changedUpdate(DocumentEvent evt) {
			staff.setAlias(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staff.setAlias(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staff.setAlias(getText(evt));			
		}
		
		private String getText(DocumentEvent event){
			Document source = event.getDocument();
			int length = source.getLength();
			String txt = "";
			try {
				txt = source.getText(0, length);
			} catch (BadLocationException e) {
				txt = "";
			}
			return txt;
		}
	}
	
	/**
	 * listener for setting the sex of the staff member depending on its address 
	 * @author Ekkehard Rose
	 *
	 */
	class SexListener implements ItemListener{

		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent evt) {
			JComboBox<Sex> comboSex;
			if(evt.getSource() instanceof JComboBox<?>){
				comboSex = (JComboBox<Sex>) evt.getSource();
				
				try{
					switch ((Sex) comboSex.getModel().getSelectedItem()) {
					case FEMALE:
						staff.setSexCode(1);
						break;				
					case MALE:
						staff.setSexCode(2);
						break;
					case INDIFFERENT:
						staff.setSexCode(0);
						break;
					default:
					staff.setSexCode(9);
					break;
					}	
				}catch(NullPointerException e){
					staff.setSexCode(9);
				}
							
			}			
		}		
	}
	
	/**
	 * renderer for the comboBox that displays the sex
	 * @author Administrator
	 *
	 */
	class SexComboRenderer implements ListCellRenderer<Sex>{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends Sex> list, Sex value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
			        isSelected, cellHasFocus);
			if(value instanceof Sex){
				switch(value){
				case FEMALE:
					renderer.setText("Frau");
					break;
				case MALE:
					renderer.setText("Herr");
					break;
				case INDIFFERENT:
					renderer.setText("Indifferent");
					break;
				case NOT_KNOWN:
					renderer.setText("unbekannt");
					break;
				default:
					renderer.setText("unbekannt");
					break;
				}
			}else{//if value is null
				renderer.setText("");
			}
			return renderer;
		}

		

			
		
	}
	
	
	
	
}
