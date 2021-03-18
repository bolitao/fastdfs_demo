package xyz.bolitao.fastdfsdemo;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class FastdfsdemoApplicationTests {
    @Test
    void contextLoads() throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        NameValuePair[] pairs = null;
        String fileId = storageClient1.upload_file1(
                "C:\\Users\\tblsy\\OneDrive\\桌面\\006v119zly1gnog2vq6ccj30u00u0471.jpg", "jpg", pairs);
        System.out.println(fileId);
    }

    @Test
    void download() throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        byte[] bytes = storageClient1.download_file1("group1/M00/00/00/wKhPg2BO-gGAeSHoAAMQ7sedL08620.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/bolitao/Downloads/test.jpg"));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    @Test
    void testAntiSteal() throws UnsupportedEncodingException, NoSuchAlgorithmException, MyException {
        int ts = (int) (System.currentTimeMillis() / 1000 + 900);
        String remoteFilename = "M00/00/00/wKhPg2BTBSeAb3ERAAT2YBE-JU0473.jpg";
        String fastDfsUrl = "http://192.168.79.131:8888/";
        String token = ProtoCommon.getToken(remoteFilename, ts, "bolitao");
        System.out.println(fastDfsUrl + "group1/" + remoteFilename + "?token=" + token + "&ts=" + ts);
    }

    @Test
    void testFileInfo() throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        System.out.println(storageClient1.get_file_info("group1", "M00/00/00/wKhPg2BTAsWAM_9gAAT2YBE-JU0174.jpg"));
        System.out.println(storageClient1.get_file_info1("group1/M00/00/00/wKhPg2BTBSeAb3ERAAT2YBE-JU0473.jpg"));
    }

    @Test
    void testDeleteFile() throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        System.out.println(storageClient1.delete_file1("group1/M00/00/00/wKhPg2BTBSeAb3ERAAT2YBE-JU0473.jpg"));
    }
}
