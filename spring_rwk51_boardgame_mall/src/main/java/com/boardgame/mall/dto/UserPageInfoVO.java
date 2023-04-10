package com.boardgame.mall.dto;

public class UserPageInfoVO {
	private int limitCnt;
	
	private int recordCnt;
	private int pageCnt;
	
	private int startRecord;
	private int currentPageNo;
	
	private int startPageNo;
	private int endPageNo;

	
	public UserPageInfoVO() {
		super();
		
		this.recordCnt = 0;
		
		this.currentPageNo = 0;
		this.startRecord = 0;
		this.limitCnt = 10;
		this.endPageNo = this.limitCnt;
	}
	public UserPageInfoVO(int limitCnt, int recordCnt, int pageCnt, int startRecord, int currentPageNo, int startPageNo,
			int endPageNo) {
		super();
		this.limitCnt = limitCnt;
		this.recordCnt = recordCnt;
		this.pageCnt = pageCnt;
		this.startRecord = startRecord;
		this.currentPageNo = currentPageNo;
		this.startPageNo = startPageNo;
		this.endPageNo = endPageNo;
	}
	
	public int getLimitCnt() {
		return limitCnt;
	}
	public void setLimitCnt(int limitCnt) {
		this.limitCnt = limitCnt;
	}
	public int getRecordCnt() {
		return recordCnt;
	}
	public void setRecordCnt(int recordCnt) {
		this.recordCnt = recordCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}
	public int getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}
	
	/**페이지를 나눌때 나머지가 10이아닌 경우 pageCnt의 값을 1증가 */
	public void adjPageCnt() {
		pageCnt = recordCnt/limitCnt;
		if(recordCnt%limitCnt !=0)
			pageCnt++;
	}
	
	/**화면에 띄워줄 데이터의 갯수  */
	public void adjStartRecord() {
		startRecord = currentPageNo * limitCnt;
	}
	
	/**10개 단위로 페이지버튼을 보여줄때 계산하는 식 메소드*/
	public void adjStartPageNo() {
		startPageNo = currentPageNo/10 * 10;
	}
	
	/**데이터를 보여주는 항목이 마지막 부분을 짜르는 메소드 */
	public void adjEndPageNo() {
		endPageNo = startPageNo + 10;
		if(endPageNo > pageCnt) {
			endPageNo = pageCnt;
		}
	}
	
	/**페이징 기능적용 */
	public void adjPageInfo() {
		adjPageCnt();
		adjStartRecord();
		adjStartPageNo();
		adjEndPageNo();
	}
	
}
