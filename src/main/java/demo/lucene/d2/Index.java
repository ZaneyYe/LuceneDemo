package demo.lucene.d2;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by yzy on 2016/3/2.
 */
public class Index {

    private IndexWriter indexWriter;

    public Index(String indexDir) throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        indexWriter = new IndexWriter(dir,config);
    }

    public void close() throws IOException {
        indexWriter.close();
    }

    public int index(String dataDir) throws IOException {
       return index(dataDir,new MyFileFilter());
    }

    public int index(String dataDir,FileFilter fileFilter) throws IOException {
        File[] files = new File(dataDir).listFiles();
        for(File file : files){
            if(fileFilter.accept(file)){
                index(file);
            }
        }
        return indexWriter.numDocs();
    }

    public class MyFileFilter implements FileFilter{
        public boolean accept(File file) {
            if(file.getName().contains("java"))
                return true;
            return false;
        }
    }

    private void index(File file) throws IOException {
       Document document = getDocument(file);
       indexWriter.addDocument(document);
    }

    private Document getDocument(File file) throws FileNotFoundException {
        Document doc = new Document();
        doc.add(new TextField("content",new FileReader(file)));
        doc.add(new TextField("title",file.getName(), Store.YES));
        return doc;
    }
}
