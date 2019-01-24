/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler;

import instabottler.service.Botfinder;
import instabottler.service.FollowerService;
import instabottler.service.Randomizer;
import instabottler.settings.Auth;
import instabottler.settings.Checker;
import instabottler.settings.General;
import instabottler.settings.SettingsMaker;
import instabottler.settings.OutputPattern;
import instabottler.settings.Path;
import instabottler.settings.Target;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;


/**
 *
 * @author Galina Benedictovich
 */
public class Main
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        if(init() && Checker.check())
        {
        
            Instagram4j instagram = startInstagram();
       
            if(Target.clearOld)
            {
               deleteResults();
            }
           
            parseTarget(instagram);
        }
        
        else
        {
            waitFinish();
        }
    }
    
    static private boolean init() throws IOException
    {
        if(chkSettings())
        {
            General.init();
            Target.init();
            OutputPattern.init();
            
            return true;
        }
        
        else
        {
            SettingsMaker sm = new SettingsMaker();
            
            System.out.println("Settings files creation");
            
            if(sm.init())
            {
                System.out.println("Settings files created succesfully. You can find them in directory: " + Path.SETTINGS_PATH);
                waitFinish();
            }
            
            else
            {
                System.out.println("Settings files creation failed");
                waitFinish();
            }
            
            return false;
        }
    }
    
    static private void waitFinish() throws IOException
    {
        System.out.println("Press \"Enter\" to finish");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
        String input = reader.readLine();
        
        if(input != null)
        {
            System.exit(0);
        }
    }

    static private Boolean chkSettings()
    {
        File dir = new File(Path.SETTINGS_PATH);
        return dir.exists();
    }
    
    static public void chkDir(String path)
    {
        File dir = new File(path);

        // if the directory does not exist, create it
        if (!dir.exists()) {
            try{
                dir.mkdir();
            } 
            catch(SecurityException se){
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, se);
                System.out.println("Can't make directory!");
                System.exit(1);
            }        
        }
    }
    
    static private Instagram4j startInstagram()
    {
        Instagram4j instagram = Instagram4j.builder().username(Auth.login).password(Auth.password).build();
        
        instagram.setup();
        try
        {
            instagram.login();
          
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Login failed. Are your login and password correct?");
            
            try {
                waitFinish();
            } catch (IOException ex1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        return instagram;
    }


    
    static private void deleteResults()
    {
        deleteAllFiles(Path.DATA_PATH);
        deleteAllFiles(Path.OUTPUT_PATH);
    }

    static private void deleteAllFiles(String path)
    {
         for (File file : new File(path).listFiles())
         {
            if (file.isFile()) file.delete();
         }
    }
    
    static private void parseTarget(Instagram4j instagram) throws IOException
    {
            try
            {
                FollowerService fs = new FollowerService();
                
                List<InstagramUserSummary> users = fs.getFollowersList(instagram);
                Map<String, InstagramUser> old = fs.getOld(fs);

                int limit = Randomizer.getRandomInt(General.limitMin, General.limitMax);
                
                int proceed = 0;
                int clear = 0;
                int bots = 0;
                int suspicious = 0;
                
                int followersCount = users.size();

                
                for (InstagramUserSummary user : users)
                {
                    if(old.get(String.valueOf(user.pk)) == null)
                    {
                        InstagramUser follower = instagram.sendRequest(new InstagramSearchUsernameRequest(user.getUsername())).getUser();

                        Boolean isBot = Botfinder.isBot(follower);
                        Boolean isSuspicious = Botfinder.isSuspicious(follower);
                        
                        fs.writeDataFile(follower, Path.DATA_ALL_FILE);
                        if(General.output)
                        {
                            fs.writeOutputFile(follower, Path.OUTPUT_ALL_FILE);
                        }
                        
                        if(isBot)
                        {
                            fs.writeDataFile(follower, Path.DATA_BOT_FILE);
                            if(General.output)
                            {
                                fs.writeOutputFile(follower, Path.OUTPUT_BOT_FILE);
                            }
                            
                            bots++;
                        }
                        
                        else if(isSuspicious)
                        {
                            fs.writeDataFile(follower, Path.DATA_SUSPICIOUS_FILE);
                            if(General.output)
                            {
                                fs.writeOutputFile(follower, Path.OUTPUT_SUSPICIOUS_FILE);
                            }
                            
                            suspicious++;
                        }
                        
                        else
                        {
                            fs.writeDataFile(follower, Path.DATA_CLEAR_FILE);
                            if(General.output)
                            {
                                fs.writeOutputFile(follower, Path.DATA_CLEAR_FILE);
                            }
                            
                            clear++;
                        }
                        
                        proceed++;

                        System.out.println(proceed + " from " + followersCount + " followers proceed");

                        if(limit == proceed)
                        {
                            limit = Randomizer.getRandomInt(General.limitMin, General.limitMax);
                            
                            int time = Randomizer.getRandomMinutes(General.waitMin, General.waitMax);
                            
                            System.out.println("WAIT " + Math.round(time/60000) + " minutes................................");
                            Thread.sleep(time);
                        }

                        else
                        {
                            Thread.sleep(Randomizer.getRandomSeconds(General.pauseMin, General.pauseMax));
                        }
                    }
                    
                    else
                    {
                        proceed++;
                    }
                }
                
                System.out.println(clear + " followers, " + bots + " bots and " + suspicious + " suspicious finded.");
            }
            
            catch (IOException ex)
            {
                System.out.println("Can't get target account.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                
                waitFinish();
            } catch (InterruptedException ex1) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
            
            waitFinish();
        }
    }
}
