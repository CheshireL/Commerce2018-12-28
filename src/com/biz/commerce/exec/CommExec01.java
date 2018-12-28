package com.biz.commerce.exec;
/*
 *  자바야 지금부터 CommExec이라는 클래스를 시작한다
 *  
 */
public class CommExec01 {
	/*
	 *  만약 JVM에서 이 프로젝트를 실행할때, CommExec 클래스에서 진입점 매서드를 호출할텐데
	 *  네가 응답하렴
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 1부터 100까지 연속된 숫자중에서 짝수인 숫자만 모아서 그 덧셈을 하여 나에게 보여주
		 */
		
		/*
		 *  intSum 변수는 덧셈을 수행하면서 짝수들의 합을 저장할 변수로 선언한다
		 *   
		 */
		int intSum = 0;
		
		// 반복문을 실행해서 1부터 100까지 연속된 숫자를 만들어 낸다
		for(int i = 1 ; i <= 100; i++) {
			// for 안에서 if분을 사용해서 짝수 판별을 하고 짝수이면 intSum 변수에 누적하라
			/*if(i%2 == 0) {
				intSum += i;
			}
			*/
			// for 반복문에서 생성된 숫자를 addEven() 매서드에게 매개변수로 전달한다
			intSum += addEven(i);
		}
	}
	// 숫자 1개를 매개변수로 받아 짝수이면 num숫자를 return하고 아니면 0을 return한다
	public static int addEven(int num) {
		if(num%2 == 0) return num;
		else return 0;
	}

}
