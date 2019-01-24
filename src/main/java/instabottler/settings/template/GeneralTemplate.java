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
public class GeneralTemplate
{
    public static final String [] TMP = {
        "/* Логин и пароль по умолчанию */",
        "/* Для отдельных пользователей логин и пароль можно настроить в файле target */",
        "",
        "login:",
        "password:",
        "",
        "",
        "/* Будет ли выводиться в отдельные файлы списков результат обработки, true/false */",
        "",
        "output:true",
        "",
        "",
        "/* Разделитель в файле вывода (если output:true) */",
        "",
        "separator: : ",
        "",
        "",
        "/* Сколько секунд скрипт будет ждать между запросами, от минимума до максимума */",
        "/* Для всех таймеров максимальное значение должно быть больше нуля! */",
        "",
        "pause min:2",
        "pause max:5",
        "",
        "",
        "/* После обработки скольких аккаунтов скрипт сделает паузу */",
        "",
        "account parsing limit min:200",
        "account parsing limit max:300",
        "",
        "",
        "/* На сколько минут (от - до) */",
        "",
        "wait min:5",
        "wait max:15"
    }; 
}
