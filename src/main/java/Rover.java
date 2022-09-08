public class Rover {

    private Grid grid;
    private Direction actualDirection = Direction.NORTH;

    public Rover(Grid grid) {
        this.grid = grid;
    }

    public String execute(String command) {

        String finalCoordinate = "";
        boolean obstacleFound;

        for (char actualChar : command.toCharArray()) {
            if (actualChar == 'L') {
                actualDirection = actualDirection.leftDirection();
            }
            if (actualChar == 'R') {
                actualDirection = actualDirection.rightDirection();
            }
            if (actualChar == 'M') {
                obstacleFound = isObstacleNextMove();
                if(!obstacleFound){
                    moveOneStepToActualDirection();
                } else {
                    finalCoordinate = "O:";
                    break;
                }
            }
        }

        return finalCoordinate +
                Math.abs(grid.getGridActualCoordinate().getX()) + ":" + Math.abs(grid.getGridActualCoordinate().getY())
                + ":" + actualDirection.getAbbreviation();
    }

    private void moveOneStepToActualDirection() {

        int nextXCoordinate;
        int nextYCoordinate;

        switch(actualDirection){
            case NORTH:
                nextYCoordinate = (grid.getGridActualCoordinate().getY() + 1) % Grid.MAX_GRID_SIZE;
                grid.getGridActualCoordinate().setY(nextYCoordinate);
                break;
            case EAST:
                nextXCoordinate = (grid.getGridActualCoordinate().getX() + 1) % Grid.MAX_GRID_SIZE;
                grid.getGridActualCoordinate().setX(nextXCoordinate);
                break;
            case SOUTH:
                nextYCoordinate = (grid.getGridActualCoordinate().getY() - 1) % Grid.MAX_GRID_SIZE;
                grid.getGridActualCoordinate().setY(nextYCoordinate);
                break;
            case WEST:
                nextXCoordinate = (grid.getGridActualCoordinate().getX() - 1) % Grid.MAX_GRID_SIZE;
                grid.getGridActualCoordinate().setX(nextXCoordinate);
                break;
        }
    }

    private boolean isObstacleNextMove() {

        int nextXCoordinate = grid.getGridActualCoordinate().getX();
        int nextYCoordinate = grid.getGridActualCoordinate().getY();
        Coordinate nextCoordinate = new Coordinate(nextXCoordinate, nextYCoordinate);

        switch(actualDirection){
            case NORTH:
                nextYCoordinate = (grid.getGridActualCoordinate().getY() + 1) % Grid.MAX_GRID_SIZE;
                nextCoordinate.setY(nextYCoordinate);
                break;
            case EAST:
                nextXCoordinate = (grid.getGridActualCoordinate().getX() + 1) % Grid.MAX_GRID_SIZE;
                nextCoordinate.setX(nextXCoordinate);
                break;
            case SOUTH:
                nextYCoordinate = (grid.getGridActualCoordinate().getY() - 1) % Grid.MAX_GRID_SIZE;
                nextCoordinate.setY(nextYCoordinate);
                break;
            case WEST:
                nextXCoordinate = (grid.getGridActualCoordinate().getX() - 1) % Grid.MAX_GRID_SIZE;
                nextCoordinate.setX(nextXCoordinate);
                break;
        }

        return grid.getObstacles().stream().anyMatch(obstacle -> obstacle.equals(nextCoordinate));
    }

    public Grid getGrid() {
        return grid;
    }
}
