package com.rose.kgp.personnel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;


import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.event.ListDataListener;


import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.ui.Ctrl_PnlSetDate;

public class Ctrl_PnlNewPhysician extends Ctrl_PnlNewStaff{

	
	private TitleListener titleListener;
	
	
	
	public Ctrl_PnlNewPhysician(Ctrl_PnlSetDate ctrlPnlSetBirthDate, Ctrl_PnlSetDate ctrlPnlSetOnsetDate) {
		super(ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate, new Pnl_NewPhysician());
		//pnlNewStaff = new Pnl_NewPhysician();
		pnlNewStaff.add(this.ctrlPnlSetBirthDate.getPanel(), "cell 1 5,growx,aligny top");
		pnlNewStaff.add(this.ctrlPnlSetOnsetDate.getPanel(), "cell 1 6,growx,aligny top");		
		TitleModel titleModel = new TitleModel();
		((Pnl_NewPhysician)pnlNewStaff).getComboTitle().setModel(titleModel);
		
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().setEnabled(false);
		
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
		((Pnl_NewPhysician) pnlNewStaff).addTitleListener(titleListener);		
		
	}
	
	/**
	 * removes all listeners of input fields of that panel
	 */
	@Override
	protected void removeListener(){
		super.removeListener();
		((Pnl_NewPhysician) pnlNewStaff).removeTitleListener(titleListener);
		
	}
	
	
	
	
	
	
	
	/**
	 * prepares the panel before a new physician is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewPhysician(){
		super.prepareForNewStaff(new Physician(""));
		
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().setEnabled(true);		
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().setSelectedItem(null);
		((Pnl_NewPhysician) pnlNewStaff).getComboTitle().repaint();
		
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
