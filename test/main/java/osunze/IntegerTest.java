package osunze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

/**
 * @author YingBo.Dai
 * @E-mail:lyyb2001@163.com
 * @qq:20880488
 * @version 创建时间：2016年7月20日 下午8:53:52 程序的简单说明
 */
public class IntegerTest {
	public static void main(String args[]) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		List<String> list3 = new ArrayList<String>();
		CollectionUtils.collect(list, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return String.valueOf(arg0);
			}
		}, list3);
		List<String> list2 = Arrays.asList("1", "2", "3", "4", "5");
		System.out.println(list2.stream().collect(Collectors.joining(",")));
		System.out.println(list3.stream().collect(Collectors.joining(",")));
	}
}
