# URL-Shortener API!

## API Docs

### 1. GET   :   /sa/:code
Redirect Decoded URL   
   
<br/>


### 2. Post   :  /{originUri}
Create Encoded URL   
   
   

```
Post /{originUri}

200
{	
	"statusCode":200,
	"message":"OK",
	"data":{
		"originUri":"https://naver.com",
		"changedUri":"KPy1"
		}
}

404
{
	"message":"유효하지 않은 URI입니다.",
	"statusCode":404,
	"err":"https://123"
}
```
   
<br/>
      
***


## 사용방법

### 1. Clone
```
git clone https://github.com/ghkdwlgns612/URI-Shortener-API.git 
```
   
<br/>


### 2. H2 DB설정 및 가동

**<src/main/application.properties>**
```
spring.datasource.url=jdbc:h2:tcp://localhost/~/test  
spring.datasource.username=sa
```
<br/>

   
**<src/main/resources/db>**

sql문 실행하여 Test Data와 Table생성하기
   
<br/>



### 3. 서버 가동

**</jar/ShortURI_main_jar/ShortURI.main.jar>**

Terminal창에서 위 디렉토리 경로로 이동하여 아래의 명령 실행(주의. Java11버전만 가능)
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

**<URI 변환>**

![news](https://user-images.githubusercontent.com/68694844/127809391-2704055b-7657-428c-a23e-037203e2aa93.gif)



**<변환 후 접속>**

![nesnew](https://user-images.githubusercontent.com/68694844/127810372-9a1e2243-759a-4f58-8a56-f83eac05171b.gif)

