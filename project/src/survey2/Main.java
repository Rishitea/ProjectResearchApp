package survey2;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean flag = true;
		int choice = -1;
		int cBrand = -1;
		int cReason = -1;
		CoffeeDAO cDao = new CoffeeDAO();
		BrandDAO bDao = new BrandDAO();
		ReasonDAO rDao = new ReasonDAO();
		Scanner in = new Scanner(System.in);
		int menu;
		System.out.println("설문조사 - 선호하는 카페 형태");
		
		
		while (flag) {
			System.out.println("1. 설문에 참여 \n2. 설문현황 보기 \n3. 프로그램 종료 \n선택(1,2,3 중 입력): ");
			menu = in.nextInt();
			switch(menu) {
			case 1 : //설문참여
				List<CoffeeVO> cRet = cDao.selectAll(); //Coffee 테이블
				System.out.println("선호하는 형태: 프랜차이즈 vs 일반카페"); // 질문1.
				for (CoffeeVO tmp1 : cRet) {
					System.out.println(tmp1.getNumber()+". "+tmp1.getCategory());
				}
				
				choice = in.nextInt();in.nextLine(); //입력값
				if (choice == 1 || choice == 2) {
					cDao.updateC(choice); //coffee 테이블에 값 저장
					if (choice == 1) { //프차 -> 브랜드 연결
						List<BrandVO> bRet = bDao.selectAll(); //Brand 테이블
						System.out.println("프랜차이즈 중 선호하는 Brand를 선택해주세요.");
						for (BrandVO tmp2 : bRet) {
							System.out.println(tmp2.getNumber()+". "+tmp2.getBrand());
						}
						System.out.println("0. 기타(직접입력)");
						cBrand = in.nextInt(); in.nextLine(); //입력값
						try {
							if (!(bDao.checkBrand(cBrand))) {
								if (cBrand == 0) {
									System.out.println("상호명 입력: ");
									String bName = in.nextLine();
									bDao.insertBrand(bName);
								} else {
									System.out.println("다시 입력해주세요.");
									continue;
								}
							} else {
								bDao.updateCount(cBrand);
							}
						} catch (NumberFormatException e) {
							System.out.println("입력값이 숫자가 아닙니다.");
						}
						//프차 선택 => Reason 고르기
						System.out.println("답변의 이유를 골라주세요.");
						List<ReasonVO> rRet = rDao.selectAll(1);
						for (ReasonVO tmp3 : rRet) {
							System.out.println(tmp3.getNumber()+". "+tmp3.getReason());
						}
						cReason = in.nextInt();in.nextLine();
						try {
							if (!(rDao.checkReason(cReason))) {
								System.out.println("입력값이 목록에 없습니다.");
								continue;
							}
							else {
								rDao.updateReason(cReason, 1);
							}
						} catch (NumberFormatException e) {
							System.out.println("입력값이 숫자가 아닙니다.");
						}
						continue;				
					} //일반카페 선택시 => 브랜드에 관한 질문은 X, Reason 고르기로 넘어감
					System.out.println("답변의 이유를 골라주세요.");
					
					List<ReasonVO> rRet = rDao.selectAll(2);
					for (ReasonVO tmp4 : rRet) {
						System.out.println(tmp4.getNumber()+". "+tmp4.getReason());
					}
					cReason = in.nextInt();in.nextLine();
					try {
						if (!(rDao.checkReason(cReason))) {
							System.out.println("입력값이 목록에 없습니다.");
							continue;
						}
						else {
							rDao.updateReason(cReason, 2);
						}
					} catch (NumberFormatException e) {
						System.out.println("입력값이 숫자가 아닙니다.");
					}
					
					
					
				} else { //1,2 외 숫자 입력시
					System.out.println("입력 값이 잘못되었습니다.");
					continue;
				}
				break;
			
			case 2:
				System.out.println("현황 보기");
				
				break;
			
			
			case 3:
				System.out.println("프로그램 종료");
				flag = false;
				break;
			
			default : 
				System.out.println("입력값이 잘못되었습니다.");
				
			}
			
			
		}

	}

}
