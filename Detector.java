import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Detector {
    List<String> keywords = new ArrayList<>();
    Detector()
    {

        keywords.add("system.out.println(");
        keywords.add("main");
        keywords.add("int");
        keywords.add("implements");
        keywords.add("switch");
        keywords.add("class");
        keywords.add("string");
        keywords.add("import");
        keywords.add("#include");
        keywords.add("print(");
        keywords.add("return");
        keywords.add("def");
        keywords.add("break");
        keywords.add("lambda");
        keywords.add("cout");
        keywords.add("cin");
        keywords.add("elif");
        keywords.add("=");
        keywords.add("()");
        keywords.add("{");
        keywords.add("}");
        keywords.add("for(");
        keywords.add("range(");
        keywords.add("):");
        keywords.add("__main__");
        keywords.add("catch(");
        keywords.add("#");
        keywords.add("//");
        keywords.add("/*");
        keywords.add("*/");
        keywords.add("(");
        keywords.add(")");
    }
    private String readFile(String s) throws FileNotFoundException {
        File f1 = new File(s);
        StringBuilder content = new StringBuilder();
        Scanner sc = new Scanner(f1);
        while (sc.hasNextLine()) content.append(sc.nextLine());
        sc.close();
        return content.toString();
    }
    private Boolean checkType(String text)
    {
        int count =0;
        for(String keyword : keywords)
        {
            if(text.contains(keyword)) count++;
        }
        return count > 5;
    }
    private HashMap<String, Integer> getWords(String s1)
    {
        String[] words = s1.split(" ");
        HashMap<String,Integer> word_count = new HashMap<String,Integer>();
        for(String s : words)
        {
            if(word_count.containsKey(s)) word_count.put(s,word_count.get(s)+1);
            else word_count.put(s.toLowerCase(),1);
        }
        return word_count;
    }
    private double getCommonCount(HashMap<String,Integer> h1,HashMap<String,Integer> h2)
    {
        int count =0;
            for (Map.Entry<String, Integer> word : h2.entrySet()) {
                String s = word.getKey();
                if(h1.containsKey(s))
                {
                    count = count + s.length() * Math.min(h1.get(s),h2.get(s));
                }
            }
        return count;
    }
    private String normaliseContent(String content) {
        for(String k: keywords)
        {
            if(content.contains(k))
            {
                content = content.replace(k,"");
            }
        }
        return content;
    }
    private int checkplag(String file1, String file2)
    {
        String content1="";
        String content2="";
        try {
            content1 = readFile(file1);
            content2 = readFile(file2);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Exception occurred!! \n " + e);
        }
        Boolean isSourceCode = checkType(content1);
        if(isSourceCode)
        {
            content1 = normaliseContent(content1);
            content2 = normaliseContent(content2);
        }
        HashMap<String,Integer> words1 = getWords(content1);
        HashMap<String,Integer> words2 = getWords(content2);
        int minLength = Math.min(content1.length(), content2.length());
        double commonLength = getCommonCount(words1,words2);
        return commonLength/minLength>=0.40 ? 1:0;
    }

    public static void main(String[] args) {
        Detector d = new Detector();
        String file1 = args[0];
        String file2 = args[1];
        System.out.println(d.checkplag(file1, file2));
    }
}