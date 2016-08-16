package uml.yangwenjing.wenjingandroidlibrary.HttpUtil;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yamengwenjing on 2016/7/27.



 */


    public class HttpHelper {
        private final static String USER_AGENT = "Mozilla/5.0";

/*
利用gson转换好的beanString 作为参数，返回服务器的stream string， 然后用gson解析
 */
        public static String sendBeanPost(String Url,String beanString) throws Exception {

//        String url = "http://54.172.7.54:8088/yyga/dialogue";
//        String url = "http://10.0.0.227:8080/MedicalQuestionAnswerBack/question/";
            URL obj = new URL(Url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Accept-Charset", "UTF-8");
        /*
        wenjing contentType 要改成json！
         */
            con.setRequestProperty("Content-Type", "application/json");
            //URLEncoder.encode("为什么要戒掉吸烟或喝酒的习惯？", "UTF-8");

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("ownerId", 4);
//        jsonObject.put("answerCount", 2);
//        jsonObject.put("questionTitle", "androidTest");
//        jsonObject.put("questionCreateTime", "1466793333");
//        jsonObject.put("questionContent", "androidTestandroidTestandroidTestandroidTest");

//        String urlParameters = "data="+ URLEncoder.encode(question, "UTF-8");
//        Log.d("wenjingresult", jsonObject.toString());
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

            writer.write(beanString);
            writer.flush();
            wr.flush();
            wr.close();
            writer.close();

            int responseCode = con.getResponseCode();

            // for login,register ... if 404 return 404 code
            if(responseCode==404){
                return "404";
            }
            if(responseCode==409){
                return "409";
            }
            Log.d("wenjingresult", "" + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

//        Log.d("wenjingresult", response.toString());
            return response.toString();
        }


/*
直接返回服务器响应code
 */
        public static String sendRelpyBeanPost(String Url,String beanString) throws Exception {

//        String url = "http://54.172.7.54:8088/yyga/dialogue";
//        String url = "http://10.0.0.227:8080/MedicalQuestionAnswerBack/question/";
            URL obj = new URL(Url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Accept-Charset", "UTF-8");
        /*
        wenjing contentType 要改成json！
         */
            con.setRequestProperty("Content-Type", "application/json");
            //URLEncoder.encode("为什么要戒掉吸烟或喝酒的习惯？", "UTF-8");

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("ownerId", 4);
//        jsonObject.put("answerCount", 2);
//        jsonObject.put("questionTitle", "androidTest");
//        jsonObject.put("questionCreateTime", "1466793333");
//        jsonObject.put("questionContent", "androidTestandroidTestandroidTestandroidTest");

//        String urlParameters = "data="+ URLEncoder.encode(question, "UTF-8");
//        Log.d("wenjingresult", jsonObject.toString());
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

            writer.write(beanString);
            writer.flush();
            wr.flush();
            wr.close();
            writer.close();

            int responseCode = con.getResponseCode();

            // for login,register ... if 404 return 404 code


            Log.d("wenjingresult", ""+responseCode);



//        Log.d("wenjingresult", response.toString());
            return responseCode+"";
        }


/*
发送 get请求 返回服务器string
 */

        public static  String sendGet(String url) throws Exception{

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            Log.d("wenjingHttp", "\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

            return response.toString();
        }

/*
发送delete请求
 */
        public static  String sendDelete(String url) throws Exception{

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("DELETE");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            Log.d("wenjingHttp", "\nSending 'delete' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
            Log.d("wenjingHttp", "Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

            return response.toString();
        }


    }
