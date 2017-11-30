package by.htp.library.bean;

import java.io.Serializable;

/**
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class Model implements Serializable{
	private static final long serialVersionUID = -2263162145391411926L;
	
	/** Поле id */
	private long id;
	
	/** Creates a new object
	 * @see Model#Model(long)
	*/
	public Model(){
	}

	/** Creates a new object with given values
	 * @see Model#Model() 
	 * @param id - ID 
	*/
	public Model(long id) {
		this.id = id;
	}

	/**
	 * Function of obtaining the value of the field {@link Model#id}
	 * @return returns id 
	*/
	public long getId() {
		return id;
	}

	/**
	 * Function of obtaining the value of the field {@link Model#id}
	 * @param id - id
	*/
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Model other = (Model) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
