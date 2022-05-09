package survey01;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean flag = true;
		SurveyDAO dao = new SurveyDAO();
		Scanner in = new Scanner(System.in);
		int menu; // Main 화면 선택
		String input; 
		int choice = -1; //input값이 숫자일때 변환
		char tmp;
		boolean output = false;
		System.out.println("설문조사 - 좋아하는 영화 장르는?");
		
		while (flag) {
			System.out.println("1. 설문에 참여 \n2. 설문현황 보기 \n3. 프로그램 종료 \n선택(1,2,3 중 입력): ");
			menu = in.nextInt();
			switch(menu) {
			case 1 : 
				List<SurveyVO> scRet = dao.selectAll();
				System.out.println("설문에 참여 - 해당하는 번호를 입력해주세요.");
				for (SurveyVO tmp1 : scRet) {
					System.out.println(tmp1.getNumber() +". "+ tmp1.getName());
				} 
				System.out.println("0. 기타(직접입력): ");choice = in.nextInt();in.nextLine();
				
				
				//input = in.nextLine(); //입력값 문자열로 받아서 확인
//				for (int i = 0 ; i < input.length() ; i++) {//입력값 String: insertCat, 1~n의 숫자:updateCat, 그 외의 숫자:오류출력
//					tmp = input.charAt(i);	
//					if (Character.isDigit(tmp) == false) { //isDigit -> 문자면 false, 숫자면 true;
//						output = false;//문자
//					} else output = true;
//					
//				}
				
				//if (output = false) { //문자입력
					//System.out.println("입력 오류(숫자로 입력해주세요)");
				//} else { //true->숫자
					try {
						if(!(dao.checkCat(choice))) {
							if(choice==0) {
								System.out.println("장르 입력:");
								String jr = in.nextLine();
								dao.insertCat(jr);
							}
							else {
								System.out.println("다시 입력해주세요."); 
								continue;
							}
						}else {
							dao.updateCat(choice);
						}
					}				
					catch (NumberFormatException e) {
						System.out.println("숫자를 입력해주세요..");
				}
			break;
			case 2 : //설문현황 보기
				System.out.println("현황 보기");
				List<SurveyVO> scRet2 = dao.selectAll();
				for (SurveyVO tmp2 : scRet2) {
					System.out.println(tmp2);
				}
				break;	
			case 3 :
				System.out.println("프로그램 종료");
				flag = false;
				break;
				
			default : 
				System.out.println("입력값이 잘못되었습니다.");
			}
		}
	}
}
