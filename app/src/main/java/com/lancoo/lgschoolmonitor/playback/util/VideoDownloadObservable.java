package com.lancoo.lgschoolmonitor.playback.util;

import android.content.Context;

import com.lancoo.cpbase.authentication.base.CurrentUser;
import com.lancoo.lgschoolmonitor.base.Constant;
import com.lancoo.lgschoolmonitor.playback.bean.VideoDownloadBean;
import com.lancoo.lgschoolmonitor.utils.SysFileUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/28 15:15.
 */
public class VideoDownloadObservable extends Observable {

    private HttpHandler mHandler;
    private Object mData;
    private Context mContext;
    public static int STATE_ORIGINAL = 0; // 初始状态
    public static int STATE_STOP = 1; // 暂停状态
    public static int STATE_DOWNLOAD = 2; // 下载状态
    public static int STATE_COMPLETE = 3; // 完成状态
    public static int STATE_WAIT = 4; // 等待下载
    public static int STATE_FAIL = 5; // 下载失败
    private DbUtils mDbUtils;
    private ArrayList<VideoDownloadBean> mDownList;
    private static VideoDownloadObservable mObservable;

    private VideoDownloadObservable() {
        mDownList = new ArrayList<>();
    }

    public void setContext(Context context, DbUtils utils) {
        mContext = context;
        mDbUtils = utils;
        mData = new Object();
        try {
            if (null == mDownList || mDownList.size() == 0) {
                List<VideoDownloadBean> dbList = mDbUtils.findAll(Selector.from(VideoDownloadBean
                        .class).where("UserID", "=", CurrentUser.UserID));
                if (null != dbList && dbList.size() > 0) {
                    mDownList.clear();
                    mDownList.addAll(dbList);
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static VideoDownloadObservable getObservable() {
        if (mObservable == null) {
            mObservable = new VideoDownloadObservable();
        }
        return mObservable;
    }

    public ArrayList<VideoDownloadBean> getmDownList() {
        if (mDownList == null) {
            return new ArrayList<>();
        }
        return mDownList;
    }

    public Object getData() {
        return mData;
    }

    public void downloadVideoFromServer(final VideoDownloadBean bean) {
        boolean isWait = false;
        if (mDownList.size() > 0) {
            for (int i = 0; i < mDownList.size(); i++) {
                if (mDownList.get(i).getDownloadType() == STATE_DOWNLOAD) {
                    isWait = true;
                    bean.setDownloadType(STATE_WAIT);
                }
            }
        }
        if (!isWait) {
            bean.setDownloadType(STATE_DOWNLOAD);
            String url = bean.getHttpUrl();
            String target = bean.getFileLocalPath();
            HttpUtils utils = new HttpUtils();
            mHandler = utils.download(url, target, true, false, new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    bean.setDownloadType(STATE_COMPLETE);
                    mData = bean;
                    setChanged();
                    notifyObservers(mData);
                    try {
                        mDbUtils.update(bean, WhereBuilder.b("UserID", "=", CurrentUser.UserID)
                                .and("FileLocalPath", "=", bean.getFileLocalPath()));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < mDownList.size(); i++) {
                        if (!bean.getHttpUrl().equals(mDownList.get(i).getHttpUrl()) && mDownList
                                .get(i).getDownloadType() == STATE_WAIT) {
                            downloadVideoFromServer(mDownList.get(i), 0);
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    if (s.equals("maybe the file has downloaded completely")) {
                        bean.setDownloadType(STATE_COMPLETE);
                        mData = bean;
                        setChanged();
                        notifyObservers(mData); // 通知观察者数据改变了，在这之前要先调用setChanged()方法
                    } else {
                        bean.setDownloadType(STATE_FAIL);
                        mData = bean;
                        setChanged();
                        notifyObservers(mData); // 通知观察者数据改变了，在这之前要先调用setChanged()方法
                        try {
                            mDbUtils.update(bean, WhereBuilder.b("UserID", "=", CurrentUser.UserID)
                                    .and("FileLocalPath", "=", bean.getFileLocalPath()));
                        } catch (DbException e1) {
                            e1.printStackTrace();
                        }
                        for (int i = 0; i < mDownList.size(); i++) {
                            if (!bean.getHttpUrl().equals(mDownList.get(i).getHttpUrl()) &&
                                    mDownList.get(i).getDownloadType() == STATE_WAIT) {
                                downloadVideoFromServer(mDownList.get(i), 0);
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    bean.setDownloadType(STATE_DOWNLOAD);
                    bean.setCurrentFileSize(current);
                    mData = bean;
                    setChanged();
                    notifyObservers(mData);
                }
            });
            bean.setHandler(mHandler);
        }
        mDownList.add(bean);
    }

    public void downloadVideoFromServer(final VideoDownloadBean bean, long currentLength) {
        boolean shouldWait = false;
        for (int i = 0; i < mDownList.size(); i++) {
            if (!mDownList.get(i).getFileLocalPath().equals(bean.getFileLocalPath()) && mDownList
                    .get(i).getDownloadType() == STATE_DOWNLOAD) {
                shouldWait = true;
                break;
            }
        }
        if (shouldWait) {
            bean.setDownloadType(STATE_WAIT);
            mData = bean;
            setChanged();
            notifyObservers(mData);
        } else {
            bean.setDownloadType(STATE_DOWNLOAD);
            String url = bean.getHttpUrl();
            String target = bean.getFileLocalPath();
            HttpUtils utils = new HttpUtils();
            mHandler = utils.download(url, target, true, false, new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    bean.setDownloadType(STATE_COMPLETE);
                    mData = bean;
                    setChanged();
                    notifyObservers(mData);
                    try {
                        mDbUtils.update(bean, WhereBuilder.b("UserID", "=", CurrentUser.UserID)
                                .and("FileLocalPath", "=", bean.getFileLocalPath()));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < mDownList.size(); i++) {
                        if (!bean.getHttpUrl().equals(mDownList.get(i).getHttpUrl()) && mDownList
                                .get(i).getDownloadType() == STATE_WAIT) {
                            downloadVideoFromServer(mDownList.get(i), 0);
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    if (s.equals("maybe the file has downloaded completely")) {
                        bean.setDownloadType(STATE_COMPLETE);
                        mData = bean;
                        setChanged();
                        notifyObservers(mData); // 通知观察者数据改变了，在这之前要先调用setChanged()方法
                    } else {
                        bean.setDownloadType(STATE_FAIL);
                        mData = bean;
                        setChanged();
                        notifyObservers(mData); // 通知观察者数据改变了，在这之前要先调用setChanged()方法
                        try {
                            mDbUtils.update(bean, WhereBuilder.b("UserID", "=", CurrentUser.UserID)
                                    .and("FileLocalPath", "=", bean.getFileLocalPath()));
                        } catch (DbException e1) {
                            e1.printStackTrace();
                        }
                        for (int i = 0; i < mDownList.size(); i++) {
                            if (!bean.getHttpUrl().equals(mDownList.get(i).getHttpUrl()) &&
                                    mDownList.get(i).getDownloadType() == STATE_WAIT) {
                                downloadVideoFromServer(mDownList.get(i), 0);
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    bean.setDownloadType(STATE_DOWNLOAD);
                    bean.setCurrentFileSize(current);
                    mData = bean;
                    setChanged();
                    notifyObservers(mData);
                }
            });
            bean.setHandler(mHandler);
        }
    }


    public boolean deleteVideo(VideoDownloadBean bean) {
        try {
            mDbUtils.delete(bean);
            if (mDownList.size() > 0) {
                mDownList.remove(bean);
            }
            if (bean.getDownloadType() == STATE_DOWNLOAD) {
                stopDownload(bean);
            }
            return true;
        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVideoWithLocal(VideoDownloadBean bean) {
        try {
            boolean deleteResult = deleteVideo(bean);
            if (deleteResult) {
                File f = new File(bean.getFileLocalPath());
                if (f.exists()) {
                    return f.delete();
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean cleanAllVideo(ArrayList<VideoDownloadBean> download) {
        try {
            mDbUtils.delete(VideoDownloadBean.class, WhereBuilder.b("UserID", "=", CurrentUser
                    .UserID));
            mDownList.removeAll(download);
            return true;
        } catch (DbException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean cleanAllVideoWithLocal(ArrayList<VideoDownloadBean> download) {
        boolean deleteResult = cleanAllVideo(download);
        try {
            if (deleteResult) {
                SysFileUtil.deleteFolderFile(Constant.VIDEO_PATH);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void stopDownload(VideoDownloadBean bean) {
        if (mHandler != null) {
            mHandler.cancel();
        }
        bean.setDownloadType(STATE_STOP);
        mData = bean;
        setChanged();
        notifyObservers(mData);
        try {
            mDbUtils.update(
                    bean,
                    WhereBuilder.b("FileLocalPath", "=",
                            bean.getFileLocalPath()).and("UserID", "=", CurrentUser.UserID));
        } catch (DbException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < mDownList.size(); i++) {
            if (!bean.getHttpUrl().equals(mDownList.get(i).getHttpUrl()) && mDownList.get(i)
                    .getDownloadType() == STATE_WAIT) {
                downloadVideoFromServer(mDownList.get(i), 0);
                return;
            }
        }
    }
}
