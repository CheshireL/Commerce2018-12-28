package com.biz.commerce.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.commerce.vo.CommVo;
import com.biz.commerce.vo.ProductVo;

/*
 * 매입매출 관련 처리를 위한
 * 클래스를 선언 
 */
public class CommService02 {

	/*
	 * 각 데이터를 관리할 List변수들 선언
	 */
	List<CommVo> iolist; // 매입매출정보 table
	List<ProductVo> pList; // 상품정보 table

	/*
	 * Text 파일 관련 변수들 선언
	 */
	String ioFile; // 매입매출 파일이름
	String pFile; // 상품정보 파일이름

	/*
	 * IoService 클래스로 객체를 생성할때 호출되는 생성자를 선언
	 * 
	 * 이 클래스는 Text 파일을 읽어서 매입매출 관련 업무를 수행할 것이므로 생성자에서는 Text 파일의 경로정보를 매개변수로 받아서 변수에
	 * 저장하는 코드를 수행한다.
	 */
	public CommService02(String ioFile, String pFile) {
		/*
		 * 매개변수로 받은 ioFile의 내용을 this.ioFile member변수에 저장하여 다른 method에서 사용할 수 있도록 준비
		 */
		this.ioFile = ioFile;

		/*
		 * 매개변수로 받은 pFile의 내용을 this.pFile member변수에 저장하여 다른 method에서 사용할 수 있도록 준비
		 */
		this.pFile = pFile;

		/*
		 * 매입매출정보의 list를 담을 iolist를 초기화한다
		 */
		this.iolist = new ArrayList();

		/*
		 * 상품정보의 list를 담을 pList를 초기화 한다.
		 */
		this.pList = new ArrayList();
	}
	
	/*
	 * iolist와 pList에 담긴 데이터를 사용해서
	 * 매입매출List 를 출력하는 메서드를 선언
	 * 1. iolist에 담겨있는 상품코드를 사용해서
	 * 		pList에 담겨있는 상품정보를 찾아서 가져오기
	 * 
	 */
	
	public void viewIoInfo() {
		for(CommVo iv : iolist) {
			for(ProductVo pv : pList ) {
				if(iv.getIo_ccode().equals(pv.getP_code())) {
					System.out.print(iv.getIo_ccode());
					System.out.println(pv.getP_name());
				}
			}
		}
	}
	
