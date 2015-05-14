package tunewise.wicket.pages;

import tunewise.Track;
import tunewise.wicket.WTunesWebSession;
import wicket.Page;
import wicket.markup.html.basic.Label;
import wicket.markup.html.form.Form;
import wicket.markup.html.link.IPageLink;
import wicket.markup.html.link.PageLink;

public class TrackDetails extends AbstractTunesPage {

	private Track iTrack;
	
	public TrackDetails(Track track) {
		iTrack = track;
		this.add(new Label("name", track.getName()));
		this.add(new Label("artist", track.getArtist()));
		this.add(new Label("album", track.getAlbum()));
		this.add(new Label("genre", track.getGenre()));
		this.add(new Label("bitrate", new Integer(track.getBitRate()).toString()));
		this.add(new Label("samplerate", new Integer(track.getSampleRate()).toString()));
		
		this.add(new PageLink("backlink", new IPageLink() {

			public Page getPage() {
				return new TunesHomePage();
			}

			public Class getPageIdentity() {
				return TunesHomePage.class;
			}
			
		}));
		
		this.add(new SubmitForm("addToPlayListForm"));
	}
	
	public final class SubmitForm extends Form {
		
		public SubmitForm(String formName) {
			super(formName);
		}
		
		public final void onSubmit() {
			System.out.println("Add to playlist");
			WTunesWebSession mySession =(WTunesWebSession) getSession();
			mySession.addTrackToPlayList(iTrack);
		}
	}
}
