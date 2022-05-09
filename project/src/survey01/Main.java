package survey01;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean flag = true;
		SurveyDAO dao = new SurveyDAO();
		Scanner in = new Scanner(System.in);
		int menu; // Main ȭ�� ����
		String input; 
		int choice = -1; //input���� �����϶� ��ȯ
		char tmp;
		boolean output = false;
		System.out.println("�������� - �����ϴ� ��ȭ �帣��?");
		
		while (flag) {
			System.out.println("1. ������ ���� \n2. ������Ȳ ���� \n3. ���α׷� ���� \n����(1,2,3 �� �Է�): ");
			menu = in.nextInt();
			switch(menu) {
			case 1 : 
				List<SurveyVO> scRet = dao.selectAll();
				System.out.println("������ ���� - �ش��ϴ� ��ȣ�� �Է����ּ���.");
				for (SurveyVO tmp1 : scRet) {
					System.out.println(tmp1.getNumber() +". "+ tmp1.getName());
				} 
				System.out.println("0. ��Ÿ(�����Է�): ");choice = in.nextInt();in.nextLine();
				
				
				//input = in.nextLine(); //�Է°� ���ڿ��� �޾Ƽ� Ȯ��
//				for (int i = 0 ; i < input.length() ; i++) {//�Է°� String: insertCat, 1~n�� ����:updateCat, �� ���� ����:�������
//					tmp = input.charAt(i);	
//					if (Character.isDigit(tmp) == false) { //isDigit -> ���ڸ� false, ���ڸ� true;
//						output = false;//����
//					} else output = true;
//					
//				}
				
				//if (output = false) { //�����Է�
					//System.out.println("�Է� ����(���ڷ� �Է����ּ���)");
				//} else { //true->����
					try {
						if(!(dao.checkCat(choice))) {
							if(choice==0) {
								System.out.println("�帣 �Է�:");
								String jr = in.nextLine();
								dao.insertCat(jr);
							}
							else {
								System.out.println("�ٽ� �Է����ּ���."); 
								continue;
							}
						}else {
							dao.updateCat(choice);
						}
					}				
					catch (NumberFormatException e) {
						System.out.println("���ڸ� �Է����ּ���..");
				}
			break;
			case 2 : //������Ȳ ����
				System.out.println("��Ȳ ����");
				List<SurveyVO> scRet2 = dao.selectAll();
				for (SurveyVO tmp2 : scRet2) {
					System.out.println(tmp2);
				}
				break;	
			case 3 :
				System.out.println("���α׷� ����");
				flag = false;
				break;
				
			default : 
				System.out.println("�Է°��� �߸��Ǿ����ϴ�.");
			}
		}
	}
}
