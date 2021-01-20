package toutiao.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

@Slf4j
public class FastDfsClientTest {

    private FastDfsClient fastDfsClient;

    @Before
    public void init() {
        final String fullExeFileName = "/Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/usr/local/bin/fdfs_test";
        final String clientConfFileName = "/Users/jintao9/linux2014/install_dir/fastdfs_all/fastdfs/etc/fdfs/client.conf";
        fastDfsClient = new FastDfsClient(fullExeFileName, clientConfFileName);
        log.info("fastDfsClient={};", fastDfsClient);
    }

    @Test
    public void upload() throws IOException, InterruptedException {
        String fileName = "/Users/jintao9/linux2014/wjt_projs/heima_toutiao/docs/淘宝网的架构演进和变化4.pdf";
        String[] result = fastDfsClient.upload(fileName, -1);
        log.info("result={};", result);
        if (result != null && result.length >= 3) {
            log.info("group_name:{};remote_filename:{};url:{};", result[0], result[1], result[2]);
        }
    }

    @Test
    public void download() throws Exception {

        String  groupName = "group1", remoteFileName = "M00/00/00/Ct5kCmAH0aCAQhdaARhvkss0y3I963.pdf", outFileName = "";
        //remoteFileName="123";
        int download = fastDfsClient.download(groupName, remoteFileName,  -1);

        log.info("download={};", download);

    }

    @Test
    public void delete() throws Exception {
        String  groupName = "group1", remoteFileName = "M00/00/00/Ct5kCmAH0aCAQhdaARhvkss0y3I963.pdf", outFileName = "";
        //remoteFileName="123";
        int delete = fastDfsClient.delete(groupName, remoteFileName, -1);

        log.info("delete={};",delete);


    }
}