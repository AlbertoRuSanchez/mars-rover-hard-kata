import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static int MAX_GRID_SIZE = 10;

    private Coordinate gridActualCoordinate;
    private List<Coordinate> obstacles = new ArrayList<>();

    Grid() {
        this.gridActualCoordinate = new Coordinate(0,0);
    }

    public Coordinate getGridActualCoordinate() {
        return gridActualCoordinate;
    }

    public void setObstacles(List<Coordinate> obstacles) {
        this.obstacles = obstacles;
    }

    public List<Coordinate> getObstacles() {
        return obstacles;
    }
}
