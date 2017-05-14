/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Achala PC
 */
public class Hasher {
    
    public String generateHash(File file) throws Exception
    {   
        System.out.println("Start");
        FileInputStream fis = new FileInputStream(file);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Digest(in hex format):: " + sb.toString());

        //convert the byte to hex format method 2
        StringBuilder hexString = new StringBuilder();
    	for (int i=0;i<mdbytes.length;i++) {
    		String hex=Integer.toHexString(0xff & mdbytes[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println("Digest(in hex format):: " + hexString.toString());
        return hexString.toString();
    }
    
    public void otherHash() throws FileNotFoundException, IOException, NoSuchAlgorithmException{
        
    
        //Get file input stream for reading the file content
    //FileInputStream fis = new FileInputStream(file);
    FileInputStream fis = new FileInputStream("c:\\VirusTest.txt");
    MessageDigest digest = MessageDigest.getInstance("SHA-1");
     
    //Create byte array to read data in chunks
    byte[] byteArray = new byte[1024];
    int bytesCount = 0; 
      
    //Read file data and update in message digest
    while ((bytesCount = fis.read(byteArray)) != -1) {
        digest.update(byteArray, 0, bytesCount);
    };
     
    //close the stream; We don't need it now.
    fis.close();
     
    //Get the hash's bytes
    byte[] bytes = digest.digest();
     
    //This bytes[] has bytes in decimal format;
    //Convert it to hexadecimal format
    StringBuilder sb = new StringBuilder();
    for(int i=0; i< bytes.length ;i++)
    {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }
     
    //return complete hash
        System.out.println("other" + sb.toString());
    }
}