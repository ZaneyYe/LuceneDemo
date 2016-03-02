package demo.lucene.d1;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by yzy on 2016/2/1.
 */
public class Indexer {
     private IndexWriter indexWriter;

    //构造方法
     public Indexer(String indexStr) throws IOException {
         Directory dir = FSDirectory.open(Paths.get(indexStr));
         Analyzer analyzer = new StandardAnalyzer();
         IndexWriterConfig config = new IndexWriterConfig(analyzer);
         indexWriter = new IndexWriter(dir,config);
     }

    //关闭
    public void close() throws IOException {
        indexWriter.close();
    }

    public int indexDir(String dataDir,FileFilter fileFilter) throws IOException {
        File[] files = new File(dataDir).listFiles();
        for(File file : files){
            if(fileFilter.accept(file)){
                index(file);
            }
        }
        return indexWriter.numDocs();
    }

    public static class TextFileFilter implements FileFilter{
        public boolean accept(File pathname) {
            if(pathname.getName().endsWith(".txt")){
                return true;
            }
            return false;
        }
    }

    //索引文件
    public void index(File file) throws IOException {
        System.out.println("索引文件"+file.getCanonicalPath());
        Document doc = getDocument(file);
        indexWriter.addDocument(doc);
    }

    public Document getDocument(File file) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("content",new FileReader(file)));
        doc.add(new TextField("filename",file.getName(), Store.YES));
        doc.add(new TextField("fullPath",file.getCanonicalPath(),Store.YES));
        return doc;
    }
}
