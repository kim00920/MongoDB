# 프로젝트 개요
- MongoDB의 특징을 알기 위해서 간단하게 만든 REST API 입니다
- 개발 기간 : 25.06.09 ~ 25.06.10
- 참여 인원 : 1명

# RDBMS / NoSQL 차이점
- 도메인 환경 : 게시글<br>

- 실행 쿼리 : 사용자는 특정 게시글을 조회할떄, 해당 게시글의 내용, 댓글, 대댓글 모두 한번에 조회한다<br>

- RDBMS(MySQL) 처리과정<br>


1. 사용자는 특정 게시글을 조회 한다<br>
2. 이떄 다음과 같은 JOIN 쿼리가 발생한다<br><br>

   **SELECT * FROM post**<br>
   **JOIN comment ON post.id = comment.post_id**<br>
   **LEFT JOIN rc ON comment.id = rc.comment_id**<br>
   **WHERE post.id = ?;**<br>

→  게시글 <> 댓글 <> 대댓글 같은 관계를 JOIN 을 통해 연결하여 데이터를 조회하기 떄문에 댓글 수가 많아 질 수록 성능 저하가 발생할 수 있다.<br><br>

- NoSQL(MongoDB) 처리과정<br>


1. 사용자는 특정 게시글을 조회 한다<br>
2. JOIN 없이 단일 쿼리를 통해 게시글의 내용, 댓글, 대댓글 모두 한 번에 조회 한다.<br>
→ MongoDB는 댓글과 대댓글을 중첩 문서로 게시글에 포함시키는 구조 덕분에, JOIN 없이도 필요한 정보를 조회하여, 성능을 개선 할 수있다<br>


# API 엔드포인트
 <img src="https://github.com/user-attachments/assets/8654fd6f-9e01-4f30-9068-fe09109add61"/>


# 인덱스 생성 및 실행계획
@ID 가 붙은 컬럼이 기본 인덱스로 생성된다<br>


  <img src="https://github.com/user-attachments/assets/ca90a32b-b965-47b7-a669-e21c25099fb5"/><br><br>



ID 를 통해서 정상적으로 인덱스를 타고 있는지 실행 계획 실행<br> →  db.post.find({ _id: ObjectId("게시글 objectId") }).explain("executionStats")<br>


  <img src="https://github.com/user-attachments/assets/85f72c8d-b62f-4d4c-9092-7748dbe5cf1a"/><br><br>

- EXPRESS_IXSCAN : Index Scan 이 발생<br>
- indexName : "_ id  _" : _id 컬럼으로 인덱스를 사용<br>
  → 즉, Full Collection Scan 이 아닌 Index Scan 이 발생됐다.<br>


# RDBMS VS NoSQL 비교 및 테스트
- 공통조건 : 특정 게시글의 댓글 + 대댓글 데이터 합 1000개, 30초 동안 각각 특정 게시글을 조회하는 API 를 1000개, 5000개, 10000개 요청 및 API 속도 측정<br><br>

case 1 : 요청 1000개 + MySQL<br>
case 2 : 요청 1000개 + MongoDB<br>
case 3 : 요청 5000개 + MySQL<br>
case 4 : 요청 5000개 + MongoDB<br>
case 5 : 요청 10000개 + MySQL<br>
case 6 : 요청 10000개 + MongoDB<br><br>

<img src="https://github.com/user-attachments/assets/400f145e-a6de-447b-8962-171ef36c041"/><br>


# 결론