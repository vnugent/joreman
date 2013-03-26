What is Joreman?
================
Joreman is a Java client that enables you to perform common Foreman tasks in Java.

```java
ForemanClient foreman = new ForemanClientFactory().createClient();

ForemanVM vm = foreman.newHost()
                           .withName("MyTestVM")
                           .build();

````
New to Foreman?
===============
Visit http://theforeman.org
