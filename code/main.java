
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        String org=args[0];
        String add=args[1];
        String ans=args[2];
        ArrayList<String> aor = new ArrayList<>();
        ArrayList<String> aad = new ArrayList<>();
        try (
            FileReader reader1 = new FileReader(org);
            BufferedReader br = new BufferedReader(reader1);
            ){
            String line;
            while ((line = br.readLine())!=null ){
                String[] or = line.split("");
                for(int i=0;i< or.length;i++){
                    aor.add(or[i]);
                }
            }

        } catch (IOException e) {
                    e.printStackTrace();
                }
        try (
                FileReader reader2 = new FileReader(add);
                BufferedReader br = new BufferedReader(reader2);
        ){
            String line;
            while ((line = br.readLine())!=null ){
                String[] ad = line.split("");
                for(int i=0;i< ad.length;i++){
                    aad.add(ad[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i< aor.size();i++)
            System.out.println(aor.get(i));
        for (int i=0;i< aad.size();i++)
            System.out.println(aad.get(i));
        int[][] num=new int[aor.size()+1][aad.size()+1];


        for(int i=0;i<=aor.size();i++){
            for (int j=0;j<=aad.size();j++){
                if (i==0||j==0) num[i][j]=0;
                else if(aor.get(i-1).equals(aad.get(j-1))){
                    num[i][j]=num[i-1][j-1]+1;
                }
                else num[i][j]=(num[i][j-1]>num[i-1][j])?num[i][j-1]:num[i-1][j];
            }
        }
        System.out.println(num[aor.size()][aad.size()]+ aor.size());
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
}