package itf.jtrack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="bugs")
public class Bug {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bugid;

	private Date created;

	private Date lastchange;

	private Date deadline;
	
	@NotNull
	private String title;
	
	@NotNull
	private String description;

	@Enumerated(EnumType.STRING)
	private TrackSate state;

	public Long getBugid() {
		return bugid;
	}

	public void setBugid(Long bugid) {
		this.bugid = bugid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastchange() {
		return lastchange;
	}

	public void setLastchange(Date lastchange) {
		this.lastchange = lastchange;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public TrackSate getState() {
		return state;
	}

	public void setState(TrackSate state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Bug [bugid=" + bugid + ", created=" + created + ", lastchange="
				+ lastchange + ", deadline=" + deadline + ", title=" + title
				+ ", description=" + description + ", state=" + state + "]";
	}

	
}
