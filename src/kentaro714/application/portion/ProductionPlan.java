package kentaro714.application.portion;

import java.util.Map;

import com.google.common.collect.Maps;

import kentaro714.application.portion.domain.SubstanceElement;

public class ProductionPlan {
	private Map<String, SubstanceElement> elements = Maps.newHashMap();
	public void addElement(SubstanceElement arg) {
		elements.put(arg.getName(), arg);
	}
}
