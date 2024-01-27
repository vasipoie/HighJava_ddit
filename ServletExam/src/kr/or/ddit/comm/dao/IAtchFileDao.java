package kr.or.ddit.comm.dao;

import java.util.List;

import kr.or.ddit.comm.vo.AtchFileVo;

public interface IAtchFileDao {
	
	/**
	 * 첨부파일 기본정보 저장
	 * @param atchFileVo
	 * @return
	 */
	public int insertAtchFile(AtchFileVo atchFileVo);
	
	
	/**
	 * 첨부파일 세부정보 저장
	 * @param atchFileVo
	 * @return
	 */
	public int insertAtchFileDetail(AtchFileVo atchFileVo);
	
	
	/**
	 * 첨부파일 목록 조회하기
	 * @param atchFileVo
	 * @return
	 */
	public List<AtchFileVo> getAtchFileList(AtchFileVo atchFileVo);
	
	
	/**
	 * 첨부파일 세부정보 조회 메서드
	 * @param atchFileVo
	 * @return
	 */
	public AtchFileVo getAtchFileDetail(AtchFileVo atchFileVo);
	
	
	

}
