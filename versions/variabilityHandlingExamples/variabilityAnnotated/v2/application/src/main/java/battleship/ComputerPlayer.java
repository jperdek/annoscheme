package battleship;
import org.annoscheme.common.annotation.Action;
import org.annoscheme.common.annotation.ActionType;
import org.annoscheme.common.annotation.BranchingType;
import org.annoscheme.common.annotation.Conditional;
import org.annoscheme.common.annotation.Joining;

//@{"computerOpponent": "true"}
public class ComputerPlayer extends AbstractPlayer{

	public ComputerPlayer(String id, BoardManager boardManager) {
		super(id, Player.SHIP_DEFAULT_LENGTH, boardManager);
	}
	
	@Conditional(branchingType = BranchingType.MAIN, condition="d1.playerAsOpponentVariability", diagramIdentifiers={"d1.id"}, trueClause="{\"computerPlayer\": \"true\"}")
	@Action(message = "d1.computerOpponentInstantiation", diagramIdentifiers = {"d1.id"}, parentMessage = "d1.instantiatePlayer")
	public ComputerPlayer(String id, int[] ship_lengths, BoardManager boardManager) {
		super(id, ship_lengths, boardManager);	
		this.setupComputer();
	}
	
	private void setupComputer() {
		System.out.println();
		int normCounter = 0;
		int maxRowRestriction = this.board.getAreaRowsWidth() - 1;
		int maxColRestriction = this.board.getAreaColsHeight() - 1;

		while (this.numOfShipsLeft() > 0) {
			for (Ship s : this.ships) {
				int row = Randomizer.nextInt(0, maxRowRestriction);
				int col = Randomizer.nextInt(0, maxColRestriction);
				int dir = Randomizer.nextInt(0, 1);

				// System.out.println("DEBUG: row-" + row + "; col-" + col + "; dir-" + dir);

				while (this.hasErrors(row, col, dir, normCounter)) // while the random nums make error, start again
				{
					row = Randomizer.nextInt(0, maxRowRestriction);
					col = Randomizer.nextInt(0, maxColRestriction);
					dir = Randomizer.nextInt(0, 1);
					// System.out.println("AGAIN-DEBUG: row-" + row + "; col-" + col + "; dir-" +
					// dir);
				}

				// System.out.println("FURTHER DEBUG: row = " + row + "; col = " + col);
				this.ships[normCounter].setLocation(row, col);
				this.ships[normCounter].setDirection(dir);
				this.playerGrid.addShip(this.ships[normCounter]);

				normCounter++;
			}
		}
	}
}
