import com.example.optimatefleet.model.Utility;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilityTest {

    private final Utility utility = new Utility();
    @Test
    public void roundNumberIntegerTest(){

        double number = 1.00;

        String result = utility.roundNumber(number);
        assertThat(Double.parseDouble(result)).isEqualTo(1);
    }

    @Test
    public void roundNumberDoubleTest(){

        double number = 1.50;

        String result = utility.roundNumber(number);
        assertThat(Double.parseDouble(result)).isEqualTo(1.50);
    }
}
