# API Gateway 서비스

각 서비스 간의 로드밸런스 역할을 해 주는 서비스이다.
클라이언트는 해당 Gateway 를 통해서 서비스를 호출하며, 클라이언트는 각 서비스가 어디에 존재하는지 알 필요 없이 Gateway 만 호출하면 된다.

## 1. Gateway 방식

각 서비스가 등록된 Eureka 에서 해당 서비스로 등록된 서비스를 name 값으로 찾아간다.
찾아낸 name 값을 통해 적절한 서비스를 통해 통신을 진행한다.
원한다면 인증에 대한 내용을 설정해서 각 서비스로 가기 전에 체크를 할 수 있다.

### 등록된 서비스가 아닐 경우 응답값

```json
{
    "success": false,
    "response": {},
    "message": "503 SERVICE_UNAVAILABLE \"Unable to find instance for ACCOUNT-SERVICE\"",
    "status": 503
}
```

## 2. 인증

각 서비스는 인증을 할 필요 없고, 해당 게이트웨이에서 JWT Token 을 인증해 준다.
만약 게이트웨이에서 인증에 대한 오류가 발생한다면 `JwtAuthenticationTokenFilter` 에서 처리하고, 만약 토큰이 만료된다면 -401 을 리턴한다.

### 토큰이 만료 되었을 경우 응답값

```json
{
    "success": false,
    "response": {},
    "message": "The Token has expired on Sun Apr 19 19:56:03 KST 2025.",
    "status": -401
}
```

### 토큰 정보가 없거나 토큰에 대한 헤더가 잘못된 경우 응답값

```json
{
    "success": false,
    "response": {},
    "message": "인증 토큰이 유효하지 않습니다.",
    "status": 401
}
```

