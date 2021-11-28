package info.morgia.timetrails.auth.interfaces;

import brave.Span;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Autowired
    Tracer tracer;

    @GetMapping("/user-detail")
    public UserDetailResult userDetail() throws InterruptedException {
        log.info("inside GET /users/user-detail");
        log.info("I'm in the original span");

        Span newSpan = tracer.nextSpan().name("newSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            Thread.sleep(1000L);
            log.info("I'm in the new span doing some cool work that needs its own span");
        } finally {
            newSpan.finish();
        }

        log.info("I'm in the original span");
        return new UserDetailResult(1,"demo user from service");
    }

    private String getValue() throws Exception {
        throw new Exception("Error in getValue");
    }

    @GetMapping("/error")
    public String error() {
        try {
            return getValue();
        } catch (Exception ex) {
            StringWriter string_writer = new StringWriter();
            ex.printStackTrace(new PrintWriter(string_writer));
            log.error("error in code. original: " + string_writer);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error in code");
        }

    }
}
