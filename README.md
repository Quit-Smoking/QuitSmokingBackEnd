# 숨쉴래 (QuitSmoking) - Backend[cite: 9]

사용자가 금연 목표를 설정하고 실천할 수 있도록 돕는 금연 보조 웹/앱 플랫폼 '숨쉴래'의 백엔드 시스템입니다[cite: 2]. 

## 🛠 Tech Stack
- **Language**: Java 17[cite: 8]
- **Framework**: Spring Boot 3.3.4[cite: 8], Spring Web MVC[cite: 8]
- **Security**: Spring Security, JWT (jjwt 0.11.5)[cite: 2, 8]
- **Database**: MySQL[cite: 4, 8], Spring Data JPA[cite: 8]
- **Infrastructure**: AWS EC2, AWS RDS, Docker[cite: 2, 5], AWS Route53, ALB[cite: 2]
- **API Docs**: Springdoc OpenAPI (Swagger)[cite: 8]
- **Build Tool**: Maven[cite: 6, 8]

## 🏗 Architecture & Design
- **계층형 아키텍처 (Layered Architecture)**: Controller, Service, Repository, DTO 계층을 분리하여 설계했습니다[cite: 2].
- **도메인형 패키지 구조**: User, Mission, Post, Comment, Store 등 각 기능(도메인)별로 디렉토리를 분리하여 코드 응집도를 높였습니다[cite: 2].

## 📌 Core Features

### 1. 사용자 인증 및 인가 (Security)
- Spring Security와 BCrypt를 활용한 단방향 암호화 비밀번호 저장[cite: 2].
- JWT(JSON Web Token)를 발급하여 상태를 유지하지 않는(Stateless) 안전한 인증 파이프라인 구축[cite: 2].

### 2. 금연 상태 자동 관리 (State Management)
- 사용자의 상태에 따라 `user-start-record`와 `user-cessation-record` 테이블을 분리 운영[cite: 2].
- 금연 중단(stop) 시 현재 진행 중이던 시작 레코드를 삭제하고, 금연 기록 레코드로 데이터를 자동 이관하는 비즈니스 로직 구현[cite: 2].

### 3. 금연 미션 및 캘린더 시스템
- 사용자의 요일별 금연 미션(물 마시기, 운동 등) 생성 및 완료 상태 체크 기능[cite: 2].
- 미션 생성 시 향후 50일 분량의 `mission-record`를 자동 생성하고, 미션 삭제 시 향후 기록을 자동 삭제하여 데이터 정합성 유지[cite: 2].

### 4. 커뮤니티 및 부가 기능
- **게시판 & 댓글**: 사용자 간 금연 기록과 고민을 공유할 수 있는 커뮤니티 CRUD 기능 구현 (좋아요 기능 포함)[cite: 2].
- **니코틴 의존도 진단**: 6가지 설문 문항 데이터를 분석하여 의존도 점수를 도출하는 로직 제공[cite: 2].
- **금연 상점**: 크롤링된 금연 보조제(니코틴 패치, 껌 등) 데이터를 키워드별로 제공[cite: 2].

## 🚀 Troubleshooting

**1. 도메인 연결 및 HTTPS 리디렉션 이슈 해결**[cite: 2]
- **문제**: AWS 환경에서 HTTPS 요청을 처리하는 과정에서 CA 인증서 오류 및 CORS, OPTIONS 요청 에러 발생[cite: 2].
- **해결**: 도메인(`quitsmoking.co.kr`)을 구매한 뒤 AWS Route53 레코드와 EC2를 연결[cite: 2]. AWS Certificate Manager(ACM)로 인증서를 발급받고, 로드밸런서(ALB) 리스너를 설정하여 HTTPS 요청을 내부적으로 HTTP(8080 포트)로 안전하게 리디렉션하도록 인프라 구조를 개선[cite: 2].

**2. Docker 컨테이너 환경의 Timezone 및 프로필 동적 분리**[cite: 5, 8]
- `Dockerfile` 내 `ENV TZ Asia/Seoul` 설정을 통해 서버 시간을 한국 시간으로 동기화[cite: 5].
- `pom.xml`과 Docker 실행 시 `SPRING_PROFILES_ACTIVE` 환경 변수(dev/prod)를 주입받도록 설계하여 로컬과 서버 환경의 DB 연결 등 설정 분리[cite: 5, 8].

## ⚙️ Getting Started

프로젝트를 로컬 환경에서 실행하는 방법입니다.

```bash
# 1. MySQL 컨테이너 실행
docker-compose up -d[cite: 4]

# 2. 프로젝트 빌드 및 실행
./mvnw clean install[cite: 6]
java -jar target/quit-smoking-backend-1.0.0.jar[cite: 5]
