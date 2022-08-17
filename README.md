
# ✨ Solo Project : 전국 사업자 연합 커뮤니티 서비스

## 1. 개요  
이용 회원(사업체 대표)의 여러 정보들을 조회하고 관리, 분석하는 애플리케이션
  
## 2. 기술 스택
### Backend
- `Java 11`
- `Gradle`
- `Spring Boot 2.6.10`
- `H2`
- `JPA`
- `JUnit5 / Mockito`
- `Asciidocs`
- `Mapstruct 1.4.2`

### 문서/협업
- Github

### ERD
현재 구현은 임베디드 (변경 예정)
![image](https://user-images.githubusercontent.com/74662808/185074950-8e778ba5-bfa5-4211-a90a-672e4b84399c.png)
  
## 3. 기능
  - 사이트 내 전체 회원 조회
  - 사이트 내 회원 특정 조건에 따라 조회 (조건 : 지역, 업종)
  
### API 문서 바로가기
https://github.com/leesh26/codestates-solo-project/wiki/API-Document
  

  
## 4. Reference
File Structure
```bash
├─docs
│  └─asciidoc
├─main
│  ├─java
│  │  ├─api
│  │  │  └─v1
│  │  │      ├─config
│  │  │      ├─controller
│  │  │      ├─dto
│  │  │      │  ├─request
│  │  │      │  └─response
│  │  │      ├─entity
│  │  │      ├─repository
│  │  │      └─service
│  │  └─codestates
│  └─resources
│      ├─static
│      │  └─docs
│      └─templates
└─test
    └─java
        ├─api
        │  └─v1
        │      ├─slice
        │      └─util
        └─codestates
 ```
