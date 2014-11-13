package sqoop;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SqoopCommand implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String finalCommand;
	protected List<String> availableParams;
	
	protected String newParam, newValue;
	
	protected String importCommand = "sqoop import";
	protected Map<String, String> mandatoryParams;
	protected Map<String, String> optionalParams;
	
	public SqoopCommand() {
		this.availableParams = new LinkedList<>();
		this.availableParams.add("split-by");
		this.availableParams.add("target-dir");
		this.availableParams.add("table");
		Collections.sort(this.availableParams);

		this.optionalParams = new HashMap<>();
		
		this.newParam = "Param";
		this.newValue = "Value";
		
		this.mandatoryParams = new HashMap<>();
		this.mandatoryParams.put("username", "username");
		this.mandatoryParams.put("password", "passwd");
		this.mandatoryParams.put("connectionUrl", "jdbc:db://hostname:port");
	}
	
	public void addParam() {
		System.out.println("Adding "+newParam+" -> "+newValue);
		this.optionalParams.put(newParam, newValue);
		this.availableParams.remove(newParam);
		this.newParam = "Param";
		this.newValue = "Value";
		this.optionalParams.entrySet().forEach(x -> System.out.println(x.getKey() +" -> "+ x.getValue()));
	}
	
	public List<Entry<String, String>> getParamList() {
		return new LinkedList<Entry<String, String>>( this.getAllParams().entrySet() );
	}
	
	public void generate() {
		this.getAllParams().entrySet().forEach(x -> System.out.println( x.getKey()+" -> "+x.getValue() ) );
		
		finalCommand = 
				importCommand +
				" --username "+ this.mandatoryParams.get("username") +
				" --password "+ this.mandatoryParams.get("password") +
				" --connect " + this.mandatoryParams.get("connectionUrl")
		;
	}
	
	public void clear() {
		this.finalCommand = "";
	}
	
	public Map<String, String> getAllParams() {
		Map<String, String> res = new HashMap<>(this.mandatoryParams);
		res.putAll(this.optionalParams);
		return res;
	}
	
	public Map<String, String> getMandatoryParams() {
		return mandatoryParams;
	}
	public void setMandatoryParams(Map<String, String> mandatoryParams) {
		this.mandatoryParams = mandatoryParams;
	}
	public Map<String, String> getOptionalParams() {
		return optionalParams;
	}
	public void setOptionalParams(Map<String, String> optionalParams) {
		this.optionalParams = optionalParams;
	}
	public String getNewParam() {
		return newParam;
	}
	public void setNewParam(String newParam) {
		this.newParam = newParam;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public List<String> getAvailableParams() {
		return availableParams;
	}
	public void setAvailableParams(List<String> availableParams) {
		this.availableParams = availableParams;
	}
	public String getFinalCommand() {
		return finalCommand;
	}
	public void setFinalCommand(String finalCommand) {
		this.finalCommand = finalCommand;
	}
}
