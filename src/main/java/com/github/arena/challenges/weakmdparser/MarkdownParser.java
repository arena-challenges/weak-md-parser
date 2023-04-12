package com.github.arena.challenges.weakmdparser;

public class MarkdownParser {
	boolean isList;
	boolean isHeader;
	boolean isParagraph;

	String parse(String markdown) {
		String[] splitText = markdown.split("\n");
		String result = "";
		boolean activeList = false;


		for (int i = 0; i < splitText.length; i++) {
			String theLineForCheckUp = splitText[i];
			String theLineAfterHeaderCheckUp = checkIfHeader(theLineForCheckUp);
			String theLineAfterListCheckUp = (!isHeader) ? checkIfList(theLineForCheckUp) : checkIfList(theLineAfterHeaderCheckUp);
			String theLineAfterParagraphCheckUp = (isList && isHeader)
					? theLineAfterListCheckUp : ((isHeader && !isList) ? theLineAfterHeaderCheckUp
					: ((!isHeader && isList) ? theLineAfterListCheckUp : checkIfParagraph(theLineForCheckUp)));

			if (isList && !isHeader && !isParagraph && !activeList) {
				activeList = true;
				result = result + "<ul>";
				result = result + theLineAfterParagraphCheckUp;
			} else if (!isList && activeList) {
				activeList = false;
				result = result + "</ul>";
				result = result + theLineAfterParagraphCheckUp;
			} else {
				result = result + theLineAfterParagraphCheckUp;
			}
		}
		if (activeList) {
			result = result + "</ul>";
		}
		return result;
	}

	private String checkIfHeader(String splitTextPart) {
		String theLine = ph(splitTextPart);
		isHeader = true;

		if (theLine == null) {
			isHeader = false;
		}
		return theLine;
	}

	private String checkIfList(String splitTextPart) {
		isList = true;
		String theLine = li(splitTextPart);
		if (theLine == null) {
			isList = false;
		}
		return theLine;
	}

	private String checkIfParagraph(String splitTextPart) {
		isParagraph = true;
		return "<p>" + parseSomeSymbols(splitTextPart) + "</p>";
	}

	protected String ph(String markdown) {
		int count = 0;

		for (int i = 0; i < markdown.length() && markdown.charAt(i) == '#'; i++) {
			count++;
		}

		if (count == 0) {
			return null;
		}

		return "<h" + count + ">" + markdown.substring(count + 1) + "</h" + count + ">";
	}

	public String li(String markdown) {
		if (markdown.startsWith("*")) {
			String skipAsterisk = markdown.substring(2);
			String listItemString = parseSomeSymbols(skipAsterisk);
			return "<li>" + listItemString + "</li>";
		}

		return null;
	}

	public String parseSomeSymbols(String markdown) {

		String lookingFor = "__(.+)__";
		String update = "<strong>$1</strong>";
		String workingOn = markdown.replaceAll(lookingFor, update);

		lookingFor = "_(.+)_";
		update = "<em>$1</em>";
		return workingOn.replaceAll(lookingFor, update);
	}
}
