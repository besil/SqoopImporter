package tutorial.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import annotations.Sqoop;
import tutorial.command.Command;

@Named(value="oldController")
@SessionScoped
public class OLDSqoopController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject @Sqoop protected Command sqoop;
	
	public void removeParam(Entry<String, String> entry) {
		sqoop.removeParam(entry);
	}

	public void addParam() {
		sqoop.addParam();
	}
	
	public StreamedContent download() throws IOException {
		String script = "#!/bin/sh\n\n"+sqoop.buildCommand();
		InputStream is = new ByteArrayInputStream(script.getBytes());
		return new DefaultStreamedContent(is, "plain/text", "sqoopImport.sh");
	}
	
	public void generate() {
		sqoop.generate();
	}
	
	public void clear() {
		sqoop.clear();
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
	
	public Command getSqoop() {
		return sqoop;
	}
	public void setSqoop(Command sqoop) {
		this.sqoop = sqoop;
	}
}
