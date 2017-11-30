package by.htp.library.bean;

/**
 * @author Glazunov Maxim
 * @version 1.0
 */
public class PublishedBy extends Model {
	private static final long serialVersionUID = -9021299000655047750L;

	/** Field - the name of the publishing house*/
	private String publishedByTitle;

	/** Creates a new object
	 * @see PublishedBy#PublishedBy(long)
	*/
	public PublishedBy() {
		super();
	}

	/** Creates a new object with given values
	 * @see PublishedBy#PublishedBy() 
	 * @param id - id of the book publishing house
	*/
	public PublishedBy(long id) {
		super(id);
	}

	/**
	 * Function of obtaining the value of the field {@link PublishedBy#publishedByTitle}
	 * @return  Returns the genre of the book
	*/
	public String getPublishedByTitle() {
		return publishedByTitle;
	}

	/**
	 * Function of obtaining the value of the field {@link PublishedBy#publishedByTitle}
	 * Функция получения значения поля
	 * @param publishedByTitle - book genre
	*/
	public void setPublishedByTitle(String publishedByTitle) {
		this.publishedByTitle = publishedByTitle;
	}

	
}
