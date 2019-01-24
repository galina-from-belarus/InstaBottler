/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import instabottler.Main;
import instabottler.settings.General;
import instabottler.settings.OutputPattern;
import instabottler.settings.Path;
import instabottler.settings.SettingsMaker;
import instabottler.settings.Target;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

/**
 *
 * @author Galina Benedictovich
 */
public class FollowerService
{
    private final String defaultFile = "data" + File.separator + "followers.txt";
    
    public Map getFollowersFromDefaultFile()
    {
        Map<String, InstagramUser> followers = new HashMap<>();
        
        try
        {
           FileInputStream fstream = new FileInputStream(defaultFile);
           BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
           String strLine;
           while ((strLine = br.readLine()) != null){
               
               	ObjectMapper mapper = new ObjectMapper();

		try {

			InstagramUser follower = mapper.readValue(strLine, InstagramUser.class);
			
			followers.put(String.valueOf(follower.pk), follower);
                        
		} catch (JsonGenerationException ex) {
                    Logger.getLogger(SettingsMaker.class.getName()).log(Level.SEVERE, null, ex);
                
		} catch (JsonMappingException ex1) {
		
                    Logger.getLogger(SettingsMaker.class.getName()).log(Level.SEVERE, null, ex1);
                
		} catch (IOException ex2) {
                    Logger.getLogger(SettingsMaker.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        }
        catch (IOException e){
           System.out.println("No followers analysed yet.");
           return followers;
        }
        
        return followers;
    }
    
    public Map<String, InstagramUser> getOld(FollowerService fs)
    {
        Map<String, InstagramUser> old = fs.getFollowersFromDefaultFile();
    
        return old;
    }
    
    public List<InstagramUserSummary> getFollowersList(Instagram4j instagram)
    {
        List<InstagramUserSummary> list = new ArrayList<>();
        
        try
        {
            InstagramSearchUsernameResult account = instagram.sendRequest(new InstagramSearchUsernameRequest(Target.target));

            InstagramGetUserFollowersRequest request = new InstagramGetUserFollowersRequest(account.getUser().getPk());

            InstagramGetUserFollowersResult followers = instagram.sendRequest(request);
            list = followers.getUsers();
        }

        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Can't get target account.");
        }
            return list;
    }

            
    public void writeDataFile(InstagramUser follower, String file) throws IOException
    {
        Main.chkDir(Path.DATA_PATH);
        
        ObjectMapper mapper = new ObjectMapper();

        String input = null;
        
        try {
            input = mapper.writeValueAsString(follower);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FollowerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            try (FileWriter dataFollowerWriter = new FileWriter(file, true)) {

                dataFollowerWriter.write(input + General.LINE_SEPARATOR);

                dataFollowerWriter.close();
        }

        } catch (JsonGenerationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex1) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex2) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }

    public void writeOutputFile(InstagramUser follower, String file) throws IOException
    {
        Main.chkDir(Path.OUTPUT_PATH);
        
        ObjectMapper mapper = new ObjectMapper();
        
        Map <String, String> map = mapper.convertValue(follower, HashMap.class);
                
        try {
            
            try (FileWriter outputFollowerWriter = new FileWriter(file, true)) {

                int fin = OutputPattern.pattern.size() - 1;
                
                for(String key:OutputPattern.pattern)
                {
                    String output;
                    
                    if(!key.contentEquals("hd_profile_pic_versions"))
                    {
                        output = String.valueOf(map.get(key));
                    }
                    
                    else
                    {
                        if(follower.getHd_profile_pic_versions() == null)
                        {
                            output = "0";
                        }
                        
                        else
                        {
                            output = String.valueOf(follower.hd_profile_pic_versions.size());
                        }
                    }
                    
                    outputFollowerWriter.write(output);
                    
                    if(!key.equals(OutputPattern.pattern.get(fin)))
                    {
                        outputFollowerWriter.write(General.separator);
                    }
                }
                
                outputFollowerWriter.write(General.LINE_SEPARATOR);
                
                outputFollowerWriter.close();
        }

        } catch (JsonGenerationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex1) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex2) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
}
