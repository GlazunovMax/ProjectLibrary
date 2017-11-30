package by.htp.library.bean;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0 
 * 
 */
public class Author extends Model {

	private static final long serialVersionUID = -4359837335267921478L;

	/** Field - the name of the author */
	private String authorName;

	/** Creates a new object
	 * @see Author#Author(long)
	 */
	public Author() {
		super();
	}

	/** Creates a new object with given values
	 * @param id - author id
	 * @see Author#Author()
	 */
	public Author(long id) {
		super(id);
	}

	/**
	 * Function of obtaining the value of the field {@link Author#authorName}
	 * @return Returns the name of the author
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Function of obtaining the value of the field {@link Author#authorName}
	 * @param authorName - author's name
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
