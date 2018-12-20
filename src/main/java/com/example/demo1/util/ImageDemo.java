package com.example.demo1.util;


import com.ksyun.ks3.sdk.dto.AccessControlList;
import com.ksyun.ks3.sdk.KS3Client;
import com.ksyun.ks3.sdk.dto.Credential;
import ij.plugin.DICOM;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * dicom文件java解析，生成图片
 * 不过这里不能解析压缩的dicom文件
 */
public class ImageDemo {
    private static final String ACCESS_KEY_ID = "AKLTyrBp0CWCQjeExPSearUboA";
    private static final String ACCESS_KEY_SECRET = "OHnFNgPZyz6c3jNM1yZFjKGI5kIfJk/rvaybM601nq3em/9GVIbg0MXnJueV9qNTXA==";

    public static void main(String args[]) throws Exception {
        //
        //create("test1.dcm");    //在本地目录生成test1.dcm.jpg图片文件


        //    create2("F:\\test3.dcm");   //在电脑dicom文件夹下生成test1.dcm.jpg图片文件
//        Credential credential = new Credential(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
//        KS3Client client = new KS3Client(credential);
//        String bucketName = "liulu1";
//        String publicPic = "123.png";
//        File file = new File("F:\\work/demo/image/test1.png");
//        //上传不同访问权限的图片(MIME与图片格式相对应)
//        //client.putObject(bucketName, privatePic, file, "image/png", AccessControlList.PRIVATE);
//        client.putObject(bucketName, publicPic, file, "image/png", AccessControlList.READONLY);
    }

    public void upload() throws Exception {
        Credential credential = new Credential(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        KS3Client client = new KS3Client(credential);
        String bucketName = "liulu1";
        String publicPic = "123.png";
        File file = new File("F:\\work/demo/image/test1.png");
        //上传不同访问权限的图片(MIME与图片格式相对应)
        //client.putObject(bucketName, privatePic, file, "image/png", AccessControlList.PRIVATE);
        client.putObject(bucketName, publicPic, file, "image/png", AccessControlList.READONLY);
    }


    /**
     * 根据dicom文件生成jpg图片
     * <p/>
     * 这里输入的是image文件夹的dicom文件名字，
     * 运行即可得到一个jpg图片，显示的是dicom里面的图形
     */
    private static void create(String fileName) {
        try {
            String projectPath = System.getProperty("user.dir");
            //Check class DICOM
            DICOM dicom = new DICOM();
            String imagePath = projectPath + "\\image\\" + fileName;
            dicom.run(imagePath);
            BufferedImage bi = (BufferedImage) dicom.getImage();
            int width = bi.getWidth();
            int height = dicom.getHeight();
            System.out.println("width: " + width + "\n" + "height: " + height);
            imagePath = projectPath + "\\image\\" + fileName + ".png";
            ImageIO.write(bi, "png", new File(imagePath));
            System.out.println("Hehe,Game over!!!");

        } catch (Exception e) {
            System.out.println("错误" + e.getMessage());
        }

    }


    /**
     * 输入一个dicom文件的绝对路径和名字
     * 获取一个jpg文件
     */
    private static void create2(String filePath) {
        try {
            DICOM dicom = new DICOM();
            dicom.run(filePath);
            BufferedImage bi = (BufferedImage) dicom.getImage();
            int width = bi.getWidth();
            int height = dicom.getHeight();
            System.out.println("width: " + width + "\n" + "height: " + height);
            String imagePath = filePath + ".jpg";
            ImageIO.write(bi, "jpg", new File(imagePath));
            System.out.println("Hehe,Game over!!!");

        } catch (Exception e) {
            System.out.println("错误" + e.getMessage());
        }

    }


}
