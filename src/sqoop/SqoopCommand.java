package sqoop;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SqoopCommand implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String importCommand = "sqoop import";
	protected String username="username", password="pass", 
			connectionUrl = "jdbc:db://hostname:port";
	protected String finalCommand;
	
	public void generate() {
		finalCommand = 
				importCommand +
				" --username "+ username +
				" --password "+ password +
				" --connect " + connectionUrl
		;
	}
	
	public void clear() {
//		for( Field f : this.getClass().getDeclaredFields() ) {
//			if( String.class.isAssignableFrom(f.getType()) ) {
//				try {
//					f.set(this, "");
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		this.finalCommand = "";
	}
	
	public String getFinalCommand() {
		return finalCommand;
	}
	public void setFinalCommand(String finalCommand) {
		this.finalCommand = finalCommand;
	}
	public String getImportCommand() {
		return importCommand;
	}
	public void setImportCommand(String importCommand) {
		this.importCommand = importCommand;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

}
