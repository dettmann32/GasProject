package gas.project.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class contollerTest {
    private static final Logger logger = LoggerFactory.getLogger(contollerTest.class);

    @GetMapping("/HelloWorld")
    public String HelloWorld() {
        logger.info("Hello World");
        return "Hello World";
    }

    

    
}