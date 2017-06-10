import dao.PersonalDataDao;
import dao.ScoreDao;
import entity.PersonalDataEntity;
import entity.ScoreEntity;
import util.excelimport;

public class UniversityOfShaft {
	
	public static void main(String[] args) {
//		ScoreEntity []score;
//		score=new ScoreEntity[10];
//		for (int i = 0; i < score.length-1; i++) {
////			System.out.println(i);
//			score[i]=new ScoreEntity();
//			score[i].setSC_CourseName("数学"+i);
//			score[i].setSC_Score(20+i);
//			score[i].setSC_Term('1');
//			score[i].setStu_SNo("631406010128");
//		}
//		ScoreDao dao=new ScoreDao();
//		dao.insert(score);
		
		String [][]data;
		data=excelimport.Import("D:/信息/2014级信息2014级信息物联网一班.xls");
		PersonalDataEntity []pe;
		pe=new PersonalDataEntity[data.length];
		for(int i=0;i<data.length;i++){		
			pe[i]=new PersonalDataEntity();
			pe[i].setStu_SNo(data[i][1]);
			pe[i].setStu_Name(data[i][2]);
			pe[i].setStu_Sex(data[i][3]);
			pe[i].setStu_Id(data[i][4]);
			pe[i].setStu_Nation(data[i][5]);
			pe[i].setStu_Photo(data[i][7]);
			pe[i].setStu_Home(data[i][11]);
			pe[i].setStu_Assess(data[i][13]);
			pe[i].setStu_Bir("");
			pe[i].setStu_ClassName("");
			pe[i].setStu_From("");
			pe[i].setStu_Photo("");
//			System.out.println(pe[i].toString());
		}
		PersonalDataDao pedao=new PersonalDataDao();
		pedao.insert(pe);
	}

}
