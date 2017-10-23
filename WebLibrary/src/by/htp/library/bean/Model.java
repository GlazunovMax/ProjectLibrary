package by.htp.library.bean;

import java.io.Serializable;

public class Model implements Serializable{

	
	private static final long serialVersionUID = -2263162145391411926L;
	
	
	private long id;
	
	public Model(){
		
	}

	public Model(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
