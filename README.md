# 프로젝트 개요
- MongoDB의 특징을 알기 위해서 간단하게 만든 REST API 입니다
- 개발 기간 : 25.06.09 ~ 25.06.10
- 참여 인원 : 1명

# RDBMS, NoSQL 차이점
- 게시글, 댓글, 대댓글 테이블이 특정 게시글의 모든 정보를 조회하는 API 를 사용하면, 이떄 JOIN 비용이 발생하고 다음과 같이 쿼리가 발생할 것이다.<br>

  SELECT * FROM post<br>
  JOIN comment ON post.id = comment.post_id<br>
  LEFT JOIN rc ON comment.id = rc.comment_id<br>

→ 이떄 발생하는 JOIN 비용은 데이터 양이 많아질수록 성능 문제로 이어질수있다<br>
→ MongoDB 의 특징인 중첩 구조를 통해서 RDBMS 와 다르게 별도의 JOIN 없이 한 번의 조회로 모든 정보를 가져와 비용을 절감할수있다<br>

# 임시
(Postman 사진 넣기)


# MongoDB 인덱스 생성
-  @Indexed(direction = IndexDirection.DESCENDING) 를 통해서 생성일 DESC 인덱스를 생성 <br>
- 그 후 MongoDb shell 에서 db.post.find().sort({createdAt: -1}).limit(10).explain("executionStats") 를 통해서 게시글 전체 조회 API 가 인덱스를 탔는지 실행계획 확인<br>

(사진 넣기)

# RDBMS VS NoSQL 비교 및 테스트
- 공통조건 : Post 테이블 더미데이터 5000개 및 테이블 전체 조회(createdAt DESC → 정렬) 비교<br> 
RDBMS, NoSQL 모두 createdAt 내림차순 인덱스 정렬 사용

case 1 : 요청 1000개 + MySQL<br>
case 2 : 요청 1000개 + MongoDB<br>
case 3 : 요청 5000개 + MySQL<br>
case 4 : 요청 5000개 + MongoDB<br>

(표 넣기)

# 결론