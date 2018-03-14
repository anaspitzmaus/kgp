package personnel;

public class Physician extends Person {

	protected String title;
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Physician(String surname, String firstname) {
		super(surname, firstname);		
	}

}
