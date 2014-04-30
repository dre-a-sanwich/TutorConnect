package event;

/**
 * Defines the data that is held by an event
 *
 */
public class Event {
	private int id;
	private String title;
	private String datetime;
	private String creatorName;
	private String location;
	private String description;
	private int rsvp;
	private int vote;
	
	public Event(String title, String datetime, String creatorName, String location){
		this(title, datetime, creatorName, location, null);
	}
	
	public Event(String title, String datetime, String creatorName, String location, String description)
	{
		this.title = title;
		this.datetime = datetime;
		this.creatorName = creatorName;
		this.location = location;
		this.description = description;
		this.rsvp = 0;
		this.vote = 0;
	}
	
	public Event(String title, String datetime, String creatorName, String location, String description, int rsvp, int vote)
	{
		this(title, datetime, creatorName, location, description);
		this.rsvp = rsvp;
		this.vote = vote;
	}
	
	public Event(String eventInfo)
	{
		setInfo(eventInfo);
		this.rsvp = 0;
		this.vote = 0;
	}
	
	public Event(int id, String title, String datetime, String creatorName, String location, String description, int rsvp, int vote)
	{
		this(title, datetime, creatorName, location, description, rsvp, vote);
		this.id = id;
	}
	
	/**
	 * Sets the contents of this event to the given formatted string
	 * @param eventInfo Formatted string containing all event info
	 */
	public void setInfo(String eventInfo) {
		String[] split = eventInfo.split("#!");
		
		this.setId(id);
		this.setCreatorName(split[0]);
		this.setLocation(split[1]);
		this.setDescription(split[2]);
		this.setDatetime(split[3]);
		this.setTitle(split[4]);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDatetime() {
		return datetime;
	}
	
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public String getCreatorName() {
		return creatorName;
	}
	
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getRsvp() {
		return rsvp;
	}

	public void setRsvp(int rsvp) {
		this.rsvp = rsvp;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}
	
	public String stringToHTML() {
		return "<div style= \"width:100%; background-color: white; color: black; border-radius: 10px; \"><div style= \" width:100%;box-shadow: 2px 2px 1px grey; background-color: #15B1D3; padding: .5%; border-radius: 10px;\"> <span style= \"font-size:x-large;\">" + this.getTitle() +
				"<span style= \"font-size:medium; float: right; \" > &nbsp; RSVP: " + this.getRsvp() +
				"</span><span style= \"font-size:medium; float: right;\"> Votes: " + this.getVote() + 
				"</span></span></div> <br/> <div style= \"width:100%;padding-left: 2%; padding-bottom: 1%;\"> <span style= \"font-size:medium; color: black;\"> Created By: &emsp;" + this.getCreatorName() +
				"</span> <br/> <span style= \"font-size:medium;\"> Date: &emsp;&emsp;&emsp;&nbsp;&nbsp;" + this.getDatetime() +
				"</span> <br/> <span style= \"font-size:medium;\"> Description: &emsp;" + this.getDescription() +
				"</span> <br/> <span style= \"font-size:medium;\"> Location: &emsp;&emsp;" + this.getLocation() +
				"</span></div></div><hr/>";
	}
	
	public String myEventsStringToHTML() {
		return "<div style= \"width:100%; background-color: white; color: black; border-radius: 10px; \"><div style= \" width:100%;box-shadow: 2px 2px 1px grey; background-color: #15B1D3; padding: .5%; border-radius: 10px;\"> <span style= \"font-size:x-large;\"> ID: " + this.getId() + "&emsp;-&emsp;" + this.getTitle() +
				"<span style= \"font-size:medium; float: right;\"> &nbsp; RSVP: " + this.getRsvp() +
				"</span><span style= \"font-size:medium; float: right;\"> Votes: " + this.getVote() + 
				"</span></span></div> <br/> <div style= \"width:100%;padding-left: 2%; padding-bottom: 1%;\"> <span style= \"font-size:medium; color: black;\"> Created By: &emsp;" + this.getCreatorName() +
				"</span> <br/> <span style= \"font-size:medium;\"> Date: &emsp;&emsp;&emsp;&nbsp;&nbsp;" + this.getDatetime() +
				"</span> <br/> <span style= \"font-size:medium;\"> Description: &emsp;" + this.getDescription() +
				"</span> <br/> <span style= \"font-size:medium;\"> Location: &emsp;&emsp;" + this.getLocation() +
				"</span></div></div><hr/>";
	}
}
