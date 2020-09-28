//source : https://www.youtube.com/watch?v=tNFoWyvTF8U

/** just for test for IFT3913

hello
this is test




*/


public class IndexFiles {
    static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            try {
                                indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                            } catch (IOException ignore) {

                            }
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        } else {
            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }

    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
        try (InputStream stream = Files.newInputStream(file)) {
            Document doc = new Document();
            Field pathField = new StringField("path", file.toString(), Field.Store.YES);
            doc.add(pathField);
            doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));

            if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                System.out.println("adding " + file);
                writer.addDocument(doc);
            } else {
                System.out.println("updating " + file);
                writer.updateDocument(new Term("path", file.toString()), doc);

            }


        }

    }


    public static void count() {
        try {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index/")));
            int numTerms = 1000;
            TermStats[] stats = HighFreqTerms.getHighFreqTerms(reader, numTerms, "contents", new HighFreqTerms.DocFreqComparator());
            for (TermStats termStats : stats) {
                String termText = termStats.termtext.utf8ToString();
                System.out.println(termText + " " + termStats.docFreq);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String indexPath = "index/";
        String docsPath = "input/";
        boolean create = true;


        final Path docDir = Paths.get(docsPath);
        Date start = new Date();
        try {
            System.out.println("Indexing to directory '" + indexPath + "'...");

            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            if (create) {
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

            } else {
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            }

            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(writer, docDir);

            writer.close();

            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + "total milliseconds");


        } catch (IOException e) {

        }

        count();

    }







}