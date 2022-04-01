public enum BookType {
	paperback("paperback"), audiobook("audiobook"), ebook("ebook");
	
	private String displayName;
	
	BookType(String displayName){
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		return this.displayName;
	}
}