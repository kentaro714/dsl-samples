package kentaro714.util;

import java.util.List;

import com.google.common.collect.Lists;

public class FunctionUtils {

	public static <T> boolean any(List<T> lists, Function<T, Boolean> function) {
		for (T elem : lists) {
			if (function.apply(elem)) {
				return true;
			}
		}
		return false;
	}

	public static <D, C> List<C> map(List<D> lists, Function<D, C> function) {
		List<C> result = Lists.newArrayList();
		for (D elem : lists) {
			result.add(function.apply(elem));
		}
		return result;
	}

}
