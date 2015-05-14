/**
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2005 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 */
package tunewise;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletConfig;
import com.philemonworks.typewise.ApplicationModel;
import com.philemonworks.typewise.CWT;
import com.philemonworks.typewise.StyleSheet;
import com.philemonworks.typewise.TableListItem;
import com.philemonworks.typewise.UIBuilder;
import com.philemonworks.typewise.cwt.Label;
import com.philemonworks.typewise.cwt.List;
import com.philemonworks.typewise.cwt.Screen;
import com.philemonworks.typewise.cwt.TableColumn;
import com.philemonworks.typewise.cwt.TableList;
import com.philemonworks.typewise.swt.client.SwtApplicationView;
import com.philemonworks.util.NLS;

/**
 * Browser is
 * 
 * @author E.M.Micklei
 */
public class Browser extends com.philemonworks.typewise.ApplicationModel {
	private static final String VERSION = "TuneWise, build 1031 (c)Philemon";
	private static final int ALBUM_WIDTH = 40;
	private static final int ALBUMS_COLUMNS = 40;
	private static final int ARTIST_WIDTH = 30;
	private static final int ARTISTS_COLUMNS = 40;
	private static final int GENRE_WIDTH = 20;
	private static final int GENRES_COLUMNS = 30;
	private static final String LIST_ALBUMS = "albums";
	private static final String LIST_ARTISTS = "artists";
	private static final String LIST_GENRES = "genres";
	private static final String LIST_PLAYLISTS = "playlists";
	private static final int LIST_ROWS = 20;
	private static final int NAME_WIDTH = 40;
	private static final int NUMBER_WIDTH = 8;
	private static final int PLAYLISTS_COLUMNS = 30;
	static iTunesRepository repos;
	private static final int SCREEN_COLUMNS = GENRES_COLUMNS + ARTISTS_COLUMNS + ALBUMS_COLUMNS + PLAYLISTS_COLUMNS + 5;
	private static final long serialVersionUID = 3834873568168521776L;
	private static final int TRACK_ROWS = 20;
	private static final int TRACKS_COLUMNS = SCREEN_COLUMNS - 2;
	private static final int SCREEN_ROWS = LIST_ROWS + TRACK_ROWS + 5;

