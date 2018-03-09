package iss.nus.clubapplication.application;

import android.app.Application;

import iss.nus.clubapplication.ClubFolder.Club;
import iss.nus.clubapplication.ClubFolder.ClubStore;

/**
 * Created by kunal on 2/3/2018.
 */

public class App extends Application {

    public static ClubStore clubStore;
    public  static Club club;

    @Override
    public void onCreate() {
        super.onCreate();
        Club club = new Club();
        ClubStore clubStore = new ClubStore(club);
        clubStore.loadClub(getApplicationContext());
    }
}
