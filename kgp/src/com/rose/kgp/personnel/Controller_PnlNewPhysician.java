package com.rose.kgp.personnel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Observable;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.ui.Controller_PnlSetDate;

public class Controller_PnlNewPhysician extends Controller_PnlNewStaff{

	
	private TitleListener titleListener;
	
	private SetNewPhysicianListener setNewPhysicianListener;
	
	public Controller_PnlNewPhysician(Controller_PnlSetDate conPnlSetBirthDate, Controller_PnlSetDate conPnlSetOnsetDate) {
		super(conPnlSetBirthDate, conPnlSetOnsetDate, new Pnl_NewPhysician());
		//pnlNewStaff = new Pnl_NewPhysician();
		pnlNewStaff.add(this.conPnlSetBirthDate.getPanel(), "cell 1 5,growx,aligny top");
		pnlNewStaff.add(this.conPnlSetOnsetDate.getPanel(), "cell 1 6,growx,aligny top");		
		TitleModel titleModel = new TitleModel();
		((Pnl_NewPhysician)pnlNewStaff).getComboTitle().setModel(titleModel);
		
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().setEnabled(false);
		
		initializeListener();
		setListener();
	}
	
	/**
	 * initializes all listeners of input fields of that panel that are not initialized by the super class
	 */
	
	private void initializeListener(){		
		titleListener = new TitleListener();		
		setNewPhysicianListener = new SetNewPhysicianListener();
	}
	
	/**
	 * add all listeners of input fields of that panel
	 */
	private void setListener(){				
		((Pnl_NewPhysician) pnlNewStaff).addTitleListener(titleListener);		
		pnlNewStaff.addSetNewStaffListener(setNewPhysicianListener);
	}
	
	/**
	 * removes all listeners of input fields of that panel
	 */
	@Override
	protected void removeListener(){
		super.removeListener();
		((Pnl_NewPhysician) pnlNewStaff).removeTitleListener(titleListener);
		pnlNewStaff.removeSetNewStaffListener(setNewPhysicianListener);
	}
	
	
	
	
	
	/**
	 * prepares the panel before a new physician is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewPhysician(){
		super.prepareForNewStaff();
		
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().setEnabled(true);
		
		//when activating the dateSetPnl: set the Date of the DateSetPanel to a specific date
		
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().setSelectedItem(null);
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().repaint();
		staff = new Physician("");
	}
	
	
	/**
	 * model for the comboBox that shows the titles of a new physician
	 * @author Ekkehard Rose
	 *
	 */
	class TitleModel extends AbstractListModel<String> implements ComboBoxModel<String>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -5723909430414256587L;
		String[] titles = {"Dr.", "Dr. med.", "Prof."};
		String selection = null;
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub			
		}

		@Override
		public String getElementAt(int index) {
			return titles[index];
		}

		@Override
		public int getSize() {
			return titles.length;
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
		public void setSelectedItem(Object title) {
			selection= (String) title;
			
		}
		
	}
	
	
	
	
	class TitleListener implements ItemListener{

		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent evt) {
			JComboBox<String> comboTitle;
			if(evt.getSource() instanceof JComboBox<?>){
				comboTitle = (JComboBox<String>) evt.getSource();
				((Physician) staff).setTitle((String) comboTitle.getModel().getSelectedItem());
			}			
		}		
	}
	
	
	
	
	
	class SetNewPhysicianListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {	
			
			if(staff.getId() == null){//if a new physician has to be added
				staff.setBirthday(conPnlSetBirthDate.getDate());
				staff.setOnset(conPnlSetOnsetDate.getDate());
				//insert into database
				Integer id = SQL_INSERT.Physician((Physician)staff, LocalDate.now());
					if(id != null){
						staff.setId(id);
						setChanged();
						notifyObservers(staff);//notify the Controller of the Dialog 'Controller_DlgPhysician'
					}
					
					removeListener(); //remove all listeners
					//empty all input fields
					pnlNewStaff.getTxtSurname().setText("");
					pnlNewStaff.getTxtFirstname().setText("");
					pnlNewStaff.getTxtAlias().setText("");
					pnlNewStaff.getComboSex().setSelectedIndex(-1);
					pnlNewStaff.getComboSex().repaint();
					((Pnl_NewPhysician) pnlNewStaff).getComboTitle().setSelectedIndex(-1);
					((Pnl_NewPhysician) pnlNewStaff).getComboTitle().repaint();
					//add all listeners to the input fields
					setListener();
				
			}
		}		
	}
	
	
	
	
	
	
	
	
}
