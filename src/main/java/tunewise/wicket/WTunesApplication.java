package tunewise.wicket;

import tunewise.iTunesRepository;
import tunewise.wicket.pages.TunesHomePage;
import wicket.ISessionFactory;
import wicket.Session;
import wicket.protocol.http.WebApplication;

public class WTunesApplication extends WebApplication {

	public static iTunesRepository repos;

	public void init() {
		String metaFile = getWicketServlet().getServletContext().getInitParameter("itunes");
		WTunesApplication.loadRepositoryFrom(metaFile);
	}
	
	private static void loadRepositoryFrom(String fileName) {
		repos = new iTunesRepository();
		repos.readFrom(fileName);
	}
	
	
	public Class getHomePage() {
		return TunesHomePage.class;
	}
	
	public ISessionFactory getSessionFactory() {
		return new ISessionFactory() {
			public Session newSession() {
				return new WTunesWebSession(WTunesApplication.this);
			}
		};
	}
}
