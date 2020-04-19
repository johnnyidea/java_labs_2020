import com.company.AppWorkout;
import com.company.Profile;
import com.company.Training;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class test_workout {
    @Test
    public void test_func() throws Exception{
        AppWorkout app = new AppWorkout();
//        проверка функций
        String word = "q";
        Assert.assertFalse(app.isInteger(word));

        ArrayList<Training> wrkt = app.create_workout();

        for (int i = 0; i < wrkt.size(); i++) {
            assertTrue( wrkt.get(i).get_cal_hour() >= 0);
        }

    }
    @Test
    public void test_after_work() throws Exception{
        //сначала запускается программа, потом уже проверяется
        //после окончания функции обязаельно:
        // -должен существовать файл для сериализации
        // -должен быть как минимум 1 профиль, после правильного завершения прог.

        Path curr_path = Paths.get("").toAbsolutePath();
        String tmp = curr_path.toString() + "/../dudes.out";
        File f = new File(tmp);

        Assert.assertEquals(true, f.exists());

        ArrayList<Profile> dudes = new ArrayList<>();
        FileInputStream fis = new FileInputStream(curr_path +"/../dudes.out");
        boolean cont = true;
        try ( ObjectInputStream input = new ObjectInputStream(fis)){
            while(cont) {
                ArrayList<Profile> obj  = (ArrayList<Profile>) input.readObject();
                if (obj != null) {
                    dudes = obj;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            cont = false;
        }

        Assert.assertNotEquals(0, dudes.size());
    }

}
