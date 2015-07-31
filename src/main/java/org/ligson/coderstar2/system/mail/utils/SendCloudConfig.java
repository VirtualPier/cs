package org.ligson.coderstar2.system.mail.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.noggit.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/31.
 */
public class SendCloudConfig {
    private static final Logger logger = Logger.getLogger(SendCloudConfig.class);
    private static final String api = "http://sendcloud.sohu.com/webapi/mail.send_template.json";
    private String apiUser;
    private String apiKey;
    private String fromEmail;
    private String appName;
    private String appDomain;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDomain() {
        return appDomain;
    }

    public void setAppDomain(String appDomain) {
        this.appDomain = appDomain;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getApiUser() {
        return apiUser;
    }

    public void setApiUser(String apiUser) {
        this.apiUser = apiUser;
    }

    public boolean sendMail(String toEmail, String templateName, Map<String, String> valMap) {
        List<String> mailList = new ArrayList<>();
        mailList.add(toEmail);
        Map<String, Object> result = new HashMap<>();
        result.put("to", mailList);
        Map<String, Object> contentMap = new HashMap<>();
        for (String key : valMap.keySet()) {
            List<String> valueList = new ArrayList<>();
            valueList.add(valMap.get(key));
            contentMap.put("%" + key + "%", valueList);
        }
        result.put("sub", contentMap);
        String vars = JSONUtil.toJSON(result);
        logger.debug(vars);
        HttpPost httpPost = new HttpPost(api);
        HttpClient httpclient = HttpClientBuilder.create().build();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("api_user", apiUser));
        params.add(new BasicNameValuePair("api_key", apiKey));
        params.add(new BasicNameValuePair("substitution_vars", vars));
        //params.add(new BasicNameValuePair("to", toEmail));
        //params.add(new BasicNameValuePair("use_maillist", "true"));

        params.add(new BasicNameValuePair("template_invoke_name", templateName));
        params.add(new BasicNameValuePair("from", fromEmail));
        params.add(new BasicNameValuePair("fromname", appName + "系统客服"));
        params.add(new BasicNameValuePair("resp_email_id", "true"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (Exception e) {
        }
        try {
            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
                logger.debug(EntityUtils.toString(response.getEntity()));
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
