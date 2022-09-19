import java.io.Serializable;
import java.sql.*;

/**
 * Uses builder pattern to build the Riddle with all nessisary information
 * and compare functions.
 * 
 * @author Ethan Taira
 * @author Kody Williams
 *
 */
public class Riddle implements Serializable{

	/**
	 * serialization ID.
	 */
	private static final long serialVersionUID = 1191556542958797903L;

	/**
	 * The percent of words for the answer to be considered correct.
	 */
	static final double WORD_ACCURACY_PERCENT = 0.5;
	
	/**
	 * Primary Key of the riddle in the SQLite DB
	 */
	private final int myRiddleID;
	
	/**
	 * The Riddle
	 */
	private final String myRiddle;
	
	/**
	 * Answer associated to the  Riddle
	 */
	private final String myAnswer;
	
	/**
	 * Type associated with the Riddle.
	 */
	private final String myType;
	
	/**
	 * The choices associated with the multiple choice riddle.
	 */
	private final String[] myChoices;
	
	/**
	 * returns riddle primary key
	 * @return returns riddle primary key
	 */
	public final int getRiddleID() {
		return myRiddleID;
	}
	
	/**
	 * returns the riddle
	 * @return returns the riddle
	 */
	public final String getRiddle() {
		return myRiddle;
	}
	
	/**
	 * returns the answer associated with the riddle
	 * @return returns answer associated with the riddle
	 */
	public final String getAnswer() {
		return myAnswer;
	}

	/**
	 * returns the type associated with the riddle
	 * @return returns type associated with the riddle
	 */
	public final String getType() {
		return myType;
	}
	
	/**
	 * returns a clone of the riddle's multiple choice answers
	 * @return String array of multiple choice riddle.
	 */
	public final String[] getChoices() {
		String[] inChoices = null;
		if (myChoices != null)
			inChoices = myChoices.clone();
		return inChoices;
	}
	/**
	 * Construct a new riddle with the use of the builder class.
	 * @param theBuilder the builder class.
	 */
	private Riddle(final RiddleBuilder theBuilder) {
		this.myRiddleID = theBuilder.myRiddleID;
		this.myRiddle = theBuilder.myRiddle;
		this.myAnswer = theBuilder.myAnswer;
		this.myType = theBuilder.myType;
		this.myChoices = theBuilder.myChoices;
	}
	/**
	 * Determines which method should be used to compare the inputed answer.
	 * Either Short answer or multiple choice.
	 * @param theAnswer the inputed answer that being compared to stored answer
	 * @return returns true if the answer it correct.
	 */
	public final boolean compareAnswer(final String theAnswer) {
		boolean inResult = false;
		if(theAnswer != null) {
			if (myChoices == null) {
				inResult = compareShortAnswer(theAnswer);
			}
			else {
				inResult = getAnswer().equalsIgnoreCase(theAnswer);
			}
		}
		return inResult;
	}
	
	/**
	 * Algorithm checks if the correct sequence of letters appear
	 * in the words. comparing a word from the real answer and input
	 * answer. This algorithm attempts to account for some input errors.
	 * @param theRealPartition a word form the real answer.
	 * @param theInputPartition a word from the inputed answer
	 * @return true a sequence that matches answer is present.
	 */
	private final boolean charSequence(final String theRealPartition, final String theInputPartition) {
		int inSeqLength = theRealPartition.length();
        int inSeqChar = 0;
        int s2length = theInputPartition.length();
        for (int i = 0; inSeqLength <= s2length-i && inSeqLength !=0; i++) {
            if(theRealPartition.charAt(inSeqChar) == theInputPartition.charAt(i)) {
            	inSeqChar ++;
                inSeqLength --;
            }
        }
        return inSeqLength == 0;
	}
	/**
	 * used to compare the inputed short answer to the stored answer to the
	 * riddle. Splits the inputed answer and real answer into arrays. If the
	 * input String is at least 50 percent correct return true.
	 * @param theAnswer the inputed answer.
	 * @return returns true if inputed answer is in the margin of error to the real answer.
	 */
	private final boolean compareShortAnswer(final String theAnswer) {
        String[] inRealAnswer = myAnswer.toLowerCase().split(" ");
		String[] inInputAnswer = theAnswer.toLowerCase().split(" ");
		double inCompareScore = 0;
        if(inInputAnswer.length != 0) {
            for (int i = 0; i < inRealAnswer.length; i++) {
                boolean pass = true;
                if(inRealAnswer[i].length() >= 2) {
                    for (int j = 0; j < inInputAnswer.length && pass; j++) {
                        if(charSequence(inRealAnswer[i],inInputAnswer[j])){
                            inCompareScore ++;
                            pass = false;
                        }		
                    }
                }
            } 
		}
		inCompareScore = inCompareScore/inRealAnswer.length;
		return inCompareScore >= WORD_ACCURACY_PERCENT;
	}

