/** 
 *<dl>
 *<dt><span class="strong">方法说明:</span></dt>
 *<dd>主要是实现了什么功能</dd>
 *</dl> 
 *<dl><dt><span class="strong">创建时间:</span></dt>
 *<dd> 2016年3月3日 上午10:57:56</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
package com.xcj.common;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * <dl>
 * <dt><span class="strong">方法说明:</span></dt>
 * <dd>主要是实现了什么功能</dd>
 * </dl>
 * <dl>
 * <dt><span class="strong">创建时间:</span></dt>
 * <dd>2016年3月3日 上午10:57:56</dd>
 * </dl>
 * </dl>
 *
 * @param <T>
 *            对象类型
 * @param entity
 *            对象
 * @return boolean true/false
 * @throws Exception
 *             XX异常
 */
public class MaoPaoKuaisuSortTest {

	@Test
	public void testStr() {
		int[] values = { 3, 1, 6, 2, 9, 0, 7, 4, 5 };
		sort(values);
		for (int i = 0; i < values.length; i++) {// 排序后打印数组中的元素

			System.out.println("Index: " + i + "  value: " + values[i]);

		}
	}

	public static void sort(int[] values) {
		int temp;
		for (int i = 0; i < values.length; i++) {// 趟数
			for (int j = 0; j < values.length - i - 1; j++) {// 比较次数
				if (values[j] > values[j + 1]) {
					temp = values[j];
					values[j] = values[j + 1];
					values[j + 1] = temp;
				}
			}
		}
	}

	public static int getMiddle(Integer[] list, int low, int high) {
		int tmp = list[low]; // 数组的第一个作为中轴
		while (low < high) {
			while (low < high && list[high] > tmp) {
				high--;
			}
			list[low] = list[high]; // 比中轴小的记录移到低端
			while (low < high && list[low] < tmp) {
				low++;
			}
			list[high] = list[low]; // 比中轴大的记录移到高端
		}
		list[low] = tmp; // 中轴记录到尾
		return low; // 返回中轴的位置
	}

	public static void _quickSort(Integer[] list, int low, int high) {
		if (low < high) {
			int middle = getMiddle(list, low, high); // 将list数组进行一分为二
			_quickSort(list, low, middle - 1); // 对低字表进行递归排序
			_quickSort(list, middle + 1, high); // 对高字表进行递归排序
		}
	}

	public static void quick(Integer[] str) {
		if (str.length > 0) { // 查看数组是否为空
			_quickSort(str, 0, str.length - 1);
		}
	}

	@Test
	public void test() {
		Integer[] list = { 34, 3, 53, 2, 23, 7, 14, 10 };
		quick(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
	}

}
