package hudson.plugins.jacoco.git;

import java.io.File;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;

public class JGitUtil {
    public static String cloneRepository(String url,String localPath,String tag)
    {
        final File tagPathFile = new File(localPath);
        if (!tagPathFile.exists()) {
            try {

                CloneCommand cc = Git.cloneRepository().setURI(url).setBranch(tag);
                cc.setDirectory(new File(localPath)).call();


                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }else {


            return "当前clone版本已存在";
        }
    }
}
