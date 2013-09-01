package com.example.ca1;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Transfer {
	public static int uploadFile(String caption, String location, String path) {
        HttpURLConnection conn = null;
        DataOutputStream dos = null; 
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize, serverResponseCode=0;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL("http://137.132.247.137/python/insert/upload");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("file", sourceFile.getName());
                  
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""  + sourceFile.getName() + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
        
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
            while (bytesRead > 0) {
                 dos.write(buffer, 0, bufferSize);
                 bytesAvailable = fileInputStream.available();
                 bufferSize = Math.min(bytesAvailable, maxBufferSize);
                 bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
            }
            dos.writeBytes(lineEnd);
            
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"caption\"" + lineEnd);
            dos.writeBytes(lineEnd); dos.writeBytes(caption); dos.writeBytes(lineEnd); 

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"location\"" + lineEnd);
            dos.writeBytes(lineEnd); dos.writeBytes(location); dos.writeBytes(lineEnd); 

            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();
            fileInputStream.close();
            dos.flush();
            dos.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
         return serverResponseCode;
     }
} 

