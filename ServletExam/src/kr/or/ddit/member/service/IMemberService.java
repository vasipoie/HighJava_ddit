package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVo;

/**
 * 회원정보 관련 서비스를 위한 인터페이스
 * @author PC-25
 *
 */
public interface IMemberService {
	
	/**
	 * 회원등록을 위한 메서드
	 * @param mv 등록할 회원정보를 담은 VO객체
	 * @return 등록 성공하면 1, 실패하면 0이 반환된다.
	 */
	public int registMember(MemberVo mv);
	
	
	/**
	 * 회원정보를 수정하기 위한 메서드
	 * @param mv 수정할 회원정보를 담은 VO객체
	 * @return 수정을 성공하면 1, 실패하면 0이 반환된다.
	 */
	public int modifyMember(MemberVo mv);
	
	
	/**
	 * 회원이 존재하는지 체크하기 위한 메서드
	 * @param memId 존재여부 체크할 회원ID
	 * @return 회원이 존재하면 true, 존재하지 않으면 false 리턴
	 */
	public boolean checkMember(String memId);
	
	
	/**
	 * 회원 상세정보를 가져오기 위한 메서드
	 * @param memId 존재여부 체크할 회원ID
	 * @return 회원정보를 담은 MemberVo객체 리턴
	 */
	public MemberVo getMember(String memId);
	
	
	public MemberVo getLoginInfo(MemberVo loginInfo);
	
	
	/**
	 * 회원정보를 삭제하기 위한 메서드
	 * @param memId 삭제 할 회원ID
	 * @return 삭제 성공하면 1, 실패하면 0이 반환된다.
	 */
	public int removeMember(String memId);
	
	
	/**
	 * DB에 존재하는 모든 회원정보를 조회하기 위한 메서드
	 * @return 모든 등록된 회원정보
	 */
	public List<MemberVo> displayAll();

	
	/**
	 * 회원정보를 검색하기 위한 메서드
	 * @param mv 검색정보를 담은 MemberVo객체
	 * @return 검색된 회원정보를 담은 List
	 */
	public List<MemberVo> searchMember(MemberVo mv);
	
}
