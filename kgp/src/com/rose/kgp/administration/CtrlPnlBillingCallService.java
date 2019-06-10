package com.rose.kgp.administration;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.MutableComboBoxModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.rose.kgp.db.DB;
import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.personnel.Physician;
import com.rose.kgp.ui.Ctrl_PnlSetDate;


public class CtrlPnlBillingCallService {
		
		// TODO Auto-generated constructor stub
	ArrayList<Physician> physicians;
	PnlBillingCallService pnlBillingCallService;
	PhysicianComboModel physicianComboModel;
	PhysicianRenderer physicianRenderer;
	PhysicianItemListener physicianItemListener;
	SalaryPerCoroListener salaryPerCoroListener;
	SalaryPerPCIListener salaryPerPCIListener; 
	CoroCountListener coroCountListener;
	PCICountListener pciCountListener;
	Ctrl_PnlSetDate ctrlPnlSetDate;
	Physician physicianSel;
	Integer salaryPerCoro = 0, salaryPerPCI = 0;
	Integer coroCount = 0, pciCount = 0;
	CreateBillListener createBillListener;
	MonthComboModel monthComboModel;
	Month monthSel;
	YearComboModel yearComboModel;
	Year yearSel;
	Integer billNrData;
	BillNumberListener billNumberListener;
	
	
	public static void main(String[] args) {
		new CtrlPnlBillingCallService(true);
	}
	
	/**
	 * constructor
	 * @param direct if true, the Panel is embedded in a JDialog
	 */
	public CtrlPnlBillingCallService(Boolean direct) {
		if(direct) {
			connectDB();
			if (DB.getConnection() != null) {
				getActivePhysicians();
				pnlBillingCallService = new PnlBillingCallService();
				setModels();
				setRenderer();
				setListener();				
				ctrlPnlSetDate = new Ctrl_PnlSetDate(pnlBillingCallService.getPnlSetDate(), "dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(10)); 
				ctrlPnlSetDate.getPanel().setLabelDateText("");
				JDialog dialog = new JDialog(); 
				dialog.setVisible(true);	
				dialog.setContentPane(pnlBillingCallService);
				dialog.pack();
			}
		}
	}
	
	
	
	private void setModels() {
		physicianComboModel = new PhysicianComboModel(physicians);
		pnlBillingCallService.getComboPhysician().setModel(physicianComboModel);
		monthComboModel = new MonthComboModel();
		pnlBillingCallService.getComboMonth().setModel(monthComboModel);
		yearComboModel = new YearComboModel();
		pnlBillingCallService.getComboYear().setModel(yearComboModel);
	}
	
	private void setRenderer() {
		physicianRenderer = new PhysicianRenderer();
		pnlBillingCallService.getComboPhysician().setRenderer(physicianRenderer);
	}
	
	private void setListener(){
		physicianItemListener = new PhysicianItemListener();
		pnlBillingCallService.getComboPhysician().addItemListener(physicianItemListener);
		salaryPerCoroListener = new SalaryPerCoroListener();
		pnlBillingCallService.getTxtSalaryPerCoro().getDocument().addDocumentListener(salaryPerCoroListener);
		salaryPerPCIListener = new SalaryPerPCIListener();
		pnlBillingCallService.getTxtSalaryPerPCI().getDocument().addDocumentListener(salaryPerPCIListener);
		coroCountListener = new CoroCountListener();
		pnlBillingCallService.getSpinCoroCount().addChangeListener(coroCountListener);
		pciCountListener = new PCICountListener();
		pnlBillingCallService.getSpinPCICount().addChangeListener(pciCountListener);
		createBillListener = new CreateBillListener();
		pnlBillingCallService.getBtnBillCreate().addActionListener(createBillListener);
		billNumberListener = new BillNumberListener();
		pnlBillingCallService.getTxtBillNumber().getDocument().addDocumentListener(billNumberListener);
	}
	
	
	
	private void getActivePhysicians() {
		physicians = SQL_SELECT.activePhysicians(LocalDate.now());
	}
	
	private void connectDB() {
		if (DB.getConnection() == null) {		
			if(DB.createConnection() != null){			
				 //go further on only if a connection to the database could be established
				if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
					//start the login dialog
					try {
//						Dlg_LogIn dialog = new Dlg_LogIn();
//						dialog.setLocationRelativeTo(null); //show in the center of the screen
//						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						//dialog.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JFrame(),
							    "The Application couldn't be started!", "Fatal Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(new JFrame(),
						    "The Application couldn't be started!", "Fatal Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}else{ //if there couldn't be created a database connection
				//open the Dialog with the database settings
				Dlg_DBSettings dlgDBSettings = new Dlg_DBSettings();
				dlgDBSettings.setLocationRelativeTo(null);
				dlgDBSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dlgDBSettings.setModal(true);
				dlgDBSettings.setVisible(true);	
			}
		}
	}
	
	class PhysicianComboModel implements MutableComboBoxModel<Physician>{
		
		 int index=-1;
		 
		public PhysicianComboModel(ArrayList<Physician> physicians) {
			
		}

		@Override
		public Physician getSelectedItem() {
			if(index >= 0)
	        {
	            return ((Physician)physicians.get(index));
	        }
	        else
	        {
	            return null;
	        }
		}

		@Override
		public void setSelectedItem(Object physician) {
			 for(int i = 0; i< physicians.size(); i++)
		        {
		            if(((Physician)physicians.get(i)).equals(physician))
		                
		            {
		                index = i;
		                break;
		            }
		        }
			
		}

		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}		

		@Override
		public int getSize() {
			return physicians.size();
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}
		

		@Override
		public void removeElement(Object arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeElementAt(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Physician getElementAt(int index) {
			return ((Physician)physicians.get(index));
		}

		@Override
		public void addElement(Physician physician) {
			if(!physicians.contains(physician))
	        { 
	            physicians.add(physician);
	           
	        }
			
		}

		@Override
		public void insertElementAt(Physician item, int index) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class PhysicianRenderer implements ListCellRenderer<Physician>{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		@Override
		public Component getListCellRendererComponent(JList<? extends Physician> list, Physician value, int index,
				boolean isSelected, boolean cellHasFocus) {
			JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
			        isSelected, cellHasFocus);
			// TODO Auto-generated method stub
			if(value instanceof Physician){
				renderer.setText(((Physician)value).getFirstname() + " " + ((Physician)value).getSurname());
				
			}else {
				renderer.setText("");
			}
			return renderer;
		}	
	}
	
	class PhysicianItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<Physician> physicians = (JComboBox<Physician>) event.getSource();
			physicianSel = (Physician) physicians.getSelectedItem();
			BankData bankData = SQL_SELECT.BankAccount(physicianSel);
			physicianSel.setBankData(bankData);
			pnlBillingCallService.getTxtIBAN().setText(bankData.getIban());
			pnlBillingCallService.getTxtBIC().setText(bankData.getBic());
			pnlBillingCallService.getTxtBank().setText(bankData.getInstitute());
		}		
	}
	
	class SalaryPerCoroListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {			
			setText();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			setText();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			setText();
		}
		
		private void setText(){
			try{
				salaryPerCoro = Integer.parseInt(pnlBillingCallService.getTxtSalaryPerCoro().getText());
			}catch(NumberFormatException e){
				salaryPerCoro = 0;
			}			
		}
		
	}
	
