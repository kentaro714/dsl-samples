package kentaro714.model.dn;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import kentaro714.model.dn.Environment.Keys;
import kentaro714.util.Function;
import kentaro714.util.FunctionUtils;

import com.google.common.collect.Lists;


public class SubstanceElement implements DependencyNode {
	
	private String name;
	private Date lastModified = new Date(0L);
	private SubstanceElementData data;
	private ProductionPlan plan;
	private List<SubstanceElement> inputs = Lists.newArrayList();
	private List<Keys> storedEnvironmentReferences = Lists.newArrayList();
	private TimeCalculator productionTime = new ConstantTimeCalculator(0);
	
	public SubstanceElement(String name, ProductionPlan plan) {
		this.name = name;
		this.plan = plan;
	}	
	
	public boolean modifiedSince(Date arg) {
		return lastModified.after(arg);
	}
	
	public void addInputs(List<SubstanceElement> args) {
		inputs.addAll(args);
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

	protected void execute() {
		log("execute");
		data = new SubstanceElementData();
		updateFastestTime();
		lastModified = new Date();
	}

	public MoonPhases getEnvironmentValue(Keys key) {
		if(!getEnvironmentReferences().contains(key)) {
			throw new RuntimeException(key + "is not declared in" + this);
		}
		return plan.getEnvironment().<MoonPhases>getValue(key);
	}
	
	public int getFastestTime() {
		return data.getFastestTime();
	}
	
	public String getName() {
		return name;
	}
	
	public TimeCalculator getProductionTime() {
		return productionTime;
	}
	
	public void setProductionTime(TimeCalculator calculator) {
		this.productionTime = calculator;
	}
	
	/**
	 * 依存プロダクトの最短時間のうち、最も大きな値で自身の最短時間を更新する。
	 * 事前のinvokeによって、依存プロダクトの最短時間もすべてのこのメソッド経由で更新されていることに注意。
	 */
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
	
	private List<? extends DependencyNode> getInputPrereqs() {
		return Collections.unmodifiableList(inputs);
	}
	

	private boolean isOutOfDate() {
		if (wasNeverCalculated()) {
			return true;
		}
		return FunctionUtils.any(getPrerequisites(), new Function<DependencyNode, Boolean>() {
			@Override
			public Boolean apply(DependencyNode param) {
				return param.modifiedSince(lastModified);
			}
		});
	}

	private List<DependencyNode> getPrerequisites() {
		List<DependencyNode> result = Lists.newArrayList();
		result.addAll(getInputPrereqs());
		result.addAll(getEnvironmentPrereqs());
		return result;
	}

	private List<? extends DependencyNode> getEnvironmentPrereqs() {
		FunctionUtils.map(getEnvironmentReferences(), new Function<Keys, DependencyNode>() {
			@Override
			public DependencyNode apply(Keys param) {
				return plan.getEnvironment().getTrackedProperty(param);
			}
		});
		
		return null;
	}
	
	private List<Keys> getEnvironmentReferences() {
		List<Keys> result = Lists.newArrayList();
		result.addAll(productionTime.getEnvironmentReferences());
		result.addAll(storedEnvironmentReferences);
		return result;
	}

	private boolean wasNeverCalculated() {
		return null == data;
	}
	
	private void log(String string) {
		System.out.println(string);
	}
}
