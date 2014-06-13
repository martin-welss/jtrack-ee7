package itf.jtrack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="components")
public class Component {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long componentid;
	
	@NotNull
	@Size(max=30)
	private String name;

	public Long getComponentid() {
		return componentid;
	}

	public void setComponentid(Long componentid) {
		this.componentid = componentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
