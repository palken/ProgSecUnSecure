package no.ntnu.tdt4237;


import java.util.Date;

import no.ntnu.tdt4237.helperactions.HtmlFormater;

public class BlogPost {
	
	private String title;
	private String text;
	private String pictureName;
	private User owner;
	private Date date;
	
	public BlogPost(String title, String text, Date date, User owner, String picture)
	{
		this(title, text, date, owner);
		setPictureName(picture);
	}
	
	public BlogPost(String title,String text,Date date,User owner)
	{
		setTitle(title);
		setText(text);
		setDate(date);
		setOwner(owner);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	
	public String getFormatedText() {
		return HtmlFormater.formatNewLine(text);
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPictureName() {
		if ("null".equals(pictureName))
		{
			return null;
		}
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
