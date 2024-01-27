package kr.or.ddit.member.vo;

import java.time.LocalDate;

/**
 * DB 테이블에 존재하는 컬럼명을 기준으로 데이터를 처리하기 위한 클래스
 * @author PC-25
 *
 */
public class MemberVo {
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	private LocalDate regDt;
	private long atchFileId = -1;
	private String memPass;
	
	public MemberVo(String memId, String memName, String memTel, String memAddr) {
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
		this.memAddr = memAddr;
	}
	
	
	
	public String getMemPass() {
		return memPass;
	}



	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}



	public MemberVo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public LocalDate getRegDt() {
		return regDt;
	}
	public void setRegDt(LocalDate regDt) {
		this.regDt = regDt;
	}
	public long getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}

	@Override
	public String toString() {
		return "MemberVo [memId=" + memId + ", memName=" + memName + ", memTel=" + memTel + ", memAddr=" + memAddr
				+ ", regDt=" + regDt + "]";
	}
	
	
}
