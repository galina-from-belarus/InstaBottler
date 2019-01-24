/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.settings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Galina Benedictovich
 */
public class Target
{
    private static final String TARGET_FILE = Path.TARGET;
    
    public static String target;
    
    public static int botMediasFewer;
    public static int botFollowingsMore;

    public static int suspiciousMediasFewer;
    public static int suspiciousFollowingsMore;
    
    public static boolean clearOld;


    
    static public void init()
    {
        try (FileReader fr = new FileReader(TARGET_FILE))
        {
            Scanner scan = new Scanner(fr);
            
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                
                input = Patterns.COMMENT.matcher(input).replaceAll("");
                
                if(input.contains("target username"))
                {
                    target = input.replace("target username:", "").replace(" ", "");
                }
                
                else if(input.contains("login"))
                {
                    String s = input.replace("login:", "").replace(" ", "");
                    
                    if(!s.contains("default"))
                    {
                        Auth.login = s;
                    }
                }

                else if(input.contains("password"))
                {
                    String s = input.replace("password:", "").replace(" ", "");
                    
                    if(!s.contains("default"))
                    {
                        Auth.password = s;
                    }
                }
                
                else if(input.contains("clear old"))
                {
                    clearOld = Boolean.parseBoolean(input.replace("clear old:", ""));
                }

                else if(input.contains("bot media"))
                {
                    botMediasFewer = Integer.parseInt(input.replace("bot media < :", "").replace(" ", ""));
                }

                else if(input.contains("bot followings"))
                {
                    botFollowingsMore = Integer.parseInt(input.replace("bot followings > :", "").replace(" ", ""));
                }                

                else if(input.contains("suspicious media"))
                {
                    suspiciousMediasFewer = Integer.parseInt(input.replace("suspicious media < :", "").replace(" ", ""));
                }                

                else if(input.contains("suspicious followings"))
                {
                    suspiciousFollowingsMore = Integer.parseInt(input.replace("suspicious followings > :", "").replace(" ", ""));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Can't find file " + TARGET_FILE);
        }
        catch (IOException e)
        {
            System.out.println("Can't read file " + TARGET_FILE);
        }
    }

    public static Boolean check()
    {
        return !target.isEmpty();
    }
}
