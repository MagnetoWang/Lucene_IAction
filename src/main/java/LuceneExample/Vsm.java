package LuceneExample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * @see. 向量空间模型 来计算关键词和文档之间的相关度
 * 线性代数的简单模型
 * 词组的权重不是二元的
 * 文档和查询之间的相似度取值是连续的
 * 允许根据文档之间可能的相关性进行排序
 * 允许局部匹配
 *
 * 核心算法 ： cosA 的计算
 */
public class Vsm {
    public static double calCosSim(Map<String, Double> v1,
                                   Map<String, Double> v2){
        double sclar = 0.0,norm1=0.0,norm2=0.0,similarity=0.0;
        Set<String> v1Keys = v1.keySet();
        Set<String> v2Keys = v2.keySet();
        Set<String> both= new HashSet<>();
        both.addAll(v1Keys);
        both.retainAll(v2Keys);
        System.out.println("两个文档之间的交集");
        System.out.println(both);
//        sclar是点乘然后求和
        for (String str1 : both) {
            sclar += v1.get(str1) * v2.get(str1);
        }
        for (String str1:v1.keySet()){
            norm1+=Math.pow(v1.get(str1),2);
        }
        for (String str2:v2.keySet()){
            norm2+=Math.pow(v2.get(str2),2);
        }
        //感觉计算有些问题 10：22

        //没有问题 10：23
        similarity=sclar/Math.sqrt(norm1*norm2);

        System.out.println("sclar:"+sclar);
//        文档1的范式距离
        System.out.println("norm1:" + norm1);
//        文档2的范式距离
        System.out.println("norm2:" + norm2);


//        similarity=sclar*norm2/Math.sqrt(sclar*norm2);
        System.out.println("similarity:"+similarity);
//        System.out.println("similarity2:"+similarity);
        return similarity;
    }
    public static void main(String[] args) {
        Map<String, Double> m1 = new HashMap<>();
        m1.put("Hello", 1.0);
        m1.put("css", 2.0);
        m1.put("Lucene", 3.0);

        Map<String, Double> m2 = new HashMap<>();
        m2.put("Hello", 1.0);
        m2.put("Word", 2.0);
        m2.put("Hadoop", 3.0);
        m2.put("java", 4.0);
        m2.put("html", 1.0);
        m2.put("css", 2.0);
        calCosSim(m1, m2);
    }
}

