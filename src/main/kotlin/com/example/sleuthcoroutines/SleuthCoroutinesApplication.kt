package com.example.sleuthcoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SleuthCoroutinesApplication

fun main(args: Array<String>) {
	runApplication<SleuthCoroutinesApplication>(*args)
}

@RestController
class WebController {
	val log: Logger = LoggerFactory.getLogger(this::class.java)

	@GetMapping("/hello")
	suspend fun hello(): Map<String, String> {
		log.info("in main context")

		GlobalScope.launch {
			log.info("in Coroutines context (launch)")
		}

		GlobalScope.async {
			log.info("in Coroutines context (async)")
		}.await()

		return mapOf(
			"msg" to "hello"
		)
	}
}