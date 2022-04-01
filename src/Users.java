import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Users{
	private String username;
	private int userID;
	private String surname;
	private int houseNumber;
	private String postCode;
	private String city;
	private String role;
	private List<Books> basket = new ArrayList<>();
	
	public Users(String username, int userID, String surname, int houseNumber, String postCode, String city, String role) {
		this.username = username;
		this.userID = userID;
		this.surname = surname;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
		this.role = role;
		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void addBooks(Books book) {
		this.basket.add(book);
	}
	
	public void emptyBasket() {
		this.basket.clear();
	}
	public String getRole() {
		return role;
	}
	public List<Books> getBasket() {
		Collections.sort(basket);;
		return basket;
	}
}
