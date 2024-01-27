package kr.or.ddit.comm.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import kr.or.ddit.comm.vo.AtchFileVo;

public interface IAtchFileService {
	
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * @param parts 파트 정보를 담고 있는 컬렉션 객체
	 * @return
	 */
	public AtchFileVo saveAtchFileList(Collection<Part> parts);
	

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