	class SalaryPerPCIListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {			
			setText();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			setText();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			setText();
		}
		
		private void setText(){
			try{
				salaryPerPCI = Integer.parseInt(pnlBillingCallService.getTxtSalaryPerPCI().getText());
			}catch(NumberFormatException e){
				salaryPerPCI = 0;
			}			
		}		
	}
	
	class BillNumberListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			setNumber();
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			setNumber();
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			setNumber();
			
		}
		
		private void setNumber(){
			try{
				billNrData = Integer.parseInt(pnlBillingCallService.getTxtBillNumber().getText());
			}catch(NumberFormatException e){
				billNrData = 0;
			}			
		}	
		
	}
	
	class CoroCountListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			coroCount = (Integer) pnlBillingCallService.getSpinCoroCount().getValue();
			pnlBillingCallService.getTxtCoroSalary().setText((coroCount * salaryPerCoro) + "");
		}
		
	}
	
	class PCICountListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			pciCount = (Integer) pnlBillingCallService.getSpinPCICount().getValue();
			
			pnlBillingCallService.getTxtPCISalary().setText((pciCount * salaryPerPCI) + "");
		}		
	}
	
	class YearComboModel extends AbstractListModel<Year> implements ComboBoxModel<Year>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 8929958239293255494L;
		ArrayList<Year> years;
		
		public YearComboModel() {
			years = new ArrayList<Year>();
			for(int i = 0; i<=2; i++){
				years.add(Year.of(((LocalDate.now()).getYear())).plusYears(i-1));
			}
		}
		@Override
		public Year getElementAt(int index) {
			return years.get(index);
		}

		@Override
		public int getSize() {
			return years.size();
		}

		@Override
		public Object getSelectedItem() {
			// TODO Auto-generated method stub
			return yearSel;
		}

		@Override
		public void setSelectedItem(Object year) {
			yearSel = (Year) year;
			
		}
		
	}
	
	class MonthComboModel extends AbstractListModel<Month> implements ComboBoxModel<Month>{
		ArrayList<Month> month;
		
		public MonthComboModel() {
			month = new ArrayList<Month>();
			for (int i = 1; i<=12; i++){				
				month.add(Month.of(i));			
			}
			
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = -4131036391512587009L;

		@Override
		public Month getElementAt(int index) {
			
			return month.get(index);
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return month.size();
		}

		@Override
		public Object getSelectedItem() {
			// TODO Auto-generated method stub
			return monthSel;
		}

		@Override
		public void setSelectedItem(Object month) {
			monthSel = (Month) month;
			
		}
		
		
		
	}
	
	class CreateBillListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String dest = "results/billing/test.pdf";
			File file = new File(dest); //create a new file
		    file.getParentFile().mkdirs();//create a new directory
			PdfDocument pdf = null;
			try {
				pdf = new PdfDocument(new PdfWriter(dest));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	        
	        Document document = new Document(pdf);
	        PdfPage page = pdf.addNewPage();
	        PdfFont font = null;
	        try {
				font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        document.setTextAlignment(TextAlignment.CENTER);
	        Text title_1 = new Text("Kardiologische Gemeinschaftspraxis").setFontSize(16);
	        Text title_2 = new Text("im Klinikum Chemnitz").setFontSize(12);
	        Text title_3 = new Text("Dres. Schubert - Gerner - Jurowsky - Sek - Rose").setFontSize(16);
	        Paragraph title = new Paragraph().setFont(font);
	        title.add(title_1).add("\n").add(title_2).add("\n").add(title_3);
	        
	        document.add(title);
	        
	        Paragraph subtitle = new Paragraph().setFont(font);
	        Text subtitle_1 = new Text("Flemmingstraße 2, 09116 Chemnitz").setFontSize(12);
	        subtitle.setFontSize(12);
	        subtitle.add("\n").add(subtitle_1);
	        
	       
	        document.add(subtitle);
	        
	        Text telfax = new Text("Tel.: 0371/33334090                                                                               Fax: 0371/33334091").setFontSize(12);
	        Paragraph pTelFax = new Paragraph().setFont(font).add(telfax).setFontSize(12);
	        document.add(pTelFax);
	        
	        //add a line
	        
	        SolidLine sline = new SolidLine(1f);
	        sline.setColor(Color.BLACK);
	        LineSeparator ls = new LineSeparator(sline);
	        ls.setWidthPercent(50);
	        ls.setMarginTop(5);
	        document.add(ls);
	       
	        
	        document.setTextAlignment(TextAlignment.LEFT);	
	        
	        Text billReceiver_0 = new Text("Klinikum Chemnitz gGmbH");
	        Text billReceiver_1 = new Text("Krankenhaus Flemmingstraße");
	        Text billReceiver_2 = new Text("Flemmingstraße 2");
	        Text billReceiver_3 = new Text("09116 Chemnitz");
	        Paragraph pBillReceiver = new Paragraph().setFont(font).setFontSize(12);
	        pBillReceiver.add(billReceiver_0).add("\n").add(billReceiver_1).add("\n").add(billReceiver_2).add("\n").add("\n").add(billReceiver_3);
	        document.add(pBillReceiver);
	        
	        PdfFont bold = null;
			try {
				bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Text liq = new Text("Liquidation Bereitschaftsdienst:").setFont(bold);
	        Text physician = new Text(" " + physicianSel.getTitle() + " " + physicianSel.getFirstname() + " " + physicianSel.getSurname()).setFont(bold); 
	        Paragraph pLiq = new Paragraph().add("\n").add(liq).add(physician);
	        document.add(pLiq);
	        
	        Text lt = new Text("Leistungszeit: ");
	        Text ltData = new Text(monthSel.getValue() + "/" + yearSel);
	        
	        Paragraph pMonth = new Paragraph().add("\n\n").add(lt).add(new Tab()).add(ltData).setFixedLeading(5).setFont(font);
	        document.add(pMonth);
	        
	        Text billNr = new Text("Rechnungsnummer: ");
	        Text txtbillNrData = new Text(billNrData + "/" + yearSel);
	        
	        Paragraph pBillNr = new Paragraph().add(billNr).add(new Tab()).add(txtbillNrData).setFixedLeading(5).setFont(font);
	        document.add(pBillNr);
	        
	        Text txtBillDate = new Text("Rechnungsdatum: ");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
	        Text txtBillDateData = new Text(formatter.format(ctrlPnlSetDate.getDate()));
	        
	        Paragraph pBillDate = new Paragraph().add(txtBillDate).add(new Tab()).add(txtBillDateData).setFixedLeading(5).setFont(font).add("\n");
	        document.add(pBillDate);
	        
	        Text txtAL = new Text("Abzurechnende Leistungen").setFont(bold);
	        Paragraph pTxtAL = new Paragraph().add("\n").add(txtAL).setFixedLeading(12).setFont(font);
	        document.add(pTxtAL);
	        
	        //the table
	        Table table = new Table(4);
	        //columnHeader
	        Cell cellColHead_1 = new Cell().add(new Paragraph("Anzahl").setFont(bold));
	        Cell cellColHead_2 = new Cell().add(new Paragraph("Leistung").setFont(bold));
	        Cell cellColHead_3 = new Cell().add(new Paragraph("Preis pro Leistung").setFont(bold));
	        Cell cellColHead_4 = new Cell().add(new Paragraph("Summe").setFont(bold));
	        table.addCell(cellColHead_1).addCell(cellColHead_2).addCell(cellColHead_3).addCell(cellColHead_4);
	        
	        //cells
	        Double flat = 639.11;
	        Double coroFlat = 384.37;
	        Double coroSum = coroCount * coroFlat;
	        Double pciFlat = 440.32;
	        Double pciSum = pciCount * pciFlat;
	        Double coroPciSum = coroSum + pciSum;
	        Double allSum = coroPciSum + flat;
	        table.addCell("1").addCell("Rufbereitschafts-pauschale").addCell(new DecimalFormat("##.##").format(flat)+ "€").addCell(new DecimalFormat("##.##").format(flat)+ "€").setFont(font);
	        table.addCell(Integer.toString(coroCount)).addCell("Koronarangiographie").addCell(new DecimalFormat("##.##").format(coroFlat)+ "€").addCell(new DecimalFormat("##.##").format(coroCount * coroFlat) + "€").setFont(font);
	        table.addCell(Integer.toString(pciCount)).addCell("Koronarangiographie mit PCI").addCell(new DecimalFormat("##.##").format(pciFlat)+ "€").addCell(new DecimalFormat("##.##").format(pciCount * pciFlat) + "€").setFont(font);
	        
	        Cell cellSum_1 = new Cell().add(new Paragraph("Gesamtbetrag").setFont(bold));
	        Cell cellSum_2 = new Cell().add(new Paragraph(new DecimalFormat("##.##").format(allSum)+ "€").setFont(bold));
	        table.addCell("").addCell(cellSum_1).addCell("").addCell(cellSum_2);
	        document.add(table);
	        
	        Text txtFine = new Text("Ich darf Sie höflichst um Anweisung des oben angeführten Gesamtbetrages "
	        		+ "binnen 14 Tagen ab Rechnungsdatum auf mein unten angegebenes Konto ersuchen.");
	        Paragraph pTxtFine = new Paragraph("\n").add(txtFine).setFont(font);
	        document.add(pTxtFine);
	        
	        Text txtBank = new Text("Bank: " + physicianSel.getBankData().getInstitute());
	        Text txtIBAN = new Text("IBAN: " + physicianSel.getBankData().getIban());
	        Text txtBIC = new Text("BIC: " + physicianSel.getBankData().getBic());
	        
	        Paragraph pAccountData = new Paragraph("\n").add(txtBank).setFont(font);
	        Paragraph pIBAN = new Paragraph(txtIBAN).setFont(font);
	        Paragraph pBIC = new Paragraph(txtBIC).setFont(font);
	        document.add(pAccountData).add(pIBAN).add(pBIC);
	        
	        Paragraph pName = new Paragraph("\n" + physicianSel.getTitle() + " " + physicianSel.getFirstname() + " " + physicianSel.getSurname()).setFont(font);
	        document.add(pName);
	        
	        
	        document.close();
	        
	        
	        //Close document
	        pdf.close();
			
		}
		
	}
	
}
