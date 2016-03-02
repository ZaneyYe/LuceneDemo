package demo.lucene.d1;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by yzy on 2016/2/1.
 */
public class Reader {

    public static void search(String indexDir,String s) throws IOException, ParseException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        QueryParser parser = new QueryParser("content",new StandardAnalyzer());
        Query query = parser.parse(s);
        TopDocs topDocs = is.search(query, 10);
        for(ScoreDoc scoreDoc : topDocs.scoreDocs){
            Document doc = is.doc(scoreDoc.doc);
            System.out.println(doc.get("filename"));
            System.out.println(scoreDoc.score);
        }
//        is.search();
    }
}
