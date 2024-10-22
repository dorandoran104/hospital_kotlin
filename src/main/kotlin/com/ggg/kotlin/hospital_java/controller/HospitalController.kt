package com.ggg.kotlin.hospital_java.controller

import com.ggg.kotlin.hospital_java.service.HospitalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hospital")
class HospitalController {

    @Autowired
    private lateinit var hospitalService: HospitalService

    @GetMapping("/update")
    fun updateHospital(){
        hospitalService.updateHospital()
    }
}