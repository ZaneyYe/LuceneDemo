package demo.lucene.d1;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

/**
 * Created by yzy on 2016/2/1.
 */
public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String indexDir = "E:\\data\\index";
//        String dataDir = "E:\\data\\txt";
//        long start = System.currentTimeMillis(); //开始时间
//        Indexer indexer = new Indexer(indexDir);
//        indexer.indexDir(dataDir,new TextFileFilter());
//        indexer.close();
//        long end = System.currentTimeMillis(); //结束时间
//        System.out.println("创建索引,消耗时间"+(end - start)+"毫秒！");

        Reader.search(indexDir,"MongoDB");
    }
}
