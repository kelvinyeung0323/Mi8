package com.hidata.mi8pro.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hidata.mi8pro.Index.IndexSummary;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by k_way on 2017/5/17.
 */

public class HttpUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * get方法
     *
     * @param url
     * @return
     */
    public static ResponseResult<?> get(String url){
        return get(url,new TypeReference<ResponseResult<Object>>(){});
    }
    public static ResponseResult<?> get(String url,TypeReference typeReference) {
        ResponseResult<?> responseResult= null;

        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL uRL = new URL(url);
            conn = (HttpURLConnection) uRL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoInput(true);
            //不缓存
            conn.setUseCaches(false);

            int status = conn.getResponseCode();
            if(status==200){
                is =  conn.getInputStream();
                String retStr = readStream(is,1024);
                responseResult = objectMapper.readValue(retStr,typeReference);
                //objectMapper.readValue(retStr,clazz);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                conn.disconnect();
            }

        }


        return  responseResult;
    }

    /**
     * post方法
     */
    public static ResponseResult<?> post(String url, Object params){
        return post(url,params,new TypeReference<ResponseResult<Object>>(){});
    }
    public static ResponseResult<?> post(String url, Object mparams,TypeReference typeReference){
        ResponseResult<?> responseResult= null;
        URL uRL = null;
        InputStream inputStream= null;
        OutputStream outputStream = null;
        HttpURLConnection conn = null;
        try {
            uRL = new URL(url);
            conn = (HttpURLConnection) uRL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            outputStream = conn.getOutputStream();
            String paramStr = objectMapper.writeValueAsString(mparams);
            outputStream.write(paramStr.getBytes());
            outputStream.flush();
            conn.getInputStream();
            int status = conn.getResponseCode();
            if(status==200){
                inputStream =  conn.getInputStream();
                String retStr = readStream(inputStream,1024);
                responseResult = objectMapper.readValue(retStr,typeReference);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!= null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                conn.disconnect();
            }


        }

        return responseResult;
    }


    private static String readStream(InputStream stream, int maxLength) throws IOException {
        String result = null;
        // Read InputStream using the UTF-8 charset.
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        // Create temporary buffer to hold Stream data with specified max length.
        char[] buffer = new char[maxLength];
        // Populate temporary buffer with Stream data.
        int numChars = 0;
        int readSize = 0;
        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            // The stream was not empty.
            // Create String that is actual length of response body if actual length was less than
            // max length.
            numChars = Math.min(numChars, maxLength);
            result = new String(buffer, 0, numChars);
        }
        return result;
    }

}
