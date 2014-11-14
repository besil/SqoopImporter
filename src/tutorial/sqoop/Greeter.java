package tutorial.sqoop;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class Greeter extends IGreeter {
	public String greet(String name) {
		return "Hi "+name+"!";
	}
}
