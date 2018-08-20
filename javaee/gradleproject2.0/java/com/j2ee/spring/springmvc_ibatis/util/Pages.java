package com.j2ee.spring.springmvc_ibatis.util;

import java.util.List;

/**
 * ��ҳ������
 * @author archie2010
 *
 * @param <T>
 */
public class Pages<T> {
	private int pageSize =5; //һҳ��ʾ�������
	private int totalCount; //������ݵ�������
	private int start; //��ǰҳ����ʼ����
	private int totalPages; //��ҳ��
	private List<T> pageList;
	
	public Pages(int totalCount){
		this.totalCount = totalCount;
	}
	
	/**
	 * ��ȡ��ǰҳ��
	 */
	public int getCurrentPageNo(){
		return start / pageSize + 1;
	}
	/**			
	 * �Ƿ�����һҳ
	 * @return
	 */
	public boolean getHasNextPage(){
		return getCurrentPageNo() < totalPages;
	}
	/**
	 * �Ƿ�����һҳ
	 * @return
	 */
	public boolean getHasPavPage(){
		return getCurrentPageNo() > 1;
	}
	/**
	 * ��ȡ��ҳ��
	 * @return
	 */
	public int getTotalPages() {
		totalPages = totalCount / pageSize;
		
		if(totalCount % pageSize != 0){
			totalPages++;
		}
		
		return totalPages;
	}
	/**
	 * ��õ�ǰҳ����ʼ��
	 * @param pageNo ��ǰҳ��
	 * @return
	 */
	public int getStart(int pageNo){
		
		if(pageNo < 1){
			pageNo = 1;
		}
		else if(pageNo > getTotalPages()){
			pageNo = getTotalPages();
		}
		
		start = (pageNo-1) * pageSize;
		return start;
	}
	
	//get and set
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
