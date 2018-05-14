/**
 * @author Helen 
 * @date 2018年4月12日  
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：mysql数据库链接处理
 */
public class mysql_conn {
	private static final String url = "jdbc:mysql://192.168.6.245/jnx";/* jdbc:mysql://数据库ＩＰ/数据库名 */
	private static final String user = "jnx";// 数据库用户名
	private static final String password = "jnx";// 对应的用户密码

	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {
		mysql_conn mConn = new mysql_conn();
		//System.out.println(mConn.getData("SELECT id from stage_period where stage_id=127", "id").get(0));
		mConn.updateData("UPDATE course_stage SET name='修改阶段2' where course_schedule_id=67 ORDER BY id ASC LIMIT 1;");
	}

	/* 链接数据库 */
	public void My_conn() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
	}

	/* 执行SQL语句，返回查询结果列表(返回列只有一个的情况) */
	public List<String> getData(String sql, String targetName) {
		List<String> result = new ArrayList<String>();
		try {
			this.My_conn();// 链接数据库
			Thread.sleep(5000);// 需要等待数据生成
			pst = conn.prepareStatement(sql);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				result.add(resultSet.getString(targetName));
			}
			this.close();
		} catch (Exception e) {
			System.out.println("执行查询语句失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/*修改或删除数据*/
	public void updateData(String sql) {
		try {
			this.My_conn();
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			this.close();
		} catch (Exception e) {
			System.out.println("执行语句失败");
			e.printStackTrace();
		}
	}

	/* 关闭链接 */
	private void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (Exception e) {
			System.out.println("关闭数据库连接失败！");
			e.printStackTrace();
		}
	}

}