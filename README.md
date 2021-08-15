## URL Shortener API

### 1. GET   :   /{code}
Redirect Decoded URL   

<br/>


### 2. Post   :  /general/{originUri}

```
Post /general/{originUri}

200
{
    "statusCode": 200,
    "message": "OK",
    "data": {
        "hashValue": "577abc5c77",
        "originUrl": "https://naver.com",
        "encodedValue": "gChowKX",
        "name": null
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

### 3. Post   :  /login/{originUri}&{name}

```
Post login/{originUri}&{name}

200
{
    "statusCode": 200,
    "message": "OK",
    "data": {
        "hashValue": "004ebee35c",
        "originUrl": "https://github.com/ghkdwlgns612/uri-shortener-api",
        "encodedValue": "ab2zvbc",
        "name": "jihuhwan"
    }
}

404
{
	"message":"유효하지 않은 URI입니다.",
	"statusCode":404,
	"err":"https://123"
}

417
{
    "message": "이미 변환된 URL입니다.",
    "statusCode": 417,
    "err": "https://github.com/ghkdwlgns612/uri-shortener-api"
}
```
<br/>


***


## 사용방법
<br/>

### 1. Git Clone
<br/>

```
git clone https://github.com/ghkdwlgns612/URI-Shortener-API.git 
```

<br/>


### 2. DB설정 및 가동
<br/>

각 DB에 맞게 가동 후 테이블 생성(/main/resource/db/scheme.sql)



<br/>



### 3. 서버 가동
<br/>

```
java -jar ShortURI.main.jar
```

<br/>


- java 11버전이 아닐 경우

IDE환경에서 직접 Main문 Run실행.


<br/>

### 4. Front Web 접속
<br/>

**<src/main/resources/general/index.html>**

위의 디렉토리에서 html파일 web으로 실행   
<br/>




