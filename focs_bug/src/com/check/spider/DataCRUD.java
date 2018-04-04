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
	 * Description: ��
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
			// ��ȡ����
			connection = BaseDao.getConnection();
			// ��ȡPrepareStatement����
			preparedStatement = connection.prepareStatement(sql);
			// ִ��sql
			int num = preparedStatement.executeUpdate();// ����Ӱ�쵽������
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
	 * Description: ��ѯ�����Ƿ����
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
			System.out.println("ִ�в�ѯ�쳣������"+sql);
		} finally {
			BaseDao.releaseDB(connection, preparedStatement, resultSet);
			return flag;
		}
	}
	
	
	
	/**
	 * @param listParams  ��ҳ�Ĳ������� ���������Զ��ж��Ƿ�insert����update
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
					System.out.println(map.get("case_Num")+"��������ɣ�");
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
						System.out.println("����ʧ��SQL��"+sql);
					}else {
						System.out.println(map.get("case_Num")+"�������ɹ���");
					}
				}
				flag = true;
			} catch (Exception e) {
				System.out.println(map.get("caseNum")+"�����ݿ���Ϣ����ʧ�ܣ�����ϵ����Ա");
				e.printStackTrace();
			}
		}
		return flag;
	}


}
