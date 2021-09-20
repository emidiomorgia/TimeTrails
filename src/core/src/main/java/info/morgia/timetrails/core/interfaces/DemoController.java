package info.morgia.timetrails.core.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {
    @Autowired
    RestTemplate restTemplate;

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

    @GetMapping("/user-detail")
    public UserDetailResult userDetail(){
        log.info("inside GET /demo/user-detail");
        UserDetailResult res = restTemplate.getForObject("http://AUTH-SERVICE/users/user-detail", UserDetailResult.class);
        return res;
    }

}
