//import com.company.DistributionLaw;
import com.company.DistributionLaw;
import com.company.MainParam;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class TestRV {
    @Test
    public void test_distribution() throws Exception{
        ArrayList<String> test_w1 = new ArrayList<>();
        test_w1.add("abc");
        test_w1.add("def");
        DistributionLaw dl = new DistributionLaw(test_w1);
        MainParam mainParam = dl.getMainParams();

        Assert.assertEquals(3, mainParam.mx, 0);
        Assert.assertEquals(0, mainParam.dx, 0);
    }
}
