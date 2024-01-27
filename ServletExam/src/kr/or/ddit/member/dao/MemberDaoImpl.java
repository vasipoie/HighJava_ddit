package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVo;
import kr.or.ddit.util.MyBatisUtil;

public class MemberDaoImpl implements IMemberDao{

	private static IMemberDao memDao;
	
	private MemberDaoImpl() {
	}

	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	@Override
	public int insertMember(MemberVo mv) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.insert("member.insertMember", mv);
			
			if(cnt>0) {
				sqlSession.commit();
			}
			
		} catch (PersistenceException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVo mv) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.update("member.updateMember", mv);
			
			if(cnt>0) {
				sqlSession.commit();
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		SqlSession sqlSession = MyBatisUtil.getInstance(true);

		boolean isExist = false;
		
		try {
			int cnt = sqlSession.selectOne("member.checkMember", memId);
			
			if(cnt>0) {
				isExist = true;
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return isExist;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.delete("member.deleteMember", memId);
			
			if(cnt>0) {
				sqlSession.commit();
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}

	@Override
	public List<MemberVo> selectAll() {
		SqlSession sqlSession = MyBatisUtil.getInstance(true);

		List<MemberVo> memList = new ArrayList<MemberVo>();
		
		try {
			memList = sqlSession.selectList("member.selectAll");
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return memList;
	}

	@Override
	public List<MemberVo> searchMember(MemberVo mv) {
		SqlSession sqlSession = MyBatisUtil.getInstance(true);

		List<MemberVo> memList = new ArrayList<MemberVo>();
		
		try {
			memList = sqlSession.selectList("member.searchMember", mv);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return memList;
	}

	@Override
	public MemberVo getMember(String memId) {
		//트랜잭션이 큰 의미가 없으니까 true
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		MemberVo mv = null;
		
		try {
			mv = sqlSession.selectOne("member.getMember",memId);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return mv;
	}

	@Override
	public MemberVo getLoginInfo(MemberVo loginInfo) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		MemberVo mv = null;
		
		try {
			mv = sqlSession.selectOne("member.getLoginInfo",loginInfo);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return mv;
	}

}
