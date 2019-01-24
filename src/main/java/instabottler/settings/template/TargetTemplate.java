/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instabottler.settings.template;

/**
 *
 * @author Galina Benedictovich
 */
public class TargetTemplate
{
    public static final String [] TMP = {
        "/* аккаунт, по которому будут собираться данные */",
        "",
        "target username:",
        "",
        "",
        "/* можно использовать логин и пароль от этого аккаунта, или их файла auth */",
        "/* чтобы использовать логин и пароль из файла, укажите для обоих default */",
        "",
        "login:default",
        "password:default",
        "",
        "",
        "/* Очистить список \"знакомых\" боту подписчиков */",
        "/* Внимание, полностью сбросит предыдущие результаты! */",
        "/* Если у вас есть сомнения - скопируйте директории data и output */",
        "",
        "clear old:false",
        "",
        "bot media < :10",
        "bot followings > :1000",
        "",
        "suspicious media < :1",
        "suspicious followings > :500",
    };
}
