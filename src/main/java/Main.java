import java.io.IOException;
import java.util.*;

public class Main
{
    private static boolean Flag;
    private static String InDir = "";

    private static void Judge()
    {
        if(Flag == true)
            System.out.println("操作成功!");
        else
            System.out.println("操作失败!");
    }

    private static void Sure()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("是否继续进行操作，Y or N ?:");
        String sure = sc.next();
        if(sure.equals("N") || sure.equals("n"))
        {
            System.out.println("退出程序!");
            System.exit(0);
        }
        else if(sure.equals("Y") || sure.equals("y"))
            ;
        else
        {
            System.out.println("未识别，请重新操作!");
            Sure();
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        // TODO Auto-generated method stub
        int i = 0;
        while(true)
        {
            System.out.println("请选择想要进行的操作:");
            System.out.println("1.创建文件夹:");
            System.out.println("2.删除文件夹:");
            System.out.println("3.当前文件夹下的内容罗列:");
            System.out.println("4.文件拷贝:");
            System.out.println("5.文件夹拷贝:");
            System.out.println("6.加密文件:");
            System.out.println("7.解密文件:");
            System.out.println("8.退出程序:");
            System.out.print("选择操作:");
            i = sc.nextInt();
            switch(i)
            {
                case 1:
                    System.out.println("输入要创建的目录:");
                    String CDir = InDir + sc.next();
                    Flag = DirectoryOperate.CreatDir(CDir);
                    Judge();
                    Sure();
                    continue;
                case 2:
                    System.out.println("输入要删除的目录:");
                    String DDir = InDir + sc.next();
                    Flag = DirectoryOperate.DeleteDir(DDir);
                    Judge();
                    Sure();
                    continue;
                case 3:
                    System.out.println("输入文件夹路径:");
                    String ViewDir = InDir + sc.next();
                    Flag = DirectoryOperate.ViewDir(ViewDir);
                    Judge();
                    Sure();
                    continue;

                case 4:
                    System.out.println("输入源文件路径:");
                    String SourceFile = InDir + sc.next();
                    System.out.println("输入目标文件路径:");
                    String TargetFile = InDir + sc.next();
                    Flag = FileOperate.CopyFile(SourceFile, TargetFile);
                    Judge();
                    Sure();
                    continue;

                case 5:
                    System.out.println("输入源文件夹路径:");
                    String SourceDir = InDir + sc.next();
                    System.out.println("输入目标文件夹路径:");
                    String TargetDir = InDir + sc.next();
                    Flag = DirectoryOperate.CopyDir(SourceDir, TargetDir);
                    Judge();
                    Sure();
                    continue;

                case 6:
                    System.out.println("输入加密文件路径:");
                    String EnFile = InDir + sc.next();
                    Flag = FileOperate.Encrypt(EnFile);
                    Judge();
                    Sure();
                    continue;
                case 7:
                    System.out.println("输入解密文件路径:");
                    String DeFile = InDir + sc.next();
                    Flag = FileOperate.Decrypt(DeFile);
                    Judge();
                    Sure();
                    continue;

                case 14:
                    System.out.println("退出程序!");
                    System.exit(0);
            }
        }
    }

}
