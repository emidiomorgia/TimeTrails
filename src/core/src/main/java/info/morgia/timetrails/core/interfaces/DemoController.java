package info.morgia.timetrails.core.interfaces;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BeanResult beanResult1;

    @Autowired
    BeanResult beanResult2;

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
        log.info("inside GET /demo/user-detail","10");
        UserDetailResult res = restTemplate.getForObject("http://AUTH-SERVICE/users/user-detail", UserDetailResult.class);
        return res;
    }

    @GetMapping("/bean1")
    public BeanResult bean1(){
        log.info("inside GET /demo/bean1");
        return beanResult1;
    }

    @GetMapping("/bean2")
    public BeanResult bean2(){
        log.info("inside GET /demo/bean2");
        return beanResult2;
    }

    @GetMapping("/error")
    public String error() {
        String res;
        try {
           res  = restTemplate.getForObject("http://AUTH-SERVICE/users/error", String.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred on the server");
        }
        return res;
    }



}
