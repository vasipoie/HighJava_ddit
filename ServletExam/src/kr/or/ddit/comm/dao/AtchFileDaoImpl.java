package kr.or.ddit.comm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comm.vo.AtchFileVo;
import kr.or.ddit.util.MyBatisUtil;

public class AtchFileDaoImpl implements IAtchFileDao {
	
	private static IAtchFileDao fileDao;
	
	private AtchFileDaoImpl() {
		
	}
	
	public static IAtchFileDao getInstance() {
		if(fileDao == null) {
			fileDao = new AtchFileDaoImpl();
		}
		return fileDao;
	}

	@Override
	public int insertAtchFile(AtchFileVo atchFileVo) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = session.insert("atchFile.insertAtchFile",atchFileVo);
			
			if(cnt>0) {
				session.commit();
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int insertAtchFileDetail(AtchFileVo atchFileVo) {
SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = session.insert("atchFile.insertAtchFileDetail",atchFileVo);
			
			if(cnt>0) {
				session.commit();
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public List<AtchFileVo> getAtchFileList(AtchFileVo atchFileVo) {
		
		List<AtchFileVo> atchFileList = new ArrayList<AtchFileVo>();
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			atchFileList = session.selectList("atchFile.getAtchFileList",atchFileVo);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return atchFileList;
	}

	@Override
	public AtchFileVo getAtchFileDetail(AtchFileVo atchFileVo) {
		
		AtchFileVo atchFileDetail = null;
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			atchFileDetail = session.selectOne("atchFile.getAtchFileDetail",atchFileVo);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return atchFileDetail;
	}
	
	
	public static void main(String[] args) {
		
		IAtchFileDao fileDao = AtchFileDaoImpl.getInstance();
		
		AtchFileVo atchFileVo = new AtchFileVo();
		int cnt = fileDao.insertAtchFile(atchFileVo);
		
		if(cnt > 0) {
			System.out.println("등록 성공!!!");
			System.out.println(atchFileVo.getAtchFileId());
			
			
			//////////////////////////////////////////////
			
			
			atchFileVo.setFileStreCours("/aaa/bbb/ccc.jpg");
			atchFileVo.setStreFileNm("aaaabbbbbb.jpg");
			atchFileVo.setOrignlFileNm("ccc.jpg");
			atchFileVo.setFileExtsn("jpg");
			atchFileVo.setFileCn("");
			atchFileVo.setFileSize(10);
			
			cnt = fileDao.insertAtchFileDetail(atchFileVo);
			
			if(cnt>0) {
				System.out.println("detail등록 성공!!!");
			}
			
		}
	}

}
