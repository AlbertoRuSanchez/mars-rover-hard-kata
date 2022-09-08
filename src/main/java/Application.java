import java.util.List;

public class Application {

    public static void main(String[] args) {

        Grid grid = new Grid();
        grid.setObstacles(List.of(new Coordinate(2,3)));
        Rover rover = new Rover(grid);

        System.out.println(rover.execute("MMMRMMM"));

    }

}
