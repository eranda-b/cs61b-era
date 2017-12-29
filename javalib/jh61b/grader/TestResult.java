/** Handy container class for storing test results. */
package jh61b.grader;

public class TestResult {
	final String name;
	final String number;
	final double maxScore; 
	double score;
	
	/* outputSB is any text that we want to relay to the user when teh test is done running. */
	private StringBuilder outputSB;
	

	/* private List<String> tags; // Not yet implemented */
	/* private String visibility; Can be always, published, or never. Not yet implemented. */


	public TestResult(String name, String number, double maxScore) {
		this.name = name;
		this.number = number;
		this.maxScore = maxScore;
		this.outputSB = new StringBuilder();
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void addOutput(String x) {
		outputSB.append(x);
	}

	/* Return in JSON format. TODO: Need to escape newlines and possibly other characters. */
	public String toJSON() {
		String output = outputSB.toString();
		String noWindowsNewLines = output.replace("\r\n", "\\n");
		String noWeirdNewLines = noWindowsNewLines.replace("\r", "\\n");
		String noLinuxNewLines = noWeirdNewLines.replace("\n", "\\n");
		String noTabs = noLinuxNewLines.replace("\t", "    ");

		return "{" + String.join(",", new String[] {
			String.format("\"%s\": \"%s\"", "name", name),
			String.format("\"%s\": \"%s\"", "number", number),
			String.format("\"%s\": %s", "score", score),
			String.format("\"%s\": %s", "max_score", maxScore),
			String.format("\"%s\": \"%s\"", "output", noTabs)
		}) + "}";
	}

	/* For debugging only. */
	public String toString() {
		return("name: " + name + ", number: " + number + ", score: " + score + ", max_score: " + maxScore + ", detailed output if any (on next line): \n"  + outputSB.toString());
	}
}
