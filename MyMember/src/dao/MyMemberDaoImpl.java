package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.MyBatisDao;
import util.MyBatisUtil;
import vo.MyMemberVO;

public class MyMemberDaoImpl extends MyBatisDao implements IMyMemberDao{
	
	private static IMyMemberDao mmDao;
	
	private MyMemberDaoImpl() {
	}

	public static IMyMemberDao getInstance() {
		if(mmDao == null) {
			mmDao = new MyMemberDaoImpl();
		}
		return mmDao;
	}

	@Override
	public List<MyMemberVO> selectAll() {
		
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		List<MyMemberVO> memList = new ArrayList<MyMemberVO>();
		
		try {
			memList = sqlSession.selectList("mymember.selectAll");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return memList;
	}
	
	
	
	
	
}
