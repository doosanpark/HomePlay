/*
*  작성담당 : Login폼 개발팀
*  작성배포 : 2020년 12월 18일
*  MyPage 보관함 기능 지원을 위한 테이블 생성
*/


--/사용자별 보관함기능을 수행할 테이블/--
--Drop Table R_FAVORITES
CREATE TABLE R_FAVORITES (
    SN      NUMBER PRIMARY KEY, --순서번호 KEY
    ID       VARCHAR2(100),  -- 사용자 ID
    RNO    NUMBER           -- 영화 고유 번호
)

--/테스트용 데이터/--
--Delete  from R_FAVORITES
INSERT INTO R_FAVORITES VALUES (1, 'HelloWorld111', 1);
INSERT INTO R_FAVORITES VALUES (2, 'HelloWorld111', 2);
INSERT INTO R_FAVORITES VALUES (3, 'HelloWorld111', 3);
INSERT INTO R_FAVORITES VALUES (4, 'HelloWorld111', 4);
INSERT INTO R_FAVORITES VALUES (4, 'HelloWorld111', 5);
INSERT INTO R_FAVORITES VALUES (4, 'HelloWorld111', 6);
INSERT INTO R_FAVORITES VALUES (4, 'HelloWorld111', 7);
INSERT INTO R_FAVORITES VALUES (5, 'HelloWorld222', 1);
INSERT INTO R_FAVORITES VALUES (6, 'HelloWorld222', 2);
INSERT INTO R_FAVORITES VALUES (7, 'HelloWorld333', 3);



--/MyPage에 표시할 보관함 정보용 VIEW Table 만들기
--/VIEW TABLE은 DBA(SYSTEM계정) 권한에서만 만들 수 있음.
 1)SQL Developer에서 SYSTEM계정으로 접속한다.
 2)좌측 메뉴 하단 '다른 사용자' 선택 후 DB사용자 선택 'oraclejava'
 3)선 후 '뷰'항목에서 오른쪽 마우스 버튼 크릭 '새뷰'메뉴 선택
 4) 이름(B): 에 'V_FM'입력
 5)SQL질의(C):항목에 아래 Query 복사
 SELECT * FROM R_FAVORITES RF, R_MOVIE RM
 WHERE RF.RNO = RM.NO
 6) 테스트 실행 후 성공확인후 '확인'버튼 클릭


