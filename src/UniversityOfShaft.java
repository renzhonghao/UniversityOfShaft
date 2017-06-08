import dao.ScoreDao;
import entity.ScoreEntity;

public class UniversityOfShaft {
	
	public static void main(String[] args) {
		ScoreEntity []score;
		score=new ScoreEntity[10];
//		System.out.println(score.length);
		for (int i = 0; i < score.length-1; i++) {
//			System.out.println(i);
			score[i]=new ScoreEntity();
			score[i].setSC_CourseName("数学"+i);
			score[i].setSC_Score(20+i);
			score[i].setSC_Term('1');
			score[i].setStu_SNo("631406010128");
		}
		score[9]=new ScoreEntity();
		score[9].setSC_CourseName("数学1");
		score[9].setSC_Score(99);
		score[9].setSC_Term('1');
		score[9].setStu_SNo("631406010128");
		ScoreDao dao=new ScoreDao();
		dao.insert(score);
//		ScoreEntity score=new ScoreEntity();
//		score.setSC_CourseName("语文");
//		score.setSC_Score(60);
//		score.setSC_Term('1');
//		score.setStu_SNo("12321");
//		System.out.println(score.toString());
	}

}
