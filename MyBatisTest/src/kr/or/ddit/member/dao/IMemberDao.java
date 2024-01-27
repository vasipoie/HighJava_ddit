package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVo;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 받아와 서비스에 전달하는 DAO의 인터페이스
 * @author PC-25
 *
 */
public interface IMemberDao {
	
	/**
	 * MemberVo에 담긴 데이터를 insert하기 위한 메서드
	 * @param mv 등록할 회원정보를 담은 VO객체
	 * @return DB작업이 성공하면 1, 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVo mv);
	
	
	/**
	 * MemberVo에 담긴 데이터를 update하기 위한 메서드
	 * @param mv 수정할 회원정보를 담은 VO객체
	 * @return DB작업이 성공하면 1, 실패하면 0이 반환된다.
	 */
	public int updateMember(MemberVo mv);
	
	
	/**
	 * 회원이 존재하는지 체크하기 위한 메서드
	 * @param memId 존재여부 체크할 회원ID
	 * @return 회원이 존재하면 true, 존재하지 않으면 false 리턴
	 */
	public boolean checkMember(String memId);
	
	
	/**
	 * 회원정보를 삭제하기 위한 메서드
	 * @param memId 삭제 할 회원ID
	 * @return DB작업이 성공하면 1, 실패하면 0이 반환된다.
	 */
	public int deleteMember(String memId);
	
	
	/**
	 * DB에 존재하는 모든 회원정보를 조회하기 위한 메서드
	 * @return
	 */
	public List<MemberVo> selectAll();

	
	/**
	 * 
	 * @param mv 
	 * @return
	 */
	public List<MemberVo> searchMember(MemberVo mv);
	
}
