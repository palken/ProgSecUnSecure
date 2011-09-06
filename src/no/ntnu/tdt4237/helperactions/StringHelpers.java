package no.ntnu.tdt4237.helperactions;

public class StringHelpers {
	
	public static String getBlogOwnerName(String url) 
	{
		return urlToString(url).replaceFirst("http?://.*/blog/","");
	}
	
	public static String getProfileOwnerName(String url) 
	{
		return urlToString(url).replaceFirst("http?://.*/profile/","");
	}
	
	public static String getPostOwnerName(String url, String basePath) 
	{
		return urlToString(url).replaceFirst("http?://.*/"+basePath+"/","").replaceFirst("/[0-9]+[-][0-9]+[0-9]+[-][0-9]+/.*$", "");
	}
	
	public static String getPostOwnerName(String url)
	{
		return getPostOwnerName(url, "post");
	}
	
	public static String getPostTitle(String url, String ownerName, String basePath)
	{
		return urlToString(url).replaceFirst("http?://.*/"+basePath+"/"+ownerName+"/[0-9]+[-][0-9]+[0-9]+[-][0-9]+/","");
	}
	
	public static String getPostTitle(String url, String ownerName) 
	{
		return getPostTitle(url, ownerName, "post");
	}
	
	public static String getPostDate(String url, String ownerName, String basePath) 
	{
		return urlToString(url).replaceFirst("http?://.*/"+basePath+"/"+ownerName+"/","").replaceFirst("/.*$", "");
	}
	
	public static String getPostDate(String url, String ownerName)
	{
		return getPostDate(url, ownerName, "post");
	}
	
	public static String urlToString(String url) {
		return url.replaceAll("%20", " ");
	}
}
