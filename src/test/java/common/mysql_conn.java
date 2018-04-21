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
		System.out.println(mConn.getData("SELECT id from stage_period where stage_id=127", "id").get(0));
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

	/* 执行SQL语句:查询并返回结果, 这是单结果返回，我们测试的时候就是根据条件查询，然后返回一个结果与期望结果比较即可，所以单结果足已 
	public List<String> getData(String sql, String targetName) {
		String result = null;
		try {
			this.My_conn();// 链接数据库
			Thread.sleep(1000);// 需要等待数据生成
			pst = conn.prepareStatement(sql);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString(targetName);
			}
			this.close();
		} catch (Exception e) {
			System.out.println("执行查询语句失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 执行SQL语句，返回查询结果列表(返回列只有一个的情况) */
	public List<String> getData(String sql, String targetName) {
		List<String> result = new ArrayList<String>();
		try {
			this.My_conn();// 链接数据库
			Thread.sleep(1000);// 需要等待数据生成
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