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
		System.out.println("�������� - ��ȣ�ϴ� ī�� ����");
		
		
		while (flag) {
			System.out.println("1. ������ ���� \n2. ������Ȳ ���� \n3. ���α׷� ���� \n����(1,2,3 �� �Է�): ");
			menu = in.nextInt();
			switch(menu) {
			case 1 : //��������
				List<CoffeeVO> cRet = cDao.selectAll(); //Coffee ���̺�
				System.out.println("��ȣ�ϴ� ����: ���������� vs �Ϲ�ī��"); // ����1.
				for (CoffeeVO tmp1 : cRet) {
					System.out.println(tmp1.getNumber()+". "+tmp1.getCategory());
				}
				
				choice = in.nextInt();in.nextLine(); //�Է°�
				if (choice == 1 || choice == 2) {
					cDao.updateC(choice); //coffee ���̺� �� ����
					if (choice == 1) { //���� -> �귣�� ����
						List<BrandVO> bRet = bDao.selectAll(); //Brand ���̺�
						System.out.println("���������� �� ��ȣ�ϴ� Brand�� �������ּ���.");
						for (BrandVO tmp2 : bRet) {
							System.out.println(tmp2.getNumber()+". "+tmp2.getBrand());
						}
						System.out.println("0. ��Ÿ(�����Է�)");
						cBrand = in.nextInt(); in.nextLine(); //�Է°�
						try {
							if (!(bDao.checkBrand(cBrand))) {
								if (cBrand == 0) {
									System.out.println("��ȣ�� �Է�: ");
									String bName = in.nextLine();
									bDao.insertBrand(bName);
								} else {
									System.out.println("�ٽ� �Է����ּ���.");
									continue;
								}
							} else {
								bDao.updateCount(cBrand);
							}
						} catch (NumberFormatException e) {
							System.out.println("�Է°��� ���ڰ� �ƴմϴ�.");
						}
						//���� ���� => Reason ����
						System.out.println("�亯�� ������ ����ּ���.");
						List<ReasonVO> rRet = rDao.selectAll(1);
						for (ReasonVO tmp3 : rRet) {
							System.out.println(tmp3.getNumber()+". "+tmp3.getReason());
						}
						cReason = in.nextInt();in.nextLine();
						try {
							if (!(rDao.checkReason(cReason))) {
								System.out.println("�Է°��� ��Ͽ� �����ϴ�.");
								continue;
							}
							else {
								rDao.updateReason(cReason, 1);
							}
						} catch (NumberFormatException e) {
							System.out.println("�Է°��� ���ڰ� �ƴմϴ�.");
						}
						continue;				
					} //�Ϲ�ī�� ���ý� => �귣�忡 ���� ������ X, Reason ����� �Ѿ
					System.out.println("�亯�� ������ ����ּ���.");
					
					List<ReasonVO> rRet = rDao.selectAll(2);
					for (ReasonVO tmp4 : rRet) {
						System.out.println(tmp4.getNumber()+". "+tmp4.getReason());
					}
					cReason = in.nextInt();in.nextLine();
					try {
						if (!(rDao.checkReason(cReason))) {
							System.out.println("�Է°��� ��Ͽ� �����ϴ�.");
							continue;
						}
						else {
							rDao.updateReason(cReason, 2);
						}
					} catch (NumberFormatException e) {
						System.out.println("�Է°��� ���ڰ� �ƴմϴ�.");
					}
					
					
					
				} else { //1,2 �� ���� �Է½�
					System.out.println("�Է� ���� �߸��Ǿ����ϴ�.");
					continue;
				}
				break;
			
			case 2:
				System.out.println("��Ȳ ����");
				
				break;
			
			
			case 3:
				System.out.println("���α׷� ����");
				flag = false;
				break;
			
			default : 
				System.out.println("�Է°��� �߸��Ǿ����ϴ�.");
				
			}
			
			
		}

	}

}
