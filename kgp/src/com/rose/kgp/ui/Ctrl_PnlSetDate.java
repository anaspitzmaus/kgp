package com.rose.kgp.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.rose.kgp.useful.DateMethods;


public class Ctrl_PnlSetDate {
	protected Pnl_SetDate pnlSetDate;
	DateChangeListener dateChangeListener;
	LocalDate minDate, dateSet;
	
	public Ctrl_PnlSetDate(String dateFormat, LocalDate ld, LocalDate minDate){
		pnlSetDate = new Pnl_SetDate(dateFormat, ld, minDate);
		pnlSetDate.addCalendarListener(new CalendarListener(minDate));
		dateChangeListener = new DateChangeListener();
		pnlSetDate.addDateChangeListener(dateChangeListener);//listener for changing the date
		dateSet = ld;
	}
	
//	public Controller_PnlSetDate(DefaultFormatterFactory factory){
//		pnlSetDate = new Pnl_SetDate(factory);
//		pnlSetDate.addCalendarListener(new CalendarListener());
//		pnlSetDate.addDateChangeListener(new DateChangeListener());
//		pnlSetDate.date = useful.DateMethods.ConvertDateToLocalDate((Date) pnlSetDate.getFtxtCalendar().getValue()); //set the initial value of the variable 'date'
//	}
	
	public DateChangeListener getDateChangeListener(){
		return this.dateChangeListener; 
	}
	
	public Pnl_SetDate getPanel(){
		return this.pnlSetDate;		
	}
	
	public LocalDate getDate(){
		return dateSet;
	}	
	
	public void setDate(LocalDate date){
		Date seldate = DateMethods.ConvertLocalDateToDate(date);		
		pnlSetDate.getFtxtCalendar().setValue(seldate); //set the selected date to the formattedTextField
	}
	
	public void setPnlEnabled(Boolean en){
		pnlSetDate.getFtxtCalendar().setEnabled(en);
		pnlSetDate.getLblCalendar().setEnabled(en);
	}
	
	
	class CalendarListener implements MouseListener{
		Dlg_Calendar dlgCal;
		LocalDate minDate;
		
		public CalendarListener(LocalDate minDate) {
			this.minDate = minDate;
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			dlgCal = new Dlg_Calendar(pnlSetDate.getFtxtCalendar(), DateMethods.ConvertLocalDateToDate(this.minDate));			
			
			dlgCal.setLocationRelativeTo(pnlSetDate.getFtxtCalendar());
			dlgCal.setVisible(true);
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * listener class for changing the date of the formatted JTextField
	 * @author Ekki
	 *
	 */
	class DateChangeListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			dateSet = DateMethods.ConvertDateToLocalDate((Date)pnlSetDate.getFtxtCalendar().getValue());
			
		}
		
	}
}
