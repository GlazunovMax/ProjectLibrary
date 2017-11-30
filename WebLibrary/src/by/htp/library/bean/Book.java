package by.htp.library.bean;

import java.util.Arrays;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class Book extends Model {
	private static final long serialVersionUID = 8698812249406955207L;
 
	/** Field - the title of the book */
	private String bookTitle;
	
	/** field - the year of publication */
	private int publicationYear;
	
	/** field - the name of the author */
	private String authorName;
	
	/** field - publisher ID */
	private String publishedById;
	
	/** field - genre ID */
	private String genreId;
	
	/** field - picture */
	private byte[] image;
	
	/** field - content */
	private byte[] content;
	
	
	/** Creates a new object
	 * @see Book#Book(long)
	 */
	public Book() {
		super();
	}
 
	/** Creates a new object with given values
	 * @see Book#Book()
	 * @param id - book id
	 */
	public Book(long id) {
		super(id);
	}

	/**
	 * Function of obtaining the value of the field {@link Book#bookTitle}
	 * @return - returns the name of the book 
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#bookTitle}
	 * @param bookTitle - book title
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#authorName}
	 * @return - returns the name of the author
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#authorName}
	 * @param authorName - author's name
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#publishedById}
	 * @return - returns the publishing ID
	 */
	public String getPublishedById() {
		return publishedById;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#publishedById}
	 * @param publishedById - Publisher ID
	 */
	public void setPublishedById(String publishedById) {
		this.publishedById = publishedById;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#genreId}
	 * @return - returns id of the book's genre
	 */
	public String getGenreId() {
		return genreId;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#genreId}
	 * @param genreId - Book genre ID
	 */
	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#publicationYear}
	 * @return - returns the year of publication of the book
	 */
	public int getPublicationYear() {
		return publicationYear;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#publicationYear}
	 * @param publicationYear - year of publication of the book
	 */
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#bookTitle}
	 * @return - returns the cover of the book
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#image}
	 * @param image - book cover
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#bookTitle}
	 * @return - returns the content of the book
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * Function of obtaining the value of the field {@link Book#content}
	 * @param content - content book
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + Arrays.hashCode(content);
		result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + publicationYear;
		result = prime * result + ((publishedById == null) ? 0 : publishedById.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (bookTitle == null) {
			if (other.bookTitle != null)
				return false;
		} else if (!bookTitle.equals(other.bookTitle))
			return false;
		if (!Arrays.equals(content, other.content))
			return false;
		if (genreId == null) {
			if (other.genreId != null)
				return false;
		} else if (!genreId.equals(other.genreId))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (publicationYear != other.publicationYear)
			return false;
		if (publishedById == null) {
			if (other.publishedById != null)
				return false;
		} else if (!publishedById.equals(other.publishedById))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", publicationYear=" + publicationYear + ", authorName=" + authorName
				+ ", publishedById=" + publishedById + ", genreId=" + genreId + ", image=" + Arrays.toString(image)
				+ ", content=" + Arrays.toString(content) + "]";
	}
	
	
	
}
