
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

import entity.PersonalDataEntity;
import entity.ScoreEntity;
import util.DBUtil;

public class PersonalDataDao {
	/**
	 * 插入个人信息
	 * 
	 * @param pe
	 *            个人信息实体
	 * @throws Exception
	 *             系统异常
	 * 
	 */
	public void insertPerData(PersonalDataEntity pe) throws Exception {
		// 获得数据库连接
		Connection connection = DBUtil.getConnection();

		// 构建sql语句
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_stuinfo ")
				.append(" (Stu_SNo,Stu_Name,Stu_ClassName,Stu_Sex,Stu_Id,Stu_Nation,Stu_Bir,Stu_Tel,Stu_Home,Stu_From,Stu_Photo,Stu_Assess) ")
				.append(" values ").append(" (?,?,?,?,?) ");
		// 传入sql参数
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, pe.getStu_SNo());
		ps.setString(2, pe.getStu_Name());
		ps.setString(3, pe.getStu_ClassName());
		ps.setString(4, pe.getStu_Sex());
		ps.setString(5, pe.getStu_Id());
		ps.setString(6, pe.getStu_Nation());
		ps.setString(7, pe.getStu_Bir());
		ps.setString(8, pe.getStu_Tel());
		ps.setString(9, pe.getStu_Home());
		ps.setString(10, pe.getStu_From());
		ps.setString(11, pe.getStu_Photo());
		ps.setString(12, pe.getStu_Assess());
		ps.executeUpdate(); // 执行
	}

	public void insert(PersonalDataEntity pe[]) {
		// 连接数据库
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			// 把自动提交事务设置为FALSE
			connection.setAutoCommit(false);
			// 构建sql语句
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into t_stuinfo ")
					.append(" (Stu_SNo,Stu_Name,Stu_ClassName,Stu_Sex,Stu_Id,Stu_Nation,Stu_Bir,Stu_Tel,Stu_Home,Stu_From,Stu_Photo,Stu_Assess) ")
					.append(" values ").append(" (?,?,?,?,?,?,?,?,?,?,?,?) ");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			for (int i = 0; i < pe.length; i++) {
				ps.setString(1, pe[i].getStu_SNo());
				ps.setString(2, pe[i].getStu_Name());
				ps.setString(3, pe[i].getStu_ClassName());
				ps.setString(4, String.valueOf(pe[i].getStu_Sex()));
				ps.setString(5, pe[i].getStu_Id());
				ps.setString(6, pe[i].getStu_Nation());
				ps.setString(7, pe[i].getStu_Bir());
				ps.setString(8, pe[i].getStu_Tel());
				ps.setString(9, pe[i].getStu_Home());
				ps.setString(10, pe[i].getStu_From());
				ps.setString(11, pe[i].getStu_Photo());
				ps.setString(12, pe[i].getStu_Assess());
				// 把这些操作添加到批处理中
				ps.addBatch();
			}
			// 执行批处理
			System.out.println("dfas");
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
