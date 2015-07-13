package xpadro.spring.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xpadro.spring.web.service.TaskService;

import java.util.concurrent.Callable;

@RestController
public class AsyncCallableController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskService taskService;

    @Autowired
    public AsyncCallableController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/callable", method = RequestMethod.GET, produces = "text/html")
    public Callable<String> executeSlowTask() {
        logger.info("Request received");
        Callable<String> callable = taskService::execute;
        logger.info("Servlet thread released");

        return callable;
    }
}
