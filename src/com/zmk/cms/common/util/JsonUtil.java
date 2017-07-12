package com.zmk.cms.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    public static Map<String, String> resultStr2Map(String str) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("result", str);
        return result;
    }

    public static Map<String, Integer> resultInt2Map(int str) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("result", str);
        return result;
    }

    public static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    /**
     * jackson的ObjectMapper对象
     */
    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象转换为json字符串
     * 
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null)
            return "";
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("write to json string error:" + object, e);
            return "";
        }
    }

    /**
     * 根据指定类型解析json字符串，并返回该类型的对象
     * 
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            log.error("parse json string error:" + jsonString, e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonArrayStr(List list) {
        JSONArray array = JSONArray.fromObject(list);
        String jsonstr = array.toString();
        return jsonstr;
    }

    @SuppressWarnings("unchecked")
    public static List toArrayList(String jsonStr) {
        jsonStr = parseStr(jsonStr);
        JSONArray json = JSONArray.fromObject(jsonStr);
        List lt = (List) JSONArray.toCollection(json);
        return lt;
    }

    // 去掉json字符串头信息
    public static String parseStr(String str) {
        str = str.split(":", 2)[1].substring(0,
                str.split(":", 2)[1].length() - 1);
        return str;
    }

    // 封装参数为Map
    public static Map<String, String> toMap(String key, String value) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        return map;
    }

    /**
     * 解析json对象为Object
     * 
     * @param <T>
     * @param json
     * @param claz
     * @return
     */
    @SuppressWarnings( { "unchecked" })
    public static <T> T parserJsonObj(String json, Class<T> claz) {
        T t = null;
        Map<String, Object> map = JsonUtil.fromJson(json, Map.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            String str = JsonUtil.toJson(obj);
            t = JsonUtil.fromJson(str, claz);
            break;
        }
        return t;
    }

    @SuppressWarnings( { "unchecked" })
    public static String parserJsonStr(String json) {
        String result = "";
        Map<String, String> map = JsonUtil.fromJson(json, Map.class);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result = entry.getValue();
            break;
        }
        return result;
    }

    /**
     * 解析json对象为List集合
     * 
     * @param <T>
     * @param json
     * @param claz
     * @return
     */
    @SuppressWarnings( { "unchecked" })
    public static <T> List<T> parserJsonArray(String json, Class<T> claz) {
        List<T> list = new ArrayList<T>();
        Map<String, List> map = JsonUtil.fromJson(json, Map.class);

        for (Map.Entry<String, List> entry : map.entrySet()) {
            List li = entry.getValue();
            for (int i = 0; i < li.size(); i++) {
                String str = JsonUtil.toJson(li.get(i));
                T t = JsonUtil.fromJson(str, claz);
                list.add(t);
            }
            break;
        }
        return list;
    }

    /**
     * Map对象转换为NameValuePair对象结合
     * 
     * @param map
     * @return
     */
    public static List<BasicNameValuePair> map2NameValuePair(
            Map<String, String> map) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }
        return params;
    }

    /**
     * 把map对象转换为String类型的参数K=V&K2=V2形式
     * 
     * @param parameters
     *            Map对象
     * @return
     * @throws UnsupportedEncodingException
     */

    public static String assemblyParameter(Map<String, String> parameters)
            throws UnsupportedEncodingException {
        String para = "?";
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            log.info(entry.getValue());
            para += entry.getKey() + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8") + "&";
            // para += entry.getKey() + "=" +
            // URLEncoder.encode(entry.getValue()) + "&";
        }
        return para.substring(0, para.length() - 1);
    }

    /*
     * 在json串头部添加信息，以便转为Map集合
     */
    public static String add2JsonStr(String str) {
        return "{" + '"' + "List" + '"' + ":" + str + "}";
    }
}
