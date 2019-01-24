/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.settings;

/**
 *
 * @author Galina Benedictovich
 */
public class Auth
{
    public static String login;
    public static String password;
    
    
    public static Boolean check()
    {
        return !(login.isEmpty() || password.isEmpty());
    }
}
