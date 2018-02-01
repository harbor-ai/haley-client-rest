# harbor-client-rest

The primary Harbor APIs are the streaming APIs with JVM (Java/Groovy/Scala) and JavaScript options avalable.

This REST API is used for non-streaming cases for delivery or retrieval of data resources.

Please see the WIKI for the currently available resources and instructions.

This can be built using maven, such as:

```mvn package```

and then can be run using:

```java -cp ./target/haley-client-rest-0.0.1-fat.jar ai.harbor.client.rest.HarborAILeadSubmit <username> <password> [endpoint]```

The JSON submitted is hardcoded to a demo value, and the endpoint has the testing endpoint as default.

If you get a TLS/SSL error, it may be that your Java installation does not have the intermediary certificate of Let's Encrypt installed.

This can be resolved by: (with your JAVA_HOME set)

```wget https://letsencrypt.org/certs/lets-encrypt-x3-cross-signed.der```

```sudo keytool -trustcacerts -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -noprompt -importcert -alias lets-encrypt-x3-cross-signed -file lets-encrypt-x3-cross-signed.der```

See StackOverFlow: https://stackoverflow.com/a/38312489




