package com.lancoo.lgschoolmonitor.playback.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.http.HttpHandler;

import java.io.File;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/22 11:27.
 */
@Table
public class VideoDownloadBean {
    @Id
    private int id;
    @Column
    private String BuildName;
    @Column
    private String positionName;
    @Column
    private String FileID;
    @Column
    private String FileName;
    @Column
    private String StartTime;
    @Column
    private String EndTime;
    @Column
    private long FileSize;
    @Column
    private String FtpUrl;
    @Column
    private String HttpUrl;
    @Column
    private String ClassHourNO;
    @Column
    private String ClassHourName;
    @Column
    private String UserID;
    @Column
    private String FileLocalPath;
    @Column
    private long CurrentFileSize;
    @Column
    private int downloadType;
    @Column
    private HttpHandler<File> handler;

    public String getBuildName() {
        return BuildName == null ? "" : BuildName;
    }

    public void setBuildName(String buildName) {
        BuildName = buildName;
    }

    public String getPositionName() {
        return positionName == null ? "" : positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getFileID() {
        return FileID == null ? "" : FileID;
    }

    public void setFileID(String fileID) {
        FileID = fileID;
    }

    public String getFileName() {
        return FileName == null ? "" : FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getStartTime() {
        return StartTime == null ? "" : StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime == null ? "" : EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public long getFileSize() {
        return FileSize;
    }

    public void setFileSize(long fileSize) {
        FileSize = fileSize;
    }

    public String getFtpUrl() {
        return FtpUrl == null ? "" : FtpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        FtpUrl = ftpUrl;
    }

    public String getHttpUrl() {
        return HttpUrl == null ? "" : HttpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        HttpUrl = httpUrl;
    }

    public String getClassHourNO() {
        return ClassHourNO == null ? "" : ClassHourNO;
    }

    public void setClassHourNO(String classHourNO) {
        ClassHourNO = classHourNO;
    }

    public String getClassHourName() {
        return ClassHourName == null ? "" : ClassHourName;
    }

    public void setClassHourName(String classHourName) {
        ClassHourName = classHourName;
    }

    public String getUserID() {
        return UserID == null ? "" : UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getFileLocalPath() {
        return FileLocalPath == null ? "" : FileLocalPath;
    }

    public void setFileLocalPath(String fileLocalPath) {
        FileLocalPath = fileLocalPath;
    }

    public long getCurrentFileSize() {
        return CurrentFileSize;
    }

    public void setCurrentFileSize(long currentFileSize) {
        CurrentFileSize = currentFileSize;
    }


    public int getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(int downloadType) {
        this.downloadType = downloadType;
    }

    public HttpHandler<File> getHandler() {
        return handler;
    }

    public void setHandler(HttpHandler<File> handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "VideoDownloadBean{" +
                "id=" + id +
                ", BuildName='" + BuildName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", FileID='" + FileID + '\'' +
                ", FileName='" + FileName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", FileSize=" + FileSize +
                ", FtpUrl='" + FtpUrl + '\'' +
                ", HttpUrl='" + HttpUrl + '\'' +
                ", ClassHourNO='" + ClassHourNO + '\'' +
                ", ClassHourName='" + ClassHourName + '\'' +
                ", UserID='" + UserID + '\'' +
                ", FileLocalPath='" + FileLocalPath + '\'' +
                ", CurrentFileSize=" + CurrentFileSize +
                ", downloadType=" + downloadType +
                ", handler=" + handler +
                '}';
    }
}
