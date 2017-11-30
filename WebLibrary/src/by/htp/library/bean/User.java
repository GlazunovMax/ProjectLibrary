package by.htp.library.bean;

/**
 * 
 * @author Glazunov Maxim
 * @version 1.0
 * 
 */
public class User extends Model {
	private static final long serialVersionUID = -4819220118118128236L;

	/** Field - user name */
	private String name;
	
	/** Field - last name of the user */
	private String surname;
	
	/** Field - login */
	private String login;
	
	/** Password field */
	private String password;
	
	/** Field - User role */
	private String role;

	/** Creates a new object
	 * @see User#User(long)
	*/
	public User() {
		super();
	}

	/** Creates a new object with given values
	 * @see User#User() 
	 * @param id - ID user
	*/
	public User(long id) {
		super(id);
	}

	/**
	 * Function of obtaining the value of the field {@link User#name}
	 * @return Returns the user name 
	*/
	public String getName() {
		return name;
	}

	/**
	 * Function of obtaining the value of the field {@link User#name}
	 * @param name - Username
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Function of obtaining the value of the field {@link User#surname}
	 * @return Returns the user's last name
	*/
	public String getSurname() {
		return surname;
	}

	/**
	 * Function of obtaining the value of the field {@link User#surname}
	 * @param surname - surname of the user
	*/
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Function of obtaining the value of the field {@link User#login}
	 * @return Returns the user's login
	*/
	public String getLogin() {
		return login;
	}

	/**
	 * Function of obtaining the value of the field {@link User#login}
	 * @param login - user login
	*/
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Function of obtaining the value of the field {@link User#password}
	 * @return returns user password
	*/
	public String getPassword() {
		return password;
	}

	/**
	 * Function of obtaining the value of the field {@link User#password}
	 * @param password - user password
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Function of obtaining the value of the field {@link User#role}
	 * @return Returns the user role
	*/
	public String getRole() {
		return role;
	}

	/**
	 * Function of obtaining the value of the field {@link User#role}
	 * @param role - user role
	*/
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", login=" + login + ", password=" + password + ", role="
				+ role + "]";
	}

}
