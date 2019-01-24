/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instabottler.settings;

import instabottler.settings.template.GeneralTemplate;
import instabottler.settings.template.OutputTemplate;
import instabottler.settings.template.TargetTemplate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Galina Benedictovich
 */
public class SettingsMaker
{
    public Boolean init() throws IOException
    {
        return makeDir()&&
                (
                    writeFile(Path.GENERAL, GeneralTemplate.TMP) &&
                    writeFile(Path.OUTPUT, OutputTemplate.TMP) &&
                    writeFile(Path.TARGET, TargetTemplate.TMP)
                );
    }
    
    private Boolean makeDir()
    {
        File dir = new File(Path.SETTINGS_PATH);

        return dir.mkdir();
    }
    
    private Boolean makeFile(String path) throws IOException
    {
        File file = new File(path);
        
        return file.exists() || file.createNewFile();
    }
    
    private Boolean writeFile(String file, String[] tmp) throws IOException
    {
        if(makeFile(file))
        {
            try(FileWriter writer = new FileWriter(file, true))
            {
                for(String input : tmp)
                {
                    writer.write(input);
                    writer.write(General.LINE_SEPARATOR);
                }

                return true;

            } catch (IOException ex) {
                Logger.getLogger(SettingsMaker.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        return false;
    }
}
