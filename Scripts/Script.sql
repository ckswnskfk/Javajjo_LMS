-- 테이블 생성
CREATE TABLE STUDENT(
	ID VARCHAR2(12) NOT NULL,
	NAME VARCHAR(30) NOT NULL,
	PW VARCHAR2(100) NOT NULL,
	GENDER CHAR(1) NOT NULL CONSTRAINT GENDER_CHECK CHECK (GENDER IN ('M','F')),
	BIRTH DATE,
	ADDR VARCHAR2(100),
	S_CHECK CHAR(1) DEFAULT 'N',
	DELFLAG CHAR(1) CONSTRAINT DELFLAG_CHECK CHECK (DELFLAG IN ('N', 'Y')),
	CONSTRAINT STUDENT_PK PRIMARY KEY (ID)
);

CREATE TABLE TEACHER(
	ID VARCHAR2(12) NOT NULL PRIMARY KEY,
	NAME VARCHAR2(30) NOT NULL,
	PW VARCHAR2(100) NOT NULL,
	COURSECODE VARCHAR2(10) NOT NULL
);

CREATE TABLE ADMIN(
	ID VARCHAR2(12) NOT NULL PRIMARY KEY,
	NAME VARCHAR2(30) NOT NULL,
	PW VARCHAR2(100) NOT NULL
);

CREATE TABLE NOTICE_BOARD(
	SEQ NUMBER NOT NULL PRIMARY KEY,
	TITLE VARCHAR2(50) NOT NULL,
	CONTENT VARCHAR2(500),
	ID VARCHAR2(12),
	REGDATE DATE,
	READCOUNT NUMBER
);
CREATE SEQUENCE NOTICE_BOARD_SEQ START WITH 1 INCREMENT BY 1;


CREATE TABLE EMPTY_BOARD(
	CODE VARCHAR2(20) NOT NULL PRIMARY KEY,
	NAME VARCHAR2(30) NOT NULL,
	PERSONEL NUMBER
);

CREATE TABLE EMPTY_USER(
	CODE VARCHAR2(20) NOT NULL PRIMARY KEY,
	ID VARCHAR2(12) NOT NULL,
	REGDATE DATE
);

CREATE TABLE FILE_BOARD(
	SEQ NUMBER NOT NULL,
	ID VARCHAR2(12) NOT NULL,
	TITLE VARCHAR(50) NOT NULL,
	CONTENT VARCHAR(500) NOT NULL,
	REGDATE DATE NOT NULL,
	READCOUNT NUMBER NOT NULL,
	FILENAME VARCHAR2(500),
	NEWFILENAME VARCHAR2(500),
	CONSTRAINT FILE_BOARD_PK PRIMARY KEY (SEQ)
);

CREATE TABLE COURSE(
	COURSECODE VARCHAR2(10) NOT NULL PRIMARY KEY,
	COURSENAME VARCHAR2(100) NOT NULL,
	STARTDATE DATE,
	COURSECNT NUMBER NOT NULL
);

CREATE TABLE SUBJECT(
	SUBJECTCODE VARCHAR2(6) NOT NULL PRIMARY KEY,
	SUBJECTNAME VARCHAR2(30) NOT NULL,
	SUBJECTTYPE VARCHAR2(10) NOT NULL,
	EXAMTYPE VARCHAR2(10) NOT NULL
);


-- 학생-과정 테이블 연결
CREATE TABLE USERCOURSE(
	ID VARCHAR2(12) NOT NULL,
	COURSECODE VARCHAR2(10) NOT NULL,
	REGDATE DATE NOT NULL
);

-- 과정-과목 테이블 연결 
CREATE TABLE COURSE_SUBJECT(
	SEQ NUMBER NOT NULL,
	COURSECODE VARCHAR2(10) NOT NULL,
	SUBJECTCODE VARCHAR2(6) NOT NULL,
	SUBJECTTIME NUMBER NOT NULL,
	CONTENT VARCHAR2(50),
	STARTDATE DATE
);

--과목-과제 연결
CREATE TABLE SUBJECT_TEST(
	SUBJECTCODE VARCHAR2(10) NOT NULL,
	TESTCODE VARCHAR2(10) NOT NULL,
	TESTDAY DATE NOT NULL,
	COURSECODE VARCHAR2(10) NOT NULL
);

