package tunewise.wicket.pages;

import java.util.ArrayList;
import java.util.List;

import tunewise.Track;
import tunewise.wicket.WTunesApplication;
import tunewise.wicket.WTunesWebSession;
import wicket.Page;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.markup.html.AjaxFallbackLink;
import wicket.markup.html.basic.Label;
import wicket.markup.html.form.DropDownChoice;
import wicket.markup.html.form.Form;
import wicket.markup.html.link.IPageLink;
import wicket.markup.html.link.Link;
import wicket.markup.html.link.PageLink;
import wicket.markup.html.list.ListItem;
import wicket.markup.html.list.ListView;
import wicket.model.PropertyModel;

public class TunesHomePage extends AbstractTunesPage {

	private String selectedGenre = "";
	private String selectedArtist = "";
	private String selectedAlbum = ""; 
	
	private ListView currentTracks;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TunesHomePage() {
		
		Form form = new TunesForm("tunesForm"); 
		
		this.addArtistSelectBox(form);
		this.addAlbumSelectBox(form);
		this.addGenreSelectBox(form);
		this.addResultView(form);

		this.add(form);
		
	}

	public final class TunesForm extends Form {
		
		public TunesForm(String id) {
			super(id);
		}
		
		protected void onSubmit() {
		}
	}
	
	private void addGenreSelectBox(Form form) {
		//Adding genre label for form
		form.add(new Label("genreHeader", "All genres"));
		
		//Adding dropdown box for genres
		form.add(
			new DropDownChoice("genres", 
					new PropertyModel(this, "selectedGenre") , 
					WTunesApplication.repos.allGenres()) {

				private static final long serialVersionUID = -295586315887326527L;

				//Submit the form when selection changed 
				protected boolean wantOnSelectionChangedNotifications() {
					return true;
				}
				
				protected void onSelectionChanged(Object newSelection) {
					super.onSelectionChanged(newSelection);
					setSelectedGenre((String)newSelection);
					currentTracks.setList(WTunesApplication.repos.tracksWithGenre(selectedGenre));
					currentTracks.modelChanged();
				}
			}
		);
	}

	private void addResultView(Form form) {
		//Start with empty list
		List tracks = new ArrayList();
		
		//Add the listview to the form
		form.add(currentTracks = new ListView("tracksForGenre", tracks) {
		
			protected void populateItem(ListItem item) {
				final Track track = (Track) item.getModelObject();
				item.add(new PageLink("link", new IPageLink() {

					public Page getPage() {
						return new TrackDetails(track);
					}

					public Class getPageIdentity() {
						return TrackDetails.class;
					}
					
				}));
				
				item.add(new Link("addToPlaylist") {
					public void onClick() {
						System.out.println("Add to playlist");
						WTunesWebSession mySession =(WTunesWebSession) getSession();
						mySession.addTrackToPlayList(track);
					}
				});
				 
				item.add(new Label("name", new PropertyModel(track, "name")));
                item.add(new Label("artist", new PropertyModel(track, "artist")));
                item.add(new Label("album", new PropertyModel(track, "album")));
                item.add(new Label("genre", new PropertyModel(track, "genre")));
			}
		
		});
	}

	private void addAlbumSelectBox(Form form) {
		form.add(new Label("albumHeader", "All albums"));
		
		//Adding dropdown box for albums
		form.add(
				new DropDownChoice("albums", 
						new PropertyModel(this, "selectedAlbum") , 
						WTunesApplication.repos.allAlbums()) {

					private static final long serialVersionUID = 4697199470469841701L;

					//Submit the form when selection changed 
					protected boolean wantOnSelectionChangedNotifications() {
						return true;
					}
					
					protected void onSelectionChanged(Object newSelection) {
						super.onSelectionChanged(newSelection);
						setSelectedAlbum((String)newSelection);
						currentTracks.setList(WTunesApplication.repos.tracksForAlbum(selectedAlbum));
						currentTracks.modelChanged();
					}
				}
			);
	}

	private void addArtistSelectBox(Form form) {
		form.add(new Label("artistHeader", "All artists"));
		
		//Adding dropdown box for albums
		form.add(
				new DropDownChoice("artists", 
						new PropertyModel(this, "selectedArtist") , 
						WTunesApplication.repos.allArtists()) {

					private static final long serialVersionUID = 7141521685843645470L;

					//Submit the form when selection changed 
					protected boolean wantOnSelectionChangedNotifications() {
						return true;
					}
					
					protected void onSelectionChanged(Object newSelection) {
						super.onSelectionChanged(newSelection);
						setSelectedArtist((String)newSelection);
						currentTracks.setList(WTunesApplication.repos.tracksForArtist(selectedArtist));
						currentTracks.modelChanged();
					}
				}
			);
	}
	
	public String getSelectedGenre() {
		return selectedGenre;
	}

	public void setSelectedGenre(String selectedGenre) {
		this.selectedGenre = selectedGenre;
	}

	public String getSelectedAlbum() {
		return selectedAlbum;
	}

	public void setSelectedAlbum(String selectedAlbum) {
		this.selectedAlbum = selectedAlbum;
	}

	public String getSelectedArtist() {
		return selectedArtist;
	}

	public void setSelectedArtist(String selectedArtist) {
		this.selectedArtist = selectedArtist;
	}
}

