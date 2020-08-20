**後台WEB程式**


- 
![](doc/後台UI.png) 


開發環境運行指令

`grails dev run-app`

包版環境(測試過的穩定版本組合)

```
grailsVersion=3.3.5
gormVersion=6.1.9.RELEASE
gradleWrapperVersion=3.5
```

包版指令

`grails package war`

包板之後檔案位於`./build/libs/console-0.1.war`


運行指令

`java -jar console-0.1.war`
