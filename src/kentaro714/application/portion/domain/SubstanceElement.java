package kentaro714.application.portion.domain;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import kentaro714.application.portion.ProductionPlan;

public class SubstanceElement {
	
	private Date lastModified = new Date(0L);
	public boolean modifiedSince(Date arg) {
		return lastModified.after(arg);
	}

	private SubstanceElementData data;
	public int getFastestTime() {
		return data.getFastestTime();
	}
	
	private String name;
	public String getName() {
		return name;
	}
	
	private ProductionPlan plan;
	
	public SubstanceElement(String name, ProductionPlan plan) {
		this.name = name;
		this.plan = plan;
	}
	
	private List<SubstanceElement> inputs = Lists.newArrayList();
	public void addInputs(List<SubstanceElement> args) {
		inputs.addAll(args);
	}
	
	private TimeCalculator productionTime = new ConstantTimeCalculator(0);
	public TimeCalculator getProductionTime() {
		return productionTime;
	}
	
	public void setProductionTime(TimeCalculator calculator) {
		this.productionTime = calculator;
	}

	public void invoke() {
		log("Invoking.");
		for (SubstanceElement element : inputs) {
			element.invoke();
		}
		if (isOutOfDate()) {
			execute();
		}
	}

	private boolean isOutOfDate() {
		if (wasNeverCalculated()) {
			return true;
		}
//		return Prerequisites.any..;
		return false;
	}

	private boolean wasNeverCalculated() {
		return null == data;
	}

	protected void execute() {
		log("execute");
		data = new SubstanceElementData();
		updateFastestTime();
		lastModified = new Date();
	}

	private void updateFastestTime() {
		int prerequisiteTime = 0;
		if (0 != inputs.size()) {
			for (SubstanceElement element : inputs) {
				if (element.getFastestTime() > prerequisiteTime) {
					prerequisiteTime = element.getFastestTime();
				}
			}
		}
		data.setFastestTime(prerequisiteTime + productionTime.getValue());
	}

	private void log(String string) {
		System.out.println(string);
	}

}
