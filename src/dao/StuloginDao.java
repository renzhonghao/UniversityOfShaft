package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.StuloginEntity;
import util.DBUtil;

public class StuloginDao {

	public void insert(StuloginEntity stulogin[]) {
		// 连接数据库
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			//把自动提交事务设置为FALSE
			connection.setAutoCommit(false);
			// 构建sql语句
			StringBuilder sql = new StringBuilder();
			sql.append("insert into t_stulogin ").append("(Stu_SNo, US_Name, US_Password)").append("values ")
					.append("(?, ?, ?)");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			for (int i = 0; i < stulogin.length; i++) {
				ps.setString(1, stulogin[i].getStu_SNo());
				ps.setString(2, stulogin[i].getUS_Name());
				ps.setString(3, stulogin[i].getUS_Password());
				//把这些操作添加到批处理中
				ps.addBatch();
			}
			//执行批处理
			ps.executeBatch();
			//提交事务
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
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 根据学号查询该账号是否存在
	 * 
	 * @param Sno
	 *            学号
	 * @return
	 * @throws SQLException
	 */
	public StuloginEntity selectBySno(String Sno) throws SQLException {
		// 连接数据库
		Connection connection = DBUtil.getConnection();
		// 构建sql语句
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_stulogin where Stu_SNo=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		// 传入sql参数
		ps.setString(1, Sno);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return this.row2entity(rs);
		}
		return null;
	}

	/**
	 * 将ResultSet类型转换为StuloginEntity
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private StuloginEntity row2entity(ResultSet rs) throws SQLException {
		StuloginEntity g = new StuloginEntity();
		g.setStu_SNo(rs.getString("Stu_SNo"));
		g.setUS_Name(rs.getString("US_Name"));
		g.setUS_Password(rs.getString("US_Password"));
		return g;
	}

}
