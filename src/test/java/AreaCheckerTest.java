import org.junit.Test;
import utils.AreaChecker;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class AreaCheckerTest {
    @Test
    public void hit() {
        //given
        double x = 0;
        double y = 1;
        double r = 2;

        //when
        boolean result = AreaChecker.checkHit(x, y, r);

        //then
        assertTrue(result);
    }

    @Test
    public void miss() {
        //given
        double x = -1;
        double y = -2;
        double r = 2;

        //when
        boolean result = AreaChecker.checkHit(x, y, r);

        //then
        assertFalse(result);
    }
}
