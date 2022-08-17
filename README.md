
## Solo Project : 전국 사업자 연합 커뮤니티 서비스

### 개요  
이용 회원(사업체 대표)의 여러 정보들을 조회하고 관리, 분석하는 애플리케이션
  
### 기능
  - 사이트 내 전체 회원 조회
  - 사이트 내 회원 특정 조건에 따라 조회 (조건 : 지역, 업종)
  
### API 문서 바로가기
https://github.com/leesh26/codestates-solo-project/wiki/API-Document
  
### DB Schema
![image](https://user-images.githubusercontent.com/74662808/185067407-d66b1a6d-53fc-4352-bc1f-61b430e43a65.png)
  
### Reference
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
