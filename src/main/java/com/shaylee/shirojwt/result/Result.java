package com.shaylee.shirojwt.result;

import com.shaylee.shirojwt.base.BaseConstant;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Title: 正常响应实体
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {
	private static final long serialVersionUID = -495286822550431822L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;


	@Getter
	@Setter
	private T data;

	public static <T> Result<T> ok() {
		return restResult(null, BaseConstant.SUCCESS, null);
	}

	public static <T> Result<T> ok(T data) {
		return restResult(data, BaseConstant.SUCCESS, null);
	}

	public static <T> Result<T> ok(T data, String msg) {
		return restResult(data, BaseConstant.SUCCESS, msg);
	}

	public static <T> Result<T> failed() {
		return restResult(null, BaseConstant.FAIL, null);
	}

	public static <T> Result<T> failed(String msg) {
		return restResult(null, BaseConstant.FAIL, msg);
	}

	public static <T> Result<T> failed(T data) {
		return restResult(data, BaseConstant.FAIL, null);
	}

	public static <T> Result<T> failed(T data, String msg) {
		return restResult(data, BaseConstant.FAIL, msg);
	}

	private static <T> Result<T> restResult(T data, int code, String msg) {
		Result<T> apiResult = new Result<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
}
