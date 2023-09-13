package com.skypaps.fleevy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@SpringBootApplication
class FleevyApplication

@RequestMapping("/")
@ResponseBody
fun home(){
    "Hello world"
}

fun main(args: Array<String>) {
    runApplication<FleevyApplication>(*args)
}
