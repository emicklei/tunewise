package tunewise.wicket;

import java.util.ArrayList;
import java.util.List;

import tunewise.Track;
import wicket.Application;
import wicket.protocol.http.WebSession;

public class WTunesWebSession extends WebSession {

	private static final long serialVersionUID = 1L;
	
	private List playListTracks = new ArrayList();
	
	protected WTunesWebSession(Application application) {
		super(application);
	}

	public List getPlayListTracks() {
		return playListTracks;
	}

	public void setPlayListTracks(List playListTracks) {
		this.playListTracks = playListTracks;
	}
	
	public void addTrackToPlayList(Track track) {
		playListTracks.add(track);
	}
}
