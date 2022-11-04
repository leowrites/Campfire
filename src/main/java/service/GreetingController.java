package service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// marks class as controller, every method returns a object
@RestController
public class GreetingController {

    private static final String template = "Welcome to rate my intern, %s";

    // request to /greeting maps to this method
    // there are also @PostMapping
    @GetMapping("/")
    // @RequestParam binds query parameter name to the name parameter of greeting()

    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world")
                             String name){
        // this object data will be converted to a JSON http response
        return new Greeting("Leo", String.format(template, name));
    }
}
