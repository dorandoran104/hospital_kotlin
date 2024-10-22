package com.ggg.kotlin.hospital_java.service

import com.ggg.kotlin.hospital_java.dto.HospitalDto
import com.ggg.kotlin.hospital_java.model.HospitalModel
import com.ggg.kotlin.hospital_java.util.XmlUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.w3c.dom.Element
import java.net.URLEncoder
import javax.xml.parsers.DocumentBuilderFactory

@Service
class HospitalService {

    private val logger = LoggerFactory.getLogger(HospitalService::class.java)

    @Autowired
    private lateinit var hospitalModel: HospitalModel

    @Autowired
    private lateinit var xmlUtil: XmlUtil

    @Value("\${data.api.url}")
    private lateinit var hospitalUrl:String

    @Value("\${data.api.key}")
    private lateinit var hospitalKey:String

    /**
     * 병원 리스트 업데이트
     */
    @Transactional
    fun updateHospital() {
        var page = 1
        var whileFlag = false
        while(!whileFlag){
            val sb = StringBuilder()
            sb.append(hospitalUrl)
                .append("?")
                .append(URLEncoder.encode("serviceKey","UTF-8"))
                .append("=${hospitalKey}&")
                .append(URLEncoder.encode("pageNo","UTF-8"))
                .append("=${page}&")
                .append(URLEncoder.encode("numOfRows","UTF-8"))
                .append("=10000")

            val document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(sb.toString())

            document.normalizeDocument()

            val nodeList = document.getElementsByTagName("item")

            val hospitalArr = ArrayList<HospitalDto>()

            for (i in 0 until nodeList.length){
                val node = nodeList.item(i)
                val element = node as Element
                val hospitalDto = HospitalDto(
                    rnum = xmlUtil.getTagValue("rnum", element)!!.toInt(),
                    hpid = xmlUtil.getTagValue("hpid", element)!!,
                    dutyName = xmlUtil.getTagValue("dutyName", element)!!,
                    dutyAddr = xmlUtil.getTagValue("dutyAddr", element),
                    dutyDiv = xmlUtil.getTagValue("dutyDiv", element) ,
                    dutyDivNam = xmlUtil.getTagValue("dutyDivNam", element),
                    postCdn1 = xmlUtil.getTagValue("postCdn1", element),
                    dutyEmcls = xmlUtil.getTagValue("dutyEmcls", element),
                    dutyEmclsName = xmlUtil.getTagValue("dutyEmclsName", element),
                    dutyEryn = xmlUtil.getTagValue("dutyEryn", element),
                    dutyEtc = xmlUtil.getTagValue("dutyEtc", element),
                    dutyMapimg = xmlUtil.getTagValue("dutyMapimg", element),
                    dutyTel1 = xmlUtil.getTagValue("dutyTel1", element)!!,
                    dutyTel3 = xmlUtil.getTagValue("dutyTel3", element),
                    dutyTime1c = xmlUtil.getTagValue("dutyTime1c", element),
                    dutyTime2c = xmlUtil.getTagValue("dutyTime2c", element),
                    dutyTime3c = xmlUtil.getTagValue("dutyTIme3c", element),
                    dutyTime4c = xmlUtil.getTagValue("dutyTime4c", element),
                    dutyTime5c = xmlUtil.getTagValue("dutyTime5c", element),
                    dutyTime6c = xmlUtil.getTagValue("dutyTime6c", element),
                    dutyTime7c = xmlUtil.getTagValue("dutyTime7c", element),
                    dutyTime8c = xmlUtil.getTagValue("dutyTime8c", element),
                    dutyTime1s = xmlUtil.getTagValue("dutyTime1s", element),
                    dutyTime2s = xmlUtil.getTagValue("dutyTime2s", element),
                    dutyTime3s = xmlUtil.getTagValue("dutyTime3s", element),
                    dutyTime4s = xmlUtil.getTagValue("dutyTime4s", element),
                    dutyTime5s = xmlUtil.getTagValue("dutyTime5s", element),
                    dutyTime6s = xmlUtil.getTagValue("dutyTime6s", element),
                    dutyTime7s = xmlUtil.getTagValue("dutyTime7s", element),
                    dutyTime8s = xmlUtil.getTagValue("dutyTime8s", element),
                    postCdn2 = xmlUtil.getTagValue("postCdn2",element),
                    wgs84Lon = xmlUtil.getTagValue("wgs84Lon",element),
                    wgs84Lat = xmlUtil.getTagValue("wgs84Lat",element),
                    dutyInf = xmlUtil.getTagValue("dutyInf",element),
                )
                hospitalArr.add(hospitalDto)
            }
            if(hospitalArr.size > 0){
                hospitalModel.insertHospital(hospitalArr)
            }
            if(nodeList.length < 1000){
                whileFlag = true
            }
            page++
        }
    }
}