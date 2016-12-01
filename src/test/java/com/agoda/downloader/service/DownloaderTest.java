package com.agoda.downloader.service;

import com.agoda.downloader.domain.DownloadState;
import com.agoda.downloader.domain.FileResource;
import com.agoda.downloader.exception.DownloadException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static com.agoda.downloader.domain.DownloadState.COMPLETED;
import static org.junit.Assert.assertEquals;

/**
 * Created by hrishikeshshinde on 27/11/16.
 */
public class DownloaderTest {

    @Test
    public void fromHTTP() throws IOException, DownloadException {
        String source = "https://s3.ap-south-1.amazonaws.com/hriships/HrishikeshShinde_resume.pdf";
        String path = "/Users/hrishikeshshinde/";
        FileResource fileResource =  new FileResource(source);
        String fileName = fileResource.getFilename();

        Downloader downloader = new HttpDownloader();
        DownloadState status = downloader.download(source, path, fileName);
        assertEquals(COMPLETED, status);
    }

    @Test(expected = DownloadException.class)
    public void emptyFileFromHTTP() throws IOException, DownloadException {
        String source = "https://s3.ap-south-1.amazonaws.com/hriships";
        String path = "/Users/hrishikeshshinde/";
        FileResource fileResource =  new FileResource(source);
        String fileName = fileResource.getFilename();

        Downloader downloader = new HttpDownloader();
        downloader.download(source, path, fileName);
    }

    @Test
    public void fromFTP() throws IOException, DownloadException {
        String source = "ftp://anonymous:myemailname@ftp.hq.nasa.gov/pub/astrophysics/FTP_Instructions.txt";
        String path = "/Users/hrishikeshshinde/";
        FileResource fileResource =  new FileResource(source);
        String fileName = fileResource.getFilename();

        Downloader downloader = new FtpDownloader();
        DownloadState status = downloader.download(source, path, fileName);
        assertEquals(COMPLETED, status);
    }

    @Test
    @Ignore
    public void getPath() {
        String test = "ftp://anonymous:myemailname@ftp.hq.nasa.gov/pub/astrophysics/FTP_Instructions.txt";
        String[] test1 = test.split("@");
        String userName = test1[0].split("//")[1];
        String password = test1[1];
        String[] tests = test.split("//");
        String server = tests[1].substring(0, tests[1].indexOf("/"));
        String file = tests[1].substring(tests[1].indexOf("/"), tests[1].length());
        System.out.print(server + ": " + file);
    }
}
