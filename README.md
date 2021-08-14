## URL Shortener API

### 1. GET   :   /{code}
Redirect Decoded URL   

<br/>


### 2. Post   :  /general

```
dasdasdsadadd
```
<br/>

### 3. Post   :  /login

```

```
<br/>


***


## 사용방법
<br/>

### 1. Git Clone

```
git clone https://github.com/ghkdwlgns612/URI-Shortener-API.git 
```

<br/>


### 2. H2 DB설정 및 가동

@@ -52,13 +56,15 @@ git clone https://github.com/ghkdwlgns612/URI-Shortener-API.git
spring.datasource.url=jdbc:h2:tcp://localhost/~/test  
spring.datasource.username=sa
```

<br/>


**<src/main/resources/db>**

sql문 실행하여 Test Data와 Table생성하기


<br/>



### 3. 서버 가동
@@ -69,26 +75,26 @@ Terminal창에서 위 디렉토리 경로로 이동하여 아래의 명령 실
```
java -jar ShortURI.main.jar
```

  <br/>


- java 11버전이 아닐 경우

IDE환경에서 직접 Main문 Run실행.


<br/>

### 4. Front Web 접속

**<src/main/resources/front/index.html>**

위의 디렉토리에서 html파일 web으로 실행   
<br/>


***




<br/>

### 실행결과
