import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestController {
	
	@Test
	void getEndPoint4by4Test() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		assertTrue(endLocation.x < myController.getMazeWidth() || endLocation.x >= 0);
		assertTrue(endLocation.y < myController.getMazeHeight() || endLocation.y >= 0);
	}
	
	@Test
	void getEndPoint2by4Test() {
		Control myController = new Control(2,4);
		Point endLocation = myController.getEndpointLocation();
		assertTrue(endLocation.x < myController.getMazeWidth() || endLocation.x >= 0);
		assertTrue(endLocation.y < myController.getMazeHeight() || endLocation.y >= 0);
	}
	
	@Test
	void getEndPoint4by3Test() {
		Control myController = new Control(4,3);
		Point endLocation = myController.getEndpointLocation();
		assertTrue(endLocation.x < myController.getMazeWidth() || endLocation.x >= 0);
		assertTrue(endLocation.y < myController.getMazeHeight() || endLocation.y >= 0);
	}
	
	@Test
	void checkEndPointPlayerAtEndTest() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		myController.setPlayerLocation(endLocation.x, endLocation.y);
		assertTrue(myController.checkEnd());
	}
	
	@Test
	void checkEndPointPlayerNotAtEndTest() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		assertFalse(myController.checkEnd());
	}
	
	@Test
	void checkEndPointPlayerXAtEndPointTest() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		Point playerLocation = myController.getPlayerLocation();
		myController.setPlayerLocation(endLocation.x, playerLocation.y);
		assertFalse(myController.checkEnd());
	}

	@Test
	void checkEndPointPlayerYAtEndPointTest() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		Point playerLocation = myController.getPlayerLocation();
		myController.setPlayerLocation(playerLocation.x,endLocation.y);
		assertFalse(myController.checkEnd());
	}
	
	@Test
	void getDirectionPlayerInCenterNorth() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(1,1);
		assertTrue(myController.getDirection("North").getClass().getName().equals("Door"));
	}
	
	@Test
	void getDirectionPlayerInCenterSouth() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(1,1);
		assertTrue(myController.getDirection("South").getClass().getName().equals("Door"));
	}
	
	@Test
	void getDirectionPlayerInCenterEast() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(1,1);
		assertTrue(myController.getDirection("East").getClass().getName().equals("Door"));
	}
	
	@Test
	void getDirectionPlayerInCenterWest() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(1,1);
		assertTrue(myController.getDirection("West").getClass().getName().equals("Door"));
	}
	
	@Test
	void getDirectionPlayerInEdgeWest() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(0,0);
		assertNull(myController.getDirection("West"));
	}
	
	@Test
	void getDirectionPlayerInEdgeNorth() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(0,0);
		assertNull(myController.getDirection("North"));
	}
	
	@Test
	void getDirectionPlayerInEdgeEast() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(0,0);
		assertTrue(myController.getDirection("East").getClass().getName().equals("Door"));
	}
	
	@Test
	void getDirectionPlayerInEdgeSouth() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(0,0);
		assertTrue(myController.getDirection("South").getClass().getName().equals("Door"));
	}
	
	@Test
	void moveDirectionDoorOpenTest() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(1,1);
		Door door = myController.getDirection("North");
		door.setState(true);
		assertTrue(myController.moveDirection("North", door));
	}
	
	@Test
	void moveDirectionDoorClosedTest() {
		Control myController = new Control(4,4);
		myController.setPlayerLocation(1,1);
		Door door = myController.getDirection("North");
		door.setState(false);
		assertFalse(myController.moveDirection("North", door));
	}

	@Test
	void PlayerLocationNotSameXEndPointLocationTest() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		Point playerLocation = myController.getPlayerLocation();
		assertFalse(playerLocation.x == endLocation.x);
	}
	
	@Test
	void PlayerLocationNotSameYEndPointLocationTest() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		Point playerLocation = myController.getPlayerLocation();
		assertFalse(playerLocation.y == endLocation.y);
	}
	
	@Test
	void PlayerLocationNotAroundEndPointLocationTest() {
		Control myController = new Control(4,4);
		Point endLocation = myController.getEndpointLocation();
		Point playerLocation = myController.getPlayerLocation();
		for(int i = playerLocation.y-1; i <= playerLocation.y+1; i++) {
			for(int j = playerLocation.x-1; j <= playerLocation.x+1; j++) {
				assertFalse((i == endLocation.y)&&(j == endLocation.x));
			}
		}
		
	}

}
