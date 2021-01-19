package toutiao.dao;

import lombok.extern.slf4j.Slf4j;
import org.csource.client.FdfsClient;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

@Slf4j
public class ArticleHomeMapperTest {

    private FdfsClient fdfsClient;

    @Before
    public void init() {
        String fileName = "classpath:fastdfs/fastdfs-client.properties";

        fdfsClient = new FdfsClient(fileName);

        log.info("fdfsClient={};",fdfsClient);
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


}