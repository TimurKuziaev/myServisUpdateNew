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
        // ������ ���� � �����������        
        long starTime1, starTime2, deltaTime;
        String sDirNewVersion = null;
        String sVersionServer = null; 
        String sVersionLocal = null;
        String sStroka= null;

        String progDir = System.getProperty("user.dir");
        progDir = progDir + "\\";
        
        starTime1 = System.currentTimeMillis();
        
        // ������� ������ ��� ���� ����
        //Path fileLogDel = Paths.get("temp\\myServisUpdate_log.txt");
        //try {Files.delete(fileLogDel);}
        //catch (IOException e) {e.printStackTrace();}
                
        //������ ��������� ��������� ��� ����, ����� ������ ������� ��� ��������� ����� ����� ������
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
        
        // ������ ����� ������ �� �������
        try {            
            try (BufferedReader reader = new BufferedReader(new FileReader(sDirNewVersion+"myVersionProgServer.ver"))) {
                String line = reader.readLine();
                sVersionServer = line;
            }            
        } catch (IOException e) {}
        
        // ������ ����� ������������� ������
        try {            
            try (BufferedReader reader = new BufferedReader(new FileReader("myVersionProgLocal.ver"))) {
                String line = reader.readLine();
                sVersionLocal = line;
            }            
        } catch (IOException e) {}     
        
        String sDateTimeTek = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        
        String sTextFile;
        sTextFile ="****************************************************************************\n";
        sTextFile +="����: " + sDateTimeTek + "\n";
        sTextFile +="****************************************************************************\n";
        sTextFile += "������� ������������ ����� ������: "+sDirNewVersion+"\n";
        sTextFile +="���� � ������� ����� ������      : "+sDirNewVersion+"myVersionProgServer.ver\n";
        sTextFile +="���� � ������� ������� ������    : myVersionProgLocal.ver\n";
        sTextFile +="----------------------------------------------------------------------------\n";
        sTextFile +="����� ����� ������               : "+sDirNewVersion+sVersionServer+"\n";
        sTextFile +="����� ������� ������             : "+sDirNewVersion+sVersionLocal+"\n";
        sTextFile +="----------------------------------------------------------------------------\n";
        
        System.out.println("������� ������������ ����� ������: "+sDirNewVersion);
        System.out.println("���� � ������� ����� ������      : "+sDirNewVersion+"myVersionProgServer.ver");
        System.out.println("���� � ������� ������� ������    : myVersionProgLocal.ver");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("����� ����� ������               : "+sDirNewVersion+sVersionServer);
        System.out.println("����� ������� ������             : "+sDirNewVersion+sVersionLocal);
        System.out.println("----------------------------------------------------------------------------");
        
        if (sVersionServer.equals(sVersionLocal) ){
            System.out.println("���������� ��������� �� ���������");
            System.out.println("****************************************************************************");
            sTextFile +="���������� ��������� �� ���������\n";
            
        }else{
            System.out.println("���������� ���������� ���������");            
            System.out.println("������ ����������� ����� ������");
            sTextFile +="���������� ���������� ���������\n";
            sTextFile +="������ ����������� ����� ������\n";
            
            // �������� ����� ������ ���������
            Path sourcePath = Paths.get(sDirNewVersion+"myServisProg.jar");
            Path destPath = Paths.get("myServisProg_New.jar");        
            try {
                Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {e.printStackTrace();}
            
            System.out.println("����������� ����� ������ ����������� �������");
            sTextFile +="����������� ����� ������ ����������� �������\n";
            System.out.println("----------------------------------------------------------------------------");
                        
            // ������� ������ ����� ���� ����            
            System.out.println("������ �������� ���������� ������ �����");
            sTextFile +="������ �������� ���������� ������ �����\n";
            Path fileDel= Paths.get("myServisProg_Old.jar");
            try {Files.delete(fileDel);}
            catch (IOException e) {e.printStackTrace();}
            
            System.out.println("�������� ���������� ������ ����� ����������� �������");
            sTextFile +="�������� ���������� ������ ����� ����������� �������\n";
            
            // ��������������� ���������� ������
            System.out.println("������ �������������� ���������� ������");
            sTextFile +="������ �������������� ���������� ������\n";
            
            File fTek = new File("myServisProg.jar");   
            File fOld = new File("myServisProg_Old.jar");   
            if (fTek.renameTo(fOld)) { 
                System.out.println("File is renamed"); 
                System.out.println("�������������� ���������� ������ ����������� �������");
                sTextFile +="�������������� ���������� ������ ����������� �������\n";
            } 
            else { 
                System.out.println("File cannot be renamed"); 
                System.out.println("�������������� ���������� ������ �� ����������");
                sTextFile +="�������������� ���������� ������ �� ����������\n";                
            }
            
            // ��������������� ����� ������
            System.out.println("������ �������������� ����� ������");
            sTextFile +="������ �������������� ����� ������\n";
            
            File fNew = new File("myServisProg_New.jar");  
            File fNewTek = new File("myServisProg.jar");   
            if (fNew.renameTo(fNewTek)) { 
                System.out.println("File is renamed"); 
                System.out.println("�������������� ����� ������ ����������� �������");
                sTextFile +="�������������� ����� ������ ����������� �������\n";
            } 
            else { 
                System.out.println("File cannot be renamed"); 
                System.out.println("�������������� ����� ������ �� ����������");
                sTextFile +="�������������� ����� ������ �� ����������\n";                
            }              
        }
        
        // ������� ����� temp:
        File directory = new File(progDir+"temp");
        if (directory.mkdirs()) {
            System.out.println("Directory created successfully");
        }        
        
        // ������� ���� ����:
        starTime2 = System.currentTimeMillis();
        deltaTime = (starTime2 - starTime1);
        
        sTextFile += "****************************************************************************\n";
        sTextFile +="����� ������: " + deltaTime +" mSek.\n";
        sTextFile += "****************************************************************************\n";
        System.out.println("****************************************************************************");
        System.out.println("����� ������ : " + deltaTime+" mSek.");
        System.out.println("****************************************************************************");
        // ������� ���� ���� �������� ����� ������:
        try {
            Path fileName = Paths.get("temp\\myServisUpdate_log.txt");
            Files.write(fileName, sTextFile.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {e.printStackTrace();}
                
        // ������� ���������� bat-����:
        String sNameFileBat="temp\\start.bat";
        String sTextBat;
        sTextBat = "@echo off\n";
        sTextBat += progDir+"myServisProg.jar";

        try {
            Path fileName = Paths.get(sNameFileBat);
            Files.write(fileName, sTextBat.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {e.printStackTrace();}

        // ��������� ��������� Bat-����:
        try {Process child = Runtime.getRuntime().exec(sNameFileBat);}
        catch (IOException ex) {Logger.getLogger(MyServisUpdateNew.class.getName()).log(Level.SEVERE, null, ex);}
        
        
    }
    
}
