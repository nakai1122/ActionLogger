package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Activites;

public class ActivitesDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionloggersample";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	public List<Activites> Get(String userId) {
		List<Activites> list = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM Action WHERE USERID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Activites act = new Activites();
				act.setId(rs.getString("ID"));
				act.setAdate(rs.getString("ADATE"));
				act.setDate(rs.getString("ACTDATE"));
				act.setStime(rs.getString("STIME"));
				act.setEtime(rs.getString("ETIME"));
				act.setPlace(rs.getString("PLACE"));
				act.setReason(rs.getString("REASON"));
				act.setRemarks(rs.getString("REMARKS"));
				act.setUserid(rs.getString("USERID"));
				list.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	public List<Activites> lGet(String userId) {
		List<Activites> list = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM Action  WHERE USERID = ? ORDER BY adate DESC LIMIT 5;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Activites act = new Activites();
				act.setId(rs.getString("ID"));
				act.setAdate(rs.getString("ADATE"));
				act.setDate(rs.getString("ACTDATE"));
				act.setStime(rs.getString("STIME"));
				act.setEtime(rs.getString("ETIME"));
				act.setPlace(rs.getString("PLACE"));
				act.setReason(rs.getString("REASON"));
				act.setRemarks(rs.getString("REMARKS"));
				act.setUserid(rs.getString("USERID"));
				list.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	public boolean set(Activites act) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select count(*) cnt from Action");
			rs.next();
			int count = rs.getInt("cnt");
			count++;
			String id = String.valueOf(count);
			act.setId(id);
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String fdate = dtformat.format(date);
			act.setAdate(fdate);
			String sql = "INSERT INTO Action "
					+ "( id, adate , actdate, STIME, ETIME, place, reason, remarks, userid) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, act.getId()); // id
			pStmt.setString(2, act.getAdate());// �ǉ����̎���
			pStmt.setString(3, act.getDate());// ���t
			pStmt.setString(4, act.getStime());// �J�n����
			pStmt.setString(5, act.getEtime());// �I������
			pStmt.setString(6, act.getPlace());// �ꏊ
			pStmt.setString(7, act.getReason());// ���R
			pStmt.setString(8, act.getRemarks());// ���l
			pStmt.setString(9, act.getUserid());// ���[�U�[ID
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}