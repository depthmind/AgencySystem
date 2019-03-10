package com.agency.crm.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.util.HttpRequestUtil;
import com.agency.crm.common.framework.util.HttpUtil;
import com.agency.crm.utils.AccessTokenUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/wxaCode")
public class WXACodeController extends BaseSimpleFormController {

	@RequestMapping(value="/createWXACode", produces="application/json;charset=utf-8")
	public void createWXACode(HttpServletRequest request, String path) {
		Map<String, Object> params = new HashMap<>();
        params.put("scene", "test");
        params.put("page", "pages/index/index");
        params.put("width", 430);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+AccessTokenUtils.getWeiXinAppAccessToken());
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String body = JSON.toJSONString(params);
        StringEntity entity;
        try {
			entity = new StringEntity(body);
			entity.setContentType("image/png");

			httpPost.setEntity(entity);
			HttpResponse response;

			response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();

			FileOutputStream out = new FileOutputStream("/Users/liuhan/Desktop/5.png");

			byte[] buffer = new byte[8192];
			int bytesRead = 0;
			while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
			    out.write(buffer, 0, bytesRead);
			}

			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
