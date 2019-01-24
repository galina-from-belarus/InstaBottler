/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instabottler.settings;

import java.io.IOException;

/**
 *
 * @author Galina Benedictovich
 */
public class Checker
{
    public static boolean check() throws IOException
    {
        boolean auth = Auth.check();
        boolean general = General.check();
        boolean target = Target.check();
        
        if(!auth)
        {
            System.out.println(ErrorMessage.AUTH);
        }

        if(!general)
        {
            System.out.println(ErrorMessage.GENERAL);
        }

        if(!target)
        {
            System.out.println(ErrorMessage.TARGET);
        }
    
        return auth && general && target;
    }
}
