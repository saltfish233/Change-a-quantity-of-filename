package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import  java.lang.Class;

class Task{
    public void changeFileName(String address,int start){
        File file=new File(address);
        if(!file.exists())
        {
            System.out.println("err");
            System.exit(0);
        }
        File[] files=file.listFiles();
        if(files.length==0)
            {
                System.out.println("该目录下没有文件，请检查是否地址是否输入正确");
                System.exit(0);
            }
        // 用ArrayList操作起来比较自由
        ArrayList<File> file_array=new ArrayList<File>();
        ArrayList<String> file_name=new ArrayList<String>();
        for(int i=0;i<files.length;i++){
            file_array.add(files[i]);
            file_name.add(file_array.get(i).getName());
        }
        for(int i=0;i<file_array.size();i++){
            String filename=file_array.get(i).getName();
            //设定新文件名并避免同名冲突
            String newfilename=start+"";
            String suffix="";
            if(filename.contains('.'+""))
            {
                    suffix=filename.substring(filename.indexOf('.'),filename.length());
                    newfilename=start+suffix;
                }
            while(file_name.contains(newfilename)) {
                int pos = file_name.indexOf(newfilename);
                file_array.remove(pos);
                file_name.remove(pos);
                start++;
                newfilename = start + suffix;
            }
            start++;
            try{
                File newfile=new File(address+"\\"+newfilename);
                file_array.get(i).renameTo(newfile);
                }
            catch (Exception err)
            {
                err.printStackTrace();
                System.exit(0);
            }
        }
    }

}

public class change {

    public static void main(String args[])
    {
        System.out.println("请输入文件路径(绝对路径)");
        Scanner input=new Scanner(System.in);
        String address=input.next();
        System.out.println(address);
        System.out.println("从数字几开始命名:");
        int start=input.nextInt();
        Task task =new Task();
        task.changeFileName(address,start);
    }
}
