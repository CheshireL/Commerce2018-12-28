package com.biz.commerce.exec;

import com.biz.commerce.service.CommService02;

public class CommExec {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * CommService 클래스를 객체로 선언하여 매입매출 업무를 수행할 준비
		 * 
		 * strIn 변수를 매개변수로 전달 == 매입매출정보를 저장하고 있는 파일 이름을 매개변수로 전달한느 것과 같다
		 */
		String strF1 = "src/com/biz/commerce/매입매출장3.txt";
		String strF2 = "src/com/biz/commerce/상품리스트.txt";
		
		
		/*
		 * CommService 클래스를 객체로 선언하여
		 * 매입매출 업무를 수행할 준비
		 * 
		 * ioFile 변수를 매개변수로 전달
		 * == 매입매출정보를 저장하고 있는 파일 이름을
		 * 매개변수로 전달하는 것과 같다.
		 */
		CommService02 com = new CommService02(strF1, strF2);
		/*
		 * 매입매출정보.txt 파일에서 데이터를 읽어서
		 * iolist에 저장하는 메서드 호출(실행)
		 */
		com.ioRead();
		
		/*
		 * 위에서 is.ioRead()메서드를 실행했으므로
		 * is의 iolist에 매입매출정보가 저장되어 있을 것이다.
		 * 그 정보를 확인 해 보자
		 * 
		 * is.ioView() 메서드를 호출(실행)해서
		 * 정보 확인
		 */
		com.ioView();
		
		/*
		 * 상품정보를 읽어 pList에 저장하기 위해서
		 * is.pRead() 메서드 실행
		 */
		com.pRead();
		com.pView();
		
		com.saveFile();
		
		
		
				

	}

}
