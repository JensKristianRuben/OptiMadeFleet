import com.example.optimadefleet.model.Utility;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilityTest {

    private final Utility utility = new Utility();
    @Test
    public void roundNumberIntegerTest(){

        double number = 1.00;

        String result = utility.roundNumber(number);
        assertThat(result).isEqualTo("1");
    }

    @Test
    public void roundNumberDoubleTest(){

        double number = 1.50;

        String result = utility.roundNumber(number);
        assertThat(result).isEqualTo("1.5");
    }

    @Test
    public void roundNumberDoubleTest2(){

        double number = 1.55;

        String result = utility.roundNumber(number);
        assertThat(result).isEqualTo("1.55");
    }
}
