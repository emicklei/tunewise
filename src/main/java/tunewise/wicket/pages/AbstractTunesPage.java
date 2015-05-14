package tunewise.wicket.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tunewise.Track;
import tunewise.wicket.WTunesWebSession;
import wicket.markup.html.WebPage;
import wicket.markup.html.basic.Label;
import wicket.markup.html.form.DropDownChoice;
import wicket.markup.html.image.Image;
import wicket.markup.html.list.ListItem;
import wicket.markup.html.list.ListView;
import wicket.model.PropertyModel;
import wicket.model.ResourceModel;

public abstract class AbstractTunesPage extends WebPage {

	private Locale selectedLocale = new Locale("EN");
	
	public AbstractTunesPage(){
		this.add(new Image("tunesImage", "typewise.png"));
		this.add(new Label("titleLabel", new ResourceModel("titleLabel")));

		List locales = new ArrayList();
		locales.add(new Locale("en"));
		locales.add(new Locale("nl"));
		
		this.add(new DropDownChoice("locales", new PropertyModel(this, "selectedLocale"), locales) {

					private static final long serialVersionUID = -661345462234295108L;

					//Submit the form when selection changed 
					protected boolean wantOnSelectionChangedNotifications() {
						return true;
					}
					
					protected void onSelectionChanged(Object newSelection) {
						selectedLocale = (Locale) newSelection;
						getSession().setLocale(selectedLocale);
						System.out.println("Locale: " +getSession().getLocale().getDisplayName());
					}
				}
			);

		this.add(new Label("playlistLabel", new ResourceModel("playlistLabel")));
		
		this.add(new ListView("tracklist", ((WTunesWebSession) getSession()).getPlayListTracks()) {

			protected void populateItem(ListItem item) {
				final Track currentTrack = (Track) item.getModelObject();
				if (currentTrack != null) {
					item.add(new Label("playListTrackName", new PropertyModel(currentTrack, "name")));
					item.add(new Label("playListTrackArtist", new PropertyModel(currentTrack, "artist")));
				}
			}
		});
		
	}

	public Locale getSelectedLocale() {
		return selectedLocale;
	}

	public void setSelectedLocale(Locale selectedLocale) {
		this.selectedLocale = selectedLocale;
	}
}
