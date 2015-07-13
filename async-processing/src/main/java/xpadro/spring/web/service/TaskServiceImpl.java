package xpadro.spring.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String execute() {
        try {
            Thread.sleep(5000);
            logger.info("Slow task executed");
            return "Task finished";
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
