## Gradox Authentication Server


* *Registering a new user*
```POST localhost:8899/userauth/register
	Payload: { 
				username: "username",
				password: "password"
	 	     }
```

* *Getting the access and request tokens*
```curl client:secret@localhost:8899/userauth/oauth/token -d username={username} -d password={password} -d grant_type=password
```