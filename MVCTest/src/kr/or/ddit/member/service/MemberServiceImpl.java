package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImplForJDBC;
import kr.or.ddit.member.vo.MemberVo;

public class MemberServiceImpl implements IMemberService{

	private IMemberDao memDao;
	
	//생성자 이용해서 초기화
	public MemberServiceImpl() {
		memDao = new MemberDaoImplForJDBC();
	}
	
	@Override
	public int registMember(MemberVo mv) {
		int cnt = memDao.insertMember(mv);
		return cnt;
	}

	@Override
	public int modifyMember(MemberVo mv) {
		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean isExist = memDao.checkMember(memId); 
		return isExist;
	}

	@Override
	public int removeMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

	@Override
	public List<MemberVo> displayAll() {
		List<MemberVo> memList = memDao.selectAll();
		return memList;
	}

	@Override
	public List<MemberVo> searchMember(MemberVo mv) {
		List<MemberVo> memList = memDao.searchMember(mv);
		return memList;
	}

}
