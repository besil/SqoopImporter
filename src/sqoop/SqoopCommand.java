package sqoop;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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

	public List<Entry<String, String>> getParamList() {
		return new LinkedList<Entry<String, String>>( this.getAllParams().entrySet() );
	}

	protected String buildCommand() {
		return importCommand + " "+
				this.getAllParams().entrySet().stream()
				.map(entry -> "--"+entry.getKey()+" "+entry.getValue()).collect(Collectors.joining(" "));
	}

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

	public StreamedContent download() throws IOException {
		String script = "#!/bin/sh\n\n"+this.buildCommand()+"\n";
		InputStream is = new ByteArrayInputStream(script.getBytes());
		return new DefaultStreamedContent(is, "plain/text", "sqoopImport.sh");
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
