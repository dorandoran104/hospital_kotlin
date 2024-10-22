package com.ggg.kotlin.hospital_java.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SerchController {

    @GetMapping("/{name}")
    fun search(@PathVariable("name") name:String):HashMap<String,Any> {
        val resultObj = HashMap<String,Any>()

        println("server logger")

        return resultObj;
    }
}