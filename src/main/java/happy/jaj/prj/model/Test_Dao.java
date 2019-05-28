package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Answer_Des_DTO;
import happy.jaj.prj.dtos.Answer_Sel_DTO;
import happy.jaj.prj.dtos.ContentSelect_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Exam_Des_DTO;
import happy.jaj.prj.dtos.Exam_Sel_DTO;
import happy.jaj.prj.dtos.Score_DTO;
import happy.jaj.prj.dtos.Subject_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.Test_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;

@Repository
public class Test_Dao implements Test_Interface {
	
	private final String NS = "happy.jaj.prj.Test_Mapper.";
	private Logger logger = LoggerFactory.getLogger(Test_Dao.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int test_insert(Test_DTO dto) {
		logger.info("Test_Dao test_insert 실행 {}", dto);
		return sqlSession.insert(NS+"test_insert",dto);
	}

	@Override
	public List<Test_DTO> test_select(Map<String, String> map) {
		logger.info("Test_Dao test_select {}", map);
		return sqlSession.selectList(NS+"test_select",map);
	}

	@Override
	public boolean se_insert(Subject_Test_DTO dto) {
		logger.info("Test_Dao se_insert {}", dto);
		int n = sqlSession.insert(NS+"se_insert", dto);
		return n>0? true:false;
	}

	@Override
	public Subject_Test_DTO se_select(Map<String, String> map) {
		logger.info("Test_Dao se_select {}", map);
		return (Subject_Test_DTO)sqlSession.selectList(NS+"se_select",map);
	}

	@Override
	public Subject_Test_DTO se_testselect(String seq) {
		logger.info("Test_Dao se_testselect {}",seq);
		return (Subject_Test_DTO)sqlSession.selectOne(NS+"se_testselect", seq);
	}

	@Override
	public boolean examdes_insert(Exam_Des_DTO dto) {
		logger.info("Test_Dao examdes_insert {}",dto);
		int n = sqlSession.insert(NS+"examdes_insert", dto);
		return n>0? true:false;
	}

	//문제등록(서술형)
	@Override
	public int examsel_insert(Exam_Sel_DTO dto) {	
		logger.info("Test_Dao examsel_insert {}",dto);
		return sqlSession.insert(NS+"examsel_insert", dto);
	}

	@Override
	public boolean examsel_modify(Exam_Sel_DTO dto) {
		logger.info("Test_Dao examsel_modify {}",dto);
		int n = sqlSession.update(NS+"examsel_modify",dto);
		return n>0? true:false;
	}

	@Override
	public boolean examdes_modify(Exam_Des_DTO dto) {
		logger.info("Test_Dao examdes_modify {}",dto);
		int n = sqlSession.update(NS+"examdes_modify", dto);
		return n>0? true : false;
	}
//과제에 문제등록(연결)
	@Override
	public boolean te_insert(Test_Exam_DTO dto) {
		logger.info("Test_Dao te_insert {}",dto);
		int n =sqlSession.insert(NS+"te_insert",dto);
		return n>0? true:false;
	}

	@Override
	public boolean te_modify(Test_Exam_DTO dto) {
		logger.info("Test_Dao te_modify {}",dto);
		int n = sqlSession.update(NS+"te_modify", dto);
		return n>0? true:false;
	}

	@Override
	public int te_selectsum(String seq) {
		logger.info("Test_Dao te_selectsum {}",seq);
		String obj = sqlSession.selectOne(NS+"te_selectsum", seq);
		return  obj==null ? 0: Integer.parseInt(obj);
	}

	@Override
	public Exam_Des_DTO te_select(Map<String, String> map) {
		logger.info("Test_Dao te_select {}",map);
		return (Exam_Des_DTO)sqlSession.selectOne(NS+"te_select",map);
	}

	@Override
	public Exam_Sel_DTO te_testselect(Map<String, String> map) {		
		logger.info("Test_Dao te_testselect {}",map);
		return (Exam_Sel_DTO)sqlSession.selectOne(NS+"te_testselect",map);
	}
	
	@Override
	public List<Test_Exam_DTO> te_selectlist(String seq){
		logger.info("Test_Dao te_selectlist {}",seq);
		return sqlSession.selectList(NS+"te_selectlist", seq);
	}
	
	@Override
	public List<Test_Exam_DTO> te_testselectlist(String seq){
		logger.info("Test_Dao te_testselectlist {}",seq);
		return sqlSession.selectList(NS+"te_testselectlist",seq);
	}

	@Override
	public boolean content_insert(ContentSelect_DTO dto) {
		logger.info("Test_Dao content_insert {}",dto);
		int n = sqlSession.insert(NS+"content_insert", dto);
		return n>0? true:false;
	}

	@Override
	public boolean content_modify(ContentSelect_DTO dto) {
		logger.info("Test_Dao content_modify {}",dto);
		int n = sqlSession.update(NS+"content_modify",dto);
		return n>0? true:false;
	}

	@Override
	public List<ContentSelect_DTO> content_select(String seq) {	
		logger.info("Test_Dao content_select {}",seq);
		return sqlSession.selectList(NS+"content_select",seq);
	}

	@Override
	public boolean answerd_insert(Answer_Des_DTO dto) {
		logger.info("Test_Dao answerd_insert {}",dto);
		int n = sqlSession.insert(NS+"answerd_insert",dto);
		return n>0? true:false;
	}

	@Override
	public boolean answerd_modify(Answer_Des_DTO dto) {
		logger.info("Test_Dao answerd_modify {}",dto);
		int n = sqlSession.update(NS+"answerd_modify",dto);
		return n>0? true:false;
	}

	@Override
	public  Answer_Des_DTO answerd_select(Map<String, String> map) {
		logger.info("Test_Dao answerd_select {}",map);
		return sqlSession.selectOne(NS+"answerd_select",map);
	}

	@Override
	public boolean answers_insert(Answer_Sel_DTO dto) {
		logger.info("Test_Dao answers_insert {}",dto);
		int n = sqlSession.insert(NS+"answers_insert",dto);
		return n>0? true:false;
	}

	@Override
	public boolean answers_modify(Answer_Sel_DTO dto) {
		logger.info("Test_Dao answers_modify {}",dto);
		int n = sqlSession.update(NS+"answers_modify",dto);
		return n>0? true:false;
	}

	@Override
	public Answer_Sel_DTO answers_select(Map<String, String> map) {
		logger.info("Test_Dao answers_select {}",map);
		return sqlSession.selectOne(NS+"answers_select",map);
	}

	@Override
	public boolean score_insertd(Score_DTO dto) {
		logger.info("Test_Dao score_insertd {}",dto);
		int n = sqlSession.insert(NS+"score_insertd",dto);
		return n>0? true:false;
	}

	@Override
	public boolean score_inserts(Score_DTO dto) {
		logger.info("Test_Dao score_inserts {}",dto);
		int n = sqlSession.insert(NS+"score_inserts",dto);
		return n>0? true:false;
	}

	@Override
	public boolean score_modify(Score_DTO dto) {
		logger.info("Test_Dao score_modify {}",dto);
		int n = sqlSession.update(NS+"score_modify",dto);
		return n>0? true:false;
	}

	@Override
	public Score_DTO score_select(Map<String, String> map) {
		logger.info("Test_Dao score_select {}",map);
		return (Score_DTO)sqlSession.selectList(NS+"score_select", map);
	}

	@Override
	public Score_DTO score_selectsum(Map<String, String> map) {
		logger.info("Test_Dao score_selectsum {}",map);
		return sqlSession.selectOne(NS+"score_selectsum", map);
	}

	@Override
	public Course_DTO test_course(String seq) {
		logger.info("Test_Dao test_course{}", seq);
		return sqlSession.selectOne(NS+"test_course",seq);
	}

	@Override
	public List<Subject_DTO> test_subject(String seq) {
		logger.info("Test_Dao test_subject {}",seq);
		return sqlSession.selectList(NS+"test_subject",seq);
	}

	@Override
	public List<Course_DTO> test_coursecnt(String seq) {
		logger.info("Test_Dao test_coursecnt {}",seq);
		return sqlSession.selectList(NS+"test_coursecnt",seq);
	}

	@Override
	public int test_maxexamnum(String testcode) {
		logger.info("Test_Dao test_maxexamnum{}", testcode);
		return sqlSession.selectOne(NS+"test_maxexamnum",testcode);
	}

	@Override
	public List<Test_Exam_DTO> test_coursecopy(Map<String, String> map) {
		logger.info("Test_Dao test_coursecopy {}" ,map);
		return sqlSession.selectList(NS+"test_coursecopy",map);
	}

	@Override
	public List<Exam_Des_DTO> test_typedesc(String subjecttype) {
		logger.info("Test_Dao test_typedesc {}", subjecttype);
		return sqlSession.selectList(NS+"test_typedesc",subjecttype);
	}

	@Override
	public List<Exam_Sel_DTO> test_typesel(String subjecttype) {
		logger.info("Test_Dao test_typesel {}", subjecttype);
		return sqlSession.selectList(NS+"test_typesel",subjecttype);
	}

	@Override
	public Exam_Des_DTO test_examdesc(String examcode) {
		logger.info("Test_Dao test_examdesc{}", examcode);
		return sqlSession.selectOne(NS+"test_examdesc",examcode);
	}

	@Override
	public Exam_Sel_DTO test_examsel(String examcode) {
		logger.info("Test_Dao test_examsel{}", examcode);
		return sqlSession.selectOne(NS+"test_examsel",examcode);
	}

	@Override
	public boolean test_deltestexam(Test_Exam_DTO dto) {
		logger.info("Test_Dao test_deltestexam {}", dto);
		int n = sqlSession.delete(NS+"test_deltestexam",dto);
		return (n>0)? true: false;
	}

	@Override
	public boolean test_deltestall(String testcode) {
		logger.info("Test_Dao test_deltestall {}",testcode);
		int n = sqlSession.delete(NS+"test_deltestall",testcode);
		return n>0? true: false;
	}

	@Override
	public List<Course_DTO> test_courselist(String id) {
		logger.info("Test_Dao test_courselist {}", id);
		return sqlSession.selectList(NS+"test_courselist",id);
	}

	
}
