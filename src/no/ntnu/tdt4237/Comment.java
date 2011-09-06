package no.ntnu.tdt4237;

import java.util.Date;

public class Comment {
	
	private User owner;
	private String text;
	private Date date;
	private BlogPost post;
	
	public Comment(User owner, String text, Date date, BlogPost post)
	{
		setOwner(owner);
		setText(text);
		setDate(date);
		setPost(post);
	}
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BlogPost getPost() {
		return post;
	}
	public void setPost(BlogPost post) {
		this.post = post;
	}
}
