
import java.io.*;


public class FileOperate
{
    private static final byte[] KEYVALUE = "fffff".getBytes();
    private static final int BUFFERLEN = 512;
//删除文件
    public static boolean DelFile(String path)
    {
        File file = new File(path);
        if (file.isFile() && file.exists())
        {
            file.delete();
            //System.out.println("删除成功!");
            return true;
        }
        else
        {
            //System.out.println("删除失败!");
            return false;
        }
    }


//复制文件
    public static boolean CopyFile(String oldPath , String newPath)
    {
        try
        {
            InputStream in=new FileInputStream(oldPath);
            OutputStream out=new FileOutputStream(newPath);
            IOOperation.copy(in, out);
            in.close();
            out.close();
            //System.out.print("操作成功!");
            return true;
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //System.out.print("操作失败!");
            return false;
        }
    }
//加密文件
    public static boolean Encrypt(String path) throws IOException
    {
        try
        {
            String newPath = path + ".txt";
            File ifile = new File(path);
            File ofile = new File(newPath);
            if (!ifile.isFile() || !ifile.exists())
            {
                System.out.println("文件不存在或不是文件!");
                return false;
            }
            if (!ofile.isFile() || !ofile.exists())
            {
                ofile.createNewFile();
            }
            FileInputStream in = new FileInputStream(path);
            FileOutputStream out = new FileOutputStream(ofile);
            int c, pos=0, keylen;
            keylen = KEYVALUE.length;
            byte buffer[] = new byte[BUFFERLEN];
            while ((c = in.read(buffer)) != -1)
            {
                for (int i = 0; i < c; i++)
                {
                    buffer[i] ^= KEYVALUE[pos];
                    out.write(buffer[i]);
                    pos++;
                    if (pos == keylen)
                        pos = 0;
                }
            }
            in.close();
            out.close();
            DelFile(path);
            ofile.renameTo(new File(path));
            return true;
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
//解密文件
    public static boolean Decrypt(String path) throws IOException
    {
        try
        {
            String newPath = path + ".txt";
            File ifile = new File(path);
            File ofile = new File(newPath);
            if (!ifile.isFile() || !ifile.exists())
            {
                System.out.println("文件不存在或不是文件!");
                return false;
            }
            if (!ofile.isFile() || !ofile.exists())
            {
                ofile.createNewFile();
            }
            FileInputStream in = new FileInputStream(path);
            FileOutputStream out = new FileOutputStream(ofile);
            int c, pos, keylen;
            pos = 0;
            keylen = KEYVALUE.length;
            byte buffer[] = new byte[BUFFERLEN];
            while ((c = in.read(buffer)) != -1)
            {
                for (int i = 0; i < c; i++)
                {
                    buffer[i] ^= KEYVALUE[pos];
                    out.write(buffer[i]);
                    pos++;
                    if (pos == keylen)
                        pos = 0;
                }
            }
            in.close();
            out.close();
            DelFile(path);
            ofile.renameTo(new File(path));
            return true;
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }



}