	@Override
	public String toString() {
		String inResult;
		if (myChoices.equals(null)) {
			inResult = toShortString();
		}
		else {
			inResult = toMultString();
		}
		return inResult;
	}
	/**
	 * return the riddle as a string in a short answer format.
	 * @return return the riddle as a string in a short answer format.
	 */
	private final String toShortString() {
		StringBuilder questionDetails = new StringBuilder();
		questionDetails.append(String.format("Riddle ID: %d\n", myRiddleID));
		questionDetails.append(String.format("Riddle Text: %s\n", myRiddle));
		questionDetails.append(String.format("Riddle Answer: %s\n", myAnswer));
		questionDetails.append(String.format("Riddle Type: %s\n", myType));
		return questionDetails.toString();
	}
	/**
	 * return the riddle as a string in a multiple choice format.
	 * @return return the riddle as a string in a multiple choice format.
	 */
	private final String toMultString() {
		StringBuilder questionDetails = new StringBuilder();
		questionDetails.append(toShortString());
		questionDetails.append("Riddle Choices: \n");
		for (String choice : myChoices) {
			questionDetails.append("\t");	
			questionDetails.append(choice);
			questionDetails.append("\n");			
		}
		return questionDetails.toString();
	}
		
	/**
	 * Builder class used to build different types of riddles. Only build 
	 * a choice riddle...
	 * @author Ethan Taira
	 * @author Kody Williams
	 *
	 */
	public static class RiddleBuilder {
		/**
		 * Primary Key of the riddle from the database.
		 */
		private final int myRiddleID;
		/**
		 * The Riddle
		 */
		private final String myRiddle;
		/**
		 * Answer to the riddle
		 */
		private final String myAnswer;
		/**
		 * Type of Riddle
		 */
		private final String myType;
		/**
		 * answer options for multiple choice riddles.
		 */
		private String[] myChoices;
		/**
		 * Constructed a new riddle with the basic information such as
		 * ID, The riddle, the answer, and the riddle type.
		 * @param theID primary key of the riddle.
		 */
        public RiddleBuilder(int theID) {
        	int tempID = -1;
        	String tempRiddle = "";
        	String tempAnswer = "";
        	String tempType = "";
            String file = "jdbc:sqlite:riddles.db";
    		Connection conn = null;
    		try {
    			String command = String.format("SELECT * FROM Riddles WHERE ID = %d", theID);
    			conn = DriverManager.getConnection(file);
    			Statement stmt = conn.createStatement();
    			ResultSet result = stmt.executeQuery(command);
    			
    			tempID = result.getInt(1);
    			tempRiddle = result.getString(2);
    			tempAnswer = result.getString(3);
    			tempType = result.getString(4);
    		
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
    		
    		this.myRiddleID = tempID;
			this.myRiddle = tempRiddle;
			this.myAnswer = tempAnswer;
			this.myType = tempType;
    		
        }
        /**
         * Initializes the questions is the riddle is a multiple choice riddle.
         * If short answer the fields myChoices remains null.
         * @return RiddleBuilder this current object.
         */
        public RiddleBuilder choices() {
        	if (this.myType.equals("multiple choice")) {
        		String file = "jdbc:sqlite:riddles.db";
        		Connection conn = null;
    			
        		try {
        			String command = String.format("SELECT * FROM Options WHERE ID = %d", myRiddleID);
        			String countcommand = String.format("SELECT count(*) as rowcount FROM Options WHERE ID = %d", myRiddleID);
        			conn = DriverManager.getConnection(file);
        			Statement stmt = conn.createStatement();
        			ResultSet countresult = stmt.executeQuery(countcommand);
        			String[] choiceArray = new String[countresult.getInt(1)];
        			ResultSet result = stmt.executeQuery(command);
        			for (int i = 0; i < choiceArray.length; i++) {
        				result.next();
        				choiceArray[i] = result.getString("Choice");
        				
        				
        			}
        			
                    this.myChoices = choiceArray;
        		} catch (SQLException e) {
        			System.out.println(e.getMessage());
        		}    		

        	}
            return this;
        }
        /**
         * returns the final constructed riddle
         * @return returns the final constructed riddle
         */
        public Riddle build() {
            return new Riddle(this);
        }
	}
}
