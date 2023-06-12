package com.namth.assignment;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public String username, password;

    public User(String username, String password){
        this.password=password;
        this.username=username;
    }
    public void writeUser(Context c, String fileName, User user){
        ArrayList<User> list =new ArrayList<>();
        try {
            FileOutputStream fos= c.openFileOutput(fileName, c.MODE_PRIVATE);
            ObjectOutputStream oss = new ObjectOutputStream(fos);
            list.add(user);
            oss.writeObject(list);
            oss.close();
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<User> readUser(Context c, String fileName){
        List<User> ojList =new ArrayList<>();
        try {
            FileInputStream fis = c.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ojList = (List<User>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return ojList;
    }

}
