package com.company;

/*Приложение позволяет указать вид тренировки: отжимание, скакалка, приседания.
Доступны команды: начать тренировку (запускается таймер тренировки),
закончить тренировку (таймер останавливается).
За отработанное время высчитывается количество потраченных калорий по формуле K*t=cal, где K - количество калорий в час,
затрачиваемое на определенный вид тренировки, t - время, засеченное трекером. От запуска к запуску программы данные должны
сохраняться и общее количество калорий - суммироваться.
Персистенция данных приложения с помощью ObjectOutputStream +5 баллов или JAXB + 10 баллов
Поддержка нескольких профилей пользователей приложением + 5 баллов
 */

import javax.naming.InvalidNameException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

    public static boolean isInteger(Object object) {
        if(object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();

            try {
                Integer.parseInt(string);
            } catch(Exception e) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException, InvalidNameException  {
        BufferedReader b_r = new BufferedReader(new InputStreamReader(System.in));
        String cmd_op;

        ArrayList<Training>  workout = new ArrayList<Training >();

        workout.add(new Training( "push_ups", 345.0));
        workout.add(new Training( "jump rope", 413.0));
        workout.add(new Training( "squats", 284.1));

        Profile dude = new Profile();

        while (true) {
            for (int i = 0; i < workout.size(); i++) {
                System.out.print(i + 1 + " ");
                workout.get(i).display();
            }

            System.out.println("Choose your workout:");
            cmd_op = b_r.readLine();

            if (!isInteger(cmd_op)) {
                System.out.println("Write number and try again");
                continue;
            }

            int train_num = Integer.parseInt(cmd_op);

            if (train_num > workout.size() || train_num < 0) {
                System.out.println("Wrong number, try again");
                continue;
            }

            System.out.println("Start training?(y/n):");
            cmd_op = b_r.readLine();

            double start = 0, time = 0;
            if (cmd_op.equals("y")) {
                start = System.currentTimeMillis();
                System.out.println("Write `stop` when you finish");
                while (true) {
                    if (cmd_op.equals("stop")) {
                        time = (System.currentTimeMillis() - start) / (1e3 * 3600);
                        workout.get(train_num - 1).all_cal += time * workout.get(train_num - 1).get_cal_hour();
                        break;
                    }
                    cmd_op = b_r.readLine();
                }
            } else continue;

            System.out.println("Time = " + time + " hour " + "\nyou spent " + workout.get(train_num - 1).all_cal + " cal\n" +
                    "your " + workout.get(train_num - 1).get_type() + " was amazing, continue?(y/n)");

            dude.all_cal += workout.get(train_num - 1).all_cal;
            dude.all_time += time;

            workout.get(train_num - 1).clear();

            cmd_op = b_r.readLine();

            if (cmd_op.equals("y")) {
                continue;
            } else if (cmd_op.equals("n")) {
                System.out.println("\n\nWhole time = " + dude.all_time + " hour " + "\nyou spent " + dude.all_cal + " cal");
                break;
            } else System.out.println("Wrong, break");

            break;

        }

    }
}

