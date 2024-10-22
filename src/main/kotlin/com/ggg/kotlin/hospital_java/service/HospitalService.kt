package com.ggg.kotlin.hospital_java.service

import com.ggg.kotlin.hospital_java.model.HospitalModel
import com.ggg.kotlin.hospital_java.util.HospitalUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HospitalService {

    private val logger = LoggerFactory.getLogger(HospitalService::class.java)

    @Autowired
    private lateinit var hospitalModel: HospitalModel

    @Autowired
    private lateinit var hospitalUtil:HospitalUtil

    fun updateHospital() {

        val returnData = hospitalUtil.getHospitalList()
        logger.info(returnData.toString())
        if (returnData.result) {

        }
    }
}