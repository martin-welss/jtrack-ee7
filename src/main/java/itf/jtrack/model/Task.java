package itf.jtrack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tasks")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long taskid;

	private Date created;

	private Date lastchange;

	private Date deadline;

	@Enumerated(EnumType.STRING)
	private TrackSate state;

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
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
	
}
