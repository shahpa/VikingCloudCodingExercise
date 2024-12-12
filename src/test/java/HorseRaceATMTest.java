import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HorseRaceATMTest {
    @Test
    public void setWinningHorseTest() {
        HorseRaceATM atm = new HorseRaceATM();
        atm.setWinningHorse("4"); // Set horse #4 as the winner

        // Assert horse #4 is marked as the winner
        assertTrue(atm.horseList.get(3).won); // Horse #4 at index 3
        // Assert all other horses are marked as losing
        for (int i = 0; i < atm.horseList.size(); i++) {
            if (i != 3) {
                assertFalse(atm.horseList.get(i).won);
            }
        }
    }
}
