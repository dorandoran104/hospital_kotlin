package com.ggg.kotlin.hospital_java.util

import com.ggg.kotlin.hospital_java.dto.ResultObjectDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

@Component
class HospitalUtil {

    @Value("\${data.api.url}")
    private lateinit var hospitalUrl:String

    @Value("\${data.api.key}")
    private lateinit var hospitalKey:String

    private var logger = LoggerFactory.getLogger(HospitalUtil::class.java)

    fun getHospitalList():ResultObjectDto{
       val resultObj = ResultObjectDto(result = false)

       val sb = StringBuilder()
       sb.append(hospitalUrl)
           .append("?")
           .append(URLEncoder.encode("serviceKey","UTF-8"))
           .append("=${hospitalKey}&")
           .append(URLEncoder.encode("pageNo","UTF-8"))
           .append("=1&")
           .append(URLEncoder.encode("numOfRows","UTF-8"))
           .append("=10")

        val url = URL(sb.toString())

        val connect:HttpURLConnection = url.openConnection() as HttpURLConnection
        connect.requestMethod = "GET"
        connect.setRequestProperty("Content-type","application/json")

        logger.info(connect.responseCode.toString())

        val rd:BufferedReader
        if(connect.responseCode in 200 ..300){
            rd = BufferedReader(InputStreamReader(connect.inputStream))
        }else{
            rd = BufferedReader(InputStreamReader(connect.errorStream))
        }

        val result = StringBuilder()

        while(true){
            val readLine = rd.readLine() ?:break
            result.append(readLine)
        }
        rd.close()
        connect.disconnect()
        logger.info(result.toString())

//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        System.out.println(sb.toString());
//    }

        return resultObj
    }
}