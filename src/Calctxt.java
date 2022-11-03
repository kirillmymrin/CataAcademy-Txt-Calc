import java.util.Scanner;

public class Calctxt {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        String firstStr = getString(exp);
        if (firstStr.equals("")) {
            throw new Exception("Первый аргумент не строка!");
        }
        if (firstStr.length() > 12){
            throw new Exception("Не верная длинна первой строки!");
        }
        exp = exp.substring(firstStr.length());
        String sign = getSign(exp);
        if (sign.equals("")){
            throw new Exception("Пришел не верный знак!!!");

        }
        exp = exp.substring(sign.length());
        if (sign.equals(" + ")||sign.equals(" - ")){
            String res = doStringOperation(firstStr,sign,exp);
            if (res.length() > 42){
                res = res.substring(0,41);
                res = res + "...\"";
            }
            System.out.println(res);
        } else {
            String res = doNumOperation(firstStr,sign,exp);
            if (res.length() > 42) {
                res = res.substring(0, 41);
                res = res + "...\"";
            }
            System.out.println(res);
        }
    }

    static String printInq(String text) {
        return ("\"" + text + "\"");
    }

    static public String getString(String exp) {
        if (exp.charAt(0) != '"') {
            return "";
        }
        int indexEnd = 0;
        for (int i = 1; i < exp.length(); i++) {
            if (exp.charAt(i) == '"'){
                indexEnd = i;
                break;
            }
        }
        if (indexEnd == 0) {
            return "";
        }
        return exp.substring(0, indexEnd + 1);
    }

    static public String getSign(String exp) {
        if (exp.charAt(0) != ' ') {
            return "";
        }
        if (exp.charAt(1)!= '+' && exp.charAt(1) != '-' && exp.charAt(1) != '*' && exp.charAt(1) != '/'){
            return "";
        }
        if (exp.charAt(2) !=  ' '){
            return "";
        }
        return exp.substring(0,3);
    }

    static public String doStringOperation (String firstArgument,String sign,String secondArgument) throws Exception {
        secondArgument = getString(secondArgument);
        if (secondArgument.equals("")){
            throw new Exception("Второй аргумент не строка");
        }
        if (secondArgument.length() > 12){
            throw new Exception("Не верная длинная второй строки");
        }
        firstArgument = firstArgument.replace("\"","");
        secondArgument = secondArgument.replace("\"","");
        if (sign.equals(" + ")){
            return printInq(firstArgument + secondArgument);
        }else {
            return printInq(firstArgument.replaceFirst(secondArgument, ""));
        }
    }

    static public String doNumOperation (String firstArgument,String sign,String secondArgument ) throws Exception {
        try {
           int num = Integer.parseInt(secondArgument);
           if (!(num >= 1 && num <= 10)) {
               throw new Exception();
           }
           firstArgument = firstArgument.replace("\"","");
           if (sign.equals(" * ")){
               String res = "";
               for (int i = 0; i < num; i++) {
                   res = firstArgument + res;
               }
               return printInq(res);
           } else {
               int l = firstArgument.length();
               int end = l / num;
               if (end == 0){
                   return printInq("");
               }
               String res = firstArgument.substring(0,end);
               return printInq(res);
           }
        } catch (Exception e) {
            throw new Exception("Второй аргумент плохое число");
        }

    }

}


