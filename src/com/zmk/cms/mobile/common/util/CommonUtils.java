package com.zmk.cms.mobile.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONObject;

public class CommonUtils {
    public static String createToken(String username) {
        return username + "_"
                + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase()
                + "_" + new Date().getTime();
    }
	public static String getUsernnameFromToken(String tokenId){
		return tokenId.split("_")[0];
	}
}