	/**
	 * This method is sent when initializing the servlet providing this service. Note that the view is not yet available
	 * at this point of the program execution.
	 * 
	 * @param configuration :
	 *        ServletConfig implementor
	 */
	public static void init(ServletConfig configuration) {
		String metaFile = configuration.getServletContext().getInitParameter("itunes");
		Browser.loadRepositoryFrom(metaFile);
	}
	private static void loadRepositoryFrom(String fileName) {
		repos = new iTunesRepository();
		repos.readFrom(fileName);
	}
	/**
	 * This method is for local testing and debugging only
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Browser.loadRepositoryFrom("itunes.xml");
		// todo: make this work again
		// new Browser(null).test()
//		SwtApplicationView view = new SwtApplicationView();
//		view.start(new Browser(null),"main");
	}
	/**
	 * @param parent
	 */
	public Browser(ApplicationModel parent) {
		super(parent);
	}
	private void addAlbumsTo(UIBuilder ui) {
		Label header = ui.addLabel("albumsH", "Album");
		header.setAlignment(CWT.CENTER);
		header.setColumns(ALBUMS_COLUMNS);
		ui.cr(1);
		List albums = ui.addList(LIST_ALBUMS, LIST_ROWS, ALBUMS_COLUMNS);
		albums.setStyle(LIST_ALBUMS);
		albums.onSendTo(CWT.EVENT_SELECTEDITEM, "changedAlbum", this);
	}
	private void addArtistsTo(UIBuilder ui) {
		Label header = ui.addLabel("artistsH", "Artist");
		header.setAlignment(CWT.CENTER);
		header.setColumns(ARTISTS_COLUMNS);
		ui.cr(1);
		List artists = ui.addList(LIST_ARTISTS, LIST_ROWS, ARTISTS_COLUMNS);
		artists.setStyle(LIST_ARTISTS);
		artists.onSendTo(CWT.EVENT_SELECTEDITEM, "changedArtist", this);
	}
	private void addButtonsTo(UIBuilder ui) {
		ui.setPosition(SCREEN_ROWS - 1, 2);
		ui.addButton("b_help", NLS.get("F1.label"), true, NLS.get("F1.help"), "button", CWT.EVENT_F1, "doDownload", this);
		ui.space(1);
		ui.addButton("b_download", NLS.get("F2.label"), true, NLS.get("F2.help"), "button", CWT.EVENT_F2, "doDownload", this);
		ui.space(1);
		ui.addButton("b_info", "F3-Info", true, "F3 - Display all info for selected track", "button", CWT.EVENT_F3, "doDownload", this);
		ui.space(1);
		ui.addButton("b_search", "F4-Search", true, "F4 - Search for tracks", "button", CWT.EVENT_F4, "doDownload", this);
	}
	private void addVersionTo(UIBuilder ui) {
		ui.setPosition(SCREEN_ROWS - 1, SCREEN_COLUMNS - VERSION.length() - 1);
		ui.addLabel("versionL", VERSION).setStyle("version");
	}
	private void addGenresTo(UIBuilder ui) {
		Label header = ui.addLabel("genreH", "Genre");
		header.setAlignment(CWT.CENTER);
		header.setColumns(GENRES_COLUMNS);
		ui.cr(1);
		List genres = ui.addList(LIST_GENRES, LIST_ROWS, GENRES_COLUMNS);
		genres.setStyle(LIST_GENRES);
		genres.onSendTo(CWT.EVENT_SELECTEDITEM, "changedGenre", this);
	}
	private void addPlaylistsTo(UIBuilder ui) {
		Label header = ui.addLabel("playlistsH", "Playlist");
		header.setAlignment(CWT.CENTER);
		header.setColumns(PLAYLISTS_COLUMNS);
		ui.cr(1);
		List albums = ui.addList(LIST_PLAYLISTS, LIST_ROWS, PLAYLISTS_COLUMNS);
		albums.setStyle(LIST_PLAYLISTS);
		albums.onSendTo(CWT.EVENT_SELECTEDITEM, "changedPlaylist", this);
	}
	private void addTracksTo(UIBuilder ui) {
		TableList tracks = ui.addTableList("tracks", TRACK_ROWS, TRACKS_COLUMNS);
		tracks.setStyle("tracks");
		TableColumn c1 = new TableColumn();
		c1.getHeading().setText("Name");
		c1.setWidth(NAME_WIDTH);
		tracks.add(c1);
		TableColumn c2 = new TableColumn();
		c2.getHeading().setText("Artist");
		c2.setWidth(ARTIST_WIDTH);
		tracks.add(c2);
		TableColumn c3 = new TableColumn();
		c3.getHeading().setText("Album");
		c3.setWidth(ALBUM_WIDTH);
		c3.getHeading().onSendTo(CWT.EVENT_CLICKED, "doUpdateAlbums", this);
		tracks.add(c3);
		TableColumn c4 = new TableColumn();
		c4.getHeading().setText("Genre");
		c4.setWidth(GENRE_WIDTH);
		tracks.add(c4);
		TableColumn c5 = new TableColumn();
		c5.getHeading().setText("No.");
		c5.setWidth(NUMBER_WIDTH);
		tracks.add(c5);
	}
	public void afterShow() {
		new MultiListSelection().update(this);
	}
	public void changedAlbum(String selection) {
		MultiListSelection multi = this.getMultiSelection();
		if (this.getAlbumsList().getSelectionIndex() == 0) {
			multi.setAlbum(null);
		}
		multi.update(this);
	}
	public void changedArtist(String selection) {
		MultiListSelection multi = this.getMultiSelection();
		if (this.getArtistsList().getSelectionIndex() == 0) {
			multi.setArtist(null);
		}
		multi.setAlbum(null);
		multi.update(this);
	}
	public void changedGenre(String selection) {
		MultiListSelection multi = new MultiListSelection();
		if (this.getGenresList().getSelectionIndex() != 0) {
			multi.setGenre((String)this.getGenresList().getSelectedItem());
		}
		multi.update(this);
	}
	public void changedPlaylist(String selection) {
		this.updateTracks();
	}
	public void doDownload() {
		view.openURL("http://www.philemonworks.com");
	}
	private List getAlbumsList() {
		return view.getList(LIST_ALBUMS);
	}
	private List getArtistsList() {
		return view.getList(LIST_ARTISTS);
	}
	private List getGenresList() {
		return view.getList(LIST_GENRES);
	}
	public MultiListSelection getMultiSelection() {
		MultiListSelection multi = new MultiListSelection();
		multi.setGenre((String)this.getGenresList().getSelectedItem());
		multi.setArtist((String)this.getArtistsList().getSelectedItem());
		multi.setAlbum((String)this.getAlbumsList().getSelectedItem());
		multi.setPlaylist((String)this.getPlaylistsList().getSelectedItem());
		return multi;
	}
	private List getPlaylistsList() {
		return view.getList(LIST_PLAYLISTS);
	}
	public iTunesRepository getRepository() {
		return repos;
	}
	public Screen mainScreen() {
		UIBuilder ui = new UIBuilder();
		Screen top = ui.addScreen("main", this, SCREEN_ROWS, SCREEN_COLUMNS);
		top.setTitle(NLS.get("title"));
		top.setStyleSheet(new StyleSheet("stylesheet.properties"));
		top.setStyle("screen");
		ui.setEdges(1, 2);
		this.addGenresTo(ui);
		ui.setEdges(1, 2 + GENRES_COLUMNS + 1);
		this.addArtistsTo(ui);
		ui.setEdges(1, 2 + GENRES_COLUMNS + 1 + ARTISTS_COLUMNS + 1);
		this.addAlbumsTo(ui);
		ui.setEdges(1, 2 + GENRES_COLUMNS + 1 + ARTISTS_COLUMNS + 1 + ALBUMS_COLUMNS + 1);
		this.addPlaylistsTo(ui);
		ui.setEdges(LIST_ROWS + 3, 2);
		this.addTracksTo(ui);
		this.addButtonsTo(ui);
		this.addVersionTo(ui);
		return top;
	}
	public void showAlbums(java.util.List albums) {
		if (!albums.isEmpty())
			albums.add(0, " All (" + albums.size() + " Albums)");
		this.getAlbumsList().setItems(albums);
	}
	public void showArtists(java.util.List artists) {
		if (!artists.isEmpty())
			artists.add(0, " All (" + artists.size() + " Artists)");
		this.getArtistsList().setItems(artists);
	}
	public void showGenres(java.util.List genres) {
		if (!genres.isEmpty())
			genres.add(0, " All (" + genres.size() + " Genres)");
		this.getGenresList().setItems(genres);
	}
	public void showPlaylists(java.util.List playlists) {
		if (!playlists.isEmpty())
			playlists.add(0, " All (" + playlists.size() + " Playlists)");
		this.getPlaylistsList().setItems(playlists);
	}
	void showTracks(java.util.List newTracks) {
		java.util.List tableItems = new ArrayList(newTracks.size());
		for (Iterator iter = newTracks.iterator(); iter.hasNext();) {
			Track element = (Track) iter.next();
			TableListItem each = new TableListItem(element.getId());
			each.add(element.getName());
			each.add(element.getArtist());
			each.add(element.getAlbum());
			each.add(element.getGenre());
			each.add(String.valueOf(element.getTrackNumber()));
			tableItems.add(each);
		}
		view.getTableList("tracks").setItems(tableItems);
	}
	private void updateTracks() {
		this.getMultiSelection().update(this);
	}
}
