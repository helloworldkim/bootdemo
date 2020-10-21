package com.example.vo;

public class Member1VO {
		private long id=0;
		private String username=null;
		private String password=null;
		private String name=null;
		private String role=null;
		private String userdate=null;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getUserdate() {
			return userdate;
		}
		public void setUserdate(String userdate) {
			this.userdate = userdate;
		}
		@Override
		public String toString() {
			return "Member1VO [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
					+ ", role=" + role + ", userdate=" + userdate + "]";
		}
		
		
}
