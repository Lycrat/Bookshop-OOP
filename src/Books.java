import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Books implements Comparable<Books>{
	private String isbn;
	private String bookType;
	private String title;
	private String language;
	private String genre;
	private String releaseDate;
	private Double price;
	private int inStock;
	private String info;
	private String info2;
	@Override
	public int compareTo(Books b) {
		
		return price.compareTo(b.price);
	}
	
	public Books(String isbn, String bookType, String title, String language, String genre, String releaseDate, Double price, int inStock, String info, String info2) {
		this.isbn = isbn;
		this.bookType = bookType;
		this.title = title;
		this.language = language;
		this.genre = genre;
		this.inStock = inStock;
		this.info = info;
		this.info2 = info2;
		this.price = price;
		
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			
			Date date = formatter.parse(releaseDate);
			this.releaseDate = formatter.format(date);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getBookType() {
		return bookType;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getInfo() {
		return info;
	}
	
	public String getInfo2() {
		return info2;
	}
	
	public Integer getInStock() {
		return inStock;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public Double getPrice() {
		return price;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getTitle() {
		return title;
	}
	
	public void decStock(int quantity) {
		
		this.inStock -= quantity;
	}
	
	public void returnToBasket() {
		this.inStock += 1;
	}
}

