package by.htp.library.bean;

public class Genre extends Model {
	

	private static final long serialVersionUID = 1666762499577808492L;

	private String genreTitle;

	public Genre() {
		super();
	}

	public Genre(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getGenreTitle() {
		return genreTitle;
	}

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
