package demo.lucene.d2;

import java.io.IOException;

/**
 * Created by yzy on 2016/3/2.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Index index = new Index("E:\\data\\index");
        index.index("E:\\data\\books");
    }
}
