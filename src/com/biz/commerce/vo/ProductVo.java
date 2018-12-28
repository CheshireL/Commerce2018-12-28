package com.biz.commerce.vo;
/*
 * 상품정보.txt 파일의 데이터를 저장할 vo클래스를 선언
 */

public class ProductVo {
	/*
	 * 상품정보의 각 요소를 저장할 칼럼
	 * member 변수 선언
	 * 
	 * 외부에서 직접 접근하지 못하도록 정보를 은닉하고 캡슐화 한다
	 */
	private String p_code;
	private String p_name;
	private String p_vat;
	private int p_iprice;
	private int p_oprice;
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_vat() {
		return p_vat;
	}
	public void setP_vat(String p_vat) {
		this.p_vat = p_vat;
	}
	public int getP_iprice() {
		return p_iprice;
	}
	public void setP_iprice(int p_iprice) {
		this.p_iprice = p_iprice;
	}
	public int getP_oprice() {
		return p_oprice;
	}
	public void setP_oprice(int p_oprice) {
		this.p_oprice = p_oprice;
	}
	@Override
	public String toString() {
		return "ProductVo [p_code=" + p_code + ", p_name=" + p_name + ", p_vat=" + p_vat + ", p_iprice=" + p_iprice
				+ ", p_oprice=" + p_oprice + "]";
	}

	
}
