/*
############테이블 정보############
## 영화 테이블 ##
영화넘버       -- primary key
영화제목
장르
줄거리
연령제한
썸네일(명장면?)
포스터
상영여부
등록일       -- sysdate

## 제작진(배우 포함) 테이블 ##
제작진넘버       -- primary key
영화넘버    -- 테이블 join을 위해
제작진 이름
제작진 역할
제작진 사진
등록일       -- sysdate

## 리뷰 테이블 ##
리뷰넘버       -- primary key
영화넘버    -- 테이블 join을 위해
회원넘버    -- 테이블 join을 위해
회원 아이디
리뷰 제목
리뷰 내용
평점
등록일       -- sysdate

## 회원 정보 테이블 ##
회원넘버       -- primary key
ID
비밀번호
이메일 주소
등록일       -- sysdate

####################################
*/


DROP TABLE R_MOVIE;
DROP SEQUENCE MOVIE_NO_SEQ;
DROP TABLE R_STAFF;
DROP SEQUENCE STAFF_NO_SEQ;
drop table R_REVIEW;
drop sequence REVIEW_NO_SEQ;
DROP TABLE R_MEMBER;
drop sequence MEMBER_NO_SEQ;



/*
영화 테이블
영화넘버MNO
제목
장르
줄거리
연령제한
포스터
썸네일(명장면?)
상영여부
등록일
*/


create table R_MOVIE (
	NO number primary key not null,
    TITLE varchar2(100) not null,
    GENRE varchar2(100) not null,
    SUMMARY_TITLE CLOB not null,
    SUMMARY_CONTENT CLOB not null,
    AGE_LIMIT varchar2(6),
    POSTER varchar2(100),
    THUMBNAIL varchar2(100),
    SCREENING varchar2(1),
    REG_DATE date not null
);


CREATE SEQUENCE MOVIE_NO_SEQ
INCREMENT BY 1
START WITH 1;

INSERT INTO R_MOVIE VALUES(
MOVIE_NO_SEQ.NEXTVAL, 
'어바웃타임스', 
'코미디, 로맨스', 
'영화 줄거리의 흥미로운 부분이나 대사를 두 줄로 진하게 강조합니다', 
'모태솔로 팀(돔놀 글리슨)은 성인이 된 날, 아버지(빌 나이)로부터 놀랄만한 가문의 비밀을 듣게 된다. 
 바로 시간을 되돌릴 수 있는 능력이 있다는 것!
 그것이 비록 히틀러를 죽이거나 여신과 뜨거운 사랑을 할 수는 없지만, 여자친구는 만들어 줄 순 있으리..
 
 꿈을 위해 런던으로 간 팀은 우연히 만난 사랑스러운 여인 메리에게 첫눈에 반하게 된다.
 그녀의 사랑을 얻기 위해 자신의 특별한 능력을 마음껏 발휘하는 팀.
 어설픈 대시, 어색한 웃음은 리와인드! 뜨거웠던 밤은 더욱 뜨겁게 리플레이!
 꿈에 그리던 그녀와 매일매일 최고의 순간을 보낸다.
 
 하지만 그와 그녀의 사랑이 완벽해질수록 팀을 둘러싼 주변 상황들은 미묘하게 엇갈리고, 예상치 못한 사건들이 여기저기 나타나기 시작하는데…
 
 어떠한 순간을 다시 살게 된다면, 과연 완벽한 사랑을 이룰 수 있을까?
',
15,
'thumbnail1.jpg',
'poster1.jpg',
'Y',
SYSDATE
);

