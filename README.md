# spring-security-rest-example 

## Dependencies

```xml
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-test</artifactId>
	<scope>test</scope>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## Files Needed
All files exist in repo. See them for reference

* Create a services package
* Create an `AutheniticationService.java` under the services package
* Create a `UserRepository.java` class
* Create your `User` Model but make sure you use the `@Table(name = "app_users")` under the @Entity annotation since user is a reserved word in postgres
* Create a `SessionApiController.java`
* Create `SecurityConfiguration.java`
* Create `CsrfIntoCookieFilter.java`
* Create your `UserController.java`
* Add the following line to your seed data to create a test user `User admin = userRepository.save(new User("admin", "user", "admin", encoder.encode("password")));`

## Test with postman
* You should get a 403 forbidden when trying to access routes not allowin in the security config
* You should be able to do a put request with postman to `http://localhost:8080/api/session` with the following json to authenticate:
```javascript
{
    "username": "admin",
    "password": "password"
}
```
* you should now be able to access routes that previously had a 403
* A delete request to the same URL as above will log you out.

## Update angular service
* Update the following import in your service
```javascript
import { Http, Response, RequestOptions, Headers } from '@angular/http';
```
* Add the following lines as properties on the data service class.
```javascript
private headers = new Headers({ 'Content-Type': 'application/json' });
private options = new RequestOptions({ headers: this.headers, withCredentials: true });
```
*pass `this.options` as the last property to any api call that requires auth.
```javascript
//this is an example

    editRecord(endpoint: string, record:object, id:number): Observable<object> {
        let apiUrl = `${this.baseUrl}${endpoint}/${id}`;
        return this.http.put(apiUrl, record, this.options)
            .map(this.extractData)
            .catch(this.handleError);
    }
```

## Using a third party http client
Make sure you sure you enable `withCredentials` with whatever client you choose. [Superagent](http://visionmedia.github.io/superagent/) is a popular client. They implement a `withCredentials()` method call to enable this functionality.

### superagent example
```javascript
	
	import request from 'superagent';

	const baseUrl = 'SOME_URL';
	
	export function login(user) {
	
	    return dispatch => {
	        request.put(`${baseUrl}/session`)
	        .set('Content-Type', 'application/json')
	        .withCredentials() // <-- WITH_CREDENTIALS
	        .send(user)
	        .end(
	            (error, response) => {
	                
	                if(error) {
	                    console.error("could not login user" + error);
	                    return;
	                }
	                
	               //DO STUFF	          
	
	            }
	        )
	    }
	}

```

