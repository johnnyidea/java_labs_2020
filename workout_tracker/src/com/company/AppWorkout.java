package com.company;

import javax.naming.InvalidNameException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AppWorkout {
    private boolean init = false;
    private int dude_id = 0;
    private ArrayList<Training> workout;
    private ArrayList<Profile> dudes;

    public AppWorkout(){
    }

    public boolean isInteger(Object object) {
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

    public void readDudes() throws IOException, ClassNotFoundException {
        Path curr_path = Paths.get("").toAbsolutePath();

        String tmp = curr_path.toString() + "/dudes.out";
        File f = new File(tmp);
        if (!f.exists()) return;

        FileInputStream fis = new FileInputStream("dudes.out");

        boolean cont = true;
        try ( ObjectInputStream input = new ObjectInputStream(fis)){
            while(cont) {
                ArrayList <Profile> obj  = (ArrayList<Profile>) input.readObject();
                if (obj != null) {
                    dudes = obj;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            cont = false;
        }

    }


    public void login() throws IOException, ClassNotFoundException {

        BufferedReader b_r = new BufferedReader(new InputStreamReader(System.in));
        String cmd_op;

        while (!init) {

            System.out.print("Do you have a profile?(y/n): ");
            cmd_op = b_r.readLine();

            if (cmd_op.equals("y")) {

                if (dudes.size() == 0) {
                    System.out.println("profiles do not exist");
                    continue;
                }
                System.out.println("Available profiles: ");
                for (int i = 0; i < dudes.size(); i++) {
                    System.out.print(i + 1 + " " + dudes.get(i).get_name() + "\n");
                }

                System.out.print("Select your profile:");
                cmd_op = b_r.readLine();

                if (!isInteger(cmd_op)) {
                    System.out.println("Try again");
                    continue;
                }

                dude_id = Integer.parseInt(cmd_op) - 1;

                if (dude_id >= dudes.size() || dude_id < 0) {
                    System.out.println("Wrong number, try again");
                    continue;
                }

                init = true;
                break;

            } else if (cmd_op.equals("n")) {

                System.out.print("Write your name:");
                cmd_op = b_r.readLine();

                if (cmd_op.equals("")){
                    System.out.println("Wrong name");
                    continue;
                }
                dudes.add(new Profile(cmd_op));
                continue;

            } else continue;
        }
    }

    private void process(ArrayList<Training> workout) throws IOException {
        BufferedReader b_r = new BufferedReader(new InputStreamReader(System.in));
        String cmd_op;

        while (init) {
            System.out.println();
            for (int i = 0; i < workout.size(); i++) {
                System.out.print(i + 1 + " ");
                workout.get(i).display();
            }

            System.out.print("Choose your workout: ");
            cmd_op = b_r.readLine();

            if (!isInteger(cmd_op)) {
                System.out.println("Write number and try again");
                continue;
            }

            int train_num = Integer.parseInt(cmd_op);

            if (train_num > workout.size() || train_num < 1) {
                System.out.println("Wrong number, try again");
                continue;
            }

            System.out.print("Start training?(y/n): ");
            cmd_op = b_r.readLine();

            double start = 0, time = 0;
            if (cmd_op.equals("y")) {
                start = System.currentTimeMillis();
                System.out.print("Write `stop` when you finish: ");
                while (true) {
                    if (cmd_op.equals("stop")) {
                        time = (System.currentTimeMillis() - start) / (1e3 * 3600 * 1000);
                        workout.get(train_num - 1).all_cal += time * workout.get(train_num - 1).get_cal_hour();
                        break;
                    }
                    cmd_op = b_r.readLine();
                    if (!cmd_op.equals("stop")){
                        System.out.print("Please, write word correctly(`stop`):");
                    }
                }
            } else continue;

            System.out.print("Time = " + time + " hour " + "\nyou spent " + workout.get(train_num - 1).all_cal + " cal\n" +
                    "your " + workout.get(train_num - 1).get_type() + " was amazing, continue?(y/n): ");

            dudes.get(dude_id).add_cal( workout.get(train_num - 1).all_cal );
            dudes.get(dude_id).add_time( time );

            workout.get(train_num - 1).clear();

            cmd_op = b_r.readLine();

            if (cmd_op.equals("y")) {
                continue;
            } else if (cmd_op.equals("n")) {
                System.out.println("\n\nWell done " + dudes.get(dude_id).get_name() + ", whole time = " + dudes.get(dude_id).get_time() + " hour " + "\nyou spent " + dudes.get(dude_id).get_cal() + " cal");
                break;
            } else System.out.println("Wrong, break");

            break;
        }
    }

    public ArrayList<Training> create_workout(){
        ArrayList<Training> exercise = new ArrayList<>();
        exercise.add(new Training("push_ups", 345.0));
        exercise.add(new Training("jump rope", 413.0));
        exercise.add(new Training("squats", 284.1));
        return exercise;
    }

    public void run() throws IOException, InvalidNameException, ClassNotFoundException {

        ArrayList<Training> workout =  create_workout();
        dudes = new ArrayList<Profile>();

        readDudes();
        login();
        process(workout);

        FileOutputStream fos = new FileOutputStream( "dudes.out" );
        ObjectOutputStream  out = new ObjectOutputStream(fos);
        out.writeObject(dudes);
        out.close();
    }

}