	/*
	 * 상품리스트.txt와 매입매출장.txt 파일에서 같은 코드에 있는 
	 * 상품들만을 읽어서 정리후 매입매출정보.txt 에 저장하는 메소드
	 */
	public void saveFile() {
		/*
		 * 새로운 txt 파일에 쓰고 저장하기 위한 PrintWriter 선언
		 */
		PrintWriter pw ;
		// 저장할 txt 파일을 지정하고 String형으로 선언
		String strFileName = "src/com/biz/commerce/vo/매입매출정보.txt";
		
		try {
			/*
			 *  생성자에서 strFileName 에 파일을 저장하기위한 코드
			 *  이 코드는 문제가 발생할수 있으므로 try .. catch로 감싼다 
			 */
			
			pw = new PrintWriter(strFileName);
		
		for(CommVo iv : iolist) {
			/*
			 * serchePro..() 메서드에게
			 * 상품코드를 전달해 주고
			 * pList로 부터 상품을 찾아라 라고 명령
			 */
			ProductVo v = searchProduct(iv.getIo_ccode());
			/*
			 * 만약 serchPro..() 메서드가 null을 보내오면
			 * 일단 그 상품은 무시하고
			 * (상품명을 없는 것으로 처리)
			 * 그 다음 매입매출 정보를 처리
			 */
			if(v == null) continue;
			
			/*
			 *  v 가 null 아니면 = 상품코드를 찾았으면
			 *  여기에 다다르면 매입매출정보의 상품코드에
			 *  해당하는 상품정보가 담긴 v가 만들어 진다.
			 */

			
			/*
			 *  단가와 금액
			 *  매입매출데이터의 단가가 
			 *  거래구분이 1 이면 매입단가이고,
			 *  거래구분이 2 이면 매출단가 이다.
			 */
			int intIPrice = 0;
			int intOPrice = 0;
			String strIOTag = "";
			/*
			 * 거래를 구분할 코드생성
			 */
			String strInOut = iv.getIo_inout();
			int intInout = Integer.valueOf(strInOut);
			
			if(intInout == 1) {
				// 1 이면 매입
				intIPrice = iv.getIo_price();
				intOPrice = 0;
				strIOTag = "매입";
			} else {
				// 아니면 (2 이면) 매출
				intIPrice = 0;
				intOPrice = iv.getIo_price();
				strIOTag = "매출";
			}
			
			// pw ( 지정된 txt 파일) 에 쓰고 저장하라
			pw.print(iv.getIo_date() + ":");
			pw.print(strIOTag + ":");
			pw.print(iv.getIo_ccode() + ":");
			pw.print(v.getP_name() + ":");
			pw.print(iv.getIo_price() + ":");
			pw.print(iv.getIo_quan() + ":");
			pw.print((intIPrice 
					* Integer.valueOf(iv.getIo_quan())) + ":");
			pw.println((intOPrice
					* Integer.valueOf(iv.getIo_quan()))+":");
			
			}
		// 쓰기가 끝났으니 닫기
		pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ProductVo searchProduct(String pCode) {
		for(ProductVo pv : pList ) {
			
			// 1번코드
			if(pCode.equals(pv.getP_code())) {
				return pv;
			}
			
			// 좀더 과장하면 위 비교 메서드도 분리한다
			// 2번코드
			if(isPCode(pCode, pv.getP_code())){
				return pv;
			}
			
			
		}
		return null;
	}
	
	public boolean isPCode(String io_pcode, String p_code) {
		return io_pcode.equals(p_code);
	}
	
	
	

	/*
	 * 상품리스트.txt 파일을 읽어 
	 * pList가 잘 만들어졌는지 확인하는 메서드 선언
	 */
	public void pView() {
		for(ProductVo vo : pList) {
			System.out.println(vo);
		}
	}
	
	
	/*
	 * 상품리스트.txt 파일에서 문자열을 읽어서 pList 에 저장하는 메서드 선언
	 */
	public void pRead() {
		
		/*
		 * 상품리스트.txt를 읽기 위한 FileReader 선언
		 */
		FileReader fr;
		// 위에서 읽은 파일을 좀더 쉽게 추출하기 위한 buffer 객체 선언
		BufferedReader buffer;

		try {
			/*
			 * 생성자에서 pFile의 내용을 참조하여 파일을 읽기 위하여 open하는 코드
			 * 이 코드는 문제가 발생할수 있으므로 try..catch 문으로 감싸 주어야 한다.
			 */
			fr = new FileReader(pFile);
			/*
			 * FileReader로 open된 파일 정보를 Buffer에 연결하여 준다. 이 코드가 실행되면 BufferedReader는 일단 파일을
			 * 읽어서 메모리의 Buffer 영역에 저장해 둔다.
			 */
			buffer = new BufferedReader(fr);
			
			// 무한 반복문을 실행해서 한줄씩 읽기 시작
			while (true) {
				// buffer에서 한줄씩 읽어서 reader에 저장
				String reader = buffer.readLine();
				// 더이상 읽을게 없을시 반복문 종료
				if (reader == null) 
					break;
				/*
				 * 문자열을 ":" 으로 분해하고 pros 객체에 저장
				 */
				String[] pros = reader.split(":");
				// 새로운 vo 생성 및 초기화
				ProductVo vo = new ProductVo();

				// 분해한 문자열을 member변수에 저장
				vo.setP_code(pros[0]);
				vo.setP_name(pros[1]);
				vo.setP_vat(pros[2]);

				// int형인 변수들을 int형으로 변환후 저장
				int iPrice = Integer.valueOf(pros[3]);
				int oPrice = Integer.valueOf(pros[4]);

				vo.setP_iprice(iPrice);
				vo.setP_oprice(oPrice);

				// vo에 세팅된 member 변수들을 pList에 저장
				pList.add(vo);

			}
			// 다 읽었으니 파일 닫기
			buffer.close();
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * iolist에 저장되어 있는 매입매출정보 리스트를 console에 표시해서 잘 저장되어 있는지 검사하는 메서드 선언
	 */
	public void ioView() {
		for (CommVo vo : iolist) {
			System.out.println(vo);
		}
	}

	/*
	 * 매입매출데이터.txt 파일에서 문자열을 읽어서 iolist에 저장하는 메서드 선언
	 */
	public void ioRead() {

		/*
		 * Text 파일을 읽기 위한 FileReader 객체 선언
		 */
		FileReader fr;
		/*
		 * FileReader로 읽은 내용에서 문자열을 쉽게 추출할수 있도록 연결되는 Buffer 객체 선언
		 */
		BufferedReader buffer;

		try {
			/*
			 * 생성자에서 값이 할당된 ioFile의 내용을 참조하여 파일을 읽기 위하여 open하는 코드
			 * 
			 * 이 코드는 작동되는 과정에서 문제가 발생할수 있으므로 try..catch 문으로 감싸 주어야 한다.
			 */
			fr = new FileReader(ioFile);

			/*
			 * FileReader로 open된 파일 정보를 Buffer에 연결하여 준다. 이 코드가 실행되면 BufferedReader는 일단 파일을
			 * 읽어서 메모리의 Buffer 영역에 저장해 둔다.
			 */
			buffer = new BufferedReader(fr);

			/*
			 * 무한반복문을 이용해서 Buffer에 저장된 파일내용에서 한줄씩 읽어서 처리한다.
			 */
			while (true) {
				/*
				 * buffer에서 한줄을 읽어서 reader 변수에 저장
				 */
				String reader = buffer.readLine();

				/*
				 * 만약 reader에 저장된 값이 null이면 모든 문자열을 다 읽었다는 것이므로 반복문을 종료한다.
				 */
				if (reader == null)
					break;

				/*
				 * 반복문이 종료되지 않았으므로 (reader 변수에 문자열이 담겨 있다는 것) 코드가 실행되어 console 에 해당 문자열을 표시한다.
				 */
				System.out.println(reader);

				/*
				 * reader 문자열을 콜론(:)으로 분리해서 IolistVO에 담고 iolist에 추가하는 부분
				 */

				/*
				 * String.split() 메서드를 사용해서 문자열 분해하고 ios 문자열 배열에 저장
				 */
				String[] ios = reader.split(":");

				/*
				 * 새로운 vo를 생성(선언과 초기화)
				 */
				CommVo vo = new CommVo();

				/*
				 * vo의 각 member변수에 값을 저장
				 */
				vo.setIo_date(ios[0]);
				vo.setIo_ccode(ios[1]);
				vo.setIo_inout(ios[2]);

				/*
				 * vo.io_price는 int형 변수 이므로 문자열을 int로 먼저 변환한다.
				 */
				int intPrice = Integer.valueOf(ios[3]);

				/*
				 * 변환된 intPrice를 vo.io_price에 저장
				 */
				vo.setIo_price(intPrice);

				int intQuan = Integer.valueOf(ios[4]);
				vo.setIo_quan(intQuan);

				/*
				 * member변수가 setting된 vo를 iolist에 추가
				 */
				iolist.add(vo);
			}

			/*
			 * 파일을 모두 사용(읽기)했으므로 안전하게 닫아 준다
			 */
			buffer.close();
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
		
}