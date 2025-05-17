# 🌩️ Spring Cloud Config Server

## 🔍 개요
Spring Cloud Config Server는 **분산 시스템의 설정을 중앙에서 관리**할 수 있게 도와주는 컴포넌트.  
애플리케이션이 필요한 설정 값을 **Git, SVN, 파일 시스템 등 외부 저장소**에서 읽어오도록 지원하며,  
이를 통해 **환경별 설정 관리, 버전 관리, 배포 자동화**에 유리한 구조를 만들 수 있다.

---

## 📦 주요 구성 요소

| 구성 요소         | 설명                                     |
|---------------|----------------------------------------|
| Config Server | 설정 값을 제공하는 서버 (Git 등에서 값을 읽음)          |
| Config Client | 설정 값을 사용하는 애플리케이션 (Spring Boot 앱 등)    |
| Backend Store | Git, SVN, File System 등 설정이 저장된 외부 저장소 |

---

## 🧱 주요 특징

- Git, SVN, 파일 시스템 등 다양한 백엔드 저장소 지원
- 애플리케이션 이름 및 프로파일별 설정 분리 가능 (`application-{profile}.yml`)
- Spring Cloud Bus와 연동해 설정 변경 실시간 반영 가능
- 보안(암호화/복호화) 기능 지원

---

## 🛠️ 설정 예시

### 📁 1. git 정보 설정
- 각자의 깃 로그인 정보를 `application-local.yml`에 설정햔다.
- `application-local.yml`은 `.gitignore`에 추가되어 있어 깃에 푸시되지 않는다.

**application-local.yml**
```yml
spring:
  cloud:
    config:
      server:
        git:
          uri: {your-git-repo-url}
          default-label: main
          username: {your-git-username}
          password: {your-git-token}
```

### 📁 2. 암호화/복호화 설정
- 강력한 암복호화를 위해 keytool 을 사용하여 비대칭키를 이용한 알고리즘을 사용한다.

**암호화 키 생성**
```bash
keytool -genkeypair -alias apiEncryptionKey -keyalg RSA -dname "CN=Seunggu Lee, OU=API Development, O=amadda.co.kr, L=Seoul, C=KR" -keypass "{yourpassword}" -keystore apiEncryptionKey.jks -storepass "{yourpassword}"
```
- 암호화 된 jks 파일의 경로를 `bootstrap.yml`에 설정한다.

**bootstrap.yml**
```yml
encrypt:
  key-store:
    alias: apiEncryptionKey
    password: password
    location: file://${user.home}/Desktop/Work/keystore/apiEncryptionKey.jks
```
- password 는 JVM 옵션으로 실행시 설정한 비밀번호를 넣어 준다.
- location 은 암호화된 jks 파일의 경로를 넣어 준다. (윈도우의 경우 file:/// -> '/' 이 세번 필요.)

### 📁 3. 암호화/복호화
- 해당 프로젝트를 실행한 이후 아래의 api 를 통해 암호화/복호화를 진행할 수 있다.

**암호화**
```bash
curl -X POST http://local.amadda.com:8888/encrypt -H 'Content-Type: application/json' -d '암호화'
``
**결과값**
```text
AYCbs60BTlTtt+EvF9ZnPe2GCJjsC2ydAzJWffIKzIaCXequ4CyqJV2IwcQd2Dzhc53rSrYVK/KesOw6pT88iMHTmjxJek8ZchtifNzqO84fr77A6Q1NdZ7Kfa9JqTu/wBJTaxHXf05Zpz0Rd/CBZoE8vT1pT7I425IkrbnR87AC8Q32+8ZNXQIWusvoZVRIh4d+fylwOAocaUkA6H8eSgHQi2BlhLWSGzK1N8kh46t1xiv0GoyCV8gkXHkSGynR5g7JeQO1+TGAwa2UPQIUpTdCe0QyYbWs7uNeBKZ6UGEVrdOUsTOMNyA0TrXjurLjDiVwNoFwPmTRDmTqQySH7DnlshlYFcSs93eiCXkl1MPNF0RWlzMm4i0KS1Y244hTt7jVzkGnqOoLBHUsiAfPVcsk6Is+k6/Kz7MWhQ41CC9Z1/pXnFhHb9CRoBZuFE0aOHTPZGmJgYx/f9LheSCcfu4esUb2mPeG2AGz1E89jHkKgPNXT5dAM0XiihKpdoFUUG2iw247VvCPCbG+hPIk9+ygFC22K9AHvx/vHClSkz2cwg==
```

**복호화**
```bash
curl -X POST http://local.amadda.com:8888/decrypt -H 'Content-Type: application/json' -d 'AYCbs60BTlTtt+EvF9ZnPe2GCJjsC2ydAzJWffIKzIaCXequ4CyqJV2IwcQd2Dzhc53rSrYVK/KesOw6pT88iMHTmjxJek8ZchtifNzqO84fr77A6Q1NdZ7Kfa9JqTu/wBJTaxHXf05Zpz0Rd/CBZoE8vT1pT7I425IkrbnR87AC8Q32+8ZNXQIWusvoZVRIh4d+fylwOAocaUkA6H8eSgHQi2BlhLWSGzK1N8kh46t1xiv0GoyCV8gkXHkSGynR5g7JeQO1+TGAwa2UPQIUpTdCe0QyYbWs7uNeBKZ6UGEVrdOUsTOMNyA0TrXjurLjDiVwNoFwPmTRDmTqQySH7DnlshlYFcSs93eiCXkl1MPNF0RWlzMm4i0KS1Y244hTt7jVzkGnqOoLBHUsiAfPVcsk6Is+k6/Kz7MWhQ41CC9Z1/pXnFhHb9CRoBZuFE0aOHTPZGmJgYx/f9LheSCcfu4esUb2mPeG2AGz1E89jHkKgPNXT5dAM0XiihKpdoFUUG2iw247VvCPCbG+hPIk9+ygFC22K9AHvx/vHClSkz2cwg=='
```
**결과값**
```text
암호화
```
- 위의 암호화된 값은 `Backend Store`에 '{cipher}'+암호화된 값 으로 넣어주면 된다.
- 예시) `common.yml`
```yml
database:
  password: '{cipher}AYCbs60BTlTtt+EvF9ZnPe2GCJjsC2ydAzJWffIKzIaCXequ4CyqJV2IwcQd2Dzhc53rSrYVK/KesOw6pT88iMHTmjxJek8ZchtifNzqO84fr77A6Q1NdZ7Kfa9JqTu/wBJTaxHXf05Zpz0Rd/CBZoE8vT1pT7I425IkrbnR87AC8Q32+8ZNXQIWusvoZVRIh4d+fylwOAocaUkA6H8eSgHQi2BlhLWSGzK1N8kh46t1xiv0GoyCV8gkXHkSGynR5g7JeQO1+TGAwa2UPQIUpTdCe0QyYbWs7uNeBKZ6UGEVrdOUsTOMNyA0TrXjurLjDiVwNoFwPmTRDmTqQySH7DnlshlYFcSs93eiCXkl1MPNF0RWlzMm4i0KS1Y244hTt7jVzkGnqOoLBHUsiAfPVcsk6Is+k6/Kz7MWhQ41CC9Z1/pXnFhHb9CRoBZuFE0aOHTPZGmJgYx/f9LheSCcfu4esUb2mPeG2AGz1E89jHkKgPNXT5dAM0XiihKpdoFUUG2iw247VvCPCbG+hPIk9+ygFC22K9AHvx/vHClSkz2cwg=='
```