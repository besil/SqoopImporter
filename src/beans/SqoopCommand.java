package beans;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;

import annotations.Sqoop;

@Sqoop
@Stateful
@Dependent
public class SqoopCommand {
	protected String finalCommand;
	protected final String importCommand = "sqoop import";
	protected List<String> availableParams;
	protected Map<String, String> optionalParams, mandatoryParams;
	
	public SqoopCommand() {
		this.finalCommand = "";
		
		this.availableParams = new LinkedList<>();
		this.availableParams.add("split-by");
		this.availableParams.add("target-dir");
		this.availableParams.add("hive-table");
		this.availableParams.add("hive-import");
		this.availableParams.add("hive-overwrite");
		
		this.optionalParams = new HashMap<>();
		
		this.mandatoryParams = new HashMap<>();
		this.mandatoryParams.put("username", "username");
		this.mandatoryParams.put("password", "passwd");
		this.mandatoryParams.put("connectionUrl", "jdbc:db://hostname:port");
		this.mandatoryParams.put("table", "tableName");
	}
	
	public List<String> getAvailableParams() {
		return availableParams;
	}
	
	public void addParam(String newParam, String newValue) {
		this.optionalParams.put(newParam, newValue);
		this.availableParams.remove(newParam);
	}

	public Map<String, String> getAllParams() {
		Map<String, String> res = new HashMap<>(this.mandatoryParams);
		res.putAll(this.optionalParams);
		return res;
	}

	public void removeParam(Entry<String, String> entry) {
		this.availableParams.add(entry.getKey());
		this.optionalParams.remove(entry.getKey());
	}

	public boolean isOptional(String key) {
		return ! this.mandatoryParams.containsKey(key);
	}

	public void clear() {
		this.finalCommand = "";
	}

	public void generateCommand() {
		this.finalCommand = importCommand + " "+
				this.getAllParams().entrySet().stream()
				.map(entry -> "--"+entry.getKey()+" "+entry.getValue()).collect(Collectors.joining(" "));
	}
	
	public String getFinalCommand() {
		return finalCommand;
	}
	
	public Map<String, String> getMandatoryParams() {
		return mandatoryParams;
	}
	
}
