package toutiao.common;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Usage: fdfs_test <config_file> <operation>
 * operation: upload, download, getmeta, setmeta, delete and query_servers
 */
@Slf4j
public class FastDfsClient {

    private final List<String> CMD_LIST = Lists.newArrayList();
    private static final String GROUP_NAME_PRFIX = "group_name=";
    private static final String REMOTE_FILENAME_PRFIX = "remote_filename=";
    private static final String URL_PREFIX = "url:";

    public FastDfsClient(final String fullExecFileName, final String clientConfFileName) {
        CMD_LIST.add(fullExecFileName);
        CMD_LIST.add(clientConfFileName);
    }

    /**
     * @param fileName
     * @param timeout
     * @return 返回的0号元素是groupName, 1号元素是remoteFileName,2号元素是url;
     * @throws IOException
     * @throws InterruptedException
     */
    public String[] upload(final String fileName, final long timeout) throws IOException, InterruptedException {
        final List<String> allCmdList = Lists.newArrayList(CMD_LIST);
        allCmdList.add("upload");
        allCmdList.add(fileName);
        Process process = new ProcessBuilder(allCmdList).start();
        List<String> result = AuxUtils.processResult(process);

        Object ret = timeout > 0 ? process.waitFor(timeout, TimeUnit.MILLISECONDS) : process.waitFor();
        log.info("ret={};", ret);

        int exitCode = process.exitValue();
        log.info("exitCode={};result={};", exitCode, result);
        if (exitCode != 0) {
            return null;
        }
        //查找group_name;
        String groupName = null, remoteFileName = null, urlStr = null;
        List<String> stringList = null;
        for (String s : result) {
            stringList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(s);
            if (stringList != null && stringList.size() > 0) {
                for (String kv : stringList) {
                    if (groupName == null && kv.contains(GROUP_NAME_PRFIX) && kv.length() > GROUP_NAME_PRFIX.length()) {
                        stringList = Splitter.on("=").omitEmptyStrings().trimResults().splitToList(kv);
                        if (stringList.size() == 2) {
                            groupName = stringList.get(1);
                        }
                    } else if (remoteFileName == null && kv.contains(REMOTE_FILENAME_PRFIX) && kv.length() > REMOTE_FILENAME_PRFIX.length()) {
                        stringList = Splitter.on("=").omitEmptyStrings().trimResults().splitToList(kv);
                        if (stringList.size() == 2) {
                            remoteFileName = stringList.get(1);
                        }
                    } else if (urlStr == null && kv.contains(URL_PREFIX) && kv.length() > URL_PREFIX.length()) {
                        stringList = Splitter.on(URL_PREFIX).omitEmptyStrings().trimResults().splitToList(kv);
                        if (stringList.size() == 2) {
                            urlStr = stringList.get(1);
                        }
                    }
                }
            }

        }
        return new String[]{groupName, remoteFileName, urlStr};

    }

    /**
     * fdfs_test <config_file> download <group_name> <remote_filename>
     *
     * @param groupName
     * @param remoteFileName
     * @param timeout
     * @return
     */
    public int download(final String groupName, final String remoteFileName, final long timeout) throws Exception {
        final List<String> allCmdList = Lists.newArrayList(CMD_LIST);
        allCmdList.add("download");
        allCmdList.add(groupName);
        allCmdList.add(remoteFileName);
        Process process = new ProcessBuilder().command(allCmdList).start();

        AuxUtils.processResult(process);

        Object ret = timeout > 0 ? process.waitFor(timeout, TimeUnit.MILLISECONDS) : process.waitFor();
        log.info("ret={};", ret);
        return process.exitValue();
    }

    //fdfs_test <config_file> getmeta <group_name> <remote_filename>
//fdfs_test <config_file> delete <group_name> <remote_filename>

    public int delete(final String groupName, final String remoteFileName, final long timeout) throws Exception {
        final List<String> allCmdList = Lists.newArrayList(CMD_LIST);
        allCmdList.add("delete");
        allCmdList.add(groupName);
        allCmdList.add(remoteFileName);
        Process process = new ProcessBuilder().command(allCmdList).start();

        AuxUtils.processResult(process);

        Object ret = timeout > 0 ? process.waitFor(timeout, TimeUnit.MILLISECONDS) : process.waitFor();
        log.info("ret={};", ret);
        return process.exitValue();
    }

}