--과제-문제  연결
CREATE TABLE TEST_EXAM(
	TESTCODE VARCHAR2(10) NOT NULL,
	EXAMCODE VARCHAR2(10) NOT NULL,
	ALLOT NUMBER NOT NULL,
	EXAMNUM NUMBER NOT NULL
);

CREATE TABLE TEST(
	TESTCODE VARCHAR2(10) NOT NULL PRIMARY KEY,
	TESTNAME VARCHAR2(300) NOT NULL,
	SUBJECTTYPE VARCHAR2(10) NOT NULL,
	EXAMTYPE VARCHAR2(15) NOT NULL
);


-- 서술형문제 
CREATE TABLE EXAMDESCRIPTIVE(
	EXAM VARCHAR2(500) NOT NULL,
	EXAMCODE VARCHAR2(10) NOT NULL PRIMARY KEY,
	EXPLANATION VARCHAR2(700),
	STANDARD VARCHAR2(500),
	C_ANSWER VARCHAR2(500) NOT NULL
);

--서술형 답
CREATE TABLE ANSWERDESCRIPTIVE(
	ID VARCHAR2(12) NOT NULL,
	EXAMCODE VARCHAR2(10) NOT NULL,
	EXAMNUM NUMBER NOT NULL,
	ANSWER VARCHAR2(700),
	ORIGINFILE VARCHAR2(500),
	NEWFILENAME VARCHAR2(500)
);

--선택형 문제
CREATE TABLE EXAMSELECT(
	EXAM VARCHAR2(500) NOT NULL,
	EXAMCODE VARCHAR2(10) NOT NULL PRIMARY KEY,
	C_ANSWER NUMBER NOT NULL
);

-- 선택형 문항
CREATE TABLE CONTENTSELECT(
	EXAMCODE VARCHAR2(10) NOT NULL,
	CONTENTNUM NUMBER NOT NULL,
	EXAMCONTENT VARCHAR2(500) NOT NULL
);

--선택형 답 
CREATE TABLE ANSWERSELECT(
	ID VARCHAR2(12) NOT NULL,
	EXAMCODE VARCHAR2(10) NOT NULL,
	EXAMNUM NUMBER NOT NULL,
	ANSWER NUMBER
);

-- 성적 테이블
CREATE TABLE SCORE(
	ID VARCHAR2(12) NOT NULL,
	EXAMCHECK CHAR(1) DEFAULT 'N' CONSTRAINT EXAMCHECK_CHECK CHECK (EXAMCHECK IN ('N','S','Y')),
	TESTCODE VARCHAR2(30) NOT NULL,
	SUBJECTTYPE VARCHAR2(10) NOT NULL,
	EXAMCODE VARCHAR2(10),
	SCORE NUMBER
);

 -- 전자결재
-- 테이블 생성
-- 결석 신청 테이블 생성
CREATE TABLE APP_FORM(
	FORM_SEQ NUMBER NOT NULL,
	APP_DATE DATE NOT NULL,
	STUDENT_ID VARCHAR2(12) NOT NULL,
	RECIPIENT_ID VARCHAR2(12) NOT NULL,
	COURSECODE VARCHAR2(10) NOT NULL,
	REASON VARCHAR2(50) NOT NULL,
	START_DATE DATE NOT NULL,
	ABSENT_DAYS NUMBER NOT NULL, -- START_DATE + ABSENT_DAYS - 1 = 마지막 결석일
	FILENAME VARCHAR2(500),
	NEWFILENAME VARCHAR2(500),
	PROCESS_DATE DATE,
	CONSTRAINT APP_FORM_PK PRIMARY KEY(FORM_SEQ)
);

-- 신청서 SEQ 생성
CREATE SEQUENCE APP_FORM_SEQ INCREMENT BY 1 START WITH 1;



-- 승인 여부 테이블 
CREATE TABLE IS_APPROVE(
	FORM_SEQ NUMBER NOT NULL,
	STM CHAR DEFAULT 'N' NOT NULL,
	CONSTRAINT IS_APPROVE_PK PRIMARY KEY(FORM_SEQ)
);

