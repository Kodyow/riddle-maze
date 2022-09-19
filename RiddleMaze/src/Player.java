import java.io.Serializable;

public final class Player implements Serializable{
	/**
	 * serialization ID.
	 */
	private static final long serialVersionUID = -2530388364447460596L;
	/**
	 * myLives is the current number of live that the player has during the game.
	 */
	private int myLives;
	/**
	 * myScore is the current players score during the game.
	 */
	private int myScore;
	/**
	 * myX the current player X/Column location within a 2D array (maze).
	 */
	private int myX;
	/**
	 * myY the current player Y/Row location within a 2D array (maze).
	 */
	private int myY;
	/**
	 * Creates a new player with 2D array location inputed x and y.
	 * Score default to 0 and Lives default to 5
	 * @param theX 2D array x location (column)
	 * @param theY 2D array y location (row)
	 */
	public Player(final int theX, final int theY) {
		myLives = 5;
		myScore = 0;
		myX = theX;
		myY = theY;
	}
	/**
	 * Creates a new player with input score and lives values.
	 * The location is set to the inputed 2D array x (column) and y (row) location.
	 * @param theLives the lives of the player.
	 * @param theScore the score of the player.
	 * @param theX the 2D array location x (column).
	 * @param theY the 2D array location y (row).
	 */
	public Player(final int theLives, final int theScore, final int theX, final int theY) {
		myLives = theLives;
		myScore = theScore;
		myX = theX;
		myY = theY;
	}
	/**
	 * Sets the player 2D array location to a new X (column) and Y (row).
	 * @param theX the 2D array location x (column). 
	 * @param theY the 2D array location y (row).
	 */
	public final void move(final int theX, final int theY) {
		myX = theX;
		myY = theY;
	}
	/**
	 * returns the players current lives amount.
 	 * @return returns the players current lives amount.
	 */
	public final int getLives() {
		return myLives;
	}
	/**
	 * returns the players current score amount.
	 * @return returns the players current score amount.
	 */
	public final int getScore() {
		return myScore;
	}
	/**
	 * Returns the player current 2D array location X (column).
	 * @return Returns the player current 2D array location X (column).
	 */
	public final int getX() {
		return myX;
	}
	/**
	 * Returns the player current 2D array location Y (row).
	 * @return Returns the player current 2D array location Y (row).
	 */
	public final int getY() {
		return myY;
	}
	/**
	 * Sets the current players lives to a new value.
	 * @param theLives new live for current player.
	 */
	public final void setLives(final int theLives) {
		myLives = theLives;
	}
	/**
	 * Sets the current players score to new score values.
	 * @param theScore new score for current player.
	 */
	public final void setScore(final int theScore) {
		myScore = theScore;
	}
	/**
	 * sets the players 2D array location to x (column).
	 * @param theX new 2D array x (column).
	 */
	public final void setX(final int theX) {
		myX = theX;
	}
	/**
	 * sets the players 2D array location to y (row).
	 * @param theY new 2D array y (row).
	 */
	public final void setY(final int theY) {
		myY = theY;
	}
	
	@Override 
	public final String toString() {
		StringBuilder playerDetails = new StringBuilder();
		playerDetails.append(String.format("Total Lives: %s\n", myLives));
		playerDetails.append(String.format("Total Score: %s\n", myScore));
		playerDetails.append(String.format("Location: (%s,%s)", myX,myY));
		return playerDetails.toString();
	}
}
