package com.shaylee.shirojwt.utils.random;

import java.util.Random;

/**
 * Title: 随机数工具
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public class RandomUtil {

	public static String genNum(String prefix, int max, int min) {
		Random random = new Random();
		int num = random.nextInt(max - min) + min;
		return prefix + num;
	}

	/**
	 * 生成随机码(length=6,生成[100000~999999]区间的随机码)
	 * @param prefix 前缀
	 * @param length 长度
	 * @return 随机码
	 */
	public static String genRandomCode(String prefix, int length) {
		int radix = 10;
		int offset = 1;
		// int最长2147483647
		int maxLength = 8;
		int max,min;
		StringBuilder result = new StringBuilder();
		// 剩余长度
		int residue = length;
		// 每次生成长度
		int cutLength = maxLength;
		while (residue > 0) {
			if (cutLength > residue) {
				cutLength = residue;
			}
			max = (int) Math.pow(radix, cutLength) - offset;
			min = (int) Math.pow(radix, cutLength - offset);
			result.append(RandomUtil.genNum(prefix, max, min));
			residue -= maxLength;
		}
		return result.toString();
	}
}