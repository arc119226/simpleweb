**API 程式說明**


* 設定檔說明 

```
application-prod.properties 正式環境設定檔
application-dev.properties 開發環境設定案
```


* 包版指令  

`mvn clean package -Dmaven.test.skip=true`


* 運行測試環境指令 
```
mvn clean package -Dspring.profiles.active=dev -Djava.net.preferIPv4Stack=true spring-boot:run /tmp 2>> /dev/null &
or
cd target
java -Dspring.profiles.active=dev -Djava.net.preferIPv4Stack=true -jar api-0.0.1-SNAPSHOT.jar /tmp 2>> /dev/null &
```

* 運行正試環境指令 
```
mvn clean package -Dspring.profiles.active=prod -Djava.net.preferIPv4Stack=true spring-boot:run /tmp 2>> /dev/null &
or
cd target
java -Dspring.profiles.active=prod -Djava.net.preferIPv4Stack=true -jar api-0.0.1-SNAPSHOT.jar /tmp 2>>  /dev/null &
```

* 包docker

安裝docker toolbox

https://docs.docker.com/toolbox/toolbox_install_windows/

啟動Docker Quickstart Terminal

`mvn clean package -Dmaven.test.skip=true docker:build`

到Docker Quickstart Terminal下指令

`docker image list`

**API 文件說明**


**API名稱:Account/CreateAccount****

功能:建立帳號 

路徑:http://hostname:port/Account/CreateAccount 

METHOD:POST 

Headers:

Content-Type:application/json 

輸入格式範例 
```
{
 "signUp":{
	"uname":"0932042885",
	"password":"123456"
 }
}
```
註冊成功輸出格式範例 
```
{
  "responseStatus": "00",
  "responseCode": "000"
}
```
註冊失敗輸出格示範例
```
{
  "responseStatus": "01",
  "responseCode": "001"
}
```

**API名稱:Account/LoginAccount****

功能:帳號登入 

路徑:http://hostname:port/Account/AccountLogin 

METHOD:POST 

Headers:

Content-Type:application/json 

輸入格式範例

