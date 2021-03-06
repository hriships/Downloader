package com.agoda.downloader.exceptions;

import org.apache.commons.io.FileDeleteStrategy;

import java.io.File;
import java.util.logging.Logger;

/**
 *
 */
public class DownloadException extends Exception {
    private final static Logger LOGGER = Logger.getLogger(DownloadException.class.getName());
    public static final String FILE_CLEANED = "File cleaned : ";

    final String filePath;
    private final String CLEANING_FAILED_ERR_MSG = "File cleaning failed :";

    public DownloadException(String filePath) {
        this.filePath = filePath;
        cleanUp(filePath);
    }

    public DownloadException(String message, String filePath) {
        super(message);
        this.filePath = filePath;
        cleanUp(filePath);
    }

    public DownloadException(String message, Throwable cause, String filePath) {
        super(message, cause);
        this.filePath = filePath;
        cleanUp(filePath);
    }

    public DownloadException(Throwable cause, String filePath) {
        super(cause);
        this.filePath = filePath;
        cleanUp(filePath);
    }

    public DownloadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String filePath) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.filePath = filePath;
        cleanUp(filePath);
    }

    private void cleanUp(String filePath) {
        try {
            FileDeleteStrategy.FORCE.delete(new File(filePath));
            LOGGER.info(FILE_CLEANED + filePath);
        } catch (Exception e) {
            LOGGER.info(CLEANING_FAILED_ERR_MSG + filePath);
        }
    }

    public String getFilePath() {
        return filePath;
    }
}
