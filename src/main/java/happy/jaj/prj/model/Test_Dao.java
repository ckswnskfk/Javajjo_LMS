package happy.jaj.prj.model;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Answer_Des_DTO;
import happy.jaj.prj.dtos.Answer_Sel_DTO;
import happy.jaj.prj.dtos.ContentSelect_DTO;
import happy.jaj.prj.dtos.Exam_Des_DTO;
import happy.jaj.prj.dtos.Exam_Sel_DTO;
import happy.jaj.prj.dtos.Score_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.Test_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;

@Repository
public class Test_Dao implements Test_Interface {
	
	private final String NS = "happy.jaj.prj.Test_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int test_insert(Test_DTO dto) {
		return sqlSession.insert(NS+"test_insert",dto);
	}

	@Override
	public Test_DTO test_select(Map<String, String> map) {
		return (Test_DTO)sqlSession.selectList(NS+"test_select",map);
	}

	@Override
	public boolean se_insert(Subject_Test_DTO dto) {
		int n = sqlSession.insert(NS+"se_insert", dto);
		return n>0? true:false;
	}

	@Override
	public Subject_Test_DTO se_select(Map<String, String> map) {
		return (Subject_Test_DTO)sqlSession.selectList(NS+"se_select",map);
	}

	@Override
	public Subject_Test_DTO se_testselect(String seq) {
		
		return (Subject_Test_DTO)sqlSession.selectList(NS+"se_testselect", seq);
	}

	@Override
	public boolean examdes_insert(Exam_Des_DTO dto) {
		int n = sqlSession.insert(NS+"examdes_insert", dto);
		return n>0? true:false;
	}

	@Override
	public int examsel_insert(Exam_Sel_DTO dto) {	
		return sqlSession.insert(NS+"examsel_insert", dto);
	}

	@Override
	public boolean examsel_modify(Exam_Sel_DTO dto) {
		int n = sqlSession.update(NS+"examsel_modify",dto);
		return n>0? true:false;
	}

	@Override
	public boolean te_insert(Test_Exam_DTO dto) {
		int n =sqlSession.insert(NS+"te_insert",dto);
		return n>0? true:false;
	}

	@Override
	public boolean te_modify(Test_Exam_DTO dto) {
		int n = sqlSession.update(NS+"te_modify", dto);
		return n>0? true:false;
	}

	@Override
	public int te_selectsum(String seq) {
		return sqlSession.selectOne(NS+"te_selectsum", seq);
	}

	@Override
	public Exam_Des_DTO te_select(Map<String, String> map) {
		return (Exam_Des_DTO)sqlSession.selectOne(NS+"te_select",map);
	}

	@Override
	public Exam_Des_DTO te_testselect(Map<String, String> map) {		
		return (Exam_Des_DTO)sqlSession.selectOne(NS+"te_testselect",map);
	}

	@Override
	public boolean content_insert(ContentSelect_DTO dto) {
		int n = sqlSession.insert(NS+"content_insert", dto);
		return n>0? true:false;
	}

	@Override
	public boolean content_modify(Map<String, String> map) {
		int n = sqlSession.update(NS+"content_modify",map);
		return n>0? true:false;
	}

	@Override
	public ContentSelect_DTO content_select(String seq) {	
		return (ContentSelect_DTO)sqlSession.selectList(NS+"content_select",seq);
	}

	@Override
	public boolean answerd_insert(Answer_Des_DTO dto) {
		int n = sqlSession.insert(NS+"answerd_insert",dto);
		return n>0? true:false;
	}

	@Override
	public boolean answerd_modify(Answer_Des_DTO dto) {
		int n = sqlSession.update(NS+"answerd_modify",dto);
		return n>0? true:false;
	}

	@Override
	public Answer_Sel_DTO answerd_select(Map<String, String> map) {
		return sqlSession.selectOne(NS+"answerd_select",map);
	}

	@Override
	public boolean answers_insert(Answer_Sel_DTO dto) {
		int n = sqlSession.insert(NS+"answers_insert",dto);
		return n>0? true:false;
	}

	@Override
	public boolean answers_modify(Answer_Sel_DTO dto) {
		int n = sqlSession.update(NS+"answers_modify",dto);
		return n>0? true:false;
	}

	@Override
	public Answer_Sel_DTO answers_select(Map<String, String> map) {
		return sqlSession.selectOne(NS+"answers_select",map);
	}

	@Override
	public boolean score_insertd(Score_DTO dto) {
		int n = sqlSession.insert(NS+"score_insertd",dto);
		return n>0? true:false;
	}

	@Override
	public boolean score_inserts(Score_DTO dto) {
		int n = sqlSession.insert(NS+"score_inserts",dto);
		return n>0? true:false;
	}

	@Override
	public boolean score_modify(Score_DTO dto) {
		int n = sqlSession.update(NS+"score_modify",dto);
		return n>0? true:false;
	}

	@Override
	public Score_DTO score_select(Map<String, String> map) {
		return (Score_DTO)sqlSession.selectList(NS+"score_select",map);
	}

	@Override
	public Score_DTO score_selectsum(String seq) {
		return sqlSession.selectOne(NS+"score_selectsum",seq);
	}

	
}