```
{
  "userLogin": {
    "userAccount": "0932042885",
    "userPwd": "123456"
  }
}
```
登入成功輸出格式範例
```
{
    "kcToken": {
        "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXNE9pd1hKQThJS2ZDaXRRcTA3M192alE1R21xZ0tSaE14cWJCY1BWZWNFIn0.eyJqdGkiOiI3NDMxNGY1Ni0wOGRhLTQ5ZGEtODU0MS05MDdlMzIwNDJjODciLCJleHAiOjE1NjY1MzAyMTUsIm5iZiI6MCwiaWF0IjoxNTY2NTI5OTE1LCJpc3MiOiJodHRwOi8vMTkyLjE2OC4yMDEuMTI4OjE4MDg3L2F1dGgvcmVhbG1zL3Byb2V4Iiwic3ViIjoiYjJhZTNmYmUtM2RlYS00ZDk3LTgxNjMtNWFlYjYyMzMyNzhhIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicHJvZXgtYXBpIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiNTliMGI2YTEtYTUxMC00YmIzLWIwYWEtYTdkYjc2MGUyYjNjIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJST0xFX0NPTlNVTUVSIl19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIlVTRVJfSUQiOiIxMDMxMTBhNC1jODFjLTQ3MzgtYjc1ZS00OTQ1Nzk0YTUxYTQiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIwOTMyMDQyODg1In0.eXt0D2h_Xf30iJtEp8-Q8Ahk1fMtZMUKTNvnt49yDMVqf7DniamutHl2B40lHbmW7UjkqGhOX1nVwMA_fbE3Yp9CAjDpfWkgHyB85SdYbu47ifOnRvBqOtMftyZocV6UlYUA8yIVzldyNlh7rHNWIy-JoxW5cGt74zsyHzCbbkfkgYZQud_DXxoxBENGjYgP8KlK8MLeEKX8nwxCt-3eGPkSjE8Hpu_U7wfNjvFZ-3PWOvFefCTLuKMIO5OY2WqUDsaBpGfEM12UMxahHu0HNx-nx_Nq_bp1PCB7LhuTRUg86pUS7fMIiLgPq1JA7GkjNL3W3ie9keiSf-yOnGFrUg",
        "expires_in": 300,
        "refresh_expires_in": 1800,
        "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIwMmFhNzgxMy04NjhkLTRiN2QtYWRiOS0wMzg1NzY0M2NkZTQifQ.eyJqdGkiOiJjMzJmODMxMy1hODkwLTQ5NjItOTgxYy0zNGUwZTZmM2QzNGUiLCJleHAiOjE1NjY1MzE3MTUsIm5iZiI6MCwiaWF0IjoxNTY2NTI5OTE1LCJpc3MiOiJodHRwOi8vMTkyLjE2OC4yMDEuMTI4OjE4MDg3L2F1dGgvcmVhbG1zL3Byb2V4IiwiYXVkIjoiaHR0cDovLzE5Mi4xNjguMjAxLjEyODoxODA4Ny9hdXRoL3JlYWxtcy9wcm9leCIsInN1YiI6ImIyYWUzZmJlLTNkZWEtNGQ5Ny04MTYzLTVhZWI2MjMzMjc4YSIsInR5cCI6IlJlZnJlc2giLCJhenAiOiJwcm9leC1hcGkiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI1OWIwYjZhMS1hNTEwLTRiYjMtYjBhYS1hN2RiNzYwZTJiM2MiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiUk9MRV9DT05TVU1FUiJdfSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIn0.H0OrLsPTiV2qQHakkdpXOx61SjhyXH6FEFK7Bej7Zeo",
        "token_type": "bearer",
        "not_before_policy": 0,
        "session_state": "59b0b6a1-a510-4bb3-b0aa-a7db760e2b3c",
        "scope": "email profile"
    },
    "userInfo": {
        "uuid": "103110a4-c81c-4738-b75e-4945794a51a4",
        "authLevel": 0,
        "authLevelPre": 0,
        "authStatus": 0,
        "authSubmitTime": "Aug 23, 2019 10:52:54 AM",
        "authTime": "Aug 23, 2019 10:52:54 AM",
        "createTime": "Aug 23, 2019 10:52:54 AM",
        "fdPasswordTime": 0,
        "fdPwdOrderEnabled": "1",
        "gender": 1,
        "invitedUid": 0,
        "isAuth": 0,
        "isAuthPrimary": 0,
        "isAuthSenior": 0,
        "isAuthVideo": 0,
        "isPhoneAuth": 0,
        "isValidateEmail": 0,
        "isValidatePass": 0,
        "isValidatePhone": 0,
        "openAuthVideo": 0,
        "password": "$2a$10$MgPf1VjhXKKsaBPPFISQL.MjNP1YYjpOc7/qgstRhsMTM7dHJu9Fm",
        "profession": "1",
        "readFailReason": 0,
        "realLocation": 0,
        "refuseReasonId": 0,
        "registerSource": 0,
        "riskLevel": 0,
        "secureLevel": 0,
        "status": 0,
        "uid": 44,
        "uname": "0932042885",
        "updateTime": "Aug 23, 2019 10:52:54 AM",
        "userType": 0
    },
    "appUserLog": {
        "uid": 120,
        "authTime": "Aug 23, 2019 11:11:55 AM",
        "country": "Taiwan",
        "createTime": "Aug 23, 2019 11:11:55 AM",
        "email": "0932042885",
        "ip": "127.0.0.1",
        "isEmailAuth": 0,
        "isPhoneAuth": 0,
        "isValidateEmail": 0,
        "isValidatePhone": 0,
        "lastLoginTime": "Aug 23, 2019 11:11:55 AM",
        "location": "Taipei City",
        "nation": "Taiwan",
        "token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXNE9pd1hKQThJS2ZDaXRRcTA3M192alE1R21xZ0tSaE14cWJCY1BWZWNFIn0.eyJqdGkiOiI3NDMxNGY1Ni0wOGRhLTQ5ZGEtODU0MS05MDdlMzIwNDJjODciLCJleHAiOjE1NjY1MzAyMTUsIm5iZiI6MCwiaWF0IjoxNTY2NTI5OTE1LCJpc3MiOiJodHRwOi8vMTkyLjE2OC4yMDEuMTI4OjE4MDg3L2F1dGgvcmVhbG1zL3Byb2V4Iiwic3ViIjoiYjJhZTNmYmUtM2RlYS00ZDk3LTgxNjMtNWFlYjYyMzMyNzhhIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicHJvZXgtYXBpIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiNTliMGI2YTEtYTUxMC00YmIzLWIwYWEtYTdkYjc2MGUyYjNjIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJST0xFX0NPTlNVTUVSIl19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIlVTRVJfSUQiOiIxMDMxMTBhNC1jODFjLTQ3MzgtYjc1ZS00OTQ1Nzk0YTUxYTQiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIwOTMyMDQyODg1In0.eXt0D2h_Xf30iJtEp8-Q8Ahk1fMtZMUKTNvnt49yDMVqf7DniamutHl2B40lHbmW7UjkqGhOX1nVwMA_fbE3Yp9CAjDpfWkgHyB85SdYbu47ifOnRvBqOtMftyZocV6UlYUA8yIVzldyNlh7rHNWIy-JoxW5cGt74zsyHzCbbkfkgYZQud_DXxoxBENGjYgP8KlK8MLeEKX8nwxCt-3eGPkSjE8Hpu_U7wfNjvFZ-3PWOvFefCTLuKMIO5OY2WqUDsaBpGfEM12UMxahHu0HNx-nx_Nq_bp1PCB7LhuTRUg86pUS7fMIiLgPq1JA7GkjNL3W3ie9keiSf-yOnGFrUg",
        "uname": "0932042885",
        "uuid": "103110a4-c81c-4738-b75e-4945794a51a4"
    },
    "responseStatus": "00",
    "responseCode": "000"
}
```
登入失敗輸出格式範例
```
{
    "responseStatus": "97",
    "responseCode": "002",
    "kcToken": null,
    "userInfo": null,
    "appUserLog": null,
    "message": "error(002) keycloak登入帳密錯誤"
}
```