/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package myservisupdatenew;

/**
 *
 * @author Timur
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyServisUpdateNew {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Читаем файл с настройками        
        long starTime1, starTime2, deltaTime;
        String sDirNewVersion = null;
        String sVersionServer = null; 
        String sVersionLocal = null;
        String sStroka= null;

        String progDir = System.getProperty("user.dir");
        progDir = progDir + "\\";
        
        starTime1 = System.currentTimeMillis();
        
        // Удаляем старый лог если есть
        //Path fileLogDel = Paths.get("temp\\myServisUpdate_log.txt");
        //try {Files.delete(fileLogDel);}
        //catch (IOException e) {e.printStackTrace();}
                
        //Читаем настройки программы для того, чтобы узнать каталог где прочитать номер новой версии
        try {            
            //BufferedReader d = new BufferedReader(new InputStreamReader(in));
            try (BufferedReader reader = new BufferedReader(new FileReader("myServisProg.ini"))) {
                String line = reader.readLine();
                int ik = 0;
                while (line != null) {
                    ik++;
                    if (ik == 1) sStroka = line;
                    if (ik == 2) sStroka = line;
                    if (ik == 3) sStroka = line;
                    if (ik == 4) sStroka = line;
                    if (ik == 5) sStroka = line;
                    if (ik == 6) sStroka = line;
                    if (ik == 7) sStroka = line;
                    if (ik == 8) sDirNewVersion = line;
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {}
        
        // Читаем номер версии на Сервере
        try {            
            try (BufferedReader reader = new BufferedReader(new FileReader(sDirNewVersion+"myVersionProgServer.ver"))) {
                String line = reader.readLine();
                sVersionServer = line;
            }            
        } catch (IOException e) {}
        
        // Читаем номер установленной версии
        try {            
            try (BufferedReader reader = new BufferedReader(new FileReader("myVersionProgLocal.ver"))) {
                String line = reader.readLine();
                sVersionLocal = line;
            }            
        } catch (IOException e) {}     
        
        String sDateTimeTek = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        
        String sTextFile;
        sTextFile ="****************************************************************************\n";
        sTextFile +="Дата: " + sDateTimeTek + "\n";
        sTextFile +="****************************************************************************\n";
        sTextFile += "Каталог расположения новой версии: "+sDirNewVersion+"\n";
        sTextFile +="Файл с номером новой версии      : "+sDirNewVersion+"myVersionProgServer.ver\n";
        sTextFile +="Файл с номером текущей версии    : myVersionProgLocal.ver\n";
        sTextFile +="----------------------------------------------------------------------------\n";
        sTextFile +="Номер новой версии               : "+sDirNewVersion+sVersionServer+"\n";
        sTextFile +="Номер текущей версии             : "+sDirNewVersion+sVersionLocal+"\n";
        sTextFile +="----------------------------------------------------------------------------\n";
        
        System.out.println("Каталог расположения новой версии: "+sDirNewVersion);
        System.out.println("Файл с номером новой версии      : "+sDirNewVersion+"myVersionProgServer.ver");
        System.out.println("Файл с номером текущей версии    : myVersionProgLocal.ver");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Номер новой версии               : "+sDirNewVersion+sVersionServer);
        System.out.println("Номер текущей версии             : "+sDirNewVersion+sVersionLocal);
        System.out.println("----------------------------------------------------------------------------");
        
        if (sVersionServer.equals(sVersionLocal) ){
            System.out.println("Обновление программы не требуется");
            System.out.println("****************************************************************************");
            sTextFile +="Обновление программы не требуется\n";
            
        }else{
            System.out.println("Необходимо обновление программы");            
            System.out.println("Запуск копирования новой версии");
            sTextFile +="Необходимо обновление программы\n";
            sTextFile +="Запуск копирования новой версии\n";
            
            // копируем новую версию программы
            Path sourcePath = Paths.get(sDirNewVersion+"myServisProg.jar");
            Path destPath = Paths.get("myServisProg_New.jar");        
            try {
                Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {e.printStackTrace();}
            
            System.out.println("Копирование новой версии произведено успешно");
            sTextFile +="Копирование новой версии произведено успешно\n";
            System.out.println("----------------------------------------------------------------------------");
                        
            // удаляем старую копию если есть            
            System.out.println("Запуск удаления предыдущей старой копии");
            sTextFile +="Запуск удаления предыдущей старой копии\n";
            Path fileDel= Paths.get("myServisProg_Old.jar");
            try {Files.delete(fileDel);}
            catch (IOException e) {e.printStackTrace();}
            
            System.out.println("Удаления предыдущей старой копии произведено успешно");
            sTextFile +="Удаления предыдущей старой копии произведено успешно\n";
            
            // переименовываем предыдущую версию
            System.out.println("Запуск переименования предыдущей версии");
            sTextFile +="Запуск переименования предыдущей версии\n";
            
            File fTek = new File("myServisProg.jar");   
            File fOld = new File("myServisProg_Old.jar");   
            if (fTek.renameTo(fOld)) { 
                System.out.println("File is renamed"); 
                System.out.println("Переименование предыдущей версии произведено успешно");
                sTextFile +="Переименование предыдущей версии произведено успешно\n";
            } 
            else { 
                System.out.println("File cannot be renamed"); 
                System.out.println("Переименование предыдущей версии не произведно");
                sTextFile +="Переименование предыдущей версии не произведно\n";                
            }
            
            // переименовываем новую версию
            System.out.println("Запуск переименования новой версии");
            sTextFile +="Запуск переименования новой версии\n";
            
            File fNew = new File("myServisProg_New.jar");  
            File fNewTek = new File("myServisProg.jar");   
            if (fNew.renameTo(fNewTek)) { 
                System.out.println("File is renamed"); 
                System.out.println("Переименование новой версии произведено успешно");
                sTextFile +="Переименование новой версии произведено успешно\n";
            } 
            else { 
                System.out.println("File cannot be renamed"); 
                System.out.println("Переименование новой версии не произведно");
                sTextFile +="Переименование новой версии не произведно\n";                
            }              
        }
        
        // Создаем папку temp:
        File directory = new File(progDir+"temp");
        if (directory.mkdirs()) {
            System.out.println("Directory created successfully");
        }        
        
        // Создаем файл лога:
        starTime2 = System.currentTimeMillis();
        deltaTime = (starTime2 - starTime1);
        
        sTextFile += "****************************************************************************\n";
        sTextFile +="Время работы: " + deltaTime +" mSek.\n";
        sTextFile += "****************************************************************************\n";
        System.out.println("****************************************************************************");
        System.out.println("Время работы : " + deltaTime+" mSek.");
        System.out.println("****************************************************************************");
        // Создаем файл лога проверки новой версии:
        try {
            Path fileName = Paths.get("temp\\myServisUpdate_log.txt");
            Files.write(fileName, sTextFile.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {e.printStackTrace();}
                
        // создаем запусковый bat-файл:
        String sNameFileBat="temp\\start.bat";
        String sTextBat;
        sTextBat = "@echo off\n";
        sTextBat += progDir+"myServisProg.jar";

        try {
            Path fileName = Paths.get(sNameFileBat);
            Files.write(fileName, sTextBat.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {e.printStackTrace();}

        // Запускаем программу Bat-файл:
        try {Process child = Runtime.getRuntime().exec(sNameFileBat);}
        catch (IOException ex) {Logger.getLogger(MyServisUpdateNew.class.getName()).log(Level.SEVERE, null, ex);}
        
        
    }
    
}
