# CEOS back-end 21st vote project

## 컨벤션

### commit 메세지 작성 규칙

- 커밋 메세지명은 `#[issue번호] 동작: 커밋내용` 으로 작성
- 커밋 메세지는 행위의 내용을 모두 포함할 수 있도록 자세히 작성
- commit 메시지 **유형**은 다음과 같다.
    - **feat**: 기능 추가, 삭제, 변경
    - **fix**: 버그 수정
    - **docs**: 문서 추가, 삭제, 변경
    - **style**: 코드 형식, 정렬, 주석 등의 변경, eg; 세미콜론 추가
    - **refactor**: 코드 리펙토링
    - **test**: 테스트 코드 추가
    - **chore**: 위에 해당하지 않는 모든 변경

### Package Structures: Package by Feature (도메인형)

```
Example)

├── src
│   ├── company
│   ├──  ├── CompanyController
│   ├──  ├── CompanyEntity
│   ├──  ├── CompanyRepository
│   ├──  ├── CompanyService
│   ├──  ├── CompanyDTO
│   ├── customer
│   ├──  ├── CustomerController
│   ├──  ├── CustomerEntity
│   ├──  ├── CustomerRepository
│   ├──  ├── CustomerService
│   ├──  ├── CustomerDTO
│   ├── product
│   ├──  ├── ProductController
│   ├──  ├── ProductEntity
│   ├──  ├── ProductRepository
│   ├──  ├── ProductService
│   ├──  ├── ProductDTO
└── └── utils
```

### Work Rules

- 개인 레포지토리로 전체 레포의 Develop 브랜치에서 Fork 하기
- 각자 작업 진행 후 Pull Request 올리기 (절대 직접 커밋 x)
- Pull Request 제목: `동작: 이슈 이름`
- 1명 이상 팀원의 승인이 있으면 Push 가능하도록 룰 설정

## 투표 기능 구현
### ERD
![erd](readme-src/erd.png)

### API 명세서
![api](readme-src/api.png)

### 배포 사이트
- [vote.influy.com](https://vote.influy.com/swagger-ui/index.html#)