package kentaro714.model.dn;

import java.util.Map;

import com.google.common.collect.Maps;


public class ProductionPlan {
	private Map<String, SubstanceElement> elements = Maps.newHashMap();
	private Environment environment;
	
	public void addElement(SubstanceElement arg) {
		elements.put(arg.getName(), arg);
	}
	
	public SubstanceElement getElement(String key) {
		SubstanceElement element = elements.get(key);
		element.invoke();
		return element;
	}
	
	// FIXME 誰も環境を設定できない
	public Environment getEnvironment() {
		return environment;
	}
}
