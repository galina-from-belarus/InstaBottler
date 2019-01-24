/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.settings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Galina Benedictovich
 */
public class OutputPattern
{
    private static final String SETTINGS = Path.OUTPUT;
    public static List<String> pattern = new ArrayList<String>();
    
    
    static public void init()
    {
        try (FileReader fr = new FileReader(SETTINGS))
        {
            Scanner scan = new Scanner(fr);
            
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                
                if(!input.contains("****************"))
                {
                    pattern.add(input);
                }
                
                else
                {
                    break;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Can't find file " + SETTINGS);
        }
        catch (IOException e)
        {
            System.out.println("Can't read file " + SETTINGS);
        }
    }
}
