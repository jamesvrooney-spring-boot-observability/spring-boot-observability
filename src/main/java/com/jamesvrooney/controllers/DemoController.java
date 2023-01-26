package com.jamesvrooney.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class DemoController {

    @GetMapping("/")
    public String root(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.error(String.format("Hello %s!!", name));
        log.debug("Debugging log");
        log.info("Info log");
        log.warn("Hey, This is a warning!");
        log.error("Oops! We have an Error. OK");

        return String.format("Hello %s!!", name);
    }

    @GetMapping("/io_task")
    public String io_task(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException {
        Thread.sleep(1000);
        log.info("io_task");

        return "io_task";
    }

    @GetMapping("/cpu_task")
    public String cpu_task(@RequestParam(value = "name", defaultValue = "World") String name) {
        for (int i = 0; i < 100; i++) {
            int tmp = i * i * i;
        }
        log.info("cpu_task");
        return "cpu_task";
    }

    @GetMapping("/random_sleep")
    public String random_sleep(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException {
        Thread.sleep((int) (Math.random() / 5 * 10000));
        log.info("random_sleep");
        return "random_sleep";
    }

    @GetMapping("/random_status")
    public String random_status(@RequestParam(value = "name", defaultValue = "World") String name, HttpServletResponse response) throws InterruptedException {
        List<Integer> givenList = Arrays.asList(200, 200, 300, 400, 500);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        response.setStatus(randomElement);
        log.info("random_status");
        return "random_status";
    }

//    @GetMapping("/chain")
//    public String chain(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException, IOException {
//        String TARGET_ONE_HOST = System.getenv().getOrDefault("TARGET_ONE_HOST", "localhost");
//        String TARGET_TWO_HOST = System.getenv().getOrDefault("TARGET_TWO_HOST", "localhost");
//        log.debug("chain is starting");
//        Request.Get("http://localhost:8080/")
//                .execute().returnContent();
//        Request.Get(String.format("http://%s:8080/io_task", TARGET_ONE_HOST))
//                .execute().returnContent();
//        Request.Get(String.format("http://%s:8080/cpu_task", TARGET_TWO_HOST))
//                .execute().returnContent();
//        log.debug("chain is finished");
//        return "chain";
//    }

    @GetMapping("/error_test")
    public String error_test(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {
        throw new Exception("Error test");
    }
}
