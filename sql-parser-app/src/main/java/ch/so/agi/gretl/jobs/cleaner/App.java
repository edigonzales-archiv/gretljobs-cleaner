package ch.so.agi.gretl.jobs.cleaner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class App {

    public static void main(String[] args) {
        String repositoryUrl = "https://github.com/sogis/gretljobs";
        String localRepositoryDirectory = "./gretljobs";

        try {
            /*
            // Clone gretljobs repository.
            FileUtils.deleteDirectory(new File(localRepositoryDirectory));
            Git git = Git.cloneRepository()
                    .setURI(repositoryUrl)
                    .setDirectory( new File(localRepositoryDirectory) )
                    .call();
            */
            // Find all *.sql files in cloned repository.
            String[] extensions = {"sql", "SQL"};
            Collection<File> sqlFilesList = FileUtils.listFiles(new File(localRepositoryDirectory), extensions, true);
            
            // Parse each sql file.
            for (File sqlFile : sqlFilesList) {
                System.out.println(sqlFile.getAbsolutePath());
                String fileContent = FileUtils.readFileToString(sqlFile, Charset.defaultCharset());
                
                JSqlParser jsqlParser = new JSqlParser();
                List tableList = jsqlParser.getTableList(fileContent);
                System.out.println(tableList);
                

                
                
            }

            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        System.out.println("Hallo Welt.");
    }

}
