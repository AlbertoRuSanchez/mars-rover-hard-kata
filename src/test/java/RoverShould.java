import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoverShould {

    private Rover underTest;

    @BeforeEach
    void setUp() {
        underTest = new Rover(new Grid());
    }

    @ParameterizedTest
    @CsvSource({"MRMMLMM,N", "MRMMLMR,E", "MMRRMM,S", "MMLMMML,S", "RMMRRMMR,N", "MRMRMR,W", "MMLLMLML,N", "MMLMMMLMML,E"})
    void rotate(String command, char expectedDirection) {
        //When
        String nextCoordinate = underTest.execute(command);

        //Then
        assertThat(nextCoordinate.charAt(nextCoordinate.length() - 1)).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @CsvSource({"MRMMLMM,2:3:N", "MRMMLMR,2:2:E", "MMRRMM,0:0:S", "MMLMMML,3:2:S", "RMMLMMMMMMRM,3:6:E", "MRMRMR,1:0:W",
            "MMLMLML,1:1:E", "MMMMRMMMRMMRM,2:2:W", "MMMMMMMMMMMMMMMMMMMMMM, 0:2:N"})
    void move(String command, String expectedCoordinate){
        //When
        String nextCoordinate = underTest.execute(command);

        //Then
        assertThat(nextCoordinate).isEqualTo(expectedCoordinate);
    }

    @ParameterizedTest
    @CsvSource({"MMRMM,O:1:2:E", "MMMMMRMMMMMMMMLMM,O:7:5:E", "MMMMMMMMRMMMMMM,6:8:E", "MMMMMMMRMMMMM,O:4:7:E",
            "RMMM,O:1:0:E"})
    void stop_moving_and_return_last_coordinate_before_the_obstacle(String command, String expectedCoordinate){

        //Given
        List<Coordinate> obstacles = new ArrayList<>();
        obstacles.add(new Coordinate(2,2));
        obstacles.add(new Coordinate(8,5));
        obstacles.add(new Coordinate(5,7));
        obstacles.add(new Coordinate(2,0));
        underTest.getGrid().setObstacles(obstacles);

        //When
        String nextCoordinate = underTest.execute(command);

        //Then
        assertThat(nextCoordinate).isEqualTo(expectedCoordinate);
    }


}

