package Entity;

public class Students {

	private String id = null;
	private String fristname = null;
	private String lastname = null;
	private String classid = null;

	public Students() {
		;
	}

	public Students(String id, String fn, String ln, String claasid) {
		this.id = id;
		this.fristname = fn;
		this.lastname = ln;
		this.classid = claasid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFristName() {
		return fristname;
	}

	public void setFristName(String fristname) {
		this.fristname = fristname;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String toString() {
		return "Id " + this.id + "First name " + this.fristname + "Last name " + this.lastname + "Id Class"
				+ this.classid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classid == null) ? 0 : classid.hashCode());
		result = prime * result + ((fristname == null) ? 0 : fristname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Students other = (Students) obj;
		if (classid == null) {
			if (other.classid != null)
				return false;
		} else if (!classid.equals(other.classid))
			return false;
		if (fristname == null) {
			if (other.fristname != null)
				return false;
		} else if (!fristname.equals(other.fristname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

}
