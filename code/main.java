

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
//        三个变量接收文件地址参数
        String org=new String();
        String add=new String();
        String ans=new String();
        try{


//            org="E:\\note\\org.txt";        //测试用
//            add="E:\\note\\add.txt";
//            ans="E:\\note\\ans.txt";
        org=args[0];
        add=args[1];
        ans=args[2];
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("命令行输入文件位置错误");
            System.exit(0);
        }
        ArrayList<String> aor = new ArrayList<>();
        ArrayList<String> aad = new ArrayList<>();

        write(org,aor);                                              //   调用自己写的write方法，读进两篇相似文章
        write(add,aad);

        for (int i=0;i< aor.size();i++)                             //展示文章内容
            System.out.print(aor.get(i));
        System.out.println();
        for (int i=0;i< aad.size();i++)
            System.out.print(aad.get(i));
        System.out.println();

        int[][] num=new int[aor.size()+1][aad.size()+1];            //核心算法的代码
        for(int i=0;i<=aor.size();i++){
            for (int j=0;j<=aad.size();j++){
                if (i==0||j==0) num[i][j]=0;
                else if(aor.get(i-1).equals(aad.get(j-1))){
                    num[i][j]=num[i-1][j-1]+1;
                }
                else num[i][j]=(num[i][j-1]>num[i-1][j])?num[i][j-1]:num[i-1][j];
            }
        }

        System.out.println(num[aor.size()][aad.size()]+ aor.size());          //计算查重率并输出到指定文件位置
        float rate1=(float)num[aor.size()][aad.size()]/aor.size()*100;
        BigDecimal b=new BigDecimal(rate1);
        float rate=b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
        System.out.println(rate);
        try (
                FileWriter w = new FileWriter(ans);
        ){
            w.write("repetition rate: "+Float.toString(rate)+"%");
            w.flush();
            w.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void write(String txt , ArrayList<String> tx){
        try (
                FileReader reader1 = new FileReader(txt);
                BufferedReader br = new BufferedReader(reader1);
        ){
            String line;
            while ((line = br.readLine())!=null ){
                String[] or = line.split("");
                for(int i=0;i< or.length;i++){
                    tx.add(or[i]);
                }
            }

        } catch (IOException|NoClassDefFoundError e) {
            System.out.println("读文件出错，请检查文件地址是否正确");

        }
    }
}
