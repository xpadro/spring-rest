package xpadro.spring.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xpadro.spring.web.service.TaskService;

@RestController
public class BlockingController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskService taskService;

    @Autowired
    public BlockingController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET, produces = "text/html")
    public String executeSlowTask() {
        logger.info("Request received");
        String result = taskService.execute();
        logger.info("Servlet thread released");

        return result;
    }
}
