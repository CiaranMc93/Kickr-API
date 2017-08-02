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
    
    //login url method to take in the api request
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginOrCreate(@RequestBody User user)
    {
    	if(user.login())
    	{
    		//successful login
    	}
    	else
    	{
    		//signup
    		if(user.signUp())
    		{
    			//successful signup
    		}
    	}
    }
    
    //map the root to an error page
    @RequestMapping("/")
    public Error error() 
    {
        return new Error(counter.incrementAndGet());
    }
}