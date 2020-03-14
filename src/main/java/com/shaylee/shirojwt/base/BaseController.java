package com.shaylee.shirojwt.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

import javax.validation.Validator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 抽象控制器
 *
 * @author Alan
 * @version 1.0
 * @since 2018-6-7
 */
public abstract class BaseController {
	protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    private static final String HTTP_STATUS = "status";
    private static final String REASON = "error";
    private static final String ERROR_CODE = "errorCode";
    private static final String MESSAGE = "message";

    protected static final Integer SUCCESS_STATUS = 200;

    private static final String DEFAULT_SUCCESS_PHRASE = "Success";
    private static final String JSON_DATA = "data";

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected Validator validator;

    private String formatJSONByFilter(Map<String, Object> result, Class<?> filter) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (filter == null) {
            mapper.writeValueAsString(result);
        }
        return mapper.writerWithView(filter).writeValueAsString(result);
    }

    protected String responseSuccessJSON() throws IOException {
        return this.responseSuccessJSON(null);
    }

    protected String responseSuccessJSON(Map<String, Object> data) throws IOException {
        return this.responseSuccessJSONByFilter(data, null);
    }

    protected String responseSuccessJSONByFilter(Map<String, Object> data, Class<?> filter) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put(MESSAGE, DEFAULT_SUCCESS_PHRASE);
        result.put(JSON_DATA, data);
        result.put(HTTP_STATUS, SUCCESS_STATUS);

        return this.formatJSONByFilter(result, filter);
    }

    /**
     * 自定义返回错误信息提示
     *
     * @param status
     */
    protected String responseErrorJSON(String code, Object[] args, Locale local, HttpStatus status) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();

        String message = null;
        if (StringUtils.isNotBlank(code)) {
            message = applicationContext.getMessage("ERROR.CODE." + code, args, local);
        }

        result.put(HTTP_STATUS, status.value());
        result.put(REASON, status.getReasonPhrase());
        result.put(ERROR_CODE, code);
        result.put(MESSAGE, message);

        return mapper.writeValueAsString(result);
    }

    /**
     * 自定义返回错误信息提示
     */
    protected String responseErrorJSON(String code, Object[] args, String defaultMessage, Locale local, HttpStatus status) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();

        String message = null;
        if (StringUtils.isNotBlank(code)) {
            message = applicationContext.getMessage("ERROR.CODE." + code, args, defaultMessage, local);
        }

        result.put(HTTP_STATUS, status.value());
        result.put(REASON, status.getReasonPhrase());
        result.put(ERROR_CODE, code);
        result.put(MESSAGE, message);

        return mapper.writeValueAsString(result);
    }
}
