package by.htp.library.bean;

public class Author extends Model {

	private static final long serialVersionUID = -4359837335267921478L;

	private String authorName;

	public Author() {
		super();
	}

	public Author(long id) {
		super(id);
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
