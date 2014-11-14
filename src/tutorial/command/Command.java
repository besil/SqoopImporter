package tutorial.command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Command {
	protected String finalCommand;
	protected List<String> availableParams;
	
	protected String newParam, newValue;

	protected Map<String, String> mandatoryParams;
	protected Map<String, String> optionalParams;
	
	public Command() {
		this.availableParams = new LinkedList<>();
		this.mandatoryParams = new HashMap<>();
		this.optionalParams = new HashMap<>();
	}
	
	public void removeParam(Entry<String, String> entry) {
		this.availableParams.add(entry.getKey());
		this.optionalParams.remove(entry.getKey());
	}

	public void addParam() {
		this.optionalParams.put(newParam, newValue);
		this.availableParams.remove(newParam);
		this.newParam = "Param";
		this.newValue = "Value";
	}
	
	public abstract String buildCommand();

	public void generate() {
		finalCommand = this.buildCommand();
	}

	public void clear() {
		this.finalCommand = "";
	}

	public Map<String, String> getAllParams() {
		Map<String, String> res = new HashMap<>(this.mandatoryParams);
		res.putAll(this.optionalParams);
		return res;
	}
	
	/*
	 * Getter and setter
	 */
	public String getFinalCommand() {
		return finalCommand;
	}
	public void setFinalCommand(String finalCommand) {
		this.finalCommand = finalCommand;
	}
	public List<String> getAvailableParams() {
		return availableParams;
	}
	public void setAvailableParams(List<String> availableParams) {
		this.availableParams = availableParams;
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
	
	
}
