package beans;

import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;

import annotations.Sqoop;

@Sqoop
@Stateful
@Dependent
public class SqoopCommand extends Command {
	public SqoopCommand() {
		this.importCommand = "sqoop import";
		this.finalCommand = "";
		
		this.availableParams.add("split-by");
		this.availableParams.add("target-dir");
		this.availableParams.add("hive-table");
		this.availableParams.add("hive-import");
		this.availableParams.add("hive-overwrite");
		
		
		this.mandatoryParams.put("username", "username");
		this.mandatoryParams.put("password", "passwd");
		this.mandatoryParams.put("connectionUrl", "jdbc:db://hostname:port");
		this.mandatoryParams.put("table", "tableName");
	}
	
}
