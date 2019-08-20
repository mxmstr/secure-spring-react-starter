# Secure Spring REST API

This example is a resource server using Spring Boot and Spring Security. It also implements group-based authorization using Okta and OAuth 2.0.

Please read [Create a Secure Spring REST API](https://developer.okta.com/blog/2018/12/18/secure-spring-rest-api) to see how this app was created.

**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage, and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/oktadeveloper/okta-secure-spring-rest-api-example.git
cd okta-secure-spring-rest-api-example
```

This will get a copy of the project installed locally. To install all of its dependencies and start the app, run:
 
```bash
./mvnw spring-boot:run
```

This will likely fail. You need to configure this application to work with Okta first.

### Create an Application in Okta

You will need to create an OpenID Connect Application in Okta to get your values to perform authentication. 

Log in to your Okta Developer account (or [sign up](https://developer.okta.com/signup/) if you don’t have an account) and navigate to **Applications** > **Add Application**. Click **Service**, click **Next**, and give the app a name you’ll remember. Click **Done** and copy the `clientId` into `src/main/resources/application.properties`. 

```yaml                            
okta:  
  oauth2: 
    issuer: https://{yourOktaDomain}/oauth2/default  
    clientId: {yourClientId}  
    clientSecret: {yourClientSecret}    
```

**NOTE:** The value of `{yourOktaDomain}` should be something like `dev-123456.oktapreview`. Make sure you don't include `-admin` in the value!

After modifying this file, restart your app and you should be able to start the app and use token authentication with Okta. See [Create a Secure Spring REST API](https://developer.okta.com/blog/2018/12/18/secure-spring-rest-api) for more information.

## Links

This example uses [Okta's Spring Boot Starter](https://github.com/okta/okta-spring-boot).

## Help

Please post any questions as comments on the [blog post](https://developer.okta.com/blog/2018/12/18/secure-spring-rest-api), or visit our [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).