-- 사인 테이블 생성
CREATE TABLE SIGNATURE(
	SIGNATURE_ID VARCHAR2(12) NOT NULL,
	USECHECK CHAR NOT NULL,
	FILENAME VARCHAR2(500) NOT NULL,
	NEWFILENAME VARCHAR2(500) NOT NULL,
	CONSTRAINT SIGNATURE_PK PRIMARY KEY(SIGNATURE_ID)
);

-- 반려 사유 테이블 생성
CREATE TABLE UNAPPROVED(
	FORM_SEQ NUMBER NOT NULL,
	REASON VARCHAR2(100),
	CONSTRAINT UNAPPROVED_PK PRIMARY KEY(FORM_SEQ)
);

CREATE TABLE ATTENDEDCHECK(
	SEQ NUMBER NOT NULL,
	A_CHECK CHAR(1) CONSTRAINT A_CHECK_CHECK CHECK (A_CHECK IN ('N','Y')), --퇴실은 N, 출석은 Y , 결석은 Null
	ID VARCHAR2(12) NOT NULL,
	REGDATE DATE NOT NULL, 
	CONSTRAINT ATTENDEDCHECK_PK PRIMARY KEY(SEQ)
);
CREATE SEQUENCE ATTENDEDCHECK_SEQ INCREMENT BY 1 START WITH 1;


-- 외래키 설정
-- 서술형 답 테이블 서술형 문제 테이블에 외래키 설정
ALTER TABLE JAJ.ANSWERDESCRIPTIVE ADD CONSTRAINT ANSWERDESCRIPTIVE_FK FOREIGN KEY (EXAMCODE) REFERENCES JAJ.EXAMDESCRIPTIVE(EXAMCODE);

-- 선택형 답 테이블 선택형 문제 테이블에 외래키 설정
ALTER TABLE JAJ.ANSWERSELECT ADD CONSTRAINT ANSWERSELECT_EXAMSELECT_FK FOREIGN KEY (EXAMCODE) REFERENCES JAJ.EXAMSELECT(EXAMCODE);

-- 출석체크 테이블 학생 테이블에 외래키 설정
ALTER TABLE JAJ.ATTENDEDCHECK ADD CONSTRAINT ATTENDEDCHECK_STUDENT_FK FOREIGN KEY (ID) REFERENCES JAJ.STUDENT(ID);

-- 선택형 문항 테이블 선택형 문제 테이블에 외래키 설정
ALTER TABLE JAJ.CONTENTSELECT ADD CONSTRAINT CONTENTSELECT_EXAMSELECT_FK FOREIGN KEY (EXAMCODE) REFERENCES JAJ.EXAMSELECT(EXAMCODE);

-- 과정 과목 테이블 과정 테이블에 외래키 설정
ALTER TABLE JAJ.COURSE_SUBJECT ADD CONSTRAINT COURSE_SUBJECT_COURSE_FK FOREIGN KEY (COURSECODE) REFERENCES JAJ.COURSE(COURSECODE);

-- 과정 과목 테이블 과목 테이블에 외래키 설정
ALTER TABLE JAJ.COURSE_SUBJECT ADD CONSTRAINT COURSE_SUBJECT_SUBJECT_FK FOREIGN KEY (SUBJECTCODE) REFERENCES JAJ.SUBJECT(SUBJECTCODE);

-- 빈강의실_학생 테이블 학생 테이블에 외래키 설정
ALTER TABLE JAJ.EMPTY_USER ADD CONSTRAINT EMPTY_USER_STUDENT_FK FOREIGN KEY (ID) REFERENCES JAJ.STUDENT(ID);

-- 빈강의실_학생 테이블 빈강의실 테이블에 외래키 설정
ALTER TABLE JAJ.EMPTY_USER ADD CONSTRAINT EMPTY_USER_EMPTY_BOARD_FK FOREIGN KEY (CODE) REFERENCES JAJ.EMPTY_BOARD(CODE);

-- 점수 테이블 학생 테이블에 외래키 설정
ALTER TABLE JAJ.SCORE ADD CONSTRAINT SCORE_STUDENT_FK FOREIGN KEY (ID) REFERENCES JAJ.STUDENT(ID);

