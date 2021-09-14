package info.morgia.timetrails.core.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @GetMapping("/hello/{name}")
    public DemoHelloResult Hello(@PathVariable String name){
        log.info("inside GET /demo/hello");
        Date d=new Date();
        return new DemoHelloResult("hello from GET: " + name, d);
    }

    @PostMapping("/hello")
    public DemoHelloResult Hello(@RequestBody DemoHelloRequest request) {
        log.info("inside POST /demo/hello");
        Date d=new Date();
        return new DemoHelloResult("hello from POST: " + request.getMessage(), d);
    }

}
