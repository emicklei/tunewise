package tunewise;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * iTunesXMLHandler is a SAX Handler for reading the iTunes XML repository meta data.
 * 
 * @author E.M.Micklei
 */
public class iTunesXMLHandler extends DefaultHandler {
	/**
	 * Stores all entities read from the xml.
	 */
	public iTunesRepository repository;
	int state = -1;
	boolean COLLECT_CONTENT = false;
	static int PROPERTIES = 0;
	static int TRACKS = 1;
	static int TRACK = 2;
	private final static String PLIST = "plist";
	private final static String DICT = "dict";
	private final static String KEY = "key";
	private final static String INTEGER = "integer";
	private final static String STRING = "string";
	private final static String DATE = "date";
	String key = null;
	String content = ""; // for appending
	Track track = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
	 *      org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (KEY.equals(qName)) {
			COLLECT_CONTENT = true;
			return;
		}
		if (INTEGER.equals(qName)) {
			COLLECT_CONTENT = true;
			return;
		}
		if (STRING.equals(qName)) {
			COLLECT_CONTENT = true;
			return;
		}
		if (DICT.equals(qName)) {
			if (state == TRACKS) {
				track = new Track(key);
				state = TRACK;
				return;
			}
			if (PLIST.equals(qName)) {
				state = PROPERTIES;
				return;
			}
			if ("Tracks".equals(key)) {
				state = TRACKS;
				return;
			}
		}
		if (PLIST.equals(qName)) {
			key = "plist";
			return;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (KEY.equals(qName)) {
			key = this.takeContent();
			return;
		}
		if (STRING.equals(qName)) {
			this.store();
			return;
		}
		if (INTEGER.equals(qName)) {
			this.store();
			return;
		}
		if (DICT.equals(qName)) {
			if (TRACK == state) {
				repository.add(track);
				state = TRACKS;
				return;
			}
			if (TRACKS == state) {
				state = PROPERTIES;
				return;
			}
		}
		// If not handled then forget about content
		content = "";
	}
	private String takeContent() {
		String result = content;
		content = "";
		COLLECT_CONTENT = false;
		return result;
	}
	/**
	 * Take the contents of the current track
	 * and add it to the collection of tracks.
	 */
	public void store() {
		String value = this.takeContent();
		if (TRACK == state) {
			track.put(key, value);
			return;
		}
		if (PROPERTIES == state) {
			repository.properties.put(key, value);
			return;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (!COLLECT_CONTENT)
			return;
		content += new String(ch, start, length);
	}
}
