package Spring;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public User createUser(@RequestBody User user)
    {
    	 System.out.println("");
    	
    	 //return to show user
    	 return new User(user.getName().toString(),user.getUser().toString());
    }
    
    //map the root to an error page
    @RequestMapping("/")
    public Error error() 
    {
        return new Error(counter.incrementAndGet());
    }
}