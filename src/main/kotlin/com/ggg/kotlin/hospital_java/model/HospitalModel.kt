package com.ggg.kotlin.hospital_java.model

import com.ggg.kotlin.hospital_java.dto.HospitalDto
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class HospitalModel {

    @Autowired
    private lateinit var sqlSession: SqlSession

    private val HospitalNamespace = "HospitalMapper"

    fun insertHospital(hospitalList: List<HospitalDto>){
        sqlSession.insert(HospitalNamespace+".insert",hospitalList)
    }

}