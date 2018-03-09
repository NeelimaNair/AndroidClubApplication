package iss.nus.clubapplication.ClubFolder;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by kunal on 1/3/2018.
 */

public class ClubStore {

    private Club club;

    public ClubStore(Club club){
        this.club = club;
    }

    public void loadClub(Context context){
        loadMembers(context);
        loadFacilities(context);
    }

    public void saveAll(Context context, Club club){
        saveMembers(context,club.getMembers());
        saveFacilities(context,club.getFacilities());
    }

    public void saveMembers(Context context, List<Member> lstMember){
        String fileName = "members.txt";
        List<String> lstMemberString = new ArrayList<String>();
        StringBuilder sb = null;
        for(Member member:lstMember){
            sb = new StringBuilder();
            sb.append(member.getSurname());
            sb.append(",");
            sb.append(member.getFirstname());
            sb.append(",");
            sb.append(member.getSecondname());
            sb.append(",");
            sb.append(member.getMemberNumber());
            sb.append(",");
            lstMemberString.add(sb.toString());
        }
        saveToFile(context,lstMemberString,fileName);
    }

    public void saveFacilities(Context context, Map<String,Facility> mapFacility){
        String fileName = "facilities.txt";
        List<String> lstFacillityString = new ArrayList<String>();
        StringBuilder sb = null;
        for(String name : mapFacility.keySet()){
            sb = new StringBuilder();
            Facility fac = mapFacility.get(name);
            sb.append(name);
            sb.append(",");
            sb.append(fac.getDescription());
            sb.append(",");
            lstFacillityString.add(sb.toString());
        }
        saveToFile(context,lstFacillityString,fileName);
    }

    private void saveToFile(Context context,List<String> lstToSave,String fileName){
        try {
            FileOutputStream fileOutputStream = (FileOutputStream)context.openFileOutput(fileName,Context.MODE_PRIVATE);

            for (String line: lstToSave){
                fileOutputStream.write(line.getBytes());
            }
            fileOutputStream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private List<String> readFromFile(Context context,String fileName){
        ArrayList<String> lstFromFile = new ArrayList<String>();
        try {
            FileInputStream fileInputStream = (FileInputStream)context.getApplicationContext().openFileInput(fileName);
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                lstFromFile.add(line);
            }
            fileInputStream.close();
            reader.close();
            bufferedReader.close();
        } catch(FileNotFoundException fnf){
            fnf.printStackTrace();
        } catch(IOException io){
            io.printStackTrace();
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return lstFromFile;
    }

    public void loadMembers(Context context){
        try{
            String memberFileName = "members.txt";
            List<String> lstFromFile = (ArrayList<String>)readFromFile(context,memberFileName);
            ArrayList<Member> members = new ArrayList<Member>();
            for (String strMember:lstFromFile){
                if(null != strMember){
                    String[] arrMember = strMember.split(",");
                    if(null != arrMember && arrMember.length > 3 && null != arrMember[3]){
                        Member member = new Member(arrMember[0],arrMember[1],arrMember[2],Integer.parseInt(arrMember[3]));
                        members.add(member);
                    }
                }
            }
            this.club.setMembers(members);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void loadFacilities(Context context){
        try{
            String facilitiesFileName = "facilities.txt";
            List<String> lstFromFile = (ArrayList<String>)readFromFile(context,facilitiesFileName);
            HashMap<String,Facility> facilities = new HashMap<String,Facility>();
            for (String strFacility:lstFromFile){
                if(null != strFacility){
                    String[] arrFacility = strFacility.split(",");
                    if(null != arrFacility && arrFacility.length > 1 && null != arrFacility[0]){
                        Facility facility = new Facility(arrFacility[0],arrFacility[1]);
                        facilities.put(arrFacility[0],facility);
                    }
                }
            }
            this.club.setFacilities(facilities);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
