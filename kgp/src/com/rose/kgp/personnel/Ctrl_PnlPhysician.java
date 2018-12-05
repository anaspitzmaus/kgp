package com.rose.kgp.personnel;



import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

import com.rose.kgp.ui.Ctrl_PnlSetDate;
import com.rose.kgp.ui.Pnl_SetDate;

public class Ctrl_PnlPhysician extends Ctrl_PnlStaff{

	
	private TitleListener titleListener;
	
	private Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	
	
	protected Ctrl_PnlSetDate getCtrlPnlSetBirthDate() {
		return ctrlPnlSetBirthDate;
	}

	protected Ctrl_PnlSetDate getCtrlPnlSetOnsetDate() {
		return ctrlPnlSetOnsetDate;
	}
	
	public Ctrl_PnlPhysician(Ctrl_PnlSetDate ctrlPnlSetBirthDate, Ctrl_PnlSetDate ctrlPnlSetOnsetDate) {		
		
		this.panel = new Pnl_Physician();
		this.ctrlPnlSetBirthDate = ctrlPnlSetBirthDate;
		this.ctrlPnlSetOnsetDate = ctrlPnlSetOnsetDate;
		//add the date panels to the basic panel
		addPnlSetOnsetDate(this.ctrlPnlSetOnsetDate.getPanel());
		addPnlSetBirthDate(this.ctrlPnlSetBirthDate.getPanel());		
		TitleModel titleModel = new TitleModel();
		((Pnl_Physician)panel).getComboTitle().setModel(titleModel);		
		((Pnl_Physician) panel).getComboTitle().setEnabled(false);
		
		initializeExtraListener();
		setExtraListener();
	}
	
	/**
	 * initializes all listeners of input fields of that panel that are not initialized by the super class
	 */
	
	private void initializeExtraListener(){		
		titleListener = new TitleListener();		
		
	}
	
	/**
	 * add all listeners of input fields of that panel
	 */
	private void setExtraListener(){				
		((Pnl_Physician) panel).addTitleListener(titleListener);		
		
	}
	
	/**
	 * removes all listeners of input fields of that panel
	 */
	@Override
	protected void removeListener(){
		super.removeListener();
		((Pnl_Physician) panel).removeTitleListener(titleListener);
		
	}
	
	
	
	
	
	
	
	/**
	 * prepares the panel before a new physician is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewPhysician(){
		super.prepareForNewStaff(new Physician(""));
		
		((Pnl_Physician) panel).getComboTitle().setEnabled(true);		
		((Pnl_Physician) panel).getComboTitle().setSelectedItem(null);
		((Pnl_Physician) panel).getComboTitle().repaint();
		
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
	
	
	
	
	
	
	
	
	
	
	
}
