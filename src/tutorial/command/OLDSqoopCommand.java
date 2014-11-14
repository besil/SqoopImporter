package tutorial.command;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;

import annotations.Sqoop;

@Sqoop
@Stateful
@Dependent
public class OLDSqoopCommand extends Command {
	protected final String importCommand = "sqoop import";
	
	public OLDSqoopCommand() {
		super();
		this.availableParams.add("split-by");
		this.availableParams.add("target-dir");
		this.availableParams.add("hive-table");
		this.availableParams.add("hive-import");
		this.availableParams.add("hive-overwrite");
		
		Collections.sort(this.availableParams);

		this.newParam = "Param";
		this.newValue = "Val";

		this.mandatoryParams.put("username", "username");
		this.mandatoryParams.put("password", "passwd");
		this.mandatoryParams.put("connectionUrl", "jdbc:db://hostname:port");
		this.mandatoryParams.put("table", "tableName");
	}
	
	@Override
	public String buildCommand() {
		return importCommand + " "+
				this.getAllParams().entrySet().stream()
				.map(entry -> "--"+entry.getKey()+" "+entry.getValue()).collect(Collectors.joining(" "));
	}

}
