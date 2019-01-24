/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.service;

import java.util.Random;

/**
 *
 * @author Galina Benedictovich
 */
public class Randomizer
{
    public static int getRandomInt(int min, int max)
    {
        int range = max - min;
        
        Random rand = new Random();
        
        int number = rand.nextInt(range) + min;
        
        return number;
    }
    
    
    public static int getRandomSeconds(int min, int max)
    {
        int range = (max - min) * 1000;
        
        Random rand = new Random();
        
        int number = rand.nextInt(range) + min * 1000;
        
        return number;
    }
    
    public static int getRandomMinutes(int min, int max)
    {
        int range = (max - min) * 60000;
        
        Random rand = new Random();
        
        int number = rand.nextInt(range) + min * 60000;
        
        return number;
    }
}
