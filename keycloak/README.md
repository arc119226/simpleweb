keycloak設定文件


環境
linux or windows

mysql db

```
CREATE USER 'keycloak'@'%' IDENTIFIED BY 'keycloak';

CREATE DATABASE keycloak CHARACTER SET utf8 COLLATE utf8_unicode_ci;

GRANT ALL PRIVILEGES ON keycloak.* TO 'keycloak'@'%';
```


keycloak下載地址
https://www.keycloak.org/downloads.html 

datasource設定

1.將mysql driver jar檔放入此目錄(若找不到目錄請自行建立)
${keycloak_home}\modules\system\layers\keycloak\com\mysql\main

2.設定module.xml
於${keycloak_home}\modules\system\layers\keycloak\com\mysql\main目錄下，建立一個module.xml檔案，內容為:
```
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.3" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-java-8.0.17.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```
3.編輯${keycloak_home}\standalone\configuration\standalone.xml
修改`<datasources>...</datasources>`中的內容如下

```
<datasources>
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
        <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
        <driver>h2</driver>
        <security>
            <user-name>sa</user-name>
            <password>sa</password>
        </security>
    </datasource>
	<!--
    <datasource jndi-name="java:jboss/datasources/KeycloakDS" pool-name="KeycloakDS" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
        <connection-url>jdbc:h2:${jboss.server.data.dir}/keycloak;AUTO_SERVER=TRUE</connection-url>
        <driver>h2</driver>
        <security>
            <user-name>sa</user-name>
            <password>sa</password>
        </security>
    </datasource>
	-->
	<datasource jndi-name="java:jboss/datasources/KeycloakDS" pool-name="KeycloakDS" enabled="true" use-java-context="true">
		<connection-url>jdbc:mysql://localhost:3306/keycloak?serverTimezone=UTC</connection-url>
		<driver>mysql</driver>
		<pool>
			<max-pool-size>20</max-pool-size>
		</pool>
		<security>
			<user-name>admin</user-name>
			<password>admin</password>
		</security>
	</datasource>
    <drivers>
		<driver name="h2" module="com.h2database.h2">
            <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
        </driver>
		<driver name="mysql" module="com.mysql">
			<xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
		</driver>
    </drivers>
</datasources>
```
修改`<interfaces>...</interface>`中的的內容如下
```
    <interfaces>
        <interface name="management">
            <inet-address value="${jboss.bind.address.management:0.0.0.0}"/>
        </interface>
        <interface name="public">
            <inet-address value="${jboss.bind.address:0.0.0.0}"/>
        </interface>
    </interfaces>
```
修改`<socket-binding-group>...</socket-binding-group>`中的內容如下

```
<socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    <socket-binding name="management-http" interface="management" port="${jboss.management.http.port:19990}"/>
    <socket-binding name="management-https" interface="management" port="${jboss.management.https.port:19993}"/>
    <socket-binding name="ajp" port="${jboss.ajp.port:18009}"/>
    <socket-binding name="http" port="${jboss.http.port:18080}"/>
    <socket-binding name="https" port="${jboss.https.port:18443}"/>
    <socket-binding name="txn-recovery-environment" port="14712"/>
    <socket-binding name="txn-status-manager" port="14713"/>
    <outbound-socket-binding name="mail-smtp">
        <remote-destination host="msa.hinet.net" port="25"/>
    </outbound-socket-binding>
</socket-binding-group>
```

啟動方式
bin\standalone.bat

與spring boot 界接方式 
https://medium.com/@techgeek628/easily-secure-your-spring-boot-applications-with-keycloak-41e09acc88fd
