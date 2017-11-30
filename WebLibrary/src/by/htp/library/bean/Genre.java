package by.htp.library.bean;

/**
 * @author Glazunov Maxim
 * @version 1.0
 */
public class Genre extends Model {
	private static final long serialVersionUID = 1666762499577808492L;

	/** Field - genre */
	private String genreTitle;

	/** Creates a new object
	 * @see Genre#Genre(long)
	*/
	public Genre() {
		super();
	}

	/** Creates a new object with given values
	 * @see Genre#Genre() 
	 * @param id - Book genre ID
	*/
	public Genre(long id) {
		super(id);
	}

	/**
	 * Function of obtaining the value of the field {@link Genre#genreTitle}
	 * @return Returns the genre of the book 
	*/
	public String getGenreTitle() {
		return genreTitle;
	}

	/**
	 * Function of obtaining the value of the field {@link Genre#genreTitle}
	 * @param genreTitle - book genre
	*/
	public void setGenreTitle(String genreTitle) {
		this.genreTitle = genreTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreTitle == null) ? 0 : genreTitle.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return genreTitle;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (genreTitle == null) {
			if (other.genreTitle != null)
				return false;
		} else if (!genreTitle.equals(other.genreTitle))
			return false;
		return true;
	}
}