INSERT INTO R_MOVIE VALUES(
MOVIE_NO_SEQ.NEXTVAL, 
'트랜스포머', 
'SF, 액션, 모험', 
'두 거장의 첫 초대형 프로젝트 | 모든 것은 변신한다! | 함부로 상상하지 마라', 
'인류보다 월등히 뛰어난 지능과 파워를 지닌 외계 생명체 ‘트랜스포머’. 정의를 수호하는 ‘오토봇’ 군단과 악을 대변하는 ‘디셉티콘’ 군단으로 나뉘는 ‘트랜스포머’는 궁극의 에너지원인 ‘큐브’를 차지하기 위해 오랜 전쟁을 벌여왔다. 하지만 행성 폭발로 우주 어딘가로 사라져버린 ‘큐브’. 그들은 오랜 전쟁의 종지부를 찍기 위해 ‘큐브’를 찾아 전 우주를 떠돈다.
  인류의 미래를 좌우할 에너지원인 ‘큐브’는 우주를 떠돌다 지구에 떨어지고 디셉티콘 군단의 끈질긴 추적 끝에 ‘큐브’가 지구에 있다는 사실을 알아낸다. 그들은 최후의 전쟁의 무대로 지구를 택하고, 아무도 모르게 지구로 잠입한다. 어떤 행성이든지 침입해 그 곳에 존재하는 기계로 변신, 자신의 존재를 숨길 수 있는 트랜스포머는 자동차, 헬기, 전투기 등의 다양한 형태로 변신해 인류의 생활에 깊숙이 침투한다. 그리고 그들은 큐브의 위치를 찾는데 결정적인 열쇠를 지닌 주인공 ‘샘’의 존재를 알게 된다.
  이 모든 사실을 전혀 모른 채 살아가던 ‘샘’은 어느 날 밤, 자신의 낡은 자동차가 거대한 로봇으로 변신하는 놀라운 현장을 목격한다. 그의 자동차는 ‘트랜스포머’ 중 정의를 수호하는 ‘오토봇’ 군단의 ‘범블비’로 큐브를 차지하기 위해 음모를 꾸미는 ‘디셉티콘’ 군단에 맞서 샘을 보호하기 위해 파견된 트랜스포머. 우주의 질서를 수호하기 위해 반드시 샘을 지켜야만 하는 범블비는 자신이 형제들인 ‘오토봇’ 군단을 지구로 불러모으기 시작하는데..
  거대 에너지원 ‘큐브’가 디셉티콘의 손에 들어가는 것만은 막아야 한다. 샘을 찾아내 큐브를 손에 쥐어 지구를 지배하려는 악의 ‘디셉티콘’ 군단과 지구를 지키려는 정의의 ‘오토봇’ 군단의 인류의 운명을 건 숨막히는 대결이 펼쳐진다.
',
12,
'thumbnail2.jpg',
'poster2.jpg',
'N',
SYSDATE
);

INSERT INTO R_MOVIE VALUES(
MOVIE_NO_SEQ.NEXTVAL, 
'인사이드아웃', 
'애니메이션, 코미디', 
'“괜찮아, 다 잘 될 거야! 우리가 행복하게 만들어 줄게”', 
'모든 사람의 머릿속에 존재하는 감정 컨트롤 본부
 그곳에서 불철주야 열심히 일하는 기쁨, 슬픔, 버럭, 까칠, 소심 다섯 감정들.
 이사 후 새로운 환경에 적응해야 하는 ‘라일리’를 위해
 그 어느 때 보다 바쁘게 감정의 신호를 보내지만
 우연한 실수로 ‘기쁨’과 ‘슬픔’이 본부를 이탈하게 되자
 ‘라일리’의 마음 속에 큰 변화가 찾아온다.
 ‘라일리’가 예전의 모습을 되찾기 위해서는 ‘기쁨’과 ‘슬픔’이 본부로 돌아가야만 한다!
 그러나 엄청난 기억들이 저장되어 있는 머릿속 세계에서 본부까지 가는 길은 험난하기만 한데…
 과연, ‘라일리’는 다시 행복해질 수 있을까?
 
 지금 당신의 머릿속에서 벌어지는 놀라운 일!
 하루에도 몇번씩 변하는 감정의 비밀이 밝혀진다!
',
'전체',
'thumbnail2.jpg',
'poster2.jpg',
'N',
SYSDATE
);


/*
제작진(배우 포함) 테이블
제작진넘버
영화넘버
제작진 이름
제작진 역할
제작진 사진
등록일
*/


create table R_STAFF(
    NO number primary key not null,
    MOVIE_TITLE varchar2(100) not null,
    NAME varchar2(100) not null,
    ROLE varchar2(100) not null,
    PHOTO varchar(100),
    REG_DATE date not null
);


CREATE SEQUENCE STAFF_NO_SEQ
INCREMENT BY 1
START WITH 1;


INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'인사이드 아웃',
'피트 닥터',
'감독',
'STAFF1.JPG',
SYSDATE
);

INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'인사이드 아웃',
'에이미 포엘러',
'주연',
'STAFF2.JPG',
SYSDATE
);

INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'인사이드 아웃',
'필리스 스미스',
'주연',
'STAFF3.JPG',
SYSDATE
);




INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'트랜스포머',
'마이클 베이',
'감독',
'STAFF4.JPG',
SYSDATE
);

INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'트랜스포머',
'샤이아 라보프',
'주연',
'STAFF5.JPG',
SYSDATE
);

INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'트랜스포머',
'타이레스',
'주연',
'STAFF6.JPG',
SYSDATE
);


INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'어바웃 타임',
'리차드 커티스',
'감독',
'STAFF7.JPG',
SYSDATE
);

INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'어바웃 타임',
'도널 글리슨',
'주연',
'STAFF8.JPG',
SYSDATE
);

INSERT INTO R_STAFF VALUES(
STAFF_NO_SEQ.NEXTVAL, 
'어바웃 타임',
'레이첼 맥아담스',
'주연',
'STAFF9.JPG',
SYSDATE
);




/*
리뷰 테이블
리뷰넘버
영화넘버
회원넘버
리뷰 제목
리뷰 내용
평점
등록일
*/

create table R_REVIEW(
    NO number primary key not null,
    MOVIE_NO number not null,
    MEMBER_NO number not null,
    TITLE CLOB not null,
    CONTENT CLOB not null,
    GRADE NUMBER(5), 
    REG_DATE date not null
);

CREATE SEQUENCE REVIEW_NO_SEQ
INCREMENT BY 1
START WITH 1;

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    1,
    1,
    '영화 최고',
    '시간을 되돌릴 수 있다면… 결과는 같다? 씨네21 | 이현경 - 스포성 전문가 평점 좀 없었으면 좋겠다 젠장',
    '4',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    1,
    2,
    '영화 조아',
    '가슴을 따뜻하게 해주는 정말 완전소중한 영화 ㅠ~ㅠ',
    '4',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    1,
    3,
    '어바웃타임 짱',
    '아버지와 탁구시합 후 산책하러 가는장면에서 너무 많이울었네요. 지금도 눈물이 살짝 고이네요.네이버 평점에 처음 글 남깁니다. 너무 감동이었고 다시금보고프게 만드는 영화입니다',
    '3',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    2,
    1,
    '트랜스포머 최고',
    '재밋습니다 후회없습니다',
    '4',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    2,
    2,
    '트포 조아',
    '가슴 따뜻해지는 영화입니다. 올겨울 강추',
    '4',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    2,
    3,
    '트랜스 짱',
    '가슴을 따뜻하게 해주는 정말 완전소중한 영화 ㅠ~ㅠ',
    '3',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    3,
    1,
    '인싸 최고',
    '인생은 모두가 함께 하는 여행이다. ',
    '2',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    3,
    2,
    '인싸 조아',
    '아빠랑 산책하자',
    '4',
    SYSDATE
);

INSERT INTO R_REVIEW VALUES(
    REVIEW_NO_SEQ.NEXTVAL,
    3,
    1,
    '트랜스 짱',
    '어... 중요한 약속이 생겨서 빨리가봐야해!',
    '3',
    SYSDATE
);


/*
회원 정보 테이블
회원넘버
ID
비밀번호
이메일 주소
*/



create table R_MEMBER(
    NO number primary key not null,
    ID VARCHAR2(100) not null,
    PASS VARCHAR2(100) not null,
    EMAIL VARCHAR2(100) not null,
    REG_DATE date not null
);

CREATE SEQUENCE MEMBER_NO_SEQ
INCREMENT BY 1
START WITH 1;

insert into R_MEMBER VALUES(
    MEMBER_NO_SEQ.NEXTVAL,
    'HelloWorld111',
    'oraclejava',
    'HelloWorld111@naver.com',
    SYSDATE
);

insert into R_MEMBER VALUES(
    MEMBER_NO_SEQ.NEXTVAL,
    'HelloWorld222',
    'oraclejava222',
    'HelloWorld111@nate.com',
    SYSDATE
);

insert into R_MEMBER VALUES(
    MEMBER_NO_SEQ.NEXTVAL,
    'HelloWorld333',
    'oraclejava333',
    'HelloWorld333@gmail.com',
    SYSDATE
);

commit;