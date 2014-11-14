package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import annotations.Sqoop;
import beans.SqoopCommand;

@Named
@SessionScoped
public class SqoopController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject @Sqoop protected SqoopCommand sqoop;
	
	public List<String> getAvailableParams() {
		return sqoop.getAvailableParams();
	}
	
	public void addParam(String key, String value) {
		this.sqoop.addParam(key, value);
	}
	
	public List<Entry<String, String>> getParamList() {
		LinkedList<Entry<String, String>> l = new LinkedList<Entry<String, String>>( sqoop.getAllParams().entrySet() );
		Collections.sort(l, new Comparator<Entry<String, String>>() {
			@Override
			public int compare(Entry<String, String> e1, Entry<String, String> e2) {
				return e1.getKey().compareTo(e2.getKey());
			}
		});
		return l;
	}
	
	public void removeParam(Entry<String, String> entry) {
		sqoop.removeParam(entry);
	}
	
	public boolean isOptional(String key) {
		return sqoop.isOptional(key);
	}
	
	public void clear() {
		sqoop.clear();
	}
	
	public void generate() {
		sqoop.generateCommand();
	}
	
	public StreamedContent download() throws IOException {
		String script = "#!/bin/sh\n\n"+sqoop.getFinalCommand();
		InputStream is = new ByteArrayInputStream(script.getBytes());
		return new DefaultStreamedContent(is, "plain/text", "sqoopImport.sh");
	}
	
	public String getFinalCommand() {
		return sqoop.getFinalCommand();
	}
	
	public Map<String, String> getMandatoryParams() {
		return sqoop.getMandatoryParams();
	}
}
