package by.htp.library.bean;

public class Book extends Model {
	private static final long serialVersionUID = 8698812249406955207L;

	private String bookTitle;
	private int publicationYear;
	private String authorName;
	private String publishedById;
	private String genreId;
	private byte[] image;
	private byte[] content;
	private String img;

	public Book() {
		super();
	}

	public Book(long id) {
		super(id);
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublishedById() {
		return publishedById;
	}

	public void setPublishedById(String publishedById) {
		this.publishedById = publishedById;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", publicationYear=" + publicationYear + ", authorName=" + authorName
				+ ", publishedById=" + publishedById + ", genreId=" + genreId + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
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
		if (genreId == null) {
			if (other.genreId != null)
				return false;
		} else if (!genreId.equals(other.genreId))
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

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