-- 과제 테이블 기본키 설정
ALTER TABLE JAJ.TEST ADD CONSTRAINT TEST_PK PRIMARY KEY (TESTCODE) ENABLE;

-- 점수 테이블 과제 테이블에 외래키 설정
ALTER TABLE JAJ.SCORE ADD CONSTRAINT SCORE_TEST_FK FOREIGN KEY (TESTCODE) REFERENCES JAJ.TEST(TESTCODE);

-- 과목_과제 테이블 과목 테이블에 외래키 설정
ALTER TABLE JAJ.SUBJECT_TEST ADD CONSTRAINT SUBJECT_TEST_SUBJECT_FK FOREIGN KEY (SUBJECTCODE) REFERENCES JAJ.SUBJECT(SUBJECTCODE);

-- 과목_과제 테이블 과제 테이블에 외래키 설정
ALTER TABLE JAJ.SUBJECT_TEST ADD CONSTRAINT SUBJECT_TEST_TEST_FK FOREIGN KEY (TESTCODE) REFERENCES JAJ.TEST(TESTCODE);

-- 강사 테이블 과정 테이블에 외래키 설정
ALTER TABLE JAJ.TEACHER ADD CONSTRAINT TEACHER_COURSE_FK FOREIGN KEY (COURSECODE) REFERENCES JAJ.COURSE(COURSECODE);

-- 과제_시험 테이블 과제 테이블에 외래키 설정
ALTER TABLE JAJ.TEST_EXAM ADD CONSTRAINT TEST_EXAM_TEST_FK FOREIGN KEY (TESTCODE) REFERENCES JAJ.TEST(TESTCODE);



-- 쿼리

-- 회원 관리(학생)
-- 학생 회원 가입
INSERT INTO STUDENT VALUES('01022222222', '길동홍', '1111','F', '2019-05-08','서울시 강남구 찬주동', 'N','N');

--회원 관리(관리자)
-- 관리자 등록
INSERT INTO ADMIN
VALUES('01099999999', '관리자', '1111');

--학생 회원가입 신청 거절(관리자)
DELETE FROM STUDENT WHERE ID IN ('01011111111','01022222222');

-- 강사탈퇴(관리자)
DELETE FROM TEACHER WHERE ID = '01011111111';

-- 강사 등록(관리자)
INSERT INTO TEACHER(ID, NAME,PW,COURSECODE) 
VALUES('01012345678', '김제민', 'qweqweqwe', 'C201900001');

-- 학생 과정 연결(관리자)
INSERT INTO USERCOURSE(ID, COURSECODE, REGDATE)
VALUES('01011111111', 'C201900001', SYSDATE);

-- 학생 과정 삭제(관리자)
DELETE FROM USERCOURSE WHERE ID='01011111111' AND COURSECODE='C201900001';



-- 공지사항 게시판	

-- 게시글 작성 ( 강사, 관리자만 작성가능 )
INSERT INTO NOTICE_BOARD 
VALUES(NOTICE_BOARD_SEQ.NEXTVAL,'공지사항 글제목','내용','01011111111',SYSDATE,0)
 

-- 빈강의실	
-- 예약
INSERT INTO EMPTY_USER VALUES('1111','01011111111','2019-05-07');

-- 예약 취소 (취소는 본인이 예약한 사항만 취소 가능)
DELETE FROM EMPTY_USER WHERE ID = '01011111111'
AND CODE = '1111'
AND TO_CHAR(REGDATE,'YYYYMMDD')='20190507';

-- 강의실 추가
INSERT INTO EMPTY_BOARD VALUES('1111','ONE',10);
 
--자료게시판	
-- 글 작성
INSERT INTO FILE_BOARD(SEQ, ID, TITLE, CONTENT, REGDATE, READCOUNT, REPORT, FILENAME, NEWFILENAME)
VALUES(FILE_BOARD_SEQ.NEXTVAL, '작성자', '자료제목', '자료내용', SYSDATE, 0, 'N', '변경전파일이름','변경후파일이름');

-- 글 삭제(다중 삭제)
DELETE FROM FILE_BOARD WHERE SEQ IN (1,2);

-- 회원관리 + 로그인

