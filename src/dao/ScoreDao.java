package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.batch.Main;

import entity.ScoreEntity;
import util.DBUtil;

public class ScoreDao {
	/**
	 * 将score数组批量插入数据库（存在错误将全部回滚）
	 * @param Score
	 */
	public void insert(ScoreEntity Score[]) {
		// 连接数据库
		Connection connection = null;
		try {
			System.out.println("进入");
			connection = DBUtil.getConnection();
			// 把自动提交事务设置为FALSE
			connection.setAutoCommit(false);
			// 构建sql语句
			StringBuilder sql = new StringBuilder();
			sql.append("insert into t_stuscore ").append("(SC_CourseName, SC_Term, Stu_SNo,SC_Score)").append("values ")
					.append("(?, ?, ?,?)");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			for (int i = 0; i < Score.length; i++) {
				System.out.println(i);
				ps.setString(1, Score[i].getSC_CourseName());
				ps.setString(2, String.valueOf(Score[i].getSC_Term()));
				ps.setString(3, Score[i].getStu_SNo());
				ps.setDouble(4, Score[i].getSC_Score());
				// 把这些操作添加到批处理中
				ps.addBatch();
			}
			// 执行批处理
			ps.executeBatch();
			// 提交事务
			connection.commit();
		} catch (SQLException e) {
			// 回滚事务
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	

}
