package by.htp.library.bean;

public class PublishedBy extends Model {
	private static final long serialVersionUID = -9021299000655047750L;

	private String publishedByTitle;

	public PublishedBy() {
		super();
	}

	public PublishedBy(long id) {
		super(id);
	}

	public String getPublishedByTitle() {
		return publishedByTitle;
	}

	public void setPublishedByTitle(String publishedByTitle) {
		this.publishedByTitle = publishedByTitle;
	}

	
}
