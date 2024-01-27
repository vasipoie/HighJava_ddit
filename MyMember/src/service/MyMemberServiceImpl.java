package service;

import java.util.List;

import dao.IMyMemberDao;
import dao.MyMemberDaoImpl;
import vo.MyMemberVO;

public class MyMemberServiceImpl implements IMyMemberService{
	
	private static IMyMemberService mmSer;
	
	private IMyMemberDao mmDao = MyMemberDaoImpl.getInstance();
	
	public static IMyMemberService getInstance() {
		if(mmSer == null) {
			mmSer = new MyMemberServiceImpl();
		}
		return mmSer;
	}

	@Override
	public List<MyMemberVO> selectAll() {
		List<MyMemberVO> memList = mmDao.selectAll();
		return memList;
	}
	
	
	
}
