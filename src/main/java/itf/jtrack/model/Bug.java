package itf.jtrack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="bugs")
public class Bug {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bugid;

	private Date created;

	private Date lastchange;
	
	@NotNull
	@Size(min=5, max=40)
	private String title;
	
	@NotNull
	@Size(min=5, max=400)
	private String description;

	@Enumerated(EnumType.STRING)
	private TrackState state;
	
	@ManyToOne
	private User reporter;

	@ManyToOne
	private User assignee;
	
	@ManyToOne
	private Product product;
	
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

	public TrackState getState() {
		return state;
	}

	public void setState(TrackState state) {
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
			+ lastchange + ", title=" + title
			+ ", description=" + description + ", state=" + state + "]";
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
