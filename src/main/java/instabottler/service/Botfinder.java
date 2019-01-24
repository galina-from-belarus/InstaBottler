/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.service;

import instabottler.settings.Target;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

/**
 *
 * @author Galina Benedictovich
 */
public class Botfinder
{
    private static final int CHECKINGS = 2;
    
    static public Boolean isBot(InstagramUser follower)
    {
        int counter = 0;
        
        if(follower.media_count < Target.botMediasFewer || follower.media_count == 0)
        {
            counter++;
        }
        
        if(follower.following_count > Target.botFollowingsMore)
        {
            counter++;
        }
        
        return counter == CHECKINGS;
    }
    
    static public Boolean isSuspicious(InstagramUser follower)
    {
        int counter = 0;
        
        if(follower.media_count < Target.suspiciousMediasFewer || follower.media_count == 0)
        {
            counter++;
        }
        
        if(follower.following_count > Target.suspiciousFollowingsMore)
        {
            counter++;
        }
        
        return counter == CHECKINGS;
    }
}
