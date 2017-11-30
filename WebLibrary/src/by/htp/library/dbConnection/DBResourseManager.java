package by.htp.library.dbConnection;

import java.util.ResourceBundle;

/** The class manages a collection of resources
 * 
 * @author Glazunov Maxim
 * @version 1.0
 *
 */
public class DBResourseManager {

	private static final String DB = "db";
	
	private final static DBResourseManager instance = new DBResourseManager();
	private ResourceBundle bundle = ResourceBundle.getBundle(DB);

	public static DBResourseManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}

}
