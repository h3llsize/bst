package com.bstbackend.bstbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

@SpringBootApplication
public class BstBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BstBackendApplication.class, args);
    }

}
