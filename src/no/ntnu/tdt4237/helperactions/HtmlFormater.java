package no.ntnu.tdt4237.helperactions;

public class HtmlFormater {

	public static String formatNewLine(String textToFormat) {
		return textToFormat.replaceAll("\n", "<br/>");
	}
}
