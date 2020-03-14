package com.shaylee.shirojwt.base;

/**
 * 自定义常量的基类
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public interface BaseConstant {

	/** 删除标识：0，正常 */
	Integer DEL_FLAG_NORMAL = 0;
	/** 删除标识：1删除 */
	Integer DEL_FLAG_DELETE = 1;

	/** 成功标记 */
	Integer SUCCESS = 0;
	/** 失败标记 */
	Integer FAIL = 1;
}
