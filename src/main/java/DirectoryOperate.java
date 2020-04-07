
import java.io.*;

public class DirectoryOperate
{

    private static boolean flag;

    public static boolean CreatDir(String path)
    {
        File dir = new File(path);
        if(dir.exists())
        {
            System.out.println("创建失败，该目录已存在!");
            return false;
        }
        if(!path.endsWith(File.separator))
        {
            path = path + File.separator;
        }

        if(dir.mkdirs())
        {
            System.out.println("创建目录成功！");
            return true;
        }
        else
        {
            System.out.println("创建失败!");
            return false;
        }
    }

    public static boolean DeleteDir(String path)
    {
        if (!path.endsWith(File.separator))
        {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory())
        {
            System.out.println("删除失败!目标文件夹不存在或目标非文件夹!");
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            //删除子文件
            if (files[i].isFile())
            {
                flag = FileOperate.DelFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } //删除子目录
            else
            {
                flag = DeleteDir(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        //删除当前目录
        if (dirFile.delete())
        {
            System.out.println("删除成功!");
            return true;
        }
        else
        {
            System.out.println("删除失败!");
            return false;
        }
    }
    public static boolean ViewDir(String path) throws IOException
    {
		try
		{
			File vDir = new File(path);
			if (!vDir.exists() || !vDir.isDirectory())
			{
				System.out.println("读取失败!不存在该文件夹!");
			    return false;
			}
			//flag = true;
			File[] files = vDir.listFiles();
			for (int i = 0; i < files.length; i++)
			{
			    // 复制文件
			    if (files[i].isFile())
			    {
			    	System.out.print("文件");
			    	System.out.println(files[i].getCanonicalPath());
			    }
			    else if (files[i].isDirectory())
			    {
			    	System.out.print("文件夹");
			    	System.out.println(files[i].getCanonicalPath());
			    	ViewDir(files[i].getAbsolutePath());
			    }
			}
			return true;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}



    public static boolean CopyDir(String oldPath , String newPath)
    {
        File srcDir = new File(oldPath);
        if (!srcDir.exists() || !srcDir.isDirectory())
        {
            System.out.println("复制失败!目标文件夹不存在或目标非文件夹!");
            return false;
        }

        if (!newPath.endsWith(File.separator))
        {
            newPath = newPath + File.separator;
        }
        newPath +=  File.separator + srcDir.getName();
        // 创建目的目录
        CreatDir(newPath);
        flag = true;
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            // 复制文件
            if (files[i].isFile())
            {
                flag = FileOperate.CopyFile(files[i].getAbsolutePath(),newPath + File.separator + files[i].getName());
                if (!flag)
                    break;
            }
            else if (files[i].isDirectory())
            {
                flag =CopyDir(files[i].getAbsolutePath(),newPath + files[i].getName());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        else
            return true;
    }




}
