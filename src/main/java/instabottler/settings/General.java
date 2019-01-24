/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.settings;

import static instabottler.settings.Auth.login;
import static instabottler.settings.Auth.password;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Galina Benedictovich
 */
public class General
{
    private static final String GENERAL_FILE = Path.GENERAL;
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    public static boolean output;
    public static String separator;
    public static int pauseMin;
    public static int pauseMax;
    
    public static int limitMin;
    public static int limitMax;
    
    public static int waitMin;
    public static int waitMax;

    
    static public void init()
    {
        try (FileReader fr = new FileReader(GENERAL_FILE))
        {
            Scanner scan = new Scanner(fr);
            
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                
                input = Patterns.COMMENT.matcher(input).replaceAll("");
                
                if(input.contains("login"))
                {
                    login = input.replace("login:", "").replace(" ", "");
                }
                
                else if(input.contains("password"))
                {
                    password = input.replace("password:", "").replace(" ", "");
                }

                else if(input.contains("output"))
                {
                    output = Boolean.parseBoolean(input.replace("output:", ""));
                }
                
                else if(input.contains("separator"))
                {
                    separator = input.replace("separator:", "");
                }
                
                else if(input.contains("pause min"))
                {
                    pauseMin = Integer.parseInt(input.replace("pause min:", ""));
                }

                else if(input.contains("pause max"))
                {
                    pauseMax = Integer.parseInt(input.replace("pause max:", ""));
                }
                
                else if(input.contains("account parsing limit min"))
                {
                    limitMin = Integer.parseInt(input.replace("account parsing limit min:", ""));
                }

                else if(input.contains("account parsing limit max"))
                {
                    limitMax = Integer.parseInt(input.replace("account parsing limit max:", ""));
                }

                else if(input.contains("wait min"))
                {
                    waitMin = Integer.parseInt(input.replace("wait min:", ""));
                }

                else if(input.contains("wait max"))
                {
                    waitMax = Integer.parseInt(input.replace("wait max:", ""));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Can't find file " + GENERAL_FILE);
        }
        catch (IOException e)
        {
            System.out.println("Can't read file " + GENERAL_FILE);
        }
    }
    
    public static Boolean check()
    {
        return(pauseMin >= 0 && 
                limitMin >= 0 && 
                waitMin >= 0 && 
                pauseMax > 0 && 
                limitMax > 0 && 
                waitMax > 0);
    }
}
