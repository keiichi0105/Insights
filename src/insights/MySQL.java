package insights;


	import java.sql.Connection;

	import java.sql.DriverManager;

	import java.sql.ResultSet;

	import java.sql.SQLException;

	import java.sql.Statement;

	import java.util.HashMap;

	import java.util.Map;

	public class MySQL {
		String driver;// JDBCドライバの登録
	    String server, dbname, url, user, password;// データベースの指定
	    Connection con;
	    Statement stmt;
	    Map<String, Object> lng = new HashMap<>();
	    private String id;

	    
		public MySQL() {
			this.driver = "org.gjt.mm.mysql.Driver";
			this.server = "sangi2018.sist.ac.jp";
			this.dbname = "1618011";
			this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
	        this.user = "1618011";
	        this.password = "sist1618011";
	        this.id = "1618011";

	     
	        try {
	            this.con = DriverManager.getConnection(url, user, password);
	            this.stmt = con.createStatement ();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        try {
	            Class.forName (driver);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		}


		public ResultSet getID() {
			ResultSet rs = null;
			String sql = "SELECT * FROM `screens` WHERE 1";
			try {
				rs = stmt.executeQuery (sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}

		public void updateImage(double openness, double conscientiousness, double extraversion, double agreeableness, double neuroticism, double adventurousness, double artistic,double emotionality, 
				                double imagination, double intellect ,double challenging, double striving, double cautiousness, double dutifulness, double orderliness, double discipline, double efficacy,
				                double activity, double assertiveness, double cheerfulness, double seeking, double outgoing, double gregariousness, double altruism, double cooperation, double modesty, 
				                double uncompromising, double sympathy, double trust, double fiery, double worry, double melancholy, double immoderation, double consciousness, double susceptible, String text) {
			StringBuffer buf = new StringBuffer();
			String sql_text = "INSERT INTO  `screens` ( `openness`, `conscientiousness`, `extraversion`, `agreeableness` ,`neuroticism`, `adventurousness`, `artistic`, `emotionality`, `imagination`, "
					+ "  `intellect`,`challenging`, `striving`, `cautiousness`, `dutifulness` ,`orderliness`,`discipline`, `efficacy`, `activity`, `assertiveness`, "
					+ "  `cheerfulness`, `seeking`, `outgoing`, `gregariousness`, `altruism`,`cooperation`, `modesty`, `uncompromising`, `sympathy`, `trust` ,`fiery`,"
					+ "  `worry`, `melancholy`, `immoderation`, `consciousness` ,`susceptible`, `text`) VALUES "
					+ "   ("+openness+","+conscientiousness+","+extraversion+","+agreeableness+","+neuroticism+","+adventurousness+","+artistic+","+emotionality+","+imagination+","
				    +intellect+","+challenging+","+striving+","+cautiousness+","+dutifulness+","+orderliness+","+discipline+","+efficacy+","+activity+","+assertiveness+","
				    +cheerfulness+","+seeking+","+outgoing+","+gregariousness+","+altruism+","+cooperation+","+modesty+","+uncompromising+","+sympathy+","+trust+","+fiery+","
				    +worry+","+melancholy+","+immoderation+","+consciousness+","+susceptible+",'"+text+"');";
			System.out.println(sql_text);
			buf.append(sql_text);
			String sql = buf.toString();
			try {
				stmt.execute (sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}