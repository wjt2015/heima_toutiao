package toutiao.dao;

import com.google.common.collect.Lists;
import jdk.nashorn.internal.objects.Global;
import lombok.extern.slf4j.Slf4j;
import org.csource.client.FdfsClient;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ArticleHomeMapperTest {


/*    @Test
    public void upload() throws IOException, MyException {
        final String configFileName="classpath:fastdfs/fastdfs2/fdfs_client.conf";
        ClientGlobal.init(configFileName);

        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer=trackerClient.getTrackerServer();
        log.info("trackerClient={};trackerServer={};",trackerClient,trackerServer);
        StorageServer storageServer=null;
        StorageClient storageClient=new StorageClient(trackerServer,storageServer);
        //storageClient.upload_file()
    }*/


    private FdfsClient fdfsClient;

    //@Before
    public void init() {
        String fileName = "classpath:fastdfs/fastdfs-client.properties";

        fileName = "classpath:fastdfs/fdfs_client.conf";
        fdfsClient = new FdfsClient(fileName);
        log.info("fdfsClient={};", fdfsClient);
    }

    @Test
    public void upload() {
        String groupName = "A", uploadFileName = "/Users/jintao9/linux2014/books/从零开始搭建瓜子IM系_滴滴技术沙龙第7期.pdf", fileId = "";

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(uploadFileName))) {
            fileId = fdfsClient.upload(groupName, inputStream, uploadFileName);
        } catch (Exception e) {
            log.error("upload error!uploadFileName={};", uploadFileName, e);
        }

        log.info("fileId={};", fileId);

        String downloadFileName = "/Users/jintao9/linux2014/wjt_projs/heima_toutiao/docs/logs/瓜子IM.pdf";

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFileName))) {

            fdfsClient.download(fileId, outputStream);
        } catch (Exception e) {
            log.info("download error!", e);
        }

        log.info("upload,download finish!");
    }

    /**
     * fdfs_test /Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/etc/fdfs/client.conf upload a.mp3
     * http://10.222.100.10/group1/M00/00/00/Ct5kCmAHsEiAF_JsAHTu3cheAxQ902_big.mp3
     */
    @Test
    public void upload2() throws InterruptedException, IOException {
        String fileName = "/Users/jintao9/linux2014/wjt_projs/heima_toutiao/docs/淘宝技术这十年.pdf";
        String cmd="upload";
        List<String> cmdList = Lists.newArrayList("/Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/usr/local/bin/fdfs_test", "/Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/etc/fdfs/client.conf");

        List<String> uploadCmdList=Lists.newArrayList(cmdList);
        uploadCmdList.add(cmd);
        uploadCmdList.add(fileName);
        ProcessBuilder processBuilder = new ProcessBuilder().command(uploadCmdList);
        Process process = processBuilder.start();
        InputStream processInputStream = process.getInputStream();
        int waitFor = process.waitFor();
        List<String> result = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processInputStream))) {

            result = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            log.error("upload error!", e);
        }

        log.info("waitFor={};result={};", waitFor, result);




    }


}