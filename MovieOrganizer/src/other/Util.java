/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import organizer.model.Filename;

/**
 *
 * @author Cristian
 */
public class Util {

    //md5 proved to be so slow its not worth it
    public static String getMD5(File f) throws NoSuchAlgorithmException, FileNotFoundException {
        String output = null;
        MessageDigest digest = MessageDigest.getInstance("MD5");

        InputStream is = new FileInputStream(f);
        byte[] buffer = new byte[8192];
        int read = 0;
        try {
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            output = bigInt.toString(16);
        } catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
            }
        }

        return output;
    }

    public Util() {
    }

    public void copyFile(String src, String dest) throws IOException {
        Filename fileName = new Filename(dest);
        File sourceFile = new File(src);

        Date date = new Date(sourceFile.lastModified());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String did = formatter.format(date);

        new File(fileName.path()).mkdirs();
        dest = fileName.path() + "/" + did + "_" + fileName.fullFilename();

        File destFile = new File(dest);
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
            //destination.
        } catch (Exception e) {
            throw new Error("can not copy file");
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }

        destFile.setLastModified(sourceFile.lastModified());

    }

    public void getDate(String string) {
        System.out.println(string);
        //File file = new File(string);
        //file.setLastModified(time)

        //System.out.println(new Date(file.lastModified()));
        //String creationDate = NativeInterface.nativeFileCreationDate(file.getAbsolutePath()); return creationDate;
    }
}
