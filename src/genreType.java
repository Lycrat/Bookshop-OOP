public enum genreType {
	All_Genre("All Genre"), Biography("Biography"), Computer_Science("Computer Science"), Business("Business"), Politics("Politics"), Medicine("Medicine");
	
	private String displayName;
	
	genreType(String displayName){
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		return this.displayName;
	}
}
