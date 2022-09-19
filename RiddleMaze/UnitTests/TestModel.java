import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Objects in the Model. Ignores basic getter and setters.
 * Classes like Player, Door, and Room are ignore since they are composed
 * of mostly getters and setters. 
 * Maze - since most function are private we will be focusing on constructor.
 * limited amount to 4 when testing for riddle since there is not enough riddles.
 * Tests larger than 4 will contain default riddles with null fields nad -1 primary key.
 * 
 * @author Kody Williams
 *
 */
class TestModel {
	
	@Test
	void RiddleMultipleChoicePrimaryKeyinvalid() {
		Riddle newRiddle = new Riddle.RiddleBuilder(-1).choices().build();
		assertTrue(newRiddle.getRiddleID() == -1);
		assertTrue(newRiddle.getChoices() == null);
		assertTrue(newRiddle.getAnswer().equals(""));
		assertTrue(newRiddle.getType().equals(""));
	}

	@Test
	void RiddleShortAnswer() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertTrue(newRiddle.getRiddleID() == 1);
		assertTrue(newRiddle.getChoices() == null);
		assertTrue(newRiddle.getRiddle().equals("I知 tall when I知 young, and I知 short when I知 old. What am I?"));
		assertTrue(newRiddle.getType().equals("short answer"));
		assertTrue(newRiddle.getAnswer().equals("A candle"));
	}

	@Test
	void RiddleMultipleChoice() {
		Riddle newRiddle = new Riddle.RiddleBuilder(3).choices().build();
		assertTrue(newRiddle.getRiddleID() == 3);
		assertTrue(newRiddle.getChoices()[0].equals("Are you asleep yet?"));
		assertTrue(newRiddle.getChoices()[1].equals("Are you awake yet?"));
		assertTrue(newRiddle.getChoices()[2].equals("Did you find your way?"));
		assertTrue(newRiddle.getChoices()[3].equals("Are you here?"));
		assertTrue(newRiddle.getRiddle().equals("What question can you never answer yes to?"));
		assertTrue(newRiddle.getType().equals("multiple choice"));
		assertTrue(newRiddle.getAnswer().equals("Are you asleep yet?"));
	}


	@Test
	void RiddlecompareShortAnswerExactAnswerTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertTrue(newRiddle.compareAnswer("A candle"));
	}
	
	@Test
	void RiddlecompareShortAnswerFillerWordTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertFalse(newRiddle.compareAnswer("A"));
	}
	
	@Test
	void RiddlecompareShortAnswerRandomTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertFalse(newRiddle.compareAnswer("Afdnlkasdfbnahkbfkaankadsfb"));
	}
	
	@Test
	void RiddlecompareShortAnswerMainWordTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertTrue(newRiddle.compareAnswer("candle"));
	}
	
	@Test
	void RiddlecompareShortAnswerMainWordContainingRandomExtraTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertTrue(newRiddle.compareAnswer("canddkhele"));
	}
	
	@Test
	void RiddlecompareShortAnswerMainWordMissSpellTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertFalse(newRiddle.compareAnswer("condle"));
	}
	
	@Test
	void RiddlecompareShortAnswerNoInputTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertFalse(newRiddle.compareAnswer(""));
	}
	
	@Test
	void RiddlecompareShortAnswerSpaceAnswerTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertFalse(newRiddle.compareAnswer(" "));
	}
	
	@Test
	void RiddlecompareShortAnswerNullTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(1).choices().build();
		assertFalse(newRiddle.compareAnswer(null));
	}

	@Test
	void RiddlecompareShortAnswerInvalidNullInputRiddleTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(-1).choices().build();
		assertFalse(newRiddle.compareAnswer(null));
	}
	
	@Test
	void RiddlecompareShortAnswerInvalidRiddleBlankInputTest() {
		Riddle newRiddle = new Riddle.RiddleBuilder(-1).choices().build();
		assertFalse(newRiddle.compareAnswer(""));
	}
	
	@Test
	void Maze0by0Test() {
		Maze inMaze = new Maze(0,0);
		assertTrue(inMaze.getHeight() == 0);
		assertTrue(inMaze.getWidth() == 0);
		assertTrue(inMaze.getGrid().length == 2);
		assertTrue(inMaze.getGrid()[0].length == 2);
		for (Room[] row : inMaze.getGrid()) {
			for (Room room : row) {
				assertTrue(room==null);
			}
		}
	}
	
	@Test
	void Maze1by1FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(1,1);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze2by2FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(2,2);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze3by3FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(3,3);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze3by4FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(3,4);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze4by2FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(4,2);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze4by4FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(4,4);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze5by5FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(5,5);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze2by5FillMazeWithRoomsTest() {
		Maze inMaze = new Maze(2,5);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
				}
			}
		}
	}
	
	@Test
	void Maze1by1Test() {
		Maze inMaze = new Maze(1,1);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y < inMaze.getHeight(); y++) {
			for (int x = 0; x < inMaze.getWidth(); x++) {
				if (y == 0 || y == inMaze.getHeight()-1 || x == 0 || x == inMaze.getWidth()-1) {
					assertTrue(inGrid[y][x] == null);
				} else {
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
					for (Door door: inGrid[y][x].getDoorsArray()) {
						assertTrue(door == null);
					}
				}
			}
		}
	}
	
	@Test
	void Maze2by2FillRoomsTest() {
		Maze inMaze = new Maze(2,2);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					if (y == 1 || x == 1 || x == inMaze.getWidth() || y == inMaze.getHeight()) {
						if (y == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getNorth() == null);
							assertTrue(inGrid[y][x].getSouth() != null);
							assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
						}
						if (x == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getWest() == null);
							assertTrue(inGrid[y][x].getEast() != null);
							assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						}
						if (x == inMaze.getWidth()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getEast() == null);
							assertTrue(inGrid[y][x].getWest() != null);
							assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						}
						if (y == inMaze.getHeight()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getSouth() == null);
							assertTrue(inGrid[y][x].getNorth() != null);
							assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						}
					} else {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getEast() != null);
						assertTrue(inGrid[y][x].getWest() != null);
						assertTrue(inGrid[y][x].getSouth() != null);
						assertTrue(inGrid[y][x].getNorth() != null);	
						assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
					}
				}
			}
		}
	}
	
	@Test
	void Maze3by3FillRoomsTest() {
		Maze inMaze = new Maze(3,3);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					if (y == 1 || x == 1 || x == inMaze.getWidth() || y == inMaze.getHeight()) {
						if (y == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getNorth() == null);
							assertTrue(inGrid[y][x].getSouth() != null);
							assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
						}
						if (x == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getWest() == null);
							assertTrue(inGrid[y][x].getEast() != null);
							assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						}
						if (x == inMaze.getWidth()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getEast() == null);
							assertTrue(inGrid[y][x].getWest() != null);
							assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						}
						if (y == inMaze.getHeight()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getSouth() == null);
							assertTrue(inGrid[y][x].getNorth() != null);
							assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						}
					} else {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getEast() != null);
						assertTrue(inGrid[y][x].getWest() != null);
						assertTrue(inGrid[y][x].getSouth() != null);
						assertTrue(inGrid[y][x].getNorth() != null);	
						assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
					}
				}
			}
		}
	}

	@Test
	void Maze4by4DoorAndRiddleTest() {
		Maze inMaze = new Maze(4,4);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 1; y <= inMaze.getHeight(); y++) {
			for (int x = 1; x <= inMaze.getWidth(); x++) {
				if (y == 1 || x == 1 || x == inMaze.getWidth() || y == inMaze.getHeight()) {
					if (y == 1) {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getNorth() == null);
						assertTrue(inGrid[y][x].getSouth() != null);
						assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
					}
					if (x == 1) {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getWest() == null);
						assertTrue(inGrid[y][x].getEast() != null);
						assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
					}
					if (x == inMaze.getWidth()) {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getEast() == null);
						assertTrue(inGrid[y][x].getWest() != null);
						assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
					}
					if (y == inMaze.getHeight()) {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getSouth() == null);
						assertTrue(inGrid[y][x].getNorth() != null);
						assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
					}
				} else {
					assertTrue(inGrid[y][x].getClass().getName() == "Room");
					assertTrue(inGrid[y][x].getEast() != null);
					assertTrue(inGrid[y][x].getWest() != null);
					assertTrue(inGrid[y][x].getSouth() != null);
					assertTrue(inGrid[y][x].getNorth() != null);	
					assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
					assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
					assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
					assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
				}
			}
		}
	}
	
	@Test
	void Maze3by4FillRoomsTest() {
		Maze inMaze = new Maze(3,4);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					if (y == 1 || x == 1 || x == inMaze.getWidth() || y == inMaze.getHeight()) {
						if (y == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getNorth() == null);
							assertTrue(inGrid[y][x].getSouth() != null);
							assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
						}
						if (x == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getWest() == null);
							assertTrue(inGrid[y][x].getEast() != null);
							assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						}
						if (x == inMaze.getWidth()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getEast() == null);
							assertTrue(inGrid[y][x].getWest() != null);
							assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						}
						if (y == inMaze.getHeight()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getSouth() == null);
							assertTrue(inGrid[y][x].getNorth() != null);
							assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						}
					} else {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getEast() != null);
						assertTrue(inGrid[y][x].getWest() != null);
						assertTrue(inGrid[y][x].getSouth() != null);
						assertTrue(inGrid[y][x].getNorth() != null);	
						assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
					}
				}
			}
		}
	}
	
	@Test
	void Maze4by2FillRoomsTest() {
		Maze inMaze = new Maze(4,2);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					if (y == 1 || x == 1 || x == inMaze.getWidth() || y == inMaze.getHeight()) {
						if (y == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getNorth() == null);
							assertTrue(inGrid[y][x].getSouth() != null);
							assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
						}
						if (x == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getWest() == null);
							assertTrue(inGrid[y][x].getEast() != null);
							assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						}
						if (x == inMaze.getWidth()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getEast() == null);
							assertTrue(inGrid[y][x].getWest() != null);
							assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						}
						if (y == inMaze.getHeight()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getSouth() == null);
							assertTrue(inGrid[y][x].getNorth() != null);
							assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						}
					} else {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getEast() != null);
						assertTrue(inGrid[y][x].getWest() != null);
						assertTrue(inGrid[y][x].getSouth() != null);
						assertTrue(inGrid[y][x].getNorth() != null);	
						assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
					}
				}
			}
		}
	}
	@Test
	void Maze5by5FillRoomsTest() {
		Maze inMaze = new Maze(5,5);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					if (y == 1 || x == 1 || x == inMaze.getWidth() || y == inMaze.getHeight()) {
						if (y == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getNorth() == null);
							assertTrue(inGrid[y][x].getSouth() != null);
							assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
						}
						if (x == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getWest() == null);
							assertTrue(inGrid[y][x].getEast() != null);
							assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						}
						if (x == inMaze.getWidth()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getEast() == null);
							assertTrue(inGrid[y][x].getWest() != null);
							assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						}
						if (y == inMaze.getHeight()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getSouth() == null);
							assertTrue(inGrid[y][x].getNorth() != null);
							assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						}
					} else {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getEast() != null);
						assertTrue(inGrid[y][x].getWest() != null);
						assertTrue(inGrid[y][x].getSouth() != null);
						assertTrue(inGrid[y][x].getNorth() != null);	
						assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
					}
				}
			}
		}
	}
	@Test
	void Maze2by5FillRoomsTest() {
		Maze inMaze = new Maze(2,5);
		Room[][] inGrid = inMaze.getGrid();
		for (int y = 0; y <= inMaze.getHeight()+1; y++) {
			for (int x = 0; x <= inMaze.getWidth()+1; x++) {
				//System.out.println(inGrid[y][x]);
				if (y == 0 || y == inMaze.getHeight()+1 || x == 0 || x == inMaze.getWidth()+1) {
					assertTrue(inGrid[y][x] == null);
				} 
				else 
				{
					if (y == 1 || x == 1 || x == inMaze.getWidth() || y == inMaze.getHeight()) {
						if (y == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getNorth() == null);
							assertTrue(inGrid[y][x].getSouth() != null);
							assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
						}
						if (x == 1) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getWest() == null);
							assertTrue(inGrid[y][x].getEast() != null);
							assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						}
						if (x == inMaze.getWidth()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getEast() == null);
							assertTrue(inGrid[y][x].getWest() != null);
							assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						}
						if (y == inMaze.getHeight()) {
							assertTrue(inGrid[y][x].getClass().getName() == "Room");
							assertTrue(inGrid[y][x].getSouth() == null);
							assertTrue(inGrid[y][x].getNorth() != null);
							assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						}
					} else {
						assertTrue(inGrid[y][x].getClass().getName() == "Room");
						assertTrue(inGrid[y][x].getEast() != null);
						assertTrue(inGrid[y][x].getWest() != null);
						assertTrue(inGrid[y][x].getSouth() != null);
						assertTrue(inGrid[y][x].getNorth() != null);	
						assertTrue(inGrid[y][x].getNorth().getRiddle().getRiddleID() == inGrid[y-1][x].getSouth().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getWest().getRiddle().getRiddleID() == inGrid[y][x-1].getEast().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getEast().getRiddle().getRiddleID() == inGrid[y][x+1].getWest().getRiddle().getRiddleID());
						assertTrue(inGrid[y][x].getSouth().getRiddle().getRiddleID() == inGrid[y+1][x].getNorth().getRiddle().getRiddleID());
					}
				}
			}
		}
	}
}
