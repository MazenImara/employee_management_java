package em.model;

	import java.io.Serializable;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name="login")
	public class Admin implements Serializable  {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue
	    @Column(name="id")
	    public int id;
	    
	    @Column(name="email")
	    public String email;
	    
	    public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Column(name="password")
	    public String password;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
}
