package p2.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	// so vscode stops whining
	@Deprecated
	private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
	
	public static Session getSession() {
		return sf.openSession();
	}
}