-- 과목 등록
INSERT INTO SUBJECT(SUBJECTCODE, SUBJECTNAME, SUBJECTTYPE,  EXAMTYPE)
VALUES
((SELECT 'S'|| LPAD((TO_NUMBER(SUBSTR(MAX(SUBJECTCODE),2))+1),5,0)  FROM SUBJECT),
'코어 언어 배우기', 'JAVA', '서술형');

INSERT INTO SUBJECT(SUBJECTCODE, SUBJECTNAME, SUBJECTTYPE,  EXAMTYPE)
VALUES
((SELECT 'S'|| LPAD((TO_NUMBER(SUBSTR(MAX(SUBJECTCODE),2))+1),5,0)  FROM SUBJECT),
'화면 설계', 'HTML', '서술형');

-- 과정에 과목 추가
INSERT INTO COURSE_SUBJECT (SEQ, COURSECODE, SUBJECTCODE, SUBJECTTIME, CONTENT, STARTDATE)
VALUES(COURSE_SUBJECT_SEQ.NEXTVAL, 'C201900002','S00004','100','자바로 덧셈 배우기','20190506');
 
-- 학생 회원가입 거절
DELETE FROM STUDENT WHERE ID IN ('01011111111');


-- 학생에 과정 추가
INSERT INTO USERCOURSE(ID, COURSECODE, REGDATE)
VALUES('','',SYSDATE);

-- 학생에 과정 삭제
DELETE FROM USERCOURSE WHERE ID='' AND COURSECODE='';


-- 강사 등록
INSERT INTO TEACHER(ID, NAME,PW,COURSECODE)
VALUES('01087654321','민균전','1111','C201900002');

-- 과정테이블(COURSE)

-- 과정 추가(회차 증가)
INSERT INTO COURSE(COURSECODE, COURSENAME, STARTDATE,  COURSECNT)
VALUES
((SELECT 'C'|| EXTRACT(YEAR FROM SYSDATE) ||LPAD((TO_NUMBER(SUBSTR(MAX(COURSECODE),6))+1),5,0)  FROM COURSE),
'송희 기반 PYTHON IT 양성과정', '2019-06-12', (SELECT MAX(COURSECNT) FROM COURSE WHERE COURSENAME='송희 기반 PYTHON IT 양성과정')+1);


-- 과목
-- 새로운 과목 생성
INSERT INTO SUBJECT(SUBJECTCODE, SUBJECTNAME, SUBJECTTYPE,  EXAMTYPE)
VALUES
((SELECT 'S'|| LPAD((TO_NUMBER(SUBSTR(MAX(SUBJECTCODE),2))+1),5,0)  FROM SUBJECT),
'자바 서버 페이지', 'JSP', '선택형');


-- 과정에 과목 추가 
INSERT INTO COURSE_SUBJECT
(SEQ, COURSECODE, SUBJECTCODE, SUBJECTTIME, CONTENT, STARTDATE)
VALUES(COURSE_SUBJECT_SEQ.NEXTVAL, 'C201900001', 'S00002', 100, '코어언어인 JAVA 배우기 ', '20190617');

CREATE SEQUENCE COURSE_SUBJECT_SEQ START WITH 1 INCREMENT BY 1;

--INSERT INTO USERCOURSE
--(ID, COURSECODE, REGDATE)
--VALUES('01011111111', 'C201900001', SYSDATE);

-- 학생이 결석 신청을 함
INSERT INTO APP_FORM
			(FORM_SEQ, APP_DATE, STUDENT_ID, RECIPIENT_ID , --관리자ID
			COURSECODE, REASON, START_DATE, ABSENT_DAYS, FILENAME, NEWFILENAME) --과정ID
		VALUES
			(APP_FORM_SEQ.NEXTVAL, SYSDATE, '01011111111', '01012345678', 
		   'C201900001', '감기로 출석이 어려움', '2019-05-03', 3, '진단서.JPG', '01011111111_첨부.JPG');
		   
-- 신청 상태 테이블 연결
INSERT INTO IS_APPROVE(FORM_SEQ, STM)
VALUES((SELECT MAX(FORM_SEQ) FROM APP_FORM WHERE STUDENT_ID='01011111111'), 'N');

-- 강사 및 관리자가 미승인 사유를 작성
INSERT INTO UNAPPROVED
VALUES(2, '싫음');

