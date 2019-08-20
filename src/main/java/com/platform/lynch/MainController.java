package com.platform.lynch;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.ResourceException;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

	@Autowired
	private Environment environment;
	
	private final Logger log = LoggerFactory.getLogger(MainController.class);
	private UserRepository userRepository;

    public MainController(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }
	
	
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }
    
    @GetMapping("/protected")
    public String helloWorldProtected() {
        return "Hello VIP";
    }
    
    @PostMapping("/api/register")
    ResponseEntity<?> register(@Valid @RequestBody GenericUser user) throws URISyntaxException {
    	
        log.info("Request to create business: {}", user);
        
        // Establish a connection to Okta server using the api token
        Client client = Clients.builder()
        		.setOrgUrl(environment.getProperty("okta.oauth2.orgUrl"))
        		.setClientCredentials(new TokenClientCredentials(environment.getProperty("okta.oauth2.token")))
        		.build();
        
        // Try to create a new Okta user using the input credentials. Save user to the db if successful.
        try {
        	User oktaUser = UserBuilder.instance()
    	        .setFirstName(user.getFirstName())
    	        .setLastName(user.getLastName())
	        	.setEmail(user.getEmail())
	        	.setPassword(user.getPassword().toCharArray())
	        	.setActive(true)
	        	.buildAndCreate(client);
        	
        	// Save to db
        	user.setId(oktaUser.getId());
            userRepository.save(user);
        	
        	return ResponseEntity.ok().build();
        }
        catch(ResourceException e) {
        	return ResponseEntity.badRequest().build();
        }
        
    }
    
}
