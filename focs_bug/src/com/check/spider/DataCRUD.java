package com.check.spider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataCRUD extends BaseDao {

	/**
	 * <p>
	 * Description: 增
	 * </p>
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("finally")
	public boolean operateCud(String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			// 获取连接
			connection = BaseDao.getConnection();
			// 获取PrepareStatement对象
			preparedStatement = connection.prepareStatement(sql);
			// 执行sql
			int num = preparedStatement.executeUpdate();// 返回影响到的行数
			if (num > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		} finally {
			BaseDao.releaseDB(connection, preparedStatement, null);
			return flag;
		}
	}

	/**
	 * <p>
	 * Description: 查询案件是否存在
	 * </p>
	 * 
	 * @param sql
	 * @param param
	 */
	@SuppressWarnings("finally")
	public boolean find(String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			//String strCaseNum = resultSet.getString("caseNum");
			if (resultSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("执行查询异常。。。"+sql);
		} finally {
			BaseDao.releaseDB(connection, preparedStatement, resultSet);
			return flag;
		}
	}
	
	
	
	/**
	 * @param listParams  整页的参数集合 新增，会自动判断是否insert还是update
	 * @return boolean
	 */
	public boolean caseAdd(List<Map<String, String>> listParams){
		DataCRUD dataCRUD = new DataCRUD();
		boolean flag = false;
		for (int i = 0; i < listParams.size(); i++) {
			Map<String, String> map = listParams.get(i);
			try {
				String searchsql = "Select case_Num from check_ent_case where case_Num="+"'"+map.get("case_Num")+"'";
				boolean find = dataCRUD.find(searchsql);
				if (find) {
					String updateSql1 = "update check_ent_case set ";
					String sqladd = "";
					for (String key : map.keySet()) {
						if (key == null || "".equals(key)) {
							continue;
						}
						sqladd += key + "= '" + map.get(key) + "',";
					}
					sqladd = sqladd.substring(0, sqladd.length() - 1);
					String updateSqlf = updateSql1 + sqladd + ",update_time = now() where case_Num="+"'"+ map.get("case_Num")+"'";
					dataCRUD.operateCud(updateSqlf);
					System.out.println(map.get("case_Num")+"：更新完成！");
				}else {
					String sql1 = "";
					String sql2 = "";
					for (String key : map.keySet()) {
						if (key == null || "".equals(key)) {
							continue;
						}
						sql1 += key;
						sql1 = sql1 + ",";
						sql2 += ("'" + map.get(key) + "'");
						sql2 += ",";
					}
					sql1 = sql1.substring(0, sql1.length() - 1);
					sql2 = sql2.substring(0, sql2.length() - 1);
					String sql = "insert into check_ent_case(" + sql1
							+ ",check_date) values(" + sql2 + ",now())";
					boolean operateCud = dataCRUD.operateCud(sql);
					if (!operateCud) {
						System.out.println("插入失败SQL："+sql);
					}else {
						System.out.println(map.get("case_Num")+"：新增成功！");
					}
				}
				flag = true;
			} catch (Exception e) {
				System.out.println(map.get("caseNum")+"：数据库信息操作失败！请联系管理员");
				e.printStackTrace();
			}
		}
		return flag;
	}


}
