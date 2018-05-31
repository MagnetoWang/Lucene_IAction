package Tools;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * @see. 文件的相关操作
 *
 */
public class FileTools {
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @see. 找出指定目录下的指定后缀名文件。并把地址存入一个列表
     * @param. 文件夹名，后缀类型
     */
    public static List<String> fileList=new LinkedList<>();
    public static List<String> findFile(String Catalog, String Suffixtype){
        //fileList=new LinkedList<>();
        File file=new File(Catalog);
        String[] files =null;
        if(file.isDirectory()==true){
             files=file.list();
        }else{
            if(Catalog.substring(Catalog.length()-3,Catalog.length()).equals(Suffixtype)==true){
                System.out.println(Catalog);
                fileList.add(Catalog);
            }
            return fileList;
        }

        for(String e:files){
            //System.out.println(e);

            e=Catalog+"/"+e;
            file=new File(e);
            if(file.isDirectory()==false&&e.substring(e.length()-3,e.length()).equals(Suffixtype)==true){
                System.out.println(e);
                fileList.add(e);
            }else{
                findFile(e,Suffixtype);
            }
        }
        return fileList;
    }

    /***
     * @see . 复制文件到指定目录
     * @param. args
     */
    public static void CopyFile(String src,String dst) throws IOException {
        File source =new File(src) ;
        File dest = new File(dst);
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }

    /**
     * 输入路径列表，复制到指定位置
     * @param dst
     */
    public static  void CopyFiles(List<String> src,String dst) throws IOException {
        for(String e :src){
            String[] temp=e.split("/");
            String filename=temp[temp.length-1];
            System.out.println(e);
            //System.out.println(filename);
            String Temp=dst;
            Temp=dst+"/"+filename;
            System.out.println(Temp);
            CopyFile(e,Temp);
        }
    }
    public static void main(String[] args) throws IOException {
        String catalog="D:/EnglishPath/lucene-7.3.1";
        String suffix="jar";
        findFile(catalog,suffix);
        CopyFiles(fileList,"D:/EnglishPath/JAR/LuceneAllJAR");
        //CopyFile("D:/EnglishPath/lucene-7.3.1/analysis/common/lucene-analyzers-common-7.3.1.jar","D:/EnglishPath/JAR/lucene-analyzers-common-7.3.1.jar");
    }
}


/***
 D:/EnglishPath/lucene-7.3.1/analysis/common/lucene-analyzers-common-7.3.1.jar
 D:/EnglishPath/lucene-7.3.1/analysis/icu/lib/icu4j-59.1.jar
 D:/EnglishPath/lucene-7.3.1/analysis/icu/lucene-analyzers-icu-7.3.1.jar
 D:/EnglishPath/lucene-7.3.1/analysis/kuromoji/lucene-analyzers-kuromoji-7.3.1.jar
 D:/EnglishPath/lucene-7.3.1/analysis/morfologik/lib/morfologik-fsa-2.1.1.jar
 D:/EnglishPath/lucene-7.3.1/analysis/morfologik/lib/morfologik-polish-2.1.1.jar
 */