package com.ggg.kotlin.hospital_java.model

import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class HospitalModel {

    @Autowired
    private lateinit var sqlSession: SqlSession

    private val HospitalNamespace = "HospitalMapper"

}