-- 강사가 사인 이미지 등록
INSERT INTO SIGNATURE(SIGNATURE_ID, USECHECK, FILENAME, NEWFILENAME)
VALUES('01012345678', 'Y', '사인.jpg', '01012345678_signature.jpg');

--출결관리
--입실
INSERT INTO ATTENDEDCHECK(SEQ, A_CHECK, ID, REGDATE)
VALUES(ATTENDEDCHECK_SEQ.NEXTVAL, 'Y', '010111111111', SYSDATE);

--퇴실
INSERT INTO ATTENDEDCHECK(SEQ, A_CHECK, ID, REGDATE)
VALUES(ATTENDEDCHECK_SEQ.NEXTVAL, 'N', '01011111111', SYSDATE);

--과정, 과목 관리
--(관리자)과정 추가(회차 증가)
INSERT INTO COURSE(COURSECODE, COURSENAME, STARTDATE, COURSECNT)
VALUES((SELECT 'C'|| EXTRACT(YEAR FROM SYSDATE) ||LPAD((TO_NUMBER(SUBSTR(MAX(COURSECODE),6))+1),5,0)  FROM COURSE),
'C201900001', '2019-06-15', (SELECT MAX(COURSECNT) FROM COURSE WHERE COURSENAME='과정명')+1);

--(관리자)과정 등록(새로운 과정)
INSERT INTO COURSE(COURSECODE, COURSENAME, STARTDATE, COURSECNT)
VALUES((SELECT 'C'|| EXTRACT(YEAR FROM SYSDATE) ||LPAD((TO_NUMBER(SUBSTR(MAX(COURSECODE),6))+1),5,0)  FROM COURSE),
'C201900001', '2019-06-15', 1);

--과제 관리
--과제에 연결된 문제 삭제
DELETE FROM TEST_EXAM
WHERE TESTCODE='T00001' AND EXAMCODE='ES00001';

--과제에 연결되 문제 모두 삭제
DELETE FROM TEST_EXAM
WHERE TESTCODE='T00001';

--과목에 과제등록(연결)
INSERT INTO SUBJECT_TEST(SUBJECTCODE, TESTCODE, TESTDAY, COURSECODE)
VALUES('S00001', 'T00001', '2019-06-23', 'C201900001');

--과제에 문제등록
INSERT INTO TEST_EXAM(TESTCODE, EXAMCODE, ALLOT, EXAMNUM)
VALUES('T00002', 'ES00001', 10, 1);

--문항 등록
INSERT INTO CONTENTSELECT(EXAMCODE, CONTENTNUM, EXAMCONTENT)
VALUES('ES00001', 1, '문항1');

--학생 서술형 답안 등록
INSERT INTO ANSWERDESCRIPTIVE(ID, EXAMCODE, EXAMNUM, ANSWER, ORIGINFILE,NEWFILENAME)
VALUES('010111111111', 'ED00001', 1, '정답입니다.', '', '');

--학생 선택형 답안 등록
INSERT INTO ANSWERSELECT(ID, EXAMCODE, EXAMNUM, ANSWER)
VALUES ('010111111111', 'ES00001', 1, 1);

--등록_학생점수채점(서술형)
INSERT INTO SCORE(ID, EXAMCHECK, TESTCODE, EXAMCODE, SCORE)
VALUES('01011111111', 'N', 'T00001', 'ED00001', 10);

--등록-자동채점(선택형)
INSERT INTO SCORE(ID, EXAMCHECK, TESTCODE, EXAMCODE, SCORE)
		VALUES('010111111111', 'N', 'T00002', 'ES00001', (SELECT (CASE WHEN T1.AW='H' THEN ALLOT ELSE 0 END) FROM
(SELECT (CASE WHEN C_ANSWER=ANSWER THEN 'H' ELSE 'F' END) AW, ANSWERSELECT.EXAMCODE AE, id
	FROM ANSWERSELECT JOIN EXAMSELECT ON ANSWERSELECT.EXAMCODE=EXAMSELECT.EXAMCODE	) T1
	JOIN TEST_EXAM T2
		ON T1.AE = T2.EXAMCODE
	WHERE T1.ID='010111111111' AND T2.EXAMCODE='ES00001'));
	
