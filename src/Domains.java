
public enum Domains {
	hotmailcom("@hotmail.com"), gmailcom("@gmail.com"), hotmailcouk("@hotmail.co.uk"), gmailcouk("@gmail.co.uk");
	
	private String displayName;
	
	Domains(String displayName){
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		return this.displayName;
	}
	
